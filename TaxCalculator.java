/*
 * Name: 			Stephen O' Driscoll
 * Student Number: 	R00146853
 * Class:			Comp1C-Y
 * Date:			22/02/2017
 * 
 * Tax Calculating Program - Assessment 1
 */

import java.util.Scanner;		//Needed for Scanner Class

public class TaxCalculator {
	
	static Scanner input; 		//Used by Various Methods

	public static void main(String[] args) {
		
		//Declare Initial Constants
		final double TAX_RATE=0.2;					//Fixed Tax Rate
		final String NAME = "Enter your name:";		//Declare String for Asking Users Name
		
		printWelcomeMessage();						//Display Welcome Message by Calling Method
		String userName=getText(NAME);				//Use Method getText to Ask User for their Name
		
		//Declare Strings for Asking User for their Weekly Income and Partners Name
		final String INCOME = userName+" enter your weekly income in €:";
		final String PARTNERS_NAME = userName+" enter the name of your partner:";
		
		double income=getPosDouble(INCOME);			//Use Method getPosDouble to Ask User for their Weekly Income
		
		input.nextLine();							//Clear Input
		String partnersName=getText(PARTNERS_NAME);	//Use Method getText to Ask User for Partners name
		
		//Declare String  for Asking User for Partners Weekly Income
		final String PARTNERS_INCOME = "Enter " + partnersName + "'s weekly income in €:";
		
		double partnersIncome=getPosDouble(PARTNERS_INCOME);	//Ask User for Partners Income
		
		//Calculate Gross Income for the Couple
		double grossIncome=income+partnersIncome;
		
		//Display Gross Income to the User
		System.out.println("\nThe gross income for those values is €" + grossIncome + ".");
		
		//Use Method netWeeklyIncome to Calculate their Net Weekly Income Using TAX_RATE
		double netWeeklyIncome=calculateNetWeeklyIncome(grossIncome,TAX_RATE);
		
		//Display Income for 1 Week, 1 Month and 1 Year to User
		displayNetIncomeOverPeriod(userName, partnersName, netWeeklyIncome, 1);
		displayNetIncomeOverPeriod(userName, partnersName, netWeeklyIncome, 4);
		displayNetIncomeOverPeriod(userName, partnersName, netWeeklyIncome, 52);
	}
	
	
	public static void printWelcomeMessage() {
		
		//Display Welcome Message
		System.out.println("*********************************");
		System.out.println("* Welcome to the Tax Calculator *");
		System.out.println("*********************************\n");
	}
	
	
	public static String getText(final String TEXT) {
		input = new Scanner(System.in);		//Open Scanner Class
		
		String name="";						//Declare name Variable
		int nameLength=0;					//Declare nameLenght Variable to Check name
		
		//While nameLength is 0 Input is Blank and Loop Will Run Again
		while (nameLength==0) {
			System.out.print(TEXT);			//Display Message Asking for name
			name=input.nextLine();			//Accept name
			nameLength=name.length();		//Find nameLength
			
			//If Input is Blank
			if (nameLength==0) {
				System.out.print("Must not be blank!");	//Display Error Message
			}
		}
		return name;						//Return Value for name
	}

	
	public static Double getPosDouble(final String TEXT) {
		input = new Scanner(System.in);		//Open Scanner Class
		
		double income=-1;					//Declare Variable Income and Set Negative
		
		//While Income is Negative Run Loop
		while (income<0) {
			System.out.print(TEXT);			//Display Message Asking for Weekly Income
			income=input.nextDouble();		//Accept Value for income
			
			//If income is Negative
			if (income<0) {
				System.out.print("Must not be negative!");	//Display Error Message
			}
		}
		return income;						//Return Value for income
	}
	
	
	public static Double calculateNetWeeklyIncome(double grossIncome, final double TAX_RATE) {
		
		//Calculate texPaid and netWeeklyIncome
		double taxPaid=grossIncome*TAX_RATE;
		double netWeeklyIncome=grossIncome-taxPaid;
		
		return netWeeklyIncome;				//Return Value for netWeeklyIncome
	}
	
	
	public static void displayNetIncomeOverPeriod(String userName, String partnersName, double netWeeklyIncome, int numberOfWeeks) {
		
		//Calculate Amount of Money Based on netWeeklyIncome and numberOfWeeks
		double income=netWeeklyIncome*numberOfWeeks;
		
		//Display Value in € to User
		System.out.println("Net income for "+numberOfWeeks+" weeks for "+userName+" & "+partnersName+" is: €"+income);
	}
}

