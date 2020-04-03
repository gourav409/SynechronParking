package synechron_parking.com.synechron.parking.service.impl;

import java.util.Optional;


import synechron_parking.com.synechron.parking.dao.ParkingDataManager;
import synechron_parking.com.synechron.parking.dao.impl.ParkingDataManagerImpl;
import synechron_parking.com.synechron.parking.exception.ErrorCode;
import synechron_parking.com.synechron.parking.exception.ParkingException;
import synechron_parking.com.synechron.parking.model.Vehicle;
import synechron_parking.com.synechron.parking.service.ParkingService;

public class ParkingServiceImpl implements ParkingService {
	
	private ParkingDataManager<Vehicle> parkingManager = ParkingDataManagerImpl.getInstance();

	public ParkingServiceImpl() {
		
	}

	public Optional<Integer> park(Vehicle vehicle) throws ParkingException {
		Optional<Integer> value = Optional.empty();
		try
		{
			value = Optional.of(parkingManager.parkCar(vehicle));
			if (value.get() == -1)
				System.out.println("Sorry, parking lot is full");
			else
				System.out.println("Allocated slot number: " + value.get());
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		return value;
	}

	public boolean unPark(int slotNumber) throws ParkingException {
		boolean value;
		try
		{
			value = parkingManager.leaveCar(slotNumber);
			if (!value)
				System.out.println("Sorry, The slot was already empty");
			else
				System.out.println("Deallocated slot number: " + slotNumber);
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		return value;
	}

}
