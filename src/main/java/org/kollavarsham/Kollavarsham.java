/*
 * Kollavarsham
 * http://kollavarsham.org
 *
 * Copyright (c) 2014 - 2017 The Kollavarsham Team
 * Licensed under the MIT license.
 */
package org.kollavarsham;

import java.util.Calendar;

/**
 * This is the main class for the Kollavarsham API Extending this from
 * java.util.Date to reuse certain functionalities from that. That will make
 * available all the APIs of the 'modern' Date API
 */
public class Kollavarsham {

	private Calculations calculations;

	private Boolean bija;
	private Double latitude;
	private Double longitude;

	private String malayalamNakshatram;
	private Double malayalamYear;
	private String malayalamMonth;
	private Calendar modernDate;
	private Locations locations;

	public Kollavarsham() {
		calculations = new Calculations();
		modernDate = Calendar.getInstance();
		locations = new Locations();

		// Default options
		this.bija = false;
		this.latitude = locations.getLocationCoordinates("Ujjain").getLatitude();
		this.longitude = locations.getLocationCoordinates("Ujjain").getLongitude();

	}

	public void setModernDate(Calendar modernDate) {
		this.modernDate = modernDate;
	}

	public void setOptions(Boolean bija, String location) {
		this.bija = bija;
		this.latitude = locations.getLocationCoordinates(location).getLatitude();
		this.longitude = locations.getLocationCoordinates(location).getLongitude();
		if (!location.equals("Ujjain")) {
			calculations.setDesantara(this.longitude, locations.getLocationCoordinates("Ujjain").getLongitude());
		}
	}

	public void FromGregorian() {
		calculations._setConstants(this.bija);
		calculations.FromGregorian(this.bija, this.latitude, this.modernDate);
		malayalamNakshatram = calculations.malayalaNaksatra;
		malayalamYear = calculations.MalayalamYear;
		malayalamMonth = calculations.malayalaMasa;
	}

	public String getMalayalamNakshatram() {
		return malayalamNakshatram;
	}

	public Double getMalayalamYear() {
		return malayalamYear;
	}

	public String getMalayalamMonthNum() {
		return malayalamMonth;
	}

	/**
	 * @param args
	 */
	public static void main(String... args) {
		// Test Only
		Kollavarsham malayalamYear = new Kollavarsham();
		Calendar modernDate = Calendar.getInstance();
		modernDate.set(2017, Calendar.APRIL, 1);
		malayalamYear.setModernDate(modernDate);
		malayalamYear.setOptions(true, "Ujjain");
		malayalamYear.FromGregorian();
		System.out.println("Converted to Malayalam Calendar with the following details:");
		System.out.println("Malayalam Year  :" + malayalamYear.getMalayalamYear().intValue());
		System.out.println("Malayalam Month :" + malayalamYear.getMalayalamMonthNum());
		System.out.println("Nakshatram : " + malayalamYear.getMalayalamNakshatram());
	}

}
