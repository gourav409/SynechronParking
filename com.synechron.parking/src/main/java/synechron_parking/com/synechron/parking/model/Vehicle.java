package synechron_parking.com.synechron.parking.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author gourav
 *
 */
public class Vehicle {

	private String registrationNumber;
	private String color;
	private LocalDate entryDate;
	private LocalTime entryTime;
	private LocalDate exitDate;
	private LocalTime exitTime;
	private int slotNumber;
	
	public Vehicle(String registrationNumber) {
		super();
		this.registrationNumber = registrationNumber;
	}
	public Vehicle(String registrationNumber, String color) {
		super();
		this.registrationNumber = registrationNumber;
		this.color = color;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public LocalDate getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}
	public LocalTime getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(LocalTime entryTime) {
		this.entryTime = entryTime;
	}
	public LocalDate getExitDate() {
		return exitDate;
	}
	public void setExitDate(LocalDate exitDate) {
		this.exitDate = exitDate;
	}
	public LocalTime getExitTime() {
		return exitTime;
	}
	public void setExitTime(LocalTime exitTime) {
		this.exitTime = exitTime;
	}
	public int getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}
}
