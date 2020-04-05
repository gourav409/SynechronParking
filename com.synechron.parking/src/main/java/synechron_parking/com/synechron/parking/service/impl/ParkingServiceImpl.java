package synechron_parking.com.synechron.parking.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import synechron_parking.com.synechron.parking.dao.ParkingDataManager;
import synechron_parking.com.synechron.parking.dao.impl.ParkingDataManagerImpl;
import synechron_parking.com.synechron.parking.exception.ErrorCode;
import synechron_parking.com.synechron.parking.exception.ParkingException;
import synechron_parking.com.synechron.parking.model.Vehicle;
import synechron_parking.com.synechron.parking.service.ParkingService;

/**
 * @author gourav
 *
 */
public class ParkingServiceImpl implements ParkingService {

	private ParkingDataManager<Vehicle> parkingManager = null;

	public ParkingServiceImpl() {

	}

	@Override
	public void createParkingLot(int capacity) throws ParkingException {
		if (parkingManager != null)
			throw new ParkingException(ErrorCode.PARKING_ALREADY_EXIST.getMessage());
		this.parkingManager = ParkingDataManagerImpl.getInstance(capacity);
		System.out.println("Created parking lot with " + capacity + " slots");

	}

	public Optional<Integer> park(Vehicle vehicle) throws ParkingException {
		if(parkingManager == null) {
			this.parkingManager = ParkingDataManagerImpl.getInstance(500);
			System.out.println("Created parking lot with default 500 slots");
		}
		
		Optional<Integer> value = Optional.empty();
		try
		{
			value = Optional.of(parkingManager.parkCar(vehicle));
			if (value.get() == -1)
				System.out.println("Sorry, parking lot is full");
			else if (value.get() == -2)
				System.out.println("Sorry, Vehicle "+vehicle.getRegistrationNumber()+" already parked");
			else
				System.out.println("vehicle "+vehicle.getRegistrationNumber()+" allocated slot number: " + value.get());
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		return value;
	}

	public Optional<Integer> unPark(String registrationNumber) throws ParkingException {
		Optional<Integer> value = Optional.empty();
				
		try
		{
			value = Optional.of(parkingManager.leaveCar(registrationNumber));
			if (value.get() == -1)
				System.out.println("Registration number "+registrationNumber+" not found.");
			else
				System.out.println("Registration number "+registrationNumber+" with slot number " + value.get() + " is free.");
		}
		catch (Exception e)
		{
			throw new ParkingException(ErrorCode.PROCESSING_ERROR.getMessage(), e);
		}
		return value;
	}

	@Override
	public void getStatus() {
		try
		{
			Map<Integer, Vehicle> parkingMap = parkingManager.getStatus();
			if (parkingMap.size() == 0)
				System.out.println("Sorry, parking lot is empty.");
			else
			{
				System.out.println("====================================================");
				System.out.println("=================  Current Status  =================");
				System.out.println("====================================================");
				System.out.println("Slot No.\tRegistration No.");
				System.out.println("----------------------------------------------------");
				parkingMap.forEach((k,v) -> 
				System.out.println(k+"\t\t"+((Vehicle)v).getRegistrationNumber()));
				System.out.println("=======================  END  ======================\n");
			}
		}
		catch (Exception e)
		{
		}
	}
	
	

	@Override
	public void doCleanup() {
		parkingManager.doCleanup();
		
	}

	@Override
	public int getUsedSlotCount() {
		return parkingManager.getParkedVehicleMap().size();
	}

	@Override
	public void getStatusWithDate(String startDate, String endDate) {
		LocalDate startLocalDate = LocalDate.parse(startDate);
		LocalDate endLocalDate = LocalDate.parse(endDate);
		
		List<Vehicle> statusList = parkingManager.getVehicleListBetweenDates(startLocalDate, endLocalDate);
		System.out.println("=====================================================================================================");
		System.out.println("===================  Status between date "+startDate+" and "+endDate+"  =================================");
		System.out.println("=====================================================================================================");
		System.out.println("Registration No.\tSlot No.\tIn Date\t\tIn Time\t\tOut Date\tOut Time");
		System.out.println("-----------------------------------------------------------------------------------------------------");
		for(Vehicle eachVehicle : statusList) {
			System.out.println(eachVehicle.getRegistrationNumber()+"\t\t"+eachVehicle.getSlotNumber()+"\t\t"+
					eachVehicle.getEntryDate()+"\t"+eachVehicle.getEntryTime()+"\t"+eachVehicle.getExitDate()+"\t"+
					eachVehicle.getExitTime());
		}
		System.out.println("====================================  END  =====================================\n");
	}

	@Override
	public void getStatusOfVehicle(String registrationNumber, String startDate, String endDate) {
		LocalDate startLocalDate = LocalDate.parse(startDate);
		LocalDate endLocalDate = LocalDate.parse(endDate);
		
		List<Vehicle> vehicleEntryDetails = parkingManager.getVehicleEntryDetails(registrationNumber, startLocalDate, endLocalDate);
		System.out.println("=================================================================================================");
		System.out.println("============  Status of vehicle "+registrationNumber+" between date "+startDate+" and "+endDate+"  ===========");
		System.out.println("=================================================================================================");
		System.out.println("Slot No.\tIn Date\t\tIn Time\t\tOut Date\tOut Time");
		System.out.println("-------------------------------------------------------------------------------------------------");
		for(Vehicle eachVehicle : vehicleEntryDetails) {
			System.out.println(eachVehicle.getSlotNumber()+"\t\t"+
					eachVehicle.getEntryDate()+"\t"+eachVehicle.getEntryTime()+"\t"+eachVehicle.getExitDate()+"\t"+
					eachVehicle.getEntryTime());
		}
		System.out.println("====================================  END  ======================================================\n");
	}

}
