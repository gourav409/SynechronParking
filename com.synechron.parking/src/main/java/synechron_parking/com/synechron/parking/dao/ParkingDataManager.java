package synechron_parking.com.synechron.parking.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import synechron_parking.com.synechron.parking.model.Vehicle;

/**
 * @author gourav
 *
 * @param <T>
 */
public interface ParkingDataManager<T extends Vehicle> {

	public int parkCar(T vehicle);

	public int leaveCar(String registrationNumber);

	public Map<Integer, T> getStatus();
	
	public void doCleanup();
	public Map<String, Integer> getParkedVehicleMap();

	public List<Vehicle> getVehicleListBetweenDates(LocalDate startLocalDate, LocalDate endLocalDate);
	
	public List<Vehicle> getVehicleEntryDetails(String registrationNumber, LocalDate startLocalDate, LocalDate endLocalDate);

}
