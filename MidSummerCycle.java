/*
 * Name: 			Stephen O' Driscoll
 * Student Number: 	R00146853
 * Class:			Comp1C-Y
 * Date:			12/05/2017
 * 
 * MidSummerCycle - Final Programming Class Assessment
 */
import java.io.*;
import java.util.Scanner;

public class MidSummerCycle {
	final static int MAX = 10;
	final static int SHORT_DISTANCE = 50;
	final static int LONG_DISTANCE = 100;
	final static String TITLE = "o^o' Mid-Summer Cycle Race 2017 o^o'";
	final static Scanner input = new Scanner(System.in);
	
	//Declare Variables for Storing Cyclists Information
	static int[] distanceChosen = { 50, 100, 50, 100, 100, 0,0,0,0,0 };
	static int[] times = { 210, 390, 190, 460, 433, 0, 0, 0, 0, 0 };
	static String[] names = { "Adam", "Orla", "Jane", "Cindy", "Frank", "", "", "", "" , ""};

	/**
	 * Method to Print Title of Application
	 */
	public static void printTitle() {
		System.out.println(TITLE);		//Display Application Title
		System.out.println("------------------------------------\n");	//Display Underline

	}

	public static void main(String[] args) throws IOException {

		int numberOfCompetitors= 5;	//Set numberOfCompetitors to 5
		int choice = -1;			//Set  User Choice to -1

		// Do While Loop to Run Until Option 6 is Selected
		do {
			//Display Title and Main Menu to User
			printTitle();
			System.out.println("1.  Display a List of Competitors");
			System.out.println("2.  Print the Number of Competitors in the 100km Race");
			System.out.println("3.  Calculate and Print Funds Raised");
			System.out.println("4.  Add a Competitor");
			System.out.println("5.  Create a Certificate");
			System.out.println("6.  Exit");

			//While Loop to Run Until an Integer is Received
			while (!input.hasNextInt()) {
				input.nextLine(); 		//Clear Input
				System.out.println("Enter a number from 1 - 6");	//Prompt User to Enter a Number Between 1 & 6
			}
			choice = readIntRange(1, 6);	//Method to Return an Integer Between 1 & 6
			
			switch (choice) {
			case 1:
				printTitle();		//Display Title to User
				
				//Method to Display a List of Competitors
				displayListOfCompetitors(numberOfCompetitors);
				
				break;
			
			case 2:
				printTitle();		//Display Title to User
				
				//Method to Print the Number of Competitors in the 100km Race
				int LongDistanceCyclists = getNumberFor100kmRace(numberOfCompetitors);
				
				//Display Number of Competitors in the 100km Race to User
				System.out.println("There are " + LongDistanceCyclists + " competitors entered in the 100km race.");
				
				break;
			case 3:
				printTitle();		//Display Title to User
				
				//Method to Calculate and Print Funds Raised
				calculateAndPrintFundsRaised(numberOfCompetitors);
				
				break;
			case 4:
				printTitle();//Display Title to User
				
				//Method to  Add a Competitor
				numberOfCompetitors = addCompetitor(numberOfCompetitors);

				break;
			case 5:
				printTitle();		//Display Title to User
				
				//Method to Create a Certificate
				createCertificate();
				break;

			}

			//Press Return to Return to Main Menu
			if ((choice >= 1) && (choice <= 5)) {
				System.out.println("\nPress Return to continue");
				input.nextLine();
			} else if (choice != 6) {
				System.out.println("\nPress Return to continue");
				input.nextLine();
			}
		} while (choice != 6);

		System.out.println("Exiting....");	//Display to User that Program is Exiting

		input.close(); 		//Close Scanner
	}

	
	/**
	 * Method to Ask Question, Read & Return Integer that is in a Certain Range
	 * @param min - Minimum Possible Value of Users Input
	 * @param max - Maximum Possible Value of Users Input
	 * @return choice - Final Option Picked By User
	 */
	public static int readIntRange(int min, int max) {
		
		int choice = 0;			//Set choice to 0
		boolean valid = false;	//Set Boolean valid as false
		
		//While Loop to Run Whilst Users Input is Invalid
		while (!valid) {
			
			//While Loop to Run Whilst Input Received is not an Integer
			while (!input.hasNextInt()) {
				
				input.nextLine();										//Clear Input
				System.out.println("ERROR - Enter a  whole number");	//Display Error Message
			}
			choice = input.nextInt();		//Accept input as Integer in choice
			input.nextLine();				//Clear Input
			
			//If Users Choice is Within the Required Range
			if (choice >= min && choice <= max)
				valid = true;	//Set valid to true
				
			//Else Users Choice is Not Within the Required Range
			else
				System.out.println("ERROR - Enter a whole number between " + min + " & " + max);	//Display Error Message
		}
		return choice;	//Return Valid Choice
	}
	
	
	/**
	 * Method to Display List of Competitors to User
	 */
	public static void displayListOfCompetitors(int numberOfCompetitors) {

		String distance = "";	//Declare Variable for Distances Short or Long
		
		//For Loop to Run Until numberOfCompetitors is Reached
		for (int i=0; i<numberOfCompetitors; i++) {
			
			//If Distance Chosen is Short
			if (distanceChosen[i] == SHORT_DISTANCE)
				distance = "Short";	//Change distance to Short
			
			//Else Distance Chosen is Long
			else
				distance = "Long";	//Change distance to Long
			
			System.out.println((i+1) + ".\t" + names[i] + "\t" + distance);	//Display Data to User
		}
	}

	
	/**
	 * Method to Print the Number of Competitors in the 100km Race
	 * @param numberOfCompetitors - Number of Current Competitors
	 * @return longDistanceCyclists - Number of Long Distance Cyclists
	 */
	public static int getNumberFor100kmRace (int numberOfCompetitors) {
		
		int longDistanceCyclists = 0;	//Declare Variable for Number of Long Distance Cyclists
		
		//For Loop to Run Until numberOfCompetitors is Reached
		for (int i=0; i<numberOfCompetitors; i++) {
			
			//If distanceChosen is Long
			if (distanceChosen[i] == LONG_DISTANCE)
				longDistanceCyclists ++;	//Add One to longDistanceCyclists
		
			//Else do Nothing
		}
		return longDistanceCyclists;	//Return longDistanceCyclists
	}
	
	
	/**
	 * Method to Calculate and Print Funds Raised
	 * @param numberOfCompetitors - Number of Current Competitors
	 */
	public static void calculateAndPrintFundsRaised(int numberOfCompetitors) {
		
		double totalFunds = 0;		//Declare Variable for totalFunds
		double averageFunds = 0;	//Declare Variable for averageFundsS
		
		for (int i=0; i<numberOfCompetitors; i++) {
			
			if (distanceChosen[i] == SHORT_DISTANCE)
				totalFunds += SHORT_DISTANCE*2.50;
			
			else
				totalFunds += LONG_DISTANCE*2.50;
		}
		averageFunds = totalFunds/numberOfCompetitors;
		
		System.out.println("The total amount of money raised was " + totalFunds);
		System.out.println("The average amount of money raised per cyclist was " + averageFunds);
	}
	
	
	
	public static int addCompetitor(int numberOfCompetitors) {
		
		if (numberOfCompetitors == MAX)
			System.out.println("Error - Max number of classes reached");
		
		else {
			String name;
			int distance = 0;
			int time;
			
			System.out.println("Please enter a name:");
			name = input.nextLine();
		
			while (distance != SHORT_DISTANCE && distance != LONG_DISTANCE) {
				distance = readInt("Please enter the distance chosen (" + SHORT_DISTANCE + " or " + LONG_DISTANCE + "):");
			
			}
			time = readInt("Please enter the time taken (in minutes):");
			
			names[numberOfCompetitors] = name;
			distanceChosen[numberOfCompetitors] = distance;
			times[numberOfCompetitors] = time;
			numberOfCompetitors ++;
			
			System.out.println(name + " has been added to the list.");
			System.out.println("There are now " + numberOfCompetitors + " competitors.");
		}
		return numberOfCompetitors;
	}
	
	
	
	public static void createCertificate() throws FileNotFoundException {
		
		String name;
		int result;
		
		System.out.println("Enter first name: ");
		name = input.nextLine();
		
		result = searchStringArray(names, name);
		
		if(result<0)
			System.out.println("Error - " + name + "was not found.");
		
		else {
			PrintWriter outputFile = new PrintWriter(name+ "sCert.txt");
			
			System.out.println("The contents of the file are:\n");
			System.out.println("************************************");
			System.out.println(TITLE);
			System.out.println("------------------------------------\n");
			System.out.println(name + " completed the " + distanceChosen[result] + "km cycle.");
			System.out.println(name + "'s time was " + times[result] + " minutes.\n");
			System.out.println("************************************");
			
			outputFile.println("************************************");
			outputFile.println(TITLE);
			outputFile.println("------------------------------------\n");
			outputFile.println(name + " completed the " + distanceChosen[result] + "km cycle.");
			outputFile.println(name + "'s time was " + times[result] + " minutes.\n");
			outputFile.println("************************************");
			
			outputFile.close();
			
		}
			
		
	}
	
	/**
     * Method to Ask Question, Read and Return Integer
     * @param question - Question Asked
     * @return choice - Integer User Entered
     */
    public static int readInt(String question) {
    	
        int choice;		//Declare choice

        System.out.println("\n" + question);	//Display question to User
        
        //While Loop to Run Until input is an Integer
        while (!input.hasNextInt()) {
        	
            input.nextLine();									//Clear Input
            System.out.println("ERROR - Enter a whole number");	//Display Error Message
            System.out.println("\n" + question);				//Display question to User
        }
        choice = input.nextInt();			//Store input as Integer in choice
        input.nextLine();					//Clear Input
        
        return choice;	//Return choice
    }
    
    
    /**
	 * Method to Search for a Value in a String Array and Return its Index
	*@param array   - array to Search
	*@param value   - value to Search for 
	*@return index - Index of value in array			*/
	public static int searchStringArray(String[] array, String value) { 

		int index = -1;			//Declare index and Set to -1
		boolean found = false;	//Declare found and Set as false

		//For Loop to Run Until Match is Found or Search Finishes
	    for (int i=0; !found && i < array.length; i++) {
		    
	    	//If Current Location in array isn't null and Matches value
	    	if (!(array[i] == null) && array[i].equalsIgnoreCase(value)) {
		    		
		    	index = i;		//Set index as Current Location
		    	found = true;	//Set found as true
		    }
		}
		return index;	//Return Location in array if Found or -1 if no Match Found
	}
}