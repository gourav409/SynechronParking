package synechron_parking.com.synechron.parking.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import synechron_parking.com.synechron.parking.dao.ParkingDataManager;
import synechron_parking.com.synechron.parking.model.Parking;
import synechron_parking.com.synechron.parking.model.Vehicle;

/**
 * @author gourav
 *
 * @param <T>
 */
public class ParkingDataManagerImpl<T extends Vehicle> implements ParkingDataManager<T> {

	private Map<Integer, T> parkingSlotMap = new HashMap<Integer, T>();

	private Map<String, Integer> parkedVehicleMap = new HashMap<>();
	
	private List<Vehicle> vehicleList = new ArrayList<>();

	@SuppressWarnings("rawtypes")
	private static ParkingDataManagerImpl instance = null;
	
	private static Parking parking = null;

	private ParkingDataManagerImpl() {
	}

	@SuppressWarnings("unchecked")
	public static <T extends Vehicle> ParkingDataManagerImpl<T> getInstance(int capacity){
		if(instance == null) {
			synchronized (ParkingDataManagerImpl.class) {
				if(instance == null) {
					instance = new ParkingDataManagerImpl<T>();
					parking = Parking.getInstance(capacity);
				}
			}
		}
		return instance;
	}


	public int parkCar(T vehicle) {
		for(int i = 1; i < parking.getParkSlot().length; i++) {
			if(parking.getParkSlot()[i] == 0) {
				if(parkedVehicleMap.containsKey(vehicle.getRegistrationNumber())) {
					return -2;
				}
				parkingSlotMap.put(i, vehicle);
				parkedVehicleMap.put(vehicle.getRegistrationNumber(), i);
				parking.getParkSlot()[i] = 1;
				vehicle.setEntryDate(java.time.LocalDate.now());
				vehicle.setEntryTime(java.time.LocalTime.now());
				vehicle.setSlotNumber(i);
				vehicleList.add(vehicle);
				return i;
			}
		}
		return -1;
	}

	public int leaveCar(String registrationNumber) {
		if(parkedVehicleMap.containsKey(registrationNumber)) {
			int slotNumber = parkedVehicleMap.get(registrationNumber);
			parking.getParkSlot()[slotNumber] = 0;
			parkingSlotMap.remove(slotNumber);
			parkedVehicleMap.remove(registrationNumber);
			Vehicle localVehicle  = vehicleList.stream().filter(vehicle->registrationNumber.equals(vehicle.getRegistrationNumber())).findAny().orElse(null);
			localVehicle.setExitDate(java.time.LocalDate.now());
			localVehicle.setExitTime(java.time.LocalTime.now());
			return slotNumber;
		}
		return -1;
	}

	public void doCleanup()
	{
		parkingSlotMap.clear();
		parkedVehicleMap.clear();
		parking.setParkSlot(null);
		instance = null;
	}
	
	public Map<Integer, T> getStatus() {
		return getParkingSlotMap();
	}

	public int[] getParkSlot() {
		return parking.getParkSlot();
	}

	public Map<Integer, T> getParkingSlotMap() {
		return parkingSlotMap;
	}

	public Map<String, Integer> getParkedVehicleMap() {
		return parkedVehicleMap;
	}

	@Override
	public List<Vehicle> getVehicleListBetweenDates(LocalDate startLocalDate, LocalDate endLocalDate) {
		List<Vehicle> statusList = new ArrayList<>();
		for(Vehicle eachVehicle : vehicleList) {
			if(eachVehicle.getEntryDate().isAfter(startLocalDate) && eachVehicle.getEntryDate().isBefore(endLocalDate)){
				statusList.add(eachVehicle);
			}
		}
		return statusList;
	}

	@Override
	public List<Vehicle> getVehicleEntryDetails(String registrationNumber, LocalDate startLocalDate,
			LocalDate endLocalDate) {
		List<Vehicle> entryList = new ArrayList<>();
		for(Vehicle eachVehicle : vehicleList) {
			if(registrationNumber.equals(eachVehicle.getRegistrationNumber()) && eachVehicle.getEntryDate().isAfter(startLocalDate) && eachVehicle.getEntryDate().isBefore(endLocalDate)){
				entryList.add(eachVehicle);
			}
		}
		return entryList;
	}
}
