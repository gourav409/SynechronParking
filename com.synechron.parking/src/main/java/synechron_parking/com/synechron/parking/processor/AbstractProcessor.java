package synechron_parking.com.synechron.parking.processor;

import synechron_parking.com.synechron.parking.constants.CommandInputMap;
import synechron_parking.com.synechron.parking.exception.ParkingException;
import synechron_parking.com.synechron.parking.service.AbstractService;

/**
 * @author gourav
 *
 */
public interface AbstractProcessor {

	public void setService(AbstractService service);

	public void execute(String action) throws ParkingException;

	public default boolean validate(String inputString)
	{
		boolean valid = true;
		try
		{
			String[] inputs = inputString.split(" ");
			int params = CommandInputMap.getCommandsParameterMap().get(inputs[0]);
			switch (inputs.length)
			{
			case 1:
				if (params != 0)
					valid = false;
				break;
			case 2:
				if (params != 1)
					valid = false;
				break;
			case 3:
				if (params != 2)
					valid = false;
				break;
			case 4:
				if (params != 3)
					valid = false;
				break;
			default:
				valid = false;
			}
		}
		catch (Exception e)
		{
			valid = false;
		}
		return valid;
	}

}
