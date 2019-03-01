package userInterface;

import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;

//==============================================================================
public class BookTableModel
{
	private final SimpleStringProperty bookId;
	private final SimpleStringProperty author;
	private final SimpleStringProperty title;
	private final SimpleStringProperty pubYear;
	private final SimpleStringProperty staus;

	//----------------------------------------------------------------------------
	public BookTableModel(Vector<String> accountData)
	{
		bookId =  new SimpleStringProperty(accountData.elementAt(0));
		author =  new SimpleStringProperty(accountData.elementAt(1));
		title =  new SimpleStringProperty(accountData.elementAt(2));
		pubYear =  new SimpleStringProperty(accountData.elementAt(3));
		staus = new SimpleStringProperty(accountData.elementAt(4));
	}

	//----------------------------------------------------------------------------
	public String getbookId() {
        return bookId.get();
    }

	//----------------------------------------------------------------------------
    public void setbookId(String number) {
    	bookId.set(number);
    }

    //----------------------------------------------------------------------------
    public String getauthor() {
        return author.get();
    }

    //----------------------------------------------------------------------------
    public void setauthor(String name) {
    	author.set(name);
    }

    //----------------------------------------------------------------------------
    public String gettitle() {
        return title.get();
    }

    //----------------------------------------------------------------------------
    public void settitle(String title) {
    	this.title.set(title);
    }
    
    //----------------------------------------------------------------------------
    public String getpubYear() {
        return pubYear.get();
    }

    //----------------------------------------------------------------------------
    public void setpubYear(String yr)
    {
    	pubYear.set(yr);
    }
    
    public String getstaus() {
        return staus.get();
    }

    //----------------------------------------------------------------------------
    public void setstaus(String st)
    {
    	pubYear.set(st);
    }
    
    
}
