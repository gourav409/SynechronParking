/**
 * 
 */
package synechron_parking.com.synechron.parking.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gourav
 *
 */
public class CommandInputMap
{
	private static volatile Map<String, Integer> commandsParameterMap = new HashMap<String, Integer>();

	static
	{
		commandsParameterMap.put(Constants.CREATE_PARKING_LOT, 1);
		commandsParameterMap.put(Constants.PARK, 1);
		commandsParameterMap.put(Constants.LEAVE, 1);
		commandsParameterMap.put(Constants.CURRENT_STATUS, 0);
		commandsParameterMap.put(Constants.STATUS_WITH_DATE, 2);
		commandsParameterMap.put(Constants.STATUS_OF_VEHICLE_WITH_DATE, 3);
	}

	/**
	 * @return the commandsParameterMap
	 */
	public static Map<String, Integer> getCommandsParameterMap()
	{
		return commandsParameterMap;
	}

	/**
	 * @param commandsParameterMap
	 *            the commandsParameterMap to set
	 */
	public static void addCommand(String command, int parameterCount)
	{
		commandsParameterMap.put(command, parameterCount);
	}

}
