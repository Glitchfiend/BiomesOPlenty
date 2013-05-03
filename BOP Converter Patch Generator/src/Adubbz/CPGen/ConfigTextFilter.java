package Adubbz.CPGen;

public class ConfigTextFilter {
	
	public static String[] splitString;
	
	public static void filter()
	{
		splitString  = ConfigFileReader.text.split("I:");
		
		for (int i = 1; i < 141; ++i)
		{
			//splitString[i] = splitString[i].replaceAll( "[^\\d]", "" );
			splitString[i] = splitString[i].replaceFirst(".*?(?=[a-z]?=)", "");
			splitString[i] = splitString[i].replace("=", "");
			splitString[i] = splitString[i].replace(" ", "");
			
			splitString[i] = splitString[i] + " -> ";
		}
		
		splitString[1] =  splitString[1] + "1948";
		splitString[2] =  splitString[2] + "1962";
		splitString[3] =  splitString[3] + "1933";
		splitString[4] =  splitString[4] + "1947";
		splitString[5] =  splitString[5] + "1938";
		splitString[6] =  splitString[6] + "1949";
		splitString[7] =  splitString[7] + "1952";
		splitString[8] =  splitString[8] + "1925";
		splitString[9] =  splitString[9] + "1942";
		splitString[10] = splitString[10] + "1921:9";
		splitString[11] = splitString[11] + "1926:3";
		splitString[12] = splitString[12] + "1937";
		splitString[13] = splitString[13] + "1927";
		splitString[14] = splitString[14] + "1923:1";
		splitString[15] = splitString[15] + "1937:2";
		splitString[16] = splitString[16] + "1947:10";
		splitString[17] = splitString[17] + "1920:6";
		splitString[18] = splitString[18] + "1942:1";
		splitString[19] = splitString[19] + "1925:4";
		splitString[20] = splitString[20] + "1920:7";
		splitString[21] = splitString[21] + "1948:1";
		splitString[22] = splitString[22] + "1933:1";
		splitString[23] = splitString[23] + "1947:1";
		splitString[24] = splitString[24] + "1949:1";
		splitString[25] = splitString[25] + "1953";
		splitString[26] = splitString[26] + "1921";
		splitString[27] = splitString[27] + "1921:5";
		splitString[28] = splitString[28] + "1948:2";
		splitString[29] = splitString[29] + "1923:3";
		splitString[30] = splitString[30] + "1933:2"; 
		splitString[31] = splitString[31] + "1947:2";
		splitString[32] = splitString[32] + "1937:4";
		splitString[33] = splitString[33] + "1949:2";
		splitString[34] = splitString[34] + "1954";
		splitString[35] = splitString[35] + "1920";
		splitString[36] = splitString[36] + "1935:2";
		splitString[37] = splitString[37] + "1921:2";
		splitString[38] = splitString[38] + "1920:1";
		splitString[39] = splitString[39] + "1920:2";
		splitString[40] = splitString[40] + "1920:3";
		splitString[41] = splitString[41] + "1920:3";
		splitString[42] = splitString[42] + "1923:4";
		splitString[43] = splitString[43] + "1937:5";
		splitString[44] = splitString[44] + "1948:3";
		splitString[45] = splitString[45] + "1923:5";
		splitString[46] = splitString[46] + "1947:3";
		splitString[47] = splitString[47] + "1937:6";
		splitString[48] = splitString[48] + "1949:3";
		splitString[49] = splitString[49] + "1926";
		splitString[51] = splitString[51] + "1935:3";
		splitString[52] = splitString[52] + "1936";
		splitString[53] = splitString[53] + "1936:1"; 
		splitString[54] = splitString[54] + "1921:3";
		splitString[55] = splitString[55] + "1925:3";
		splitString[56] = splitString[56] + "1925:6";
		splitString[57] = splitString[57] + "1948:4";
		splitString[58] = splitString[58] + "1923:6";
		splitString[59] = splitString[59] + "1934";
		splitString[60] = splitString[60] + "1947:4"; 
		splitString[61] = splitString[61] + "1937:7";
		splitString[62] = splitString[62] + "1949:4";
		splitString[63] = splitString[63] + "1956";
		splitString[64] = splitString[64] + "1920:4";
		splitString[65] = splitString[65] + "1921:4";
		splitString[66] = splitString[66] + "1948:5";
		splitString[67] = splitString[67] + "1923:2";
		splitString[68] = splitString[68] + "1934:1";
		splitString[69] = splitString[69] + "1947:5";
		splitString[70] = splitString[70] + "1937:3";
		splitString[71] = splitString[71] + "1949:5";
		splitString[72] = splitString[72] + "1957";
		splitString[73] = splitString[73] + "1948:6";
		splitString[74] = splitString[74] + "1962:1";
		splitString[75] = splitString[75] + "1934:2";
		splitString[76] = splitString[76] + "1947:6";
		splitString[77] = splitString[77] + "1938:1";
		splitString[78] = splitString[78] + "1949:6";
		splitString[79] = splitString[79] + "1958"; //Correct
		splitString[80] = splitString[80] + "1924:2";
		splitString[81] = splitString[81] + "1937:11";
		splitString[82] = splitString[82] + "1925:2";
		splitString[83] = splitString[83] + "4095";
		splitString[84] = splitString[84] + "1930:2";
		splitString[85] = splitString[85] + "1931:2";
		splitString[86] = splitString[86] + "1929";
		splitString[87] = splitString[87] + "1928";
		splitString[88] = splitString[88] + "1923:7";
		splitString[89] = splitString[89] + "1937:8";
		splitString[90] = splitString[90] + "1924";
		splitString[91] = splitString[91] + "1937:9";	
		splitString[92] = splitString[92] + "1948:7";
		splitString[93] = splitString[93] + "1962:2";
		splitString[94] = splitString[94] + "1934:3";
		splitString[95] = splitString[95] + "1947:7";
		splitString[96] = splitString[96] + "1938:2";
		splitString[97] = splitString[97] + "1949:7";
		splitString[98] = splitString[98] + "1959";
		splitString[99] = splitString[99] + "1924:1";
		splitString[100] = splitString[100] + "1937:10";
		splitString[101] = splitString[101] + "1921:6";
		splitString[102] = splitString[102] + "1941";
		splitString[103] = splitString[103] + "160:1";
		splitString[104] = splitString[104] + "1930:1";
		splitString[105] = splitString[105] + "1931:1";		
		splitString[106] = splitString[106] + "1940";
		splitString[107] = splitString[107] + "162:2";
		splitString[108] = splitString[108] + "1930";
		splitString[109] = splitString[109] + "162:1";
		splitString[110] = splitString[110] + "1931";
		splitString[111] = splitString[111] + "1939";
		splitString[112] = splitString[112] + "1950";
		splitString[113] = splitString[113] + "1962:3";
		splitString[114] = splitString[114] + "1935";
		splitString[115] = splitString[115] + "1947:8";
		splitString[116] = splitString[116] + "1938:3";
		splitString[117] = splitString[117] + "1951";
		splitString[118] = splitString[118] + "1960";
		splitString[119] = splitString[119] + "1925:1";
		splitString[120] = splitString[120] + "169:1";
		splitString[121] = splitString[121] + "1925:5";
		splitString[122] = splitString[122] + "1921:1";
		splitString[123] = splitString[123] + "1920:5";
		splitString[124] = splitString[124] + "1921:11";
		splitString[125] = splitString[125] + "1921:10";
		splitString[126] = splitString[126] + "1932";
		splitString[127] = splitString[127] + "1921:8";
		splitString[128] = splitString[128] + "1924:3";
		splitString[129] = splitString[129] + "1937:12";
		splitString[130] = splitString[130] + "1921:7";
		splitString[131] = splitString[131] + "1950:1";
		splitString[132] = splitString[132] + "1922";
		splitString[133] = splitString[133] + "1962:4";
		splitString[134] = splitString[134] + "1935:1";
		splitString[135] = splitString[135] + "1947:9";
		splitString[136] = splitString[136] + "1938:4";
		splitString[137] = splitString[137] + "1951:1";
		splitString[138] = splitString[138] + "1961";
		splitString[139] = splitString[139] + "1923";
		splitString[140] = splitString[140] + "1937:1";
		
		for (int i = 1; i < 141; ++i)
		{
			System.out.println(splitString[i]);
		}
		
		try 
		{
			Gui.progressBar.setValue(68);
			TextFileWriter.write();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
