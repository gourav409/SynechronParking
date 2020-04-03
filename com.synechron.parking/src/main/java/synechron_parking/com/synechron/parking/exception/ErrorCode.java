package synechron_parking.com.synechron.parking.exception;
public enum ErrorCode
{
	PROCESSING_ERROR("Processing Error "), INVALID_REQUEST("Invalid Request");
	
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
