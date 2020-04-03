package synechron_parking.com.synechron.parking.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import synechron_parking.com.synechron.parking.dao.ParkingDataManager;
import synechron_parking.com.synechron.parking.model.Vehicle;

public class ParkingDataManagerImpl<T extends Vehicle> implements ParkingDataManager<T> {

	private int[] parkSlot = new int[500];
	
	private Map<T, Integer> parkingMap = new HashMap<T, Integer>();
	
	private static ParkingDataManagerImpl instance = null;
	
	private ParkingDataManagerImpl() {
	}

	public static <T extends Vehicle> ParkingDataManagerImpl<T> getInstance(){
		if(instance == null) {
			synchronized (ParkingDataManagerImpl.class) {
				if(instance == null) {
					instance = new ParkingDataManagerImpl<T>();
					for(int i = 0; i < 500; i++) {
						instance.parkSlot[i] = 0;
					}
				}
			}
		}
		return instance;
	}
	

	public int parkCar(T vehicle) {
		for(int i = 0; i < parkSlot.length; i++) {
			if(parkSlot[i] == 0) {
				if(parkingMap.containsKey(vehicle)) {
					return -2;
				}
				parkingMap.put(vehicle, i);
				parkSlot[i] = 1;
				return i;
			}
		}
		return -1;
	}

	public int leaveCar(Vehicle vehicle) {
		if(parkingMap.containsKey(vehicle)) {
			int slotNumber = parkingMap.get(vehicle);
			parkSlot[slotNumber] = 0;
			parkingMap.remove(vehicle);
			return slotNumber;
		}
		return -1;
	}

	public List<String> getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] getParkSlot() {
		return parkSlot;
	}

	public Map<T, Integer> getParkingMap() {
		return parkingMap;
	}

}
