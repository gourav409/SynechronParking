package synechron_parking.com.synechron.parking.dao;

import java.util.List;

import synechron_parking.com.synechron.parking.model.Vehicle;

public interface ParkingDataManager<T extends Vehicle> {
	
	public int parkCar(T vehicle);
	
	public boolean leaveCar(int slotNumber);
	
	public List<String> getStatus();

}
