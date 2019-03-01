package userInterface;

import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;

//==============================================================================
public class PatronTableModel
{
	private final SimpleStringProperty PatronId;
	private final SimpleStringProperty Name;
	private final SimpleStringProperty address;
	private final SimpleStringProperty city;
	private final SimpleStringProperty stateCode;
	private final SimpleStringProperty zip;
	private final SimpleStringProperty email;
	private final SimpleStringProperty dob;
	private final SimpleStringProperty status;

	//----------------------------------------------------------------------------
	public PatronTableModel(Vector<String> accountData)
	{
		PatronId =  new SimpleStringProperty(accountData.elementAt(0));
		Name =  new SimpleStringProperty(accountData.elementAt(1));
		address =  new SimpleStringProperty(accountData.elementAt(2));
		city =  new SimpleStringProperty(accountData.elementAt(3));
		stateCode =  new SimpleStringProperty(accountData.elementAt(4));
		zip =  new SimpleStringProperty(accountData.elementAt(5));
		email =  new SimpleStringProperty(accountData.elementAt(6));
		dob =  new SimpleStringProperty(accountData.elementAt(7));
		status =  new SimpleStringProperty(accountData.elementAt(8));
	}

	//----------------------------------------------------------------------------
	public String getPatronId() {
        return PatronId.get();
    }

	//----------------------------------------------------------------------------
    public void setPatronId(String number) {
        PatronId.set(number);
    }

    //----------------------------------------------------------------------------
    public String getName() {
        return Name.get();
    }

    //----------------------------------------------------------------------------
    public void setName(String n) {
        Name.set(n);
    }

    //----------------------------------------------------------------------------
    public String getaddress() {
        return address.get();
    }

    //----------------------------------------------------------------------------
    public void setaddress(String a) {
        address.set(a);
    }
    
    //----------------------------------------------------------------------------
    public String getcity() {
        return city.get();
    }

    //----------------------------------------------------------------------------
    public void setcity(String c)
    {
    	city.set(c);
    }
    
    //----------------------------------------------------------------------------
    public String getstateCode() {
        return stateCode.get();
    }

    //----------------------------------------------------------------------------
    public void setstateCode(String state)
    {
    	stateCode.set(state);
    }
    
    //----------------------------------------------------------------------------
    public String getzip() {
        return zip.get();
    }

    //----------------------------------------------------------------------------
    public void setzip(String c)
    {
    	zip.set(c);
    }
    
    //----------------------------------------------------------------------------
    public String getemail() {
        return city.get();
    }

    //----------------------------------------------------------------------------
    public void setemail(String mail)
    {
    	email.set(mail);
    }
    
    //----------------------------------------------------------------------------
    public String getdob() {
        return city.get();
    }

    //----------------------------------------------------------------------------
    public void setdob(String d)
    {
    	dob.set(d);
    }
    
    //----------------------------------------------------------------------------
    public String getstatus() {
        return status.get();
    }

    //----------------------------------------------------------------------------
    public void setstatus(String s)
    {
    	status.set(s);
    }
}
