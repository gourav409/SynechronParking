package synechron_parking.com.synechron.parking.service;

import synechron_parking.com.synechron.parking.exception.ParkingException;
import synechron_parking.com.synechron.parking.model.Vehicle;

public interface ParkingService extends AbstractService {
	
	public int park(Vehicle vehicle) throws ParkingException;
	public int unPark(int slotNumber) throws ParkingException;

}
