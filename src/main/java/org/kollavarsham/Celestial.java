/*
 * Kollavarsham
 * http://kollavarsham.org
 *
 * Copyright (c) 2014 - 2017 The Kollavarsham Team
 * Licensed under the MIT license.
 */
package org.kollavarsham;

import java.util.HashMap;
import java.util.Map;

public class Celestial {
	
	static final String[] PLANETS = {"Star", "Sun", "Moon", "Mercury", "Venus", "Mars", "Jupiter", "Saturn", 
			"Ketu", "Rahu"};

	private Map<String, Double> primaryYugaRotationConstants = new HashMap<>();
	Map<String, Double> YugaRotation = new HashMap<>();
	Map<String, Double> planetRotation = new HashMap<>();
	private Map<String, Double> planetSighra = new HashMap<>();
	Map<String, Double> planetApogee = new HashMap<>();
	private Map<String, Double> planetCircumm = new HashMap<>();
	private Map<String, Double> planetCircums = new HashMap<>();
	Map<String, Double> planetMeanPosition = new HashMap<>();
	Map<String, Double> planetTruePosition = new HashMap<>();

	private Double tithiDay = 1.0;
	Double yugaCivilDays = 1.0;
	private Double yugaSynodicMonth = 1.0;
	private Double yugaAdhimasa = 1.0;
	private Double yugaTithi = 1.0;
	private Double yugaKsayadina = 1.0;
	private Double backClongAhar = -1.0;
	private Double backNclongAhar = -1.0;
	private Double backClong = -1.0;
	private Double backNclong = -1.0;
	// type paksas :(suklapaksa, krsnapaksa),
	Double year = 1.0;
	private String paksa = "";
	// (suklapaksa, krsnapaksa),
	Double ahar = 1.0; // {for ahargana}
	private Double ftithi = 1.0;
	private Double sriseh = 1.0;
	private Double srisem = 1.0;
	private String suklaKrsna = "";
	private Double ayanadeg = 1.0;
	private Double ayanamin = 1.0;

	private static Celestial instance = null;

	public static Celestial getInstance() {
		if (instance == null) {
			instance = new Celestial();
		}
		return instance;
	}

	private Celestial() {
		primaryYugaRotationConstants = new HashMap<>();
		primaryYugaRotationConstants.put(PLANETS[0], 1582237800.0);
		primaryYugaRotationConstants.put(PLANETS[1], 4320000.0);
		primaryYugaRotationConstants.put(PLANETS[2], 57753336.0);
		primaryYugaRotationConstants.put(PLANETS[3], 17937000.0);
		primaryYugaRotationConstants.put(PLANETS[4], 7022388.0);
		primaryYugaRotationConstants.put(PLANETS[5], 2296824.0);
		primaryYugaRotationConstants.put(PLANETS[6], 364220.0);
		primaryYugaRotationConstants.put(PLANETS[7], 146564.0);
		primaryYugaRotationConstants.put(PLANETS[8], 488219.0);
		primaryYugaRotationConstants.put(PLANETS[9], -232226.0);

		Map<String, String> planetNames = new HashMap<>();
		planetNames.put(PLANETS[0], "Star        ");
		planetNames.put(PLANETS[1], "Sun         ");
		planetNames.put(PLANETS[2], "Moon        ");
		planetNames.put(PLANETS[3], "Mercury     ");
		planetNames.put(PLANETS[4], "Venus       ");
		planetNames.put(PLANETS[5], "Mars        ");
		planetNames.put(PLANETS[6], "Jupiter     ");
		planetNames.put(PLANETS[7], "Saturn      ");
		planetNames.put(PLANETS[8], "Ketu        ");
		planetNames.put(PLANETS[9], "Rahu        ");
	}

	public Double zero360(Double longitude) {
		Double result = longitude - KollavarshamMath.truncate(longitude / 360.0) * 360.0;
		return result < 0 ? 360.0 + result : result;
	}

	public Double threeRelation(Double leftY, Double centreY, Double rightY) {
		if (leftY < centreY && centreY < rightY) {
			return 1.0;
		}
		else if (rightY < centreY && centreY < leftY) {
			return -1.0;
		}
		return 0.0;
	}

	public void setPrimaryConstants() {
		this.YugaRotation.putAll(primaryYugaRotationConstants);
	}

	public void applyBija() {
		Double star = this.YugaRotation.get(PLANETS[0]);
		this.YugaRotation.put(PLANETS[0], star + 28.0);
		// this.YugaRotation.sun = this.YugaRotation.sun; // reduntant line -
		// kept for consistency
		// this.YugaRotation.moon = this.YugaRotation.moon; // reduntant line -
		// kept for consistency
		Double mercury = this.YugaRotation.get(PLANETS[3]);
		this.YugaRotation.put(PLANETS[3], mercury + 60.0);

		Double venus = this.YugaRotation.get(PLANETS[4]);
		this.YugaRotation.put(PLANETS[4], venus - 12.0);

		Double mars = this.YugaRotation.get(PLANETS[5]);
		this.YugaRotation.put(PLANETS[5], mars + 8.0);

		// this.YugaRotation.jupiter = this.YugaRotation.jupiter; // reduntant
		// line - kept for consistency

		Double saturn = this.YugaRotation.get(PLANETS[7]);
		this.YugaRotation.put(PLANETS[7], saturn + 4.0);

		Double Candrocca = this.YugaRotation.get(PLANETS[8]);
		this.YugaRotation.put(PLANETS[8], Candrocca - 16.0);

		Double Rahu = this.YugaRotation.get(PLANETS[9]);
		this.YugaRotation.put(PLANETS[9], Rahu - 12.0);

	}

	public void setSecondaryConstants() {
		// TODO: Add Tests if/when feasible
		this.yugaCivilDays = this.YugaRotation.get(PLANETS[0]) - this.YugaRotation.get(PLANETS[1]);
		this.yugaSynodicMonth = this.YugaRotation.get(PLANETS[2]) - this.YugaRotation.get(PLANETS[1]);
		this.yugaAdhimasa = this.yugaSynodicMonth - 12.0 *  this.YugaRotation.get(PLANETS[1]);
		this.yugaTithi = 30.0 * this.yugaSynodicMonth;
		this.yugaKsayadina = this.yugaTithi - this.yugaCivilDays;
	}

	public void setPlanetaryConstants() {
		// TODO: Add Tests if/when feasible
		// Star
		this.planetRotation.put(PLANETS[0], 0.0);
		this.planetSighra.put(PLANETS[0], 0.0);
		this.planetApogee.put(PLANETS[0], 0.0);
		this.planetCircumm.put(PLANETS[0], 0.0);
		this.planetCircums.put(PLANETS[0], 0.0);

		// Sun (Ravi)
		this.planetRotation.put(PLANETS[1], this.YugaRotation.get(PLANETS[1]));
		this.planetSighra.put(PLANETS[1], this.YugaRotation.get(PLANETS[1]));
		this.planetApogee.put(PLANETS[1], 77.0 + 17.0 / 60.0);
		this.planetCircumm.put(PLANETS[1], 13.0 + 50.0 / 60.0);
		this.planetCircums.put(PLANETS[1], 0.0);

		// Moon (Chandra)
		this.planetRotation.put(PLANETS[2], this.YugaRotation.get(PLANETS[2]));
		this.planetSighra.put(PLANETS[2], 0.0);
		this.planetApogee.put(PLANETS[2], 0.0);
		this.planetCircumm.put(PLANETS[2], 31.0 + 50.0 / 60.0);
		this.planetCircums.put(PLANETS[2], 0.0);

		// Mercury (Budha)
		this.planetRotation.put(PLANETS[3], this.YugaRotation.get(PLANETS[1]));

		this.planetSighra.put(PLANETS[3], this.YugaRotation.get(PLANETS[3]));
		this.planetApogee.put(PLANETS[3], 220.0 + 27.0 / 60.0);
		this.planetCircumm.put(PLANETS[3], 29.0);
		this.planetCircums.put(PLANETS[3], 131.5);

		// Venus (Shukra)
		this.planetRotation.put(PLANETS[4], this.YugaRotation.get(PLANETS[1]));
		this.planetSighra.put(PLANETS[4], this.YugaRotation.get(PLANETS[4]));
		this.planetApogee.put(PLANETS[4], 79.0 + 50.0 / 60.0);
		this.planetCircumm.put(PLANETS[4], 11.5);
		this.planetCircums.put(PLANETS[4], 261.0);

		// Mars (Kuja)
		this.planetRotation.put(PLANETS[5], this.YugaRotation.get(PLANETS[5]));
		this.planetSighra.put(PLANETS[5], this.YugaRotation.get(PLANETS[1]));
		this.planetApogee.put(PLANETS[5], 130.0 + 2.0 / 60.0);
		this.planetCircumm.put(PLANETS[5], 73.5);
		this.planetCircums.put(PLANETS[5], 233.5);

		// Jupiter (Guru)
		this.planetRotation.put(PLANETS[6], this.YugaRotation.get(PLANETS[6]));
		this.planetSighra.put(PLANETS[6], this.YugaRotation.get(PLANETS[1]));
		this.planetApogee.put(PLANETS[6], 171.0 + 18.0 / 60.0);
		this.planetCircumm.put(PLANETS[6], 32.5);
		this.planetCircums.put(PLANETS[6], 71.0);

		// Saturn (Manda)
		this.planetRotation.put(PLANETS[7], this.YugaRotation.get(PLANETS[7]));
		this.planetSighra.put(PLANETS[7], this.YugaRotation.get(PLANETS[1]));
		this.planetApogee.put(PLANETS[7], 236.0 + 37.0 / 60.0);
		this.planetCircumm.put(PLANETS[7], 48.5);
		this.planetCircums.put(PLANETS[7], 39.5);

		// Dragon Tail (Kethu)
		this.planetRotation.put(PLANETS[8], this.YugaRotation.get(PLANETS[8]));
		this.planetSighra.put(PLANETS[8], 0.0);
		this.planetApogee.put(PLANETS[8], 0.0);
		this.planetCircumm.put(PLANETS[8], 0.0);
		this.planetCircums.put(PLANETS[8], 0.0);

		// Dragon Head (Rahu)
		this.planetRotation.put(PLANETS[9], this.YugaRotation.get(PLANETS[9]));
		this.planetSighra.put(PLANETS[9], 0.0);
		this.planetApogee.put(PLANETS[9], 0.0);
		this.planetCircumm.put(PLANETS[9], 0.0);
		this.planetCircums.put(PLANETS[9], 0.0);
	}

	public void setAyanamsa(Double ahargana) {
		// TODO: Add Tests if/when feasible
		// Good reads:
		// https://en.wikipedia.org/wiki/Ayanamsa
		// http://pidaparthypanchangam.com/?m=201306&paged=2
		Double ayanamsa = (54.0 * 4320000.0 / this.yugaCivilDays / 3600.0) * (ahargana - 1314930.0);
		this.ayanadeg = KollavarshamMath.truncate(ayanamsa);
		this.ayanamin = 60.0 * KollavarshamMath.fractional(ayanamsa);
	}

	public Double getMeanLongitude(Double ahargana, Double rotation) {
		// TODO: Add Tests if/when feasible
		return 360.0 * KollavarshamMath.fractional(rotation * ahargana / this.yugaCivilDays);
	}

	public Double getTrueLongitude(Double ahargana, Double meanSolarLongitude, String planet) {
		Double meanLong1, meanLong2, meanLong3;
		Double argument;
		Double anomaly1, anomaly2;
		Double equation1, equation2, equation3, equation4, equation5;

		// first sighra correction
		if (planet.equals(PLANETS[3]) || planet.equals(PLANETS[4])) {
			anomaly1 = this.getMeanLongitude(ahargana, this.planetSighra.get(planet)) - meanSolarLongitude;
		} else {
			anomaly1 = this.getMeanLongitude(ahargana, this.planetSighra.get(planet))
					- this.planetMeanPosition.get(planet);
		}
		equation1 = this.getSighraEquation(anomaly1, planet);

		// first manda correction
		meanLong1 = this.planetMeanPosition.get(planet) + equation1 / 2.0;
		argument = meanLong1 - this.planetApogee.get(planet);
		equation2 = this.getMandaEquation(argument, planet);

		// second manda correction
		meanLong2 = meanLong1 - equation2 / 2.0;
		argument = meanLong2 - this.planetApogee.get(planet);
		equation3 = this.getMandaEquation(argument, planet);

		// second sighra correction
		meanLong3 = this.planetMeanPosition.get(planet) - equation3;
		anomaly2 = this.getMeanLongitude(ahargana, this.planetSighra.get(planet)) - meanLong3;
		equation4 = this.getSighraEquation(anomaly2, planet);

		equation5 = 0.0;

		return this.zero360(meanLong3 + equation4 + equation5);
	}

	public Double getSighraEquation(Double anomaly, String planet) {
		Double bhuja, koti, karna;
		bhuja = this.planetCircums.get(planet) / 360.0 * Math.sin(anomaly / KollavarshamMath.RADIAN_MULTIPLIER)
				* KollavarshamMath.RADIAN_MULTIPLIER;
		koti = this.planetCircums.get(planet) / 360.0 * Math.cos(anomaly / KollavarshamMath.RADIAN_MULTIPLIER)
				* KollavarshamMath.RADIAN_MULTIPLIER;
		karna = Math.sqrt(
				KollavarshamMath.square(KollavarshamMath.RADIAN_MULTIPLIER + koti) + KollavarshamMath.square(bhuja));

		return Math.asin(bhuja / karna) * KollavarshamMath.RADIAN_MULTIPLIER;
	}

	/* getSighraEquation : function (anomaly, planet) {
	 var bhuja, koti, karna;
	 bhuja = this.planetCircums[planet] / 360 * Math.sin(anomaly /
	 math.radianInDegrees) * math.radianInDegrees;
	 koti = this.planetCircums[planet] / 360 * Math.cos(anomaly /
	 math.radianInDegrees) * math.radianInDegrees;
	 karna = Math.sqrt(math.square(math.radianInDegrees + koti) +
	 math.square(bhuja));
	
	 return Math.asin(bhuja / karna) * math.radianInDegrees;
	 }*/
	
	public Double declination(Double longitude) {
		// https://en.wikipedia.org/wiki/Declination
		return Math.asin(Math.sin(longitude / KollavarshamMath.RADIAN_MULTIPLIER)
				* Math.sin(24.0 / KollavarshamMath.RADIAN_MULTIPLIER)) * KollavarshamMath.RADIAN_MULTIPLIER;
	}

	public Double getDaylightEquation(Double year, Double latitude) {
		// TODO: Add Tests if/when feasible
		Double meanSolarLongitude = this.getMeanLongitude(this.ahar, this.YugaRotation.get(PLANETS[1]));

		// Sayana Solar Longitude and Declination
		Double sayanaMeanSolarLongitude = meanSolarLongitude + (54.0 / 3600.0) * (this.year - 499.0);
		Double sayanaDeclination = this.declination(sayanaMeanSolarLongitude); // See Sewell, p.10

		// Equation of day light by Analemma
		// (https://en.wikipedia.org/wiki/Analemma)
		Double x = Math.tan(latitude / KollavarshamMath.RADIAN_MULTIPLIER)
				* Math.tan(sayanaDeclination / KollavarshamMath.RADIAN_MULTIPLIER);

		return 0.5 * Math.asin(x) / Math.PI;
	}

	public void setSunriseTime(Double eqTime) {
		// TODO: Add Tests if/when feasible
		Double sunriseTime = (0.25 - eqTime) * 24.0;
		this.sriseh = KollavarshamMath.truncate(sunriseTime);
		this.srisem = KollavarshamMath.truncate(60.0 * KollavarshamMath.fractional(sunriseTime));
	}

	public Double getMandaEquation(Double argument, String planet) {
		return Math.asin(this.planetCircumm.get(planet) / 360.0
				* Math.sin(argument / KollavarshamMath.RADIAN_MULTIPLIER)) * KollavarshamMath.RADIAN_MULTIPLIER;
	}

	public Double getTithi(Double tllong, Double tslong) {
		Double elong = tllong - tslong;
		elong = this.zero360(elong);
		return elong / 12.0;
	}

	public void setTithiSet(Double tithi) {
		// TODO: Add Tests if/when feasible
		this.tithiDay = KollavarshamMath.truncate(tithi) + 1;
		this.ftithi = KollavarshamMath.fractional(tithi);
	}

	public void setSuklaKrsna() {
		// TODO: Add Tests if/when feasible
		if (15 < this.tithiDay) {
			this.tithiDay -= 15.0;
			this.paksa = "Krsnapaksa";
		}
		else {
			this.paksa = "Suklapaksa";
		}
		this.suklaKrsna = this.paksa;
	}

	public Double getTllong(Double ahar) {
		Double meanLunarLongitude = this.getMeanLongitude(ahar, this.YugaRotation.get(PLANETS[2]));
		Double apogee = (this.getMeanLongitude(ahar, this.YugaRotation.get(PLANETS[8])) + 90.0);
		return this.zero360(meanLunarLongitude - this.getMandaEquation((meanLunarLongitude - apogee), PLANETS[2]));
	}

	public Double getTslong(Double ahar) {
		Double meanSolarLongitude = this.getMeanLongitude(ahar, this.YugaRotation.get(PLANETS[1]));
		Double TsLong = this.zero360(meanSolarLongitude
				- this.getMandaEquation((meanSolarLongitude - this.planetApogee.get(PLANETS[1])), PLANETS[1]));
		return TsLong;
	}

	public Double getElong(Double ahar) {
		Double elong = Math.abs(this.getTllong(ahar) - this.getTslong(ahar));
		if (180 < elong) {
			elong = 360.0 - elong;
		}
		return elong;
	}

	public Double findConj(Double leftX, Double leftY, Double rightX, Double rightY) {
		Double width = (rightX - leftX) / 2;
		Double centreX = (rightX + leftX) / 2;
		if (width < KollavarshamMath.EPSILON) {
			return this.getTslong(centreX);
		}
		else {
			Double centreY = this.getElong(centreX);
			Double relation = this.threeRelation(leftY, centreY, rightY);
			if (relation < 0) {
				rightX += width;
				rightY = this.getElong(rightX);
				return this.findConj(centreX, centreY, rightX, rightY);
			}
			else if (0 < relation) {
				leftX -= width;
				leftY = this.getElong(leftX);
				return this.findConj(leftX, leftY, centreX, centreY);
			}
			else {
				leftX += width / 2.0;
				leftY = this.getElong(leftX);
				rightX -= width / 2.0;
				rightY = this.getElong(rightX);
				return this.findConj(leftX, leftY, rightX, rightY);
			}
		}
	}

	public Double getConj(Double ahar) {
		return this.findConj(ahar - 2.0, this.getElong(ahar - 2.0), ahar + 2.0, this.getElong(ahar + 2.0));
	}

	public Double getClong(Double ahar, Double tithi) {
		Double newNew = this.yugaCivilDays
				/ (this.YugaRotation.get(PLANETS[2]) - this.YugaRotation.get(PLANETS[1]));
		ahar -= tithi * (newNew / 30.0);

		if (Math.abs(ahar - this.backClongAhar) < 1) {
			return this.backClong;
		}
		else if (Math.abs(ahar - this.backNclongAhar) < 1) {
			this.backClongAhar = this.backNclongAhar;
			this.backClong = this.backNclong;
			return this.backNclong;
		}
		else {
			this.backClongAhar = ahar;
			this.backClong = this.getConj(ahar);
			return this.backClong;
		}
	}

	public double getNclong(Double ahar, Double tithi) {
		Double newNew = this.yugaCivilDays
				/ (this.YugaRotation.get(PLANETS[2]) - this.YugaRotation.get(PLANETS[1]));
		ahar += (30.0 - tithi) * (newNew / 30.0);

		if (Math.abs(ahar - this.backNclongAhar) < 1) {
			return this.backNclong;
		}
		else {
			this.backNclongAhar = ahar;
			this.backNclong = this.getConj(ahar);
			return this.backNclong;
		}
	}

}
