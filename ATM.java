
// specify the package

// system imports


// project imports
import event.Event;
import event.EventLog;
import common.PropertyFile;

import model.Teller;


/** The class containing the main program  for the ATM application */
//==============================================================
public class ATM
{

	private static Teller myTeller;		// the main behavior for the application

	/** 
	 * The "main" entry point for the application. Carries out actions to
	 * set up the application
	 */
	//----------------------------------------------------------
    	public static void main(String[] args)
	{

    		// Create the top-level container (main frame) and add contents to it.
    		   System.out.println("CSC 429 Assignment 1");

    		   // Finish setting up the stage (ENABLE THE GUI TO BE CLOSED USING THE TOP RIGHT
    		   // 'X' IN THE WINDOW), and show it.
    	           try
    		   {
    			myTeller = new Teller();
    		   }
    		   catch(Exception exc)
    		   {
    			System.err.println("ATM.ATM - could not create Teller!");
    			new Event(Event.getLeafLevelClassName(null), "ATM.<init>", "Unable to create Teller object", Event.ERROR);
    			exc.printStackTrace();
    		   }
	}

}