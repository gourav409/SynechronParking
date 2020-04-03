package synechron_parking.com.synechron.parking.service;

import java.util.Optional;

import synechron_parking.com.synechron.parking.exception.ParkingException;
import synechron_parking.com.synechron.parking.model.Vehicle;

public interface ParkingService extends AbstractService {
	
	public Optional<Integer> park(Vehicle vehicle) throws ParkingException;
	public Optional<Integer> unPark(Vehicle vehicle) throws ParkingException;

}
