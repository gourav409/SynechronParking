package synechron_parking.com.synechron.parking.service;

import java.util.Optional;

import synechron_parking.com.synechron.parking.exception.ParkingException;
import synechron_parking.com.synechron.parking.model.Vehicle;

/**
 * @author gourav
 *
 */
public interface ParkingService extends AbstractService {

	public void createParkingLot(int capacity) throws ParkingException;
	public Optional<Integer> park(Vehicle vehicle) throws ParkingException;
	public Optional<Integer> unPark(String registrationNumber) throws ParkingException;
	public void getStatus();
	public void doCleanup();
	public int getUsedSlotCount();
	public void getStatusWithDate(String startDate, String endDate);
	public void getStatusOfVehicle(String registrationNumber, String startDate, String endDate);

}
