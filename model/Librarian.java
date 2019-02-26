// specify the package
package model;

// system imports
import java.util.Hashtable;
import java.util.Properties;
import java.util.Scanner;

import javafx.stage.Stage;
import userInterface.MainStageContainer;
import userInterface.View;
import userInterface.ViewFactory;
import userInterface.WindowPosition;
import javafx.scene.Scene;

// project imports
import impresario.IModel;
import impresario.ISlideShow;
import impresario.IView;
import impresario.ModelRegistry;

import exception.InvalidPrimaryKeyException;
import exception.PasswordMismatchException;
import event.Event;

/** The class containing the Teller  for the ATM application */
//==============================================================
public class Librarian implements IView, IModel
// This class implements all these interfaces (and does NOT extend 'EntityBase')
// because it does NOT play the role of accessing the back-end database tables.
// It only plays a front-end role. 'EntityBase' objects play both roles.
{
	// For Impresario
	private Properties dependencies;
	private ModelRegistry myRegistry;
	
	// GUI Components
		private Hashtable<String, Scene> myViews;
		private Stage	  	myStage;

	// constructor for this class
	//----------------------------------------------------------
	public Librarian()
	{
		myStage = MainStageContainer.getInstance();
		myViews = new Hashtable<String, Scene>();

		// STEP 3.1: Create the Registry object - if you inherit from
		// EntityBase, this is done for you. Otherwise, you do it yourself
		myRegistry = new ModelRegistry("Teller");
		if(myRegistry == null)
		{
			new Event(Event.getLeafLevelClassName(this), "Teller",
				"Could not instantiate Registry", Event.ERROR);
		}

		// STEP 3.2: Be sure to set the dependencies correctly
		setDependencies();

		// Set up the initial view
		createAndShowLibrarianView();
	}

	private void createAndShowLibrarianView() {
		Scene currentScene = (Scene)myViews.get("TellerView");

		if (currentScene == null)
		{
			// create our initial view
			View newView = ViewFactory.createView("LibrarianView", this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("TellerView", currentScene);
		}
				
		swapToView(currentScene);
		
	}

	//-----------------------------------------------------------------------------------
	private void setDependencies()
	{
		dependencies = new Properties();
		dependencies.setProperty("Login", "LoginError");
		dependencies.setProperty("Deposit", "TransactionError");
		dependencies.setProperty("Withdraw", "TransactionError");
		dependencies.setProperty("Transfer", "TransactionError");
		dependencies.setProperty("BalanceInquiry", "TransactionError");
		dependencies.setProperty("ImposeServiceCharge", "TransactionError");

		myRegistry.setDependencies(dependencies);
	}

	/**
	 * Method called from client to get the value of a particular field
	 * held by the objects encapsulated by this object.
	 *
	 * @param	key	Name of database column (field) for which the client wants the value
	 *
	 * @return	Value associated with the field
	 */
	//----------------------------------------------------------

	//----------------------------------------------------------------
	public void stateChangeRequest()
	{
		// STEP 4: Write the sCR method component for the key you
		// just set up dependencies for
		// DEBUG System.out.println("Teller.sCR: key = " + key);
		int selection;
		do {
		System.out.println("Enter the number of the task or any other number to exit:"
				+ "\n1. Enter new book"
				+ "\n2. Insert new patron"
				+ "\n3. Search for books with title"
				+ "\n4. Print all released in a year or prior"
				+ "\n5. Display all patron younger then a given date"
				+ "\n6. Display all patrons in a zip code");
		Scanner sc= new Scanner(System.in);
		selection= Integer.parseInt(sc.nextLine());
		if(selection==1) {
			Properties props = new Properties();
			System.out.println("Enter author: ");
			String author=sc.nextLine();
			props.put("author", author);
			System.out.println("Enter title: ");
			String title=sc.nextLine();
			props.put("title", title);
			System.out.println("Enter Publication Year: ");
			String pubYear=sc.nextLine();
			props.put("pubYear", pubYear);
			System.out.println("Enter status: ");
			String status=sc.nextLine();
			props.put("status", status);
			Properties schema = new Properties();
			schema.put("TableName", "books");
			try {
				new InsertNewBook(props);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		else if(selection==2) {
			Properties props = new Properties();
			System.out.println("Enter Name:");
			String name=sc.nextLine();
			props.put("name",name);
			System.out.println("Enter Address:");
			String address=sc.nextLine();
			props.put("address",address);
			System.out.println("Enter City:");
			String city=sc.nextLine();
			props.put("city",city);
			System.out.println("Enter 2 letter state code :");
			String stateCode=sc.nextLine();
			props.put("stateCode",stateCode);
			System.out.println("Enter ZIP: ");
			String zip=sc.nextLine();
			props.put("zip",zip);
			System.out.println("Enter email: ");
			String email=sc.nextLine();
			props.put("email",email);
			System.out.println("Enter date of birth (YYYY-MM-DD: ");
			String dateOfBirth=sc.nextLine();
			props.put("dateOfBirth",dateOfBirth);
			String status="Active";
			props.put("status",status);
			try {
				new InsertPatron(props);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(selection==3) {
			System.out.println("Please enter part of book title: ");
			String title=sc.next();
			try {
				new BookCatalog(title);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(selection==4) {
			System.out.println("Please enter book release year: ");
			String year=sc.next();
			try {
				new BookReleaseYear(year);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(selection==5) {
			System.out.println("Enter a date (YYYY-MM-DD):");
			String date = sc.nextLine();
			try {
				new PatronYounger(date);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if(selection==6) {
			System.out.println("Please enter a zip code");
			String zip=sc.next();
			try {
				new PatronZipCollection(zip);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			}while(selection!=0 || selection>6);
	}

	/** Called via the IView relationship */
	//----------------------------------------------------------
	public void updateState(String key, Object value)
	{
		// DEBUG System.out.println("Teller.updateState: key: " + key);

		stateChangeRequest(key, value);
	}



	/** Register objects to receive state updates. */
	//----------------------------------------------------------
	public void subscribe(String key, IView subscriber)
	{
		// DEBUG: System.out.println("Cager[" + myTableName + "].subscribe");
		// forward to our registry
		myRegistry.subscribe(key, subscriber);
	}

	/** Unregister previously registered objects. */
	//----------------------------------------------------------
	public void unSubscribe(String key, IView subscriber)
	{
		// DEBUG: System.out.println("Cager.unSubscribe");
		// forward to our registry
		myRegistry.unSubscribe(key, subscriber);
	}

	@Override
	public Object getState(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stateChangeRequest(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	public void swapToView(Scene newScene)
	{

		
		if (newScene == null)
		{
			System.out.println("Teller.swapToView(): Missing view for display");
			new Event(Event.getLeafLevelClassName(this), "swapToView",
				"Missing view for display ", Event.ERROR);
			return;
		}

		myStage.setScene(newScene);
		myStage.sizeToScene();
		
			
		//Place in center
		WindowPosition.placeCenter(myStage);

	}


	//-----------------------------------------------------------------------------

}