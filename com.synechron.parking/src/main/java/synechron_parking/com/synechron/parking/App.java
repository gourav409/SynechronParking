package synechron_parking.com.synechron.parking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import synechron_parking.com.synechron.parking.exception.ErrorCode;
import synechron_parking.com.synechron.parking.exception.ParkingException;
import synechron_parking.com.synechron.parking.processor.AbstractProcessor;
import synechron_parking.com.synechron.parking.processor.RequestProcessor;
import synechron_parking.com.synechron.parking.service.impl.ParkingServiceImpl;

/**
 * @author gourav
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		@SuppressWarnings("rawtypes")
		AbstractProcessor processor = new RequestProcessor();
		processor.setService(new ParkingServiceImpl());
		BufferedReader bufferReader = null;
		String input = null;
		try
		{
			System.out.println("\n\n\n\n\n");
			System.out.println("===================================================================");
			System.out.println("===================     PARKING LOT     ====================");
			System.out.println("===================================================================");
			printUsage();
			switch (args.length)
			{
			case 0:
			{
				System.out.println("Please Enter 'exit' to end Execution");
				System.out.println("Input:");
				while (true)
				{
					try
					{
						bufferReader = new BufferedReader(new InputStreamReader(System.in));
						input = bufferReader.readLine().trim();
						if (input.equalsIgnoreCase("exit"))
						{
							break;
						}
						else
						{
							if (processor.validate(input))
							{
								try
								{
									processor.execute(input.trim());
								}
								catch (Exception e)
								{
									System.out.println(e.getMessage());
								}
							}
							else
							{
								printUsage();
							}
						}
					}
					catch (Exception e)
					{
						throw new ParkingException(ErrorCode.INVALID_REQUEST.getMessage(), e);
					}
				}
				break;
			}
			case 1:
			{
				File inputFile = new File(args[0]);
				try
				{
					bufferReader = new BufferedReader(new FileReader(inputFile));
					int lineNo = 1;
					while ((input = bufferReader.readLine()) != null)
					{
						input = input.trim();
						if (processor.validate(input))
						{
							try
							{
								processor.execute(input);
							}
							catch (Exception e)
							{
								System.out.println(e.getMessage());
							}
						}
						else
							System.out.println("Incorrect Command Found at line: " + lineNo + " ,Input: " + input);
						lineNo++;
					}
				}
				catch (Exception e)
				{
					throw new ParkingException(ErrorCode.INVALID_FILE.getMessage(), e);
				}
				break;
			}
			default:
				System.out.println("Invalid input. Usage Style: java -jar <jar_file_path> <input_file_path>");
			}
		}
		catch (ParkingException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally
		{
			try
			{
				if (bufferReader != null)
					bufferReader.close();
			}
			catch (IOException e)
			{
			}
		}
	}

	private static void printUsage()
	{
		StringBuffer buffer = new StringBuffer();
		buffer = buffer.append(
				"--------------Please Enter one of the below commands. <<variable>> to be replaced -----------------------")
				.append("\n");
		buffer = buffer.append("A) For creating parking lot of size n               ---> create_parking_lot {capacity}")
				.append("\n");
		buffer = buffer
				.append("B) To park a car                                    ---> park <<car_number>> {car_color}")
				.append("\n");
		buffer = buffer.append("C) Remove(Unpark) car from parking                  ---> leave {slot_number}")
				.append("\n");
		buffer = buffer.append("D) Print status of parking slot                     ---> current_status").append("\n");
		buffer = buffer.append("D) Print status in particular date                  ---> status_with_date <<start date in format yyyy-mm-dd>> <<end date in format yyyy-mm-dd>>").append("\n");
		buffer = buffer.append("D) Print status of vehicle in particular date       ---> status_of_vehicle_with_date <<car_number>> <<start date in format yyyy-mm-dd>> <<end date in format yyyy-mm-dd>>").append("\n");
		System.out.println(buffer.toString());
	}
}
