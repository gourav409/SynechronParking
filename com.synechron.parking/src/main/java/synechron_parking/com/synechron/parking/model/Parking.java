package synechron_parking.com.synechron.parking.model;

/**
 * @author gourav
 *
 */
public class Parking {
	
	private int[] parkSlot = null;
	
	private static Parking instance = null;
	
	private Parking() {
		
	}
	
	public static Parking getInstance(int capacity){
		if(instance == null) {
			synchronized (Parking.class) {
				if(instance == null) {
					instance = new Parking();
					instance.parkSlot = new int[capacity+1];
					for(int i = 0; i < capacity; i++) {
						instance.parkSlot[i] = 0;
					}
				}
			}
		}
		return instance;
	}

	private int capacity;

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int[] getParkSlot() {
		return parkSlot;
	}

	public void setParkSlot(int[] parkSlot) {
		this.parkSlot = parkSlot;
	}
	
}
