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
	private final SimpleStringProperty status;

	//----------------------------------------------------------------------------
	public BookTableModel(Vector<String> bookData)
	{
		bookId =  new SimpleStringProperty(bookData.elementAt(0));
		author =  new SimpleStringProperty(bookData.elementAt(1));
		title =  new SimpleStringProperty(bookData.elementAt(2));
		pubYear =  new SimpleStringProperty(bookData.elementAt(3));
		status = new SimpleStringProperty(bookData.elementAt(4));
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
    
    public String getstatus() {
        return status.get();
    }

    //----------------------------------------------------------------------------
    public void setstatus(String st)
    {
    	pubYear.set(st);
    }
    
    
}
