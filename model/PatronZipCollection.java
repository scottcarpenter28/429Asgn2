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
import userInterface.PatronCollectionView;

//==============================================================
public class PatronZipCollection  extends EntityBase implements IView
{
	private static final String myTableName = "patron";

	protected Properties dependencies;
	private Vector patrons;
	// GUI Components

	// constructor for this class
	//----------------------------------------------------------
	public PatronZipCollection( String zip) throws
	Exception
	{
		super(myTableName);

		setDependencies();
		String query = "SELECT * FROM " + myTableName + " WHERE (AccountNumber = " + zip + ")";

		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

		// You must get one account at least
		if (allDataRetrieved != null)
		{
			int size = allDataRetrieved.size();

			// There should be EXACTLY one account. More than that is an error
			if (size == 0)
			{
				throw new InvalidPrimaryKeyException("No patrons matching zip : "
					+ zip + " found.");
			}
			else
			{
				// copy all the retrieved data into persistent state
				Properties retrievedAccountData = allDataRetrieved.elementAt(0);
				persistentState = new Properties();

				Enumeration allKeys = retrievedAccountData.propertyNames();
				while (allKeys.hasMoreElements() == true)
				{
					String nextKey = (String)allKeys.nextElement();
					String nextValue = retrievedAccountData.getProperty(nextKey);
					// accountNumber = Integer.parseInt(retrievedAccountData.getProperty("accountNumber"));

					if (nextValue != null)
					{
						persistentState.setProperty(nextKey, nextValue);
					}
				}

			}
		}
	}


		private void setDependencies() {
			dependencies = new Properties();
			
			myRegistry.setDependencies(dependencies);
		
	}

		/**
		 *
		 */
		//----------------------------------------------------------
		public Object getState(String key)
		{
			System.out.println("Geting state");
			if (key.equals("Patrons"))
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
