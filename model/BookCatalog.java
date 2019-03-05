// specify the package
package model;

//system imports
import java.util.Properties;
import java.util.Vector;
import javafx.scene.Scene;

//project imports
import exception.InvalidPrimaryKeyException;
import event.Event;
import database.*;

import impresario.IView;

import userInterface.View;
import userInterface.ViewFactory;

//==============================================================
public class BookCatalog  extends EntityBase implements IView
{
	private static final String myTableName = "Books";

	private Vector<Book> books;
	// GUI Components

	// constructor for this class
	//----------------------------------------------------------
	
	//Insert Boook
	public BookCatalog(Properties props) throws Exception
	{
		super(myTableName);
		Properties schema = getSchemaInfo(myTableName);
		insertAutoIncrementalPersistentState(schema, props);
		
		System.out.println("Book entered");
	}
	
	//Search for book by title
	public BookCatalog( String title) throws
		Exception
	{
		super(myTableName);

		if (title == null)
		{
			new Event(Event.getLeafLevelClassName(this), "<init>",
				"Missing title information", Event.FATAL);
			throw new Exception
				("UNEXPECTED ERROR: AccountCollection.<init>: account holder information is null");
		}


		String query = "SELECT * FROM " + myTableName + " WHERE (title like'%" + title + "%')";

		Vector allDataRetrieved = getSelectQueryResult(query);

		if (allDataRetrieved != null)
		{
			books = new Vector<Book>();

			for (int cnt = 0; cnt < allDataRetrieved.size(); cnt++)
			{
				Properties nextAccountData = (Properties)allDataRetrieved.elementAt(cnt);

				Book book = new Book(nextAccountData);

				if (book != null)
				{
					addBook(book);
				}
			}

		}
		else
		{
			throw new InvalidPrimaryKeyException("No books for : "
				+ title + ".  : " );
		}
		//createAndShowView();
	}
	
	private void addBook(Book b)
	{
		//accounts.add(a);
		int index = findIndexToAdd(b);
		books.insertElementAt(b,index); // To build up a collection sorted on some key
	}
	
	private int findIndexToAdd(Book a)
	{
		//users.add(u);
		int low=0;
		int high = books.size()-1;
		int middle;

		while (low <=high)
		{
			middle = (low+high)/2;

			Book midSession = books.elementAt(middle);

			int result = Book.compare(a,midSession);

			if (result ==0)
			{
				return middle;
			}
			else if (result<0)
			{
				high=middle-1;
			}
			else
			{
				low=middle+1;
			}


		}
		return low;
	}

	public Object getState(String key)
	{
		if (key.equals("BookList"))
			return books;
		else if(key.equals("Books"))
			return this;
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

	public Vector<String> getEntryListView() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Book retrieve(String book)
	{
		Book retValue = null;
		for (int cnt = 0; cnt < books.size(); cnt++)
		{
			Book nextBook = books.elementAt(cnt);
			String nextBookNum = (String)nextBook.getState("AccountNumber");
			if (nextBookNum.equals(book) == true)
			{
				retValue = nextBook;
				return retValue; // we should say 'break;' here
			}
		}

		return retValue;
	}
	
	
	
	protected void createAndShowView(){
		Scene currentScene = (Scene)myViews.get("title");

		if (currentScene == null)
		{
			// create our initial view
			View newView = ViewFactory.createView("title", this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("title", currentScene);
		}	
		swapToView(currentScene);
		
	}
	
}