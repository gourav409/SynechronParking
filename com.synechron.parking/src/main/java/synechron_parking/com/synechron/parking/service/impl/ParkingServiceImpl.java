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
			if (value.get() == -2)
				System.out.println("Sorry, Vehicle "+vehicle.getRegistrationNumber()+" already parked");
			else
				System.out.println("Vehicle "+vehicle.getRegistrationNumber()+" allocated at slot number: " + value.get());
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		return value;
	}

	public Optional<Integer> unPark(Vehicle vehicle) throws ParkingException {
		Optional<Integer> value = Optional.empty();
		try
		{
			value = Optional.of(parkingManager.leaveCar(vehicle));
			if (value.get() == -1)
				System.out.println("Sorry, The slot was already empty");
			else
				System.out.println("Vehicle "+vehicle.getRegistrationNumber()+" is deallocated from slot number: " + value.get());
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		return value;
	}

}
