/*
 * Name: 			Stephen O' Driscoll
 * Student Number: 	R00146853
 * Class:			Comp1C-Y
 * Submit Date:		30/04/2017
 * 
 * GotoGym Application - Programming Assessment
 */

import java.io.*;
import java.util.Scanner;
public class GymProject {
	final static Scanner input = new Scanner(System.in);	//Open Scanner Class to be Used Throughout the Program
	
	final static int SIZE = 12;								//Max Number of Classes
	
	final static String[] TIMES   = {"7","8","9"};			//Times Available for Classes Every Day
	final static String[] DAYS  = {"Mon", "Tues", "Wed"};	//Days Available for Classes Every Day
	
	final static String[] instructorsList = new String[4];		//List of the Four Instructors
	final static String[] classTypesList = new String[SIZE];	//List of Class Types
	final static String[] classCodesList = new String[SIZE];	//List of Class Types Abbreviated
	
	static String[] classCodes = new String[SIZE];			//Stores a List of Class Codes for Each Class
	static String[] days = new String[SIZE];				//Stores a List of Days for Each Class
	static String[] startTimes = new String[SIZE];			//Stores a List of Start Times for Each Class
	static String[] instructors = new String[SIZE];			//Stores a List of Instructors for Each Class
	
	
	public static void main(String[] args) throws FileNotFoundException {

		printTitle("Classes");		//Print Title for Classes Page
		
		boolean exit = false;		//Set Boolean exit to False

		//Continue Running While Loop to display menu Until User Decides to Exit
		while (exit == false) {
			
			loadFilesToArrays();		//Load Files into Program at the Start of Every Loop
			
			System.out.println("1. Add a Class Session");
			System.out.println("2. Show Times all Classes");
			System.out.println("3. Show Instructor Payments Due");
			System.out.println("4. Print TimeTable for Instructor");
			System.out.println("5. Show Ordered TimeTable with Codes");
			System.out.println("6. Exit"); 

			int choice = readIntRange(1, 6);	//Accept Users Input as choice in Range 1-6
			
			switch (choice) {
			case 1:
				
				printTitle("Add a Class");	//Display Title for Add a Class Page
				addClassSession();			//1. Add a Class Session
				
				break;
			case 2:
				
				printTitle("Classes");		//Display Title for Classes Page
				showTimes();				//2. Show Times for All Classes
				
				break;
			case 3:
				
				printTitle("Payments Due");	//Display Title for Payments Due Page
				paymentsDue();				//3. Show Instructor Payments Due
				
				break;
			case 4:
				
				printTitle("Timetables");					//Display Title for Timetables Page
				String instructorName = instructorName();	//Ask for and Verify Instructors Name
				printInstructorsTimetable(instructorName);	//4. Print Timetable for Instructor
				
				break;
			case 5:
				
				printTitle("Ordered Timetable");	//Display Title for Ordered Timetable Page
				showOrderedTimetable();				//5. Show Ordered TimeTable with Codes
				
				break;
			case 6:
				
				exit = true;	//6. Set exit to true. Program Will Terminate After This Loop
				break;
			}
			System.out.println("\nPress Return to continue");	//Press Return to Advance back to Menu
			input.nextLine();									//Clear Input
		}
	}
	
	
	/**
	 * Method to Print Title of Each Page
	 * @param name - Name of Current Page
	 */
	private static void printTitle(String name) {
		
		//Display Application Name and Current Page
		System.out.println("\n" + "The Goto Gym " + name );
		System.out.println("-------------------------\n");
	}
	
	
	/**
	 * Method to Load and Organize Files to Arrays
	 * @throws FileNotFoundException
	 */
	public static void loadFilesToArrays() throws FileNotFoundException {
		
		File classDetailsFile = new File ("ClassDetails.txt");	//Open File ClassDetails.txt as classDetailsFile
		
		int classTypes = 0; 		//Variable to Count Number of Class Types
		
		//If ClassDetails.txt Doesn't Exist
		 if(!classDetailsFile.exists())
			 
	            System.out.println("Error, ClassDetails.txt Doesn't Exist");	//Display Error Message
	     
	     else {
	    	 Scanner file1 = new Scanner(classDetailsFile);		//Load classDetailsFile to file1

	    	 //For Loop to Copy Text to Correct Arrays as Long as there is Another Line of Text Available
	    	 for (int i=0;file1.hasNext(); i++) {
	    		 
	            classCodes[i] = file1.nextLine();	//Copy Line to classCodes Array
		    	days[i] = file1.nextLine();			//Copy Line to days Array
		    	startTimes[i] = file1.nextLine();	//Copy Line to startTimes Array
		    	instructors[i] = file1.nextLine();	//Copy Line to instructors Array
	        }
	    	 
	    	 file1.close();	//Close file1 - ClassDetails.txt
	     }
	    	 
	    	 File instructorsFile = new File ("instructors.txt");	//Open File instructors.txt as instructorsFile
	    	 
	    	 //If instructors.txt Doesn't Exist
	    	 if(!instructorsFile.exists())
	    		 
		            System.out.println("Error, Instructors.txt Doesn't Exist");	//Display Error Message
		       
		     else {
		    	 Scanner file2 = new Scanner(instructorsFile);	//Load instructorsFile to file2
		    	 
		    	 //For Loop to Copy Text to instructorsList Array as Long as there is Another Line of Text Available
		    	 for (int i=0; file2.hasNext(); i++)
		    		 
		    		 instructorsList[i] = file2.nextLine();	//Copy Line to instructorsList Array
		    	 
		    	 file2.close();	//Close file2 - instructors.txt
	     }
	    	 
	    	 File classTypesFile = new File ("ClassTypes.txt");	//Open File ClassType.txt as classTypesFile
	    	 
	    	 //If ClassTypes.txt Doesn't Exist
	    	 if(!classTypesFile.exists())
	    		 
		            System.out.println("Error, ClassTypes.txt Doesn't Exist");	//Display Error Message
	    	 
		     else {
		    	 Scanner file3 = new Scanner(classTypesFile);	//Load classTypesFile to file3
		    	 
		    	//For Loop to Copy Text to classTypesList Array as Long as there is Another Line of Text Available
		    	 for (; file3.hasNext(); classTypes++)
		    		 
		    		 classTypesList[classTypes] = file3.nextLine();	//Copy Line t classTypesList Array
		    	 
		    	 file3.close();	//Close file3 - ClassTypes.txt
	     }
	    	 
	    	 //For Loop to Abbreviate classTypesList Classes and Copy to classCodesList
	    	 for(int i=0; i < classTypes; i++) {
	    			 
	    	 //Abbreviate Classes in classTypesList to First Two Characters and Copy to ClassCodesList
	    	 classCodesList[i] = classTypesList[i].substring(0,2);
	    	 classCodesList[i] = classCodesList[i].toUpperCase();	//Capitalize Class
	    	 }
	}
	
	
	/**
	 * Method to Return Integer that is in a Certain Range
	 * @param min - Minimum Possible Value of Users Input
	 * @param max - Maximum Possible Value of Users Input
	 */
	public static int readIntRange(int min, int max) {
		
		int choice = 0;			//Set choice to 0
		boolean valid = false;	//Set boolean valid as false
		
		//While Loop to Run Whilst Users Input is Invalid
		while (!valid) {
			
			//While Loop to Run Whilst Input Received is not an Integer
			while (!input.hasNextInt()) {
				
				input.nextLine();								//Clear Input
				System.out.println("Please Enter an Integer");	//Display Error Message
			}
			choice = input.nextInt();	//Accept Integer
			input.nextLine();			//Clear Input
			
			//If Users Choice is Within the Required Range
			if (choice >= min && choice <= max)
				
				valid = true;	//Set valid to true
				
			else
				System.out.println("Please Enter a value between " + min + " and " + max);	//Display Error Message
		}
		return choice;	//Return Valid Choice
	}
	
	
	/* +--------------------------------------------+
	 * - Methods For Option 1 - Add a Class Session -
	 * +--------------------------------------------+
	 */
	
	/**
	 * Method to Add Class Session
	 * @throws FileNotFoundException
	 */
	public static void addClassSession() throws FileNotFoundException {
		
		int choice = 0;				//Set choice to 0 for Users Choice
		int inputLocation = -1;		//Set inputLocation to -1 to Search for Available Space in Arrays
		
		//For Loop to Check Array classCodes for Unused Space
		for (int i=0; i<SIZE && inputLocation == -1; i++) {
			
			//If classCodes Current Location is null
			if (classCodes[i] == null)
				
				inputLocation = i;		//Set inputLocation to Current Location
		}
		
		//If inputLocation is Less that 0
		if (inputLocation<0) {
			
			System.out.println("Max Number of Classes Reached");	//Display Error Message
		}
		else {
		
			String classCode, day, time, teacher;	//Strings to Store Users Choice of Class, Day, Time and Teacher
			boolean classOverlap = false;			//boolean to Test if New Class Overlaps Classes Already On
			int x = 0;
			
			System.out.println("Enter the Class Type: ");	//Request Class Type from Options Provided
			
			//For Loop to Display All Class Choices Until a null is Found
			for (; x<classTypesList.length && !(classTypesList[x] == null); x++)
				
				System.out.println((x+1) + ". " + classTypesList[x]);	//Display Current Class Option
		
			choice = readIntRange(1, x);			//Accept Users Input as choice in Range 1-x
			classCode = classCodesList[choice-1];	//Set classCode to Users Choice from Array classCodesList
		
			System.out.println("Enter the Day: ");			//Request Day from Options Provided
			System.out.println("1. Mon");
			System.out.println("2. Tues");
			System.out.println("3. Wed");
		
			choice = readIntRange(1, 3);			//Accept Users Input as choice in Range 1-3
			day = DAYS[choice-1];					//Set day to Users Choice from Array DAYS
		
			System.out.println("Choose a Time: ");			//Request Class Time from Options Provided
			System.out.println("1. 7 o'clock");
			System.out.println("2. 8 o'clock");
			System.out.println("3. 9 o'clock");
		
			choice = readIntRange(1, 3);			//Accept Users Input as choice in Range 1-3
			time = TIMES[choice-1];					//Set time to Users Choice from Array TIMES
		
			System.out.println("Choose a Teacher: ");		//Request Instructor from Options Provided
			System.out.println("1. Amanda");
			System.out.println("2. Gerry");
			System.out.println("3. Aidan");
			System.out.println("4. Frank");
		
			choice = readIntRange(1, 4);			//Accept Users Input as choice in Range 1-4
			teacher = instructorsList[choice-1];	//Set teacher to Users Choice from Array instructorsList
			
			//For Loop to Check if New Class Session Overlaps with Other Classes
			for (int i=0; i<SIZE; i++) {
				
				//If Day, Time and Teacher Entered Matches that of Another Class
				if (day.equals(days[i]) && time.equals(startTimes[i]) && teacher.equals(instructors[i])) {
					
					System.out.println(teacher + " Alraedy has a Class on " + day + " at " + time);	//Display Error Message
					classOverlap = true;		//Set classOverlap to true
				}
					
			}
			
			//If Day, Time and Teacher Entered Doesn't Match that of Any Other Class a New Class Session is Added
			if (classOverlap == false) {
				
				classCodes[inputLocation] = classCode;	//Add classCode for Users Choice to Array classCodes
				days[inputLocation] = day;				//Add Users Choice of day to Array days
				startTimes[inputLocation] = time;		//Add Users Choice of time to Array startTimes
				instructors[inputLocation] = teacher;	//Add Users Choice of Teacher to Array instructors
				
				saveClassDetails();						//Save New Class List
				
				//Print Confirmation Message that a New Class Session has Been Added
				System.out.println("\nAdded " + classCode + " on " + day + " at " + time + " with " + teacher);
			}
		}
	}
	
	
	/**
	 * Method to Save New Class Details Once a New Class Session has Been Added
	 * @throws FileNotFoundException
	 */
	public static void saveClassDetails() throws FileNotFoundException {
		
		//Open File ClassDetails.txt as outputFile for Saving Data in Current Arrays
		PrintWriter outputFile = new PrintWriter("ClassDetails.txt");
		
		//For Loop to Upload Data in Arrays classCodes, days, startTimes and instructors to ClassDetails.txt
		for (int i=0; i<SIZE; i++) {
			
			//If Current Location in Array classCodes isn't Empty Copy it to outputFile
			if (!(classCodes[i] == null)) {
				
				outputFile.println(classCodes[i]);	//Copy classCodes for Class Sessions
				outputFile.println(days[i]);		//Copy days for Class Sessions
				outputFile.println(startTimes[i]);	//Copy startTimes for Class Sessions
				outputFile.println(instructors[i]);	//Copy instructors for Class Sessions
			}
		}
		outputFile.close();		//Close outputFile
	}
	
	
	/* +-----------------------------------------------+
	 * - Methods For Option 2 - Show Times All Classes -
	 * +-----------------------------------------------+
	 */
	
	/**
	 * Method to Show Information for All Classes
	 */
	public static void showTimes() {
		
		//For Loop to Display Information for All Classes Until a null is Found
		for (int i=0; i<classTypesList.length && !(classTypesList[i] == null); i++)
			
			showTimesFor(classTypesList[i]);	//Show Details for Current Class
	}
	
	
	/**
	 * Method to Show Information for All Classes of a Specific Activity
	 * @param activity - Activity to Show Details for
	 */
	public static void showTimesFor(String activity) {
		
		boolean activityClass = false;	//Set activityClass to false
		
		//For Loop to Make Sure there is at Least One Class of the Activity
		for(int i=0; i < SIZE && activityClass == false && !(classCodes[i] == null); i++) {
			
			//If Current Location in classCodes equals activity a Class is Found
			if(classCodes[i].charAt(0) == activity.charAt(0))
				
				activityClass = true;	//Set activityClass to true
		}
		
		//If there is at Least One Class of this Activity
		if (activityClass == true) {
			
		System.out.println(activity);	//Display Name of Activity
		
			//For Loop to Find and Display Details for All Classes of this Activity
			for(int i=0; i < SIZE && !(classCodes[i] == null); i++) {
				
				//If Current Location in classCodes equals activity a Class is Found
				if(classCodes[i].charAt(0) == activity.charAt(0))
					
					//Display Details of Class Session Found
					System.out.println("\t" + classCodes[i] + "\t" + days[i] + " at " + startTimes[i]+ " with " + instructors[i]);
			}
		}
	}
	
	
	/* +-----------------------------------------------------+
	 * - Methods For Option 3 - Show Instructor Payments Due -
	 * +-----------------------------------------------------+
	 */
	
	/**
	 * Method to Show Payments Due for Instructors and Total Payment to All Instructors
	 */
	public static void paymentsDue() {
		
		int totalPayment = 0;	//Declare Variable for Total Payment
		
		//Display Headings of Each Column of Information
		System.out.println("Name\tClasses\tPay (Euros)");
		System.out.println("----\t-------\t-----------");
		
		//For Loop to Calculate Payment Due for Each Instructor Individually and Add to Running Total
		for (int i=0; i < instructorsList.length; i++) {
			
			totalPayment += paymentsforInstructor(instructorsList[i]);	//Show Payment for Instructor and Add to Total
		}
		
		System.out.println("\nTotal wages for this week: " + totalPayment + " Euros");	//Display Total Wages to User
	}

	
	/**
	 * Method to Show  and Return Payments Due for One Instructor
	 * @param instructor - Instructor to Show Payments for
	 * @return payment - Returns Instructors Wages for the Week
	 */
	public static int paymentsforInstructor(String instructor) {
		
		int instructorClasses = 0;	//Variable for Number of Instructors Classes
		int payment = 0;			//Variable for Payment Due to Instructor
		
		//For Loop to Check All Classes and Calculate Instructors Wages
		for (int i=0; i < SIZE; i++) {
			
			//If Current Location in Array instructors Matches instructor she/he is Teaching this Class
			if (instructor.equals(instructors[i])) {
				
				instructorClasses++;	//Increment instructorClasses by One
				
				//If the  Class Starts at 7 or 8 she/he gets €60
				if (startTimes[i].equals("7") || startTimes[i].equals("8"))
					
					payment += 60;		//Add 60 to Instructors Payment
				
				//Else the Class Starts at 9 and she/he gets €80
				else
					
					payment += 80;		//Add 80 to Instructors Payment
				
				//Instructor Receives €10 Extra for Every Class After the Second that they Teach in a Week
				if (instructorClasses>2)
					
					payment += 10;		//Add 10 to Instructor Payment
				
			}
		}
		//Display Details about Instructors Payment
		System.out.println(instructor + "\t" + instructorClasses + "\t" + payment);
		
		return payment;		//Return Instructors Payment
	}
	
	
	/* +-------------------------------------------------------+
	 * - Methods For Option 4 - Print Timetable for Instructor -
	 * +-------------------------------------------------------+
	 */
	
	/**
	 * Method to ask, Verify and Return Instructors Name
	 * @return - instructorsName
	 */
	public static String instructorName() {
		
		String instructorName = "";			//Set Variable instructorsName as Empty
		boolean instructorFound = false;	//Set boolean instructorFound to False
		
		//While Loop to Run Until Valid Instructor is Received; Whilst instructorFound is false
		while (instructorFound == false) {
			
			System.out.println("Enter Instructors Name: ");		//Ask User for Instructors Name
			instructorName = input.nextLine();					//Accept Input
			
			//For Loop to Check instructorsList for a Match
			for (int i=0; i<instructorsList.length; i++) {
				
				//If instructorName Equals Current Location of instructorsList a Match is Found
				if (instructorName.equalsIgnoreCase(instructorsList[i]))
					
					instructorFound = true;	//Set instructorFound to true
			}
			
			//If No Instructor was Found
			if (instructorFound == false)
				
				System.out.println("No Match Found!");	//Display Error Message
		}
		return instructorName;	//Return Instructors Name When Loop Ends
	}
		
	
	/**
	 * Method to Print Instructors Timetable to a File
	 * @param instructorName - Name of Instructor to Print Timetable for
	 * @throws FileNotFoundException
	 */
	public static void printInstructorsTimetable(String instructorName) throws FileNotFoundException {
		
		//Create or Open File as outputFile for Saving Data for Instructor's Classes in Current Arrays
		PrintWriter outputFile = new PrintWriter(instructorName + "sTimetable.txt");
		
		System.out.println(instructorName + "sTimetable.txt created.");	//Confirm to User that a File was Created
		System.out.println("Writing to file...\n");						//Confirm to User that Writing is Starting
		
		String classType = "";		//Declare Variable for classType
		
		//For Loop to Check Arrays and Print Relevant Details to Instructors Timetable
		for (int i=0; i<SIZE; i++) {
			
			//If instructorsName Equals Current Location of Array instructors
			if (instructorName.equalsIgnoreCase(instructors[i])) {
				
				//For Loop to Check classCodesList and Convert classCode to a classType
				for (int x=0; x<classCodesList.length; x++) {
					
					//If Current Location of classCodes Matches currentLocation of classCodesList
					if (classCodes[i].equals(classCodesList[x]))
						classType = classTypesList[x];	//Set classType to Current Location of classTypesList
					
				}
				outputFile.println(classType + "\t\t" + days[i] + " at " + startTimes[i]);	//Print Details to outputFile
				
				System.out.println(classType + "\t\t" + days[i] + " at " + startTimes[i]);	//Display Details to User
			}
		}
		 outputFile.close();	//Close outputFile
	}
	
	
	/* +----------------------------------------------------------+
	 * - Methods For Option 5 - Show Ordered Timetable with Codes -
	 * +----------------------------------------------------------+
	 */
	
	/**
	 * Method to Show Ordered Timetable with Codes
	 */
	public static void showOrderedTimetable() {
		
		//For Loop to Print a List of Days and Display Details for Every Day
		for (int i=0; i < DAYS.length; i++) {
			
			System.out.println(DAYS[i] + ":");	//Display Day at Current Location of List
			showTimetableForDay(DAYS[i]);		//Display Details for Day Provided
		}
	}
	
	
	/**
	 * Method to Show Timetable for Day Provided
	 * @param day - Day to Print Timetable for
	 */
	public static void showTimetableForDay(String day) {
		
		//For Loop to List Times of Classes Each Day and Details for those Classes
		for (int time = 0; time<=2; time++) {
			
			System.out.print("\t" + (time+7));	//Display Time of Class
			
			//For Loop to Display Classes that Start at the Time Being Checked
			for (int i=0; i<SIZE; i++) {
				
				//If day Entered Matches Array days and time Chose Matches Array startTimes at Current Location
				if (day.equals(days[i]) && TIMES[time].equals(startTimes[i]))
					System.out.print("\t" + classCodes[i]);		//Display Class Code for Class on this Day and at this Time
			}

			System.out.println();	//Insert Line Break
		}
	}
}