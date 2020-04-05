Parking Lot Problem
====================================
There are two ways to run the executable bin/synechron_parking_1.0.0.jar
	1. Run command "java -jar synechron_parking_1.0.0.jar" from bin folder and follow the instructions.
	2. Create a file with set of commands and give the file as argument.
		for ex. java -jar synechron_parking_1.0.0.jar <<file path>>

Please find the commands below.
A) For creating parking lot of size n               ---> create_parking_lot {capacity}
	Example: create_parking_lot 500
	If you don't create a parking lot capacity, It will take 500 by default capacity
B) To park a car                                    ---> park <<car_number>> {car_color}
	Example: park KA-01-HH-1234
C) Remove(Unpark) car from parking                  ---> leave {slot_number}
	Example: leave KA-01-HH-1234
D) Print status of parking slot                     ---> current_status
	Example: current_status
D) Print status in particular date                  ---> status_with_date <<start date in format yyyy-mm-dd>> <<end date in format yyyy-mm-dd>>
	Example: status_with_date 2020-04-04 2020-04-08
D) Print status of vehicle in particular date       ---> status_of_vehicle_with_date <<car_number>> <<start date in format yyyy-mm-dd>> <<end date in format yyyy-mm-dd>>
	Example: status_of_vehicle_with_date KA-01-HH-1234 2020-04-04 2020-04-08

Depending on the input command the application will execute.

There is one file_inputs.txt file and synechron_parking_run_with_file_inputs.bat file in bin directory.
This batch file will take file_inputs.txt as input series of commands and gives the result in console.

If you need any more clarification, feel free to contact me at gkgourav409@gmail.com