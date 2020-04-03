package synechron_parking.com.synechron.parking;

import synechron_parking.com.synechron.parking.exception.ParkingException;
import synechron_parking.com.synechron.parking.model.Vehicle;
import synechron_parking.com.synechron.parking.processor.AbstractProcessor;
import synechron_parking.com.synechron.parking.processor.RequestProcessor;
import synechron_parking.com.synechron.parking.service.impl.ParkingServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParkingException
    {
        //System.out.println( "Hello World!" );
        Vehicle v1 = new Vehicle("TS07FM2136", "Blue");
        Vehicle v2 = new Vehicle("MH12FG9123", "Red");
        Vehicle v3 = new Vehicle("KA02FG8756", "Black");
        Vehicle v4 = new Vehicle("AP09AH2354", "Brown");
        ParkingServiceImpl p  = new ParkingServiceImpl();
        p.park(v1);
        p.park(v2);
        p.unPark(v1);
        p.park(v3);
        p.unPark(v2);
        p.park(v4);
        
        AbstractProcessor processor = new RequestProcessor<>();
        processor.getParkingDetail();
        
        p.park(v1);
        p.park(v2);
        processor.getParkingDetail();
        
        
    }
}
