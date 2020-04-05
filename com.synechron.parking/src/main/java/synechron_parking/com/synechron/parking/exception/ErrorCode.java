package synechron_parking.com.synechron.parking.exception;

/**
 * @author gourav
 *
 */
public enum ErrorCode
{
	PARKING_ALREADY_EXIST("Sorry Parking Already Created, It CAN NOT be again recreated."), PROCESSING_ERROR("Processing Error "), INVALID_REQUEST("Invalid Request"), INVALID_FILE("Invalid File"), ;

	private String message = "";

	/**
	 * @param value
	 */
	private ErrorCode(String message)
	{
		this.message = message;
	}

	/**
	 * @return String
	 */
	public String getMessage()
	{
		return message;
	}
}
