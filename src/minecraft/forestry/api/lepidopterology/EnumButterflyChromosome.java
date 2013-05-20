package forestry.api.lepidopterology;

public enum EnumButterflyChromosome {
	SPECIES,
	/**
	 * Physical size.
	 */
	SIZE,
	/**
	 * Flight speed.
	 */
	SPEED,
	/**
	 * How long the butterfly can last without access to matching pollinatables.
	 */
	LIFESPAN,
	/**
	 * Species with a higher metabolism have a higher appetite and may cause more damage to their environment.
	 */
	METABOLISM,
	/**
	 * Determines likelyhood of caterpillars and length of caterpillar/pupation phase. Also: Number of max caterpillars after mating?
	 */
	FERTILITY,
	/**
	 * Not sure yet.
	 */
	TEMPERATURE_TOLERANCE,
	/**
	 * Not sure yet.
	 */
	HUMIDITY_TOLERANCE,
	/**
	 * Only nocturnal butterflys/moths will fly at night. Allows daylight activity for naturally nocturnal species.
	 */
	NOCTURNAL,
	/**
	 * Only tolerant flyers will fly in the rain.
	 */
	TOLERANT_FLYER,
	/**
	 * Fire resistance.
	 */
	FIRE_RESIST,
	/**
	 * Required flowers/leaves.
	 */
	FLOWER_PROVIDER,
	/**
	 * Extra effect to surroundings. (?)
	 */
	EFFECT
}
