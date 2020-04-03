package synechron_parking.com.synechron.parking.processor;

import java.util.Map;

import synechron_parking.com.synechron.parking.dao.impl.ParkingDataManagerImpl;
import synechron_parking.com.synechron.parking.model.Vehicle;

public class RequestProcessor<T extends Vehicle> implements AbstractProcessor {

	public RequestProcessor() {
		// TODO Auto-generated constructor stub
	}

	public Map<Vehicle, Integer> getParkingDetail() {
		ParkingDataManagerImpl parkingDataManager = ParkingDataManagerImpl.getInstance();
		System.out.println("Vehicle\t\tParked Slot");
		System.out.println("================================================");
		parkingDataManager.getParkingMap().forEach((k,v) -> 
		System.out.println(((Vehicle)k).getRegistrationNumber()+"\t\t"+v));
		return null;
	}

}
