package synechron_parking.com.synechron.parking;

import synechron_parking.com.synechron.parking.exception.ParkingException;
import synechron_parking.com.synechron.parking.model.Vehicle;
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
        Vehicle v1 = new Vehicle("a", "red");
        Vehicle v2 = new Vehicle("b", "red");
        Vehicle v3 = new Vehicle("c", "red");
        Vehicle v4 = new Vehicle("d", "red");
        ParkingServiceImpl p  = new ParkingServiceImpl();
        p.park(v1);
        p.park(v2);
        p.unPark(0);
        p.park(v3);
        p.unPark(1);
        p.park(v4);
    }
}
