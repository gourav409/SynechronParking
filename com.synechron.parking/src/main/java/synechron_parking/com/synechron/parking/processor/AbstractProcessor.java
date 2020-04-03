package synechron_parking.com.synechron.parking.processor;

import java.util.Map;

import synechron_parking.com.synechron.parking.model.Vehicle;

public interface AbstractProcessor {
	
	public Map<Vehicle, Integer> getParkingDetail();

}
