package org.kollavarsham.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kollavarsham.Calculations;
import org.kollavarsham.Celestial;
import org.kollavarsham.KollavarshamMath;

public class CelestialTest {

	private Celestial kvcelestial;
	private Calculations kvcalculations;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		kvcelestial = Celestial.getInstance();
		kvcelestial.setPrimaryConstants();
		// kvcelestial.applyBija();
		kvcelestial.setSecondaryConstants();
		kvcelestial.setPlanetaryConstants();
		kvcalculations = new Calculations();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testZero360() {
		assertEquals(kvcelestial.zero360(75.2), 75.2, 0);
		assertEquals(kvcelestial.zero360(-75.2), 284.8, 0);
		assertEquals(kvcelestial.zero360(370.0), 10, 0);
		assertEquals(kvcelestial.zero360(0.0), 0, 0);
		assertEquals(kvcelestial.zero360(0.234), 0.234, 0);
	}

	@Test
	public void testThreeRelation() {
		assertEquals(kvcelestial.threeRelation(-1.0, 1.0, 3.0), 1, 0);
		assertEquals(kvcelestial.threeRelation(1.0, -1.0, -3.0), -1, 0);
		assertEquals(kvcelestial.threeRelation(1.0, 1.0, 1.0), 0, 0);
		assertEquals(kvcelestial.threeRelation(1.0, 5.0, -3.0), 0, 0); // invalid
																		// scenario
	}

	@Test
	public void testGetMeanLongitude() {
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMeanLongitude(1868236.15634155, 4320000.0),
				298.54776362783));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMeanLongitude(1868236.15637207, 4320000.0),
				298.547793708385));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMeanLongitude(1868236.15635681, 4320000.0),
				298.547778668108));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMeanLongitude(1868236.15635681, 4320000.0),
				298.547778668108));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMeanLongitude(1868236.15637207, 4320000.0),
				298.547793708385));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMeanLongitude(1868236.15636444, 4320000.0),
				298.547786188246));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMeanLongitude(1868236.15635681, 4320000.0),
				298.547778668108));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMeanLongitude(1868236.15636444, 4320000.0),
				298.547786188246));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMeanLongitude(1868236.15636063, 4320000.0),
				298.547782433088));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMeanLongitude(1868236.15636063, 4320000.0),
				298.547782433088));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMeanLongitude(1868236.15636444, 4320000.0),
				298.547786188246));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMeanLongitude(1868236.15636253, 4320000.0),
				298.54778430592));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMeanLongitude(1868236.15636063, 4320000.0),
				298.547782433088));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMeanLongitude(1868236.15636253, 4320000.0),
				298.54778430592));
	}

	@Test
	public void testGetTrueLongitude() {
		Calendar testDay = Calendar.getInstance();
		testDay.set(2014, Calendar.FEBRUARY, 11);
		kvcalculations.FromGregorian(false, 23.2, testDay);

		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710693.0, 215.330481398828, "Mercury"), 290.256193246842));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710694.0, 216.345092245966, "Mercury"), 287.939466847665));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710695.0, 217.360117559963, "Mercury"), 285.69872602331));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710696.0, 218.375548627069, "Mercury"), 283.567766431273));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772755.0, 183.139229101953, "Mercury"), 292.367822361191));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772756.0, 184.136821438217, "Mercury"), 293.462095381124));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772757.0, 185.135030298228, "Mercury"), 294.554161309681));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1132992.0, 355.302664567532, "Mercury"), 294.330597635538));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1868191.0, 288.309252298232, "Mercury"), 280.5291286866));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1868192.0, 289.32751969395, "Mercury"), 281.497932838323));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867492.0, 320.200309773426, "Mercury"), 285.388042602287));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867886.0, 348.803993428264, "Mercury"), 308.404674687418));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867520.0, 348.072902270539, "Mercury"), 312.012169567129));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1844848.0, 322.249740952942, "Mercury"), 287.862667589296));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710693.0, 215.330481398828, "Venus"), 324.308933009715));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710694.0, 216.345092245966, "Venus"), 321.407541005215));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710695.0, 217.360117559963, "Venus"), 318.226901026202));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710696.0, 218.375548627069, "Venus"), 314.775291122611));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772755.0, 183.139229101953, "Venus"), 253.240856229459));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772756.0, 184.136821438217, "Venus"), 253.582115854346));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772757.0, 185.135030298228, "Venus"), 253.942554219963));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1132992.0, 355.302664567532, "Venus"), 331.723144303665));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1868191.0, 288.309252298232, "Venus"), 344.369387070859));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1868192.0, 289.32751969395, "Venus"), 344.125688357144));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867492.0, 320.200309773426, "Venus"), 338.204333524322));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867886.0, 348.803993428264, "Venus"), 303.63668762932));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867520.0, 348.072902270539, "Venus"), 343.326701630526));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1844848.0, 322.249740952942, "Venus"), 289.230030387495));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710693.0, 215.330481398828, "Mars"), 158.887067202077));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710694.0, 216.345092245966, "Mars"), 159.240835569617));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710695.0, 217.360117559963, "Mars"), 159.593872869183));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710696.0, 218.375548627069, "Mars"), 159.946154425643));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772755.0, 183.139229101953, "Mars"), 147.311514092937));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772756.0, 184.136821438217, "Mars"), 147.677928778235));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772757.0, 185.135030298228, "Mars"), 148.044220074319));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1132992.0, 355.302664567532, "Mars"), 149.172853178935));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1868191.0, 288.309252298232, "Mars"), 179.846235661092));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTrueLongitude(1868192.0, 289.32751969395, "Mars"),
				180.010186773321));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867492.0, 320.200309773426, "Mars"), 179.72880836255));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867886.0, 348.803993428264, "Mars"), 158.084536851156));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867520.0, 348.072902270539, "Mars"), 159.029060106121));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1844848.0, 322.249740952942, "Mars"), 179.131773264678));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710693.0, 215.330481398828, "Saturn"), 194.843713150225));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710694.0, 216.345092245966, "Saturn"), 194.935519951897));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710695.0, 217.360117559963, "Saturn"), 195.027563651004));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710696.0, 218.375548627069, "Saturn"), 195.119823966232));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772755.0, 183.139229101953, "Saturn"), 192.153417342728));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772756.0, 184.136821438217, "Saturn"), 192.226932462565));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772757.0, 185.135030298228, "Saturn"), 192.301333486724));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1132992.0, 355.302664567532, "Saturn"), 201.3134602354));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1868191.0, 288.309252298232, "Saturn"), 200.822899002374));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1868192.0, 289.32751969395, "Saturn"), 200.88079361344));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867492.0, 320.200309773426, "Saturn"), 201.987978576402));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867886.0, 348.803993428264, "Saturn"), 201.614465529182));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867520.0, 348.072902270539, "Saturn"), 201.643214770863));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1844848.0, 322.249740952942, "Saturn"), 202.010337361371));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710693.0, 215.330481398828, "Saturn"), 194.843713150225));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710694.0, 216.345092245966, "Saturn"), 194.935519951897));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710695.0, 217.360117559963, "Saturn"), 195.027563651004));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1710696.0, 218.375548627069, "Saturn"), 195.119823966232));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772755.0, 183.139229101953, "Saturn"), 192.153417342728));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772756.0, 184.136821438217, "Saturn"), 192.226932462565));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1772757.0, 185.135030298228, "Saturn"), 192.301333486724));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1132992.0, 355.302664567532, "Saturn"), 201.3134602354));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1868191.0, 288.309252298232, "Saturn"), 200.822899002374));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1868192.0, 289.32751969395, "Saturn"), 200.88079361344));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867492.0, 320.200309773426, "Saturn"), 201.987978576402));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867886.0, 348.803993428264, "Saturn"), 201.614465529182));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1867520.0, 348.072902270539, "Saturn"), 201.643214770863));
		assertTrue(KollavarshamMath.floatingPointEqual(
				kvcelestial.getTrueLongitude(1844848.0, 322.249740952942, "Saturn"), 202.010337361371));
	}

	@Test
	public void testDeclination() {
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.declination(31.3101877453024), 12.2026059914001));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.declination(42.2597957259723), 15.8742931864835));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.declination(59.2349729472294), 20.4565845497204));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.declination(62.5975972349908), 21.1677169773821));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.declination(80.4818781723799), 23.6492922420057));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.declination(121.1497130809087), 20.3707052985127));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.declination(320.8687779979979), -14.8738036439391));
	}

	@Test
	public void testGetMandaEquation() {
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(216.448410870245, "Sun"),
				-1.30810722363905));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(-72.3184309200178, "Moon"),
				-4.83281883352571));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(150.807334962742, "Moon"),
				2.47190852495064));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(206.122810882618, "Sun"),
				-0.969422483995786));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(203.067198238109, "Moon"),
				-1.98547851952987));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(210.065221570941, "Sun"),
				-1.10305954670912));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(176.937266597806, "Moon"),
				0.270697085906033));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(208.094016226779, "Sun"),
				-1.03685394627977));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(163.872300782873, "Moon"),
				1.40749058746745));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(207.108413554698, "Sun"),
				-1.00328649380511));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(190.002232417937, "Moon"),
				-0.880005747995446));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(209.07961889886, "Sun"),
				-1.07011491083048));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(176.937266597806, "Moon"),
				0.270697085906033));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(208.094016226779, "Sun"),
				-1.03685394627977));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(170.404783692979, "Moon"),
				0.844536073585857));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(207.601214890739, "Sun"),
				-1.02010791244252));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(183.469749507872, "Moon"),
				-0.306629778128034));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(208.58681756282, "Sun"),
				-1.05352335673225));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(176.937266597806, "Moon"),
				0.270697085906033));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(208.094016226779, "Sun"),
				-1.03685394627977));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(190.002232417937, "Moon"),
				-0.880005747995446));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(209.07961889886, "Sun"),
				-1.07011491083048));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(183.469749507872, "Moon"),
				-0.306629778128034));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(208.58681756282, "Sun"),
				-1.05352335673225));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(180.203508055438, "Moon"),
				-0.0179953506933944));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(208.340416894636, "Sun"),
				-1.04519830661684));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(186.735990965544, "Moon"),
				-0.594275735600709));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(208.833218230676, "Sun"),
				-1.06182894265887));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(183.469749507872, "Moon"),
				-0.306629778128034));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(208.58681756282, "Sun"),
				-1.05352335673225));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(176.937266597806, "Moon"),
				0.270697085906033));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(208.094016226779, "Sun"),
				-1.03685394627977));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(180.203508055438, "Moon"),
				-0.0179953506933944));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(208.340416894636, "Sun"),
				-1.04519830661684));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(83.931123946793, "Mercury"),
				4.59454849262788));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(81.6338497004791, "Mercury"),
				4.57122541974445));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(191.523444971968, "Venus"),
				-0.365635863559596));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(191.706262903748, "Venus"),
				-0.37135641118768));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(34.7045977141798, "Mars"),
				6.67523064837691));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(31.3669823899913, "Mars"),
				6.10047750894678));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(-91.5542432559879, "Jupiter"),
				-5.17767683561233));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(-88.9654048381817, "Jupiter"),
				-5.17874093000353));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(-42.8326673204595, "Saturn"),
				-5.25521105975762));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getMandaEquation(-40.2050617905807, "Saturn"),
				-4.98912071710793));
	}

	@Test
	public void testGetTithi() {
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTithi(37.5275236212135, 294.989551683806),
				8.54483099478396));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTithi(45.9229472947752, 333.593757395798),
				6.02743249158144));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTithi(123.3068304585275, 15.597297524597),
				8.97579441116087));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTithi(15.5275236212135, 163.989551683806),
				17.6281643281173));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTithi(245.9229472947752, 3.593757395798),
				20.1940991582481));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTithi(302.3068304585275, 56.597297524597),
				20.4757944111609));
	}

	@Test
	public void testGetTllong() {
		// assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTllong(2299158.5),
		// 167.084587116821));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTllong(2299159.5), 179.618866280373));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTllong(2299160.5), 191.953219840454));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTllong(2299161.5), 204.131519861513));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTllong(2361220.5), 349.195739637822));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTllong(2361221.5), 1.82309136307406));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTllong(2361222.5), 14.6945040053245));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTllong(1721457.5), 6.55724149356419));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTllong(2456656.5), 16.24829446685));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTllong(2456657.5), 29.8253740270552));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTllong(2455957.5), 156.709071062542));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTllong(2456351.5), 316.081404838166));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTllong(2455985.5), 165.854323537076));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTllong(2433313.5), 236.806759936797));
	}

	@Test
	public void testGetTslong() {
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTslong(2299158.5), 215.330481398828));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTslong(2299159.5), 216.345092245966));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTslong(2299160.5), 217.360117559963));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTslong(2299161.5), 218.375548627069));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTslong(2361220.5), 183.139229101953));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTslong(2361221.5), 184.136821438217));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTslong(2361222.5), 185.135030298228));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTslong(1721457.5), 355.302664567532));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTslong(2456656.5), 288.309252298232));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTslong(2456657.5), 289.32751969395));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTslong(2455957.5), 320.200309773426));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTslong(2456351.5), 348.803993428264));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTslong(2455985.5), 348.072902270539));
		assertTrue(KollavarshamMath.floatingPointEqual(kvcelestial.getTslong(2433313.5), 322.249740952942));
	}

}
