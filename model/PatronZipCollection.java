// specify the package
package model;

import java.util.Enumeration;
// system imports
import java.util.Properties;
import java.util.Vector;

// project imports
import exception.InvalidPrimaryKeyException;
import event.Event;
import database.*;

import impresario.IView;

//==============================================================
public class PatronZipCollection  extends EntityBase implements IView
{
	private static final String myTableName = "patron";

	private Vector patrons;
	// GUI Components

	// constructor for this class
	//----------------------------------------------------------
	public PatronZipCollection( String zip) throws
		Exception
	{
		super(myTableName);

		if (zip == null)
		{
			new Event(Event.getLeafLevelClassName(this), "<init>",
				"Missing account holder information", Event.FATAL);
			throw new Exception
				("UNEXPECTED ERROR: PatronAgeCollection.<init>: account holder information is null");
		}

		String query = "SELECT * FROM patron WHERE patron.zip='"+zip+"'";

		Vector allDataRetrieved = getSelectQueryResult(query);
		
		String result="";
		if (allDataRetrieved != null)
		{
			patrons = new Vector();

			result = ("==============================================\n");

			Properties p1 = (Properties) allDataRetrieved.firstElement();

			Enumeration props1 = p1.propertyNames();

			while (props1.hasMoreElements())

				result += (props1.nextElement() + "\t");

			result += "\n";

			result += ("----------------------------------------------\n");
			Vector<Properties> data= allDataRetrieved;
			for (Properties p : data) {

				Enumeration props = p.propertyNames();

				while (props.hasMoreElements())

					result += (p.getProperty((String) (props.nextElement())) + "\t");

				result += "\n";

			}

			result += ("==============================================");

		}
		else
			System.out.println("No books found for "+zip);
		System.out.println(result);
	}

	/**
	 *
	 */
	//----------------------------------------------------------
	public Object getState(String key)
	{
		if (key.equals("Title"))
			return patrons;
		return null;
	}

	//----------------------------------------------------------------
	public void stateChangeRequest(String key, Object value)
	{
		// Class is invariant, so this method does not change any attributes

		myRegistry.updateSubscribers(key, this);
	}

	/** Called via the IView relationship */
	//----------------------------------------------------------
	public void updateState(String key, Object value)
	{
		stateChangeRequest(key, value);
	}

	//-----------------------------------------------------------------------------------
	protected void initializeSchema(String tableName)
	{
		if (mySchema == null)
		{
			mySchema = getSchemaInfo(tableName);
		}
	}

	public Vector<String> getEntryListView()
	{
		Vector<String> v = new Vector<String>();

		v.addElement(persistentState.getProperty("PatronId"));
		v.addElement(persistentState.getProperty("name"));
		v.addElement(persistentState.getProperty("address"));
		v.addElement(persistentState.getProperty("city"));
		v.addElement(persistentState.getProperty("stateCode"));
		v.addElement(persistentState.getProperty("zip"));
		v.addElement(persistentState.getProperty("email"));
		v.addElement(persistentState.getProperty("dateOfBirth"));
		v.addElement(persistentState.getProperty("status"));

		return v;
	}
}
