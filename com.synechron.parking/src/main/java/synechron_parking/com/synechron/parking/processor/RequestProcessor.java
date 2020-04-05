package synechron_parking.com.synechron.parking.processor;

import synechron_parking.com.synechron.parking.constants.Constants;
import synechron_parking.com.synechron.parking.exception.ErrorCode;
import synechron_parking.com.synechron.parking.exception.ParkingException;
import synechron_parking.com.synechron.parking.model.Car;
import synechron_parking.com.synechron.parking.model.Vehicle;
import synechron_parking.com.synechron.parking.service.AbstractService;
import synechron_parking.com.synechron.parking.service.ParkingService;

/**
 * @author gourav
 *
 * @param <T>
 */
public class RequestProcessor<T extends Vehicle> implements AbstractProcessor {
	private ParkingService parkingService;

	public void setParkingService(ParkingService parkingService) throws ParkingException
	{
		this.parkingService = parkingService;
	}

	@Override
	public void execute(String input) throws ParkingException
	{
		String[] inputs = input.split(" ");
		String key = inputs[0];
		switch (key)
		{
		case Constants.CREATE_PARKING_LOT:
			try
			{
				int capacity = Integer.parseInt(inputs[1]);
				parkingService.createParkingLot(capacity);
			}
			catch (NumberFormatException e)
			{
				throw new ParkingException(ErrorCode.INVALID_REQUEST.getMessage().replace("{variable}", "capacity"));
			}
			break;
		case Constants.PARK:
			parkingService.park(new Car(inputs[1]));
			break;
		case Constants.LEAVE:
			try
			{
				String regNumber = inputs[1];
				parkingService.unPark(regNumber);
			}
			catch (NumberFormatException e)
			{
				throw new ParkingException(
						ErrorCode.INVALID_REQUEST.getMessage().replace("{variable}", "slot_number"));
			}
			break;
		case Constants.CURRENT_STATUS:
			parkingService.getStatus();
			break;
		case Constants.STATUS_WITH_DATE:
			parkingService.getStatusWithDate(inputs[1], inputs[2]);;
			break;
		case Constants.STATUS_OF_VEHICLE_WITH_DATE:
			parkingService.getStatusOfVehicle(inputs[1], inputs[2], inputs[3]);
			break;
		default:
			break;
		}
	}

	@Override
	public void setService(AbstractService service)
	{
		this.parkingService = (ParkingService) service;
	}

}
