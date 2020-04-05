package synechron_parking.com.synechron.parking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import synechron_parking.com.synechron.parking.model.Car;
import synechron_parking.com.synechron.parking.service.ParkingService;
import synechron_parking.com.synechron.parking.service.impl.ParkingServiceImpl;


/**
 * Unit test for simple App.
 */
public class AppTest {
	private final ByteArrayOutputStream	outContent	= new ByteArrayOutputStream();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void init()
	{

		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUp()
	{
		System.setOut(null);
	}

	@Test
	public void createParkingLot() throws Exception
	{
		ParkingService instance = new ParkingServiceImpl();
		instance.createParkingLot(65);
		assertTrue("Createdparkinglotwith65slots".equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
		instance.doCleanup();
	}
	
	@Test
	public void testParkingCapacity() throws Exception
	{
		ParkingService instance = new ParkingServiceImpl();
		instance.createParkingLot(11);
		instance.park(new Car("KA-01-HH-1234"));
		instance.park(new Car("KA-01-HH-9999"));
		instance.park(new Car("KA-01-BB-0001"));
		assertEquals(3, instance.getUsedSlotCount());
		instance.doCleanup();
	}
	
	@Test
	public void leave() throws Exception
	{
		ParkingService instance = new ParkingServiceImpl();
		instance.createParkingLot(6);
		instance.unPark("MH-12-HG-2136");
		assertEquals("RegistrationnumberMH-12-HG-2136notfound.", outContent.toString().trim().replace(" ", "").split("\n")[1]);
		instance.park(new Car("KA-01-HH-1234"));
		instance.park(new Car("KA-01-HH-9999"));
		instance.park(new Car("KA-01-BB-0001"));
		instance.unPark("KA-01-BB-0001");
		assertEquals(2, instance.getUsedSlotCount());
		instance.doCleanup();
	}
	
	@Test
	public void testWhenVehicleAlreadyPresent() throws Exception
	{
		ParkingService instance = new ParkingServiceImpl();
		instance.createParkingLot(3);
		instance.park(new Car("KA-01-HH-1234"));
		instance.park(new Car("KA-01-HH-1234"));
		assertTrue(
				"Sorry,VehicleKA-01-HH-1234alreadyparked"
						.equalsIgnoreCase(outContent.toString().trim().replace(" ", "").split("\n")[2]));
		instance.doCleanup();
	}

}
