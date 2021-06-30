package biomesoplenty.common.world;

/**
 * A speed-improved simplex noise algorithm for 2D in Java.
 *
 * Based on example code by Stefan Gustavson (stegu@itn.liu.se).
 * Optimisations by Peter Eastman (peastman@drizzle.stanford.edu).
 * Better rank ordering method by Stefan Gustavson in 2012.
 * Stateless seedability and improved gradients by K.jpg in 2021.
 *
 * This could be sped up even further, but it's useful as it is.
 *
 * This code was placed in the public domain by its original author,
 * Stefan Gustavson. You may use it as you see fit, but
 * attribution is appreciated.
 */
public final class SimplexNoise {

    // Prime multipliers for each vertex coordinate, for the statelessly-seedable hash function.
    private static final long PRIME_X = 0x53A3F72DEEC546F5L;
    private static final long PRIME_Y = 0x5BCC226E9FA0BACBL;

    // After extensive searching, I have not found any duplicated seeds when using this constant,
    // if it is used in combination with large enough coordinate primes. If there are any repeat seeds,
    // they're either incredibly rare, or they require very specific combinations of bit flipping to discover.
    private static final long HASH_UNIQUIFIER = 0x7DF3BB00907DD40DL;

    // Skewing and unskewing factors for 2 dimensions
    private static final double F2 = 0.5 * (Math.sqrt(3.0) - 1.0);
    private static final double G2 = (3.0 - Math.sqrt(3.0)) / 6.0;

    // For when you want to start the noise such that the origin isn't always zero.
    // The center of the triangle in skewed space is [(0,0)+(1,0)+(1,1)]/3=(2/3,1/3)
    // Then to convert to true coordinates, (2/3-t,1/3-t) where t=(1/3+2/3)*G2=G2
    // These can also be swapped to start in the opposite triangle, though there's little practical difference.
    public static final double TRIANGLE_START_X = (2.0 / 3.0) - G2;
    public static final double TRIANGLE_START_Y = (1.0 / 3.0) - G2;

    /**
     * Deactivate constructor
     */
    private SimplexNoise() {
    }

    // 2D simplex noise
    public static double noise(long seed, double xin, double yin) {

        // Skew the input space to determine which simplex cell we're in
        double s = (xin + yin) * F2; // Hairy factor for 2D
        int i = fastfloor(xin + s);
        int j = fastfloor(yin + s);

        // Unskew the cell origin back to (x,y) space
        double t = (i + j) * G2;
        double originX0 = i - t;
        double originY0 = j - t;

        // The x,y distances from the cell origin
        double x0 = xin - originX0;
        double y0 = yin - originY0;

        // For the 2D case, the simplex shape is an equilateral triangle.
        // Determine which simplex we are in.
        int i1, j1;
        if (x0 > y0) {
            // lower triangle, XY order: (0,0)->(1,0)->(1,1)
            i1 = 1;
            j1 = 0;
        } else {
            // upper triangle, YX order: (0,0)->(0,1)->(1,1)
            i1 = 0;
            j1 = 1;
        }

        // Offsets for middle and final corners in (x,y) unskewed coords
        // A step of (1,0) in (i,j) means a step of (1-c,-c) in (x,y). For (0,1) it's (-c,1-c). c = (3-sqrt(3))/6
        double x1 = x0 - i1 + G2;
        double y1 = y0 - j1 + G2;
        double x2 = x0 - (1.0 - 2.0 * G2);
        double y2 = y0 - (1.0 - 2.0 * G2);

        // Pre-multiply primes for the seeded coordinate hash
        long xsvp = i * PRIME_X;
        long ysvp = j * PRIME_Y;

        // Calculate the contribution from the three corners
        double value = 0;
        double t0 = 0.5 - x0 * x0 - y0 * y0;
        if (t0 > 0) {
            t0 *= t0;
            value = t0 * t0 * grad(seed, xsvp, ysvp, x0, y0);
        }
        double t1 = 0.5 - x1 * x1 - y1 * y1;
        if (t1 > 0) {
            t1 *= t1;
            value += t1 * t1 * grad(seed, xsvp + (-i1 & PRIME_X), ysvp + (-j1 & PRIME_Y), x1, y1);
        }
        double t2 = 0.5 - x2 * x2 - y2 * y2;
        if (t2 > 0) {
            t2 *= t2;
            value += t2 * t2 * grad(seed, xsvp + PRIME_X, ysvp + PRIME_Y, x2, y2);
        }

        return value;
    }

    // This method is a *lot* faster than using (int)Math.floor(x)
    private static int fastfloor(double x) {
        int xi = (int) x;
        return x < xi ? xi - 1 : xi;
    }

    private static double grad(long seed, long xsvp, long ysvp, double dx, double dy) {
        long hash = seed ^ xsvp ^ ysvp;
        hash *= HASH_UNIQUIFIER;

        // Make sure every bit contributes
        hash ^= hash >> 31;
        hash ^= hash >> 17;
        hash ^= hash >> 9;

        int index = (int)(hash & 0xFE);

        return grads[index] * dx + grads[index | 1] * dy;
    }

    // 24 gradients, repeated 5 times, + an octagon out of them repeated again to fill the gap
    private static final float[] grads = {
            0.130526192220052f,  0.99144486137381f,   0.38268343236509f,   0.923879532511287f,  0.608761429008721f,  0.793353340291235f,  0.793353340291235f,  0.608761429008721f,
            0.923879532511287f,  0.38268343236509f,   0.99144486137381f,   0.130526192220051f,  0.99144486137381f,  -0.130526192220051f,  0.923879532511287f, -0.38268343236509f,
            0.793353340291235f, -0.60876142900872f,   0.608761429008721f, -0.793353340291235f,  0.38268343236509f,  -0.923879532511287f,  0.130526192220052f, -0.99144486137381f,
            -0.130526192220052f, -0.99144486137381f,  -0.38268343236509f,  -0.923879532511287f, -0.608761429008721f, -0.793353340291235f, -0.793353340291235f, -0.608761429008721f,
            -0.923879532511287f, -0.38268343236509f,  -0.99144486137381f,  -0.130526192220052f, -0.99144486137381f,   0.130526192220051f, -0.923879532511287f,  0.38268343236509f,
            -0.793353340291235f,  0.608761429008721f, -0.608761429008721f,  0.793353340291235f, -0.38268343236509f,   0.923879532511287f, -0.130526192220052f,  0.99144486137381f,
            0.130526192220052f,  0.99144486137381f,   0.38268343236509f,   0.923879532511287f,  0.608761429008721f,  0.793353340291235f,  0.793353340291235f,  0.608761429008721f,
            0.923879532511287f,  0.38268343236509f,   0.99144486137381f,   0.130526192220051f,  0.99144486137381f,  -0.130526192220051f,  0.923879532511287f, -0.38268343236509f,
            0.793353340291235f, -0.60876142900872f,   0.608761429008721f, -0.793353340291235f,  0.38268343236509f,  -0.923879532511287f,  0.130526192220052f, -0.99144486137381f,
            -0.130526192220052f, -0.99144486137381f,  -0.38268343236509f,  -0.923879532511287f, -0.608761429008721f, -0.793353340291235f, -0.793353340291235f, -0.608761429008721f,
            -0.923879532511287f, -0.38268343236509f,  -0.99144486137381f,  -0.130526192220052f, -0.99144486137381f,   0.130526192220051f, -0.923879532511287f,  0.38268343236509f,
            -0.793353340291235f,  0.608761429008721f, -0.608761429008721f,  0.793353340291235f, -0.38268343236509f,   0.923879532511287f, -0.130526192220052f,  0.99144486137381f,
            0.130526192220052f,  0.99144486137381f,   0.38268343236509f,   0.923879532511287f,  0.608761429008721f,  0.793353340291235f,  0.793353340291235f,  0.608761429008721f,
            0.923879532511287f,  0.38268343236509f,   0.99144486137381f,   0.130526192220051f,  0.99144486137381f,  -0.130526192220051f,  0.923879532511287f, -0.38268343236509f,
            0.793353340291235f, -0.60876142900872f,   0.608761429008721f, -0.793353340291235f,  0.38268343236509f,  -0.923879532511287f,  0.130526192220052f, -0.99144486137381f,
            -0.130526192220052f, -0.99144486137381f,  -0.38268343236509f,  -0.923879532511287f, -0.608761429008721f, -0.793353340291235f, -0.793353340291235f, -0.608761429008721f,
            -0.923879532511287f, -0.38268343236509f,  -0.99144486137381f,  -0.130526192220052f, -0.99144486137381f,   0.130526192220051f, -0.923879532511287f,  0.38268343236509f,
            -0.793353340291235f,  0.608761429008721f, -0.608761429008721f,  0.793353340291235f, -0.38268343236509f,   0.923879532511287f, -0.130526192220052f,  0.99144486137381f,
            0.130526192220052f,  0.99144486137381f,   0.38268343236509f,   0.923879532511287f,  0.608761429008721f,  0.793353340291235f,  0.793353340291235f,  0.608761429008721f,
            0.923879532511287f,  0.38268343236509f,   0.99144486137381f,   0.130526192220051f,  0.99144486137381f,  -0.130526192220051f,  0.923879532511287f, -0.38268343236509f,
            0.793353340291235f, -0.60876142900872f,   0.608761429008721f, -0.793353340291235f,  0.38268343236509f,  -0.923879532511287f,  0.130526192220052f, -0.99144486137381f,
            -0.130526192220052f, -0.99144486137381f,  -0.38268343236509f,  -0.923879532511287f, -0.608761429008721f, -0.793353340291235f, -0.793353340291235f, -0.608761429008721f,
            -0.923879532511287f, -0.38268343236509f,  -0.99144486137381f,  -0.130526192220052f, -0.99144486137381f,   0.130526192220051f, -0.923879532511287f,  0.38268343236509f,
            -0.793353340291235f,  0.608761429008721f, -0.608761429008721f,  0.793353340291235f, -0.38268343236509f,   0.923879532511287f, -0.130526192220052f,  0.99144486137381f,
            0.130526192220052f,  0.99144486137381f,   0.38268343236509f,   0.923879532511287f,  0.608761429008721f,  0.793353340291235f,  0.793353340291235f,  0.608761429008721f,
            0.923879532511287f,  0.38268343236509f,   0.99144486137381f,   0.130526192220051f,  0.99144486137381f,  -0.130526192220051f,  0.923879532511287f, -0.38268343236509f,
            0.793353340291235f, -0.60876142900872f,   0.608761429008721f, -0.793353340291235f,  0.38268343236509f,  -0.923879532511287f,  0.130526192220052f, -0.99144486137381f,
            -0.130526192220052f, -0.99144486137381f,  -0.38268343236509f,  -0.923879532511287f, -0.608761429008721f, -0.793353340291235f, -0.793353340291235f, -0.608761429008721f,
            -0.923879532511287f, -0.38268343236509f,  -0.99144486137381f,  -0.130526192220052f, -0.99144486137381f,   0.130526192220051f, -0.923879532511287f,  0.38268343236509f,
            -0.793353340291235f,  0.608761429008721f, -0.608761429008721f,  0.793353340291235f, -0.38268343236509f,   0.923879532511287f, -0.130526192220052f,  0.99144486137381f,
            0.38268343236509f,   0.923879532511287f,  0.923879532511287f,  0.38268343236509f,   0.923879532511287f, -0.38268343236509f,   0.38268343236509f,  -0.923879532511287f,
            -0.38268343236509f,  -0.923879532511287f, -0.923879532511287f, -0.38268343236509f,  -0.923879532511287f,  0.38268343236509f,  -0.38268343236509f,   0.923879532511287f,
    };

    // Normalize the output to a range of -1 to 1
    private static final double N2 = 0.01001634121365712;
    static {
        for (int i = 0; i < grads.length; i++) {
            grads[i] /= N2;
        }
    }

    public static void main(String[] args)
    {
        // Render in ascii
        long renderSeed = 1349226349613393113L;
        int width = 120;
        int height = 28;
        double freqX = 1.0 / 33.0;
        double freqY = 1.0 / 12.0;
        String grayscaleAscii = " .:-=+*#%@"; // http://paulbourke.net/dataformats/asciiart/
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double noise = SimplexNoise.noise(renderSeed, x * freqX, y * freqY);
                noise = noise * 0.5 + 0.5;
                int asciiGrayscaleLevel = (int)(noise * grayscaleAscii.length());
                if (asciiGrayscaleLevel < 0) asciiGrayscaleLevel = 0;
                else if (asciiGrayscaleLevel > grayscaleAscii.length())
                    asciiGrayscaleLevel = grayscaleAscii.length() - 1;
                System.out.print(grayscaleAscii.charAt(asciiGrayscaleLevel));
            }
            System.out.println();
        }

        // Generate dividing group code
        int numGroups = 9;
        int numEvalsPerGroup = 0x100000;
        double evalAreaRange = 128;
        int randomSeed = 12345;
        java.util.Random coordRandom = new java.util.Random(randomSeed);
        double[] evals = new double[numGroups * numEvalsPerGroup * 2];
        for (int i = 0; i < evals.length; i += 2) {
            double value = SimplexNoise.noise(coordRandom.nextLong(), coordRandom.nextDouble() * evalAreaRange, coordRandom.nextDouble() * evalAreaRange);
            evals[i + 0] = value;
            evals[i + 1] = -value;
        }
        java.util.Arrays.sort(evals);
        for (int i = 0; i < numGroups - 1; i++) {
            int boundIndex = (i + 1) * (numEvalsPerGroup * 2);
            double bound = (evals[boundIndex] + evals[boundIndex - 1]) * 0.5;
            if (i != 0) System.out.print("else ");
            System.out.println("if (noiseVal < " + bound + ") return " + i + ";");
        }
        System.out.print("else return " + (numGroups - 1) + ";");

    }
}