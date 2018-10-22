/*
 * Name: 			Stephen O' Driscoll
 * Student Number: 	R00146853
 * Class:			Comp1C-Y
 * Date:			22/03/2017
 * 
 * Annual Weather Report Program - Assessment 2
 */

public class Rainfall {

	public static void main(String[] args) {
		
		//Names of Months in Chronological Order
		final String[] MONTH_NAMES = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		
		int[] monthlyRainFalls = { 200, 150, 100, 220, 100, 50, 0, 10, 60, 100, 110, 150 };		// Monthly Rainfall in mm
		double[] monthlyTemperatures = { 5, -2.5, 5, 10.1, 14, 14, 16, 20, 16, 12.3, 5, 2 };	// Monthly Average Daily Temperatures
		int[] monthlyWinds = { 50, 40, 20, 20, 10, 5, 10, 18, 3, 10, 11, 10 };					// Monthly Winds in m/s
		
		
		//Opening Message to Greet User
		System.out.println("*************************");
		System.out.println("* Annual Weather Report *");
		System.out.println("*************************\n");
		
		//Calling Method printAnnualWeatherReport and Sending Parameters for Monthly Names, Rainfall in mm, Wind Speeds in m/s and Temperatures
		printAnnualWeatherReport(MONTH_NAMES, monthlyRainFalls, monthlyWinds, monthlyTemperatures);
		
		
		//Calling Method findColdestMonth with Parameter for Monthly Temperatures to return a Number to Represent Coldest Months Position in List
		int coldestMonthIndex = findColdestMonth (monthlyTemperatures);
		
		//Using coldestMonthIndex Value to Display Coldest Month With the Following Details
		System.out.println("\n\nThe coldest month was:");
		System.out.println("\t" + MONTH_NAMES[coldestMonthIndex]);							//Display Month Name
		System.out.println("\tTemperature: " + monthlyTemperatures[coldestMonthIndex]);		//Display Month Temperature
		System.out.println("\tWind: " + monthlyWinds[coldestMonthIndex] + "m/s");			//Display Month Wind Speed in m/s
		System.out.println("\tRainfall: " + monthlyRainFalls[coldestMonthIndex] + "mm");	//Display Month Rainfall in mm
		
		
		//Calling Method printRainfallForMonth to Search a List of Months for an Entered Month Name and Display Rainfall in mm for that Month if a Match is Found
		//Sends Parameters for a Month to Be Searched for, List of Monthly Rainfall Values and List of Month Names
		printRainfallForMonth("Jun", monthlyRainFalls, MONTH_NAMES);
	}
	
	
	
	//Method to Display Annual Weather Report in Table Format. Receives Parameters for Monthly Names, Rainfall in mm, Wind Speeds in m/s and Temperatures
	public static void printAnnualWeatherReport (String[] MONTH_NAMES, int[] monthlyRainFalls, int[] monthlyWinds, double[] monthlyTemperatures) {
		
		int totalAnnualRainfall = 0;					//Running Total of Annual Rainfall to be Incremented As We Go
		double averageAnnualRainfall = 0;				//Average Rainfall to be Calculated Later
		int numberOfMonths = MONTH_NAMES.length;		//Value for Number of Months
		
		//Display Table Column Labels for Organisation
		System.out.println("Month\tRain\tTemp.\tWind");
		
		//For Loop to Display Monthly Information Row by Row
		for (int x=0; x<numberOfMonths; x++) {
			System.out.println(MONTH_NAMES[x] + "\t" + monthlyRainFalls[x] + "\t" + monthlyTemperatures[x] + "\t" + monthlyWinds[x]);	//Displaying Information
			
			totalAnnualRainfall += monthlyRainFalls[x];		//Running Total of Annual Rainfall
		}
		
		averageAnnualRainfall = totalAnnualRainfall/numberOfMonths;		//Calculating Annual Average Rainfall based on Total Rainfall and Number of Months
		
		System.out.println("\nTotal Annual Rainfall: " + totalAnnualRainfall);			//Displaying Value for Total Annual Rainfall
		
		System.out.println("Average Monthly Rainfal: " + averageAnnualRainfall);		//Displaying Value for Average Monthly Rainfall
	}
	
	
	
	//Method to Return a Value for Coldest Month this Year. Receives Parameter for List of Monthly Temperatures
	public static int findColdestMonth (double[] monthlyTemperatures) {
		
		double coldestMonth = 0;					//Value to Check Against Temperatures As We Go
		int coldestMonthIndex = 0;					//Month Index Value to Return
		int months = monthlyTemperatures.length;	//Value for Number of Months
		
		//For Loop to Check Which Month was Coldest
		for (int y=0; y<months; y++) {
			
			//If Month Being Checked is Colder than the Coldest Month So Far or If this is the First Month to Be Checked
			if (coldestMonth>monthlyTemperatures[y] || y==0) {
				
				coldestMonth = monthlyTemperatures[y];		//Replace Old Coldest Months Temperature with New Coldest Months Temperature
				coldestMonthIndex = y;						//Replace the Index of Old Coldest Month with the Index of the New Coldest Month
			}
			
			//If Month Being Checked isn't Colder No Values Change
		}
		
		return coldestMonthIndex;		//Return an Index value for the Coldest Month this Year
	}
	
	
	
	//Method to Search Through a List of Months for an Entered Month and Display Rainfall for that Month if a Match is Found
	//Receives Parameters for a Month Name to be Searched, List of Rainfall Values for Each Month and List of Months
	public static void printRainfallForMonth (String monthName, int[] monthlyRainFalls, String[] MONTH_NAMES) {
		
		int months = MONTH_NAMES.length;		//Value for Number of Months
		int search = -1;						//Value for Searching List of Months. Marks Index of Month if Match is Found
		
		//For Loop to Check Each Month as We Go and Mark Location of Month if a Match is Found
		for (int z=0; z<months; z++) {
			
			//If Month Name Entered Equals to Month in List Ignoring Case
			if (monthName.equalsIgnoreCase(MONTH_NAMES[z])) {
				
				search = z;		//Mark Index of Matched Month
			}
		}
		
		//If search is Positive a Match was Found So We Display Details
		if (search>0) {
			
			//Display Month Name and amount of Rainfall in mm
			System.out.println("\n\n\tIn " + monthName + " there was " + monthlyRainFalls[search] + "mm rain.");
		}
		
		//If search is Negative no Match was Found So We Display a Message Informing User
		else {
			System.out.println("\n\n\tmonth " + monthName + " not found");	//Display Message to User that No Month was Found
		}
	}
	
}
