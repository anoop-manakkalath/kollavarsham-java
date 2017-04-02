/*
 * Kollavarsham
 * http://kollavarsham.org
 *
 * Copyright (c) 2014 - 2017 The Kollavarsham Team
 * Licensed under the MIT license.
 */
package org.kollavarsham;

import java.util.Calendar;
import static org.kollavarsham.Celestial.PLANETS;

public class Calculations {

	private MalayalamCalendar myCalendar;
	private Celestial myCelestial;
	Double ahargana = 1.0;
	Double julianDay = 1.0; // {for Julian days}
	Double yearKali = 1.0;
	Double yearSaka = 1.0;
	Double yearVikrama = 1.0;
	Double masaNum = 1.0;
	String sauraMasa = "";
	Double sauraMasaNum = 1.0;
	Double sauraMasaDay = 1.0;

	String malayalaMasa = ""; // HP
	Double MalayalamYear = 1.0;

	String weekdayName = "";
	Double tithi = 1.0;
	Double eqtime = 1.0; // {for equation of time}
	String adhimasa = "";
	String masa = "";
	String naksatra = "";
	public String malayalaNaksatra = "";

	public Calculations() {
		myCalendar = new MalayalamCalendar();
		myCelestial = Celestial.getInstance();
	}

	public void _setConstants(Boolean bija) {
		// TODO: Add Tests if/when feasible
		myCelestial.setPrimaryConstants();
		if (bija) {
			myCelestial.applyBija();
		}
		myCelestial.setSecondaryConstants();
		myCelestial.setPlanetaryConstants();
	}

	public void setDesantara(Double longitude, Double defaultLongitude) {
		myCalendar.setDesantara(longitude, defaultLongitude);
	}

	public void FromGregorian(Boolean bija, Double latitude, Calendar gregorianDate) {
		// Java port
		// Settings will come from the global instance of this application where
		// we would have provided the set APIs for it
		// For now I am going to use the hardcoded values for the settings bija
		// and latitude that is going to be used in this method
		bija = false;
		latitude = 23.2;
		// TODO: Add Tests if/when feasible
		_setConstants(bija);
		// This is how it is done in Perl - we use the new gregorianDate global
		// globals.JulianDay = calendar.gregorianDateToJulianDay(new
		// Date(globals.year, globals.month - 1, globals.day));
		this.julianDay = myCalendar.gregorianDateToJulianDay(gregorianDate);
		myCelestial.ahar = myCalendar.julianDayToAhargana(this.julianDay);
		this.julianDay = KollavarshamMath.truncate(this.julianDay + 0.5);
		this.ahargana = KollavarshamMath.truncate(myCelestial.ahar + 0.5);
		this.weekdayName = myCalendar.julianDayToWeekday(this.julianDay);
		myCelestial.year = (double) gregorianDate.get(Calendar.YEAR);
		myCelestial.setAyanamsa(ahargana);

		// at 6 o'clock
		myCelestial.ahar += 0.25;

		// desantara
		myCelestial.ahar -= myCalendar.desantara;

		// time of sunrise at local latitude
		Double eqtime = myCelestial.getDaylightEquation(myCelestial.year, latitude);
		myCelestial.ahar -= eqtime;
		myCelestial.setSunriseTime(eqtime);

		// Lunar apogee and node at sunrise
		myCelestial.planetMeanPosition.put(PLANETS[8], myCelestial.getMeanLongitude(myCelestial.ahar,
				myCelestial.YugaRotation.get(PLANETS[8])) + 90);
		myCelestial.planetMeanPosition.put(PLANETS[8],
				myCelestial.zero360(myCelestial.planetMeanPosition.get(PLANETS[8])));

		myCelestial.planetMeanPosition.put(PLANETS[9],
				myCelestial.getMeanLongitude(myCelestial.ahar, myCelestial.YugaRotation.get(PLANETS[9])) + 180);
		myCelestial.planetMeanPosition.put(PLANETS[9],
				myCelestial.zero360(myCelestial.planetMeanPosition.get(PLANETS[9])));

		// mean and true sun at sunrise
		Double mslong = myCelestial.getMeanLongitude(myCelestial.ahar, myCelestial.YugaRotation.get(PLANETS[1]));
		myCelestial.planetMeanPosition.put(PLANETS[1], mslong);
		Double tslong = myCelestial.zero360(mslong
				- myCelestial.getMandaEquation((mslong - myCelestial.planetApogee.get(PLANETS[1])), PLANETS[1]));
		myCelestial.planetTruePosition.put(PLANETS[2], tslong);

		// mean and true moon at sunrise
		Double mllong = myCelestial.getMeanLongitude(myCelestial.ahar, myCelestial.YugaRotation.get(PLANETS[2]));
		myCelestial.planetMeanPosition.put(PLANETS[2], mllong);
		myCelestial.planetApogee.put(PLANETS[2], myCelestial.planetMeanPosition.get(PLANETS[8]));
		Double tllong = myCelestial.zero360(mllong
				- myCelestial.getMandaEquation((mllong - myCelestial.planetApogee.get(PLANETS[2])), PLANETS[2]));
		myCelestial.planetTruePosition.put(PLANETS[2], tllong);

		// finding tithi and longitude of conjunction
		this.tithi = myCelestial.getTithi(tllong, tslong);
		myCelestial.setTithiSet(tithi);
		myCelestial.setSuklaKrsna();

		// last conjunction
		Double clong = myCelestial.getClong(myCelestial.ahar, tithi);

		// next conjunction
		Double nclong = myCelestial.getNclong(myCelestial.ahar, tithi);

		this.adhimasa = myCalendar.getAdhimasa(clong, nclong);
		this.masaNum = myCalendar.getMasaNum(tslong, clong);
		this.masa = myCalendar.getMasaName(masaNum);

		MalayalamCalendar.SauraMasaMonthDay sauraMasaMonthDay = myCalendar.getSauraMasaMonthDay(myCelestial.ahar);
		this.sauraMasaNum = sauraMasaMonthDay.month;
		this.sauraMasaDay = sauraMasaMonthDay.day;
		this.sauraMasa = myCalendar.getSauraMasaName(sauraMasaNum);

		Double malayalaMasaNum = (sauraMasaNum - 4 + 12) % 12;
		this.malayalaMasa = myCalendar.getMalayalaMasaName(malayalaMasaNum);

		String naksatra = myCalendar.getNaksatraName(tllong);
		this.malayalaNaksatra = myCalendar.getMalayalaNaksatraName(tllong);

		// kali and Saka era
		this.yearKali = myCalendar.aharganaToKali(myCelestial.ahar + (4 - masaNum) * 30);
		this.yearSaka = myCalendar.kaliToSaka(yearKali);
		this.yearVikrama = yearSaka + 135;

		// Sewell p.45 -
		// https://archive.org/stream/indiancalendarwi00sewerich#page/45/mode/1up
		this.MalayalamYear = yearSaka - 747
				+ KollavarshamMath.truncate((sauraMasaNum - malayalaMasaNum + 12) / 12);

		String[] planets = { "Mercury", "Venus", "Mars", "Jupiter", "Saturn" };
		for (String planet : planets) {
			myCelestial.planetMeanPosition.put(planet, myCelestial.getMeanLongitude(myCelestial.ahar,
					myCelestial.planetRotation.get(planet)));
			myCelestial.planetTruePosition.put(planet,
					myCelestial.getTrueLongitude(myCelestial.ahar, mslong, planet));
		}

	}

}
