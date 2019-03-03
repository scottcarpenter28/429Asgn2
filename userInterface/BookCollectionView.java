package userInterface;

// system imports
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Vector;
import java.util.Enumeration;

// project imports
import impresario.IModel;
import model.Book;
import model.BookCatalog;

//==============================================================================
public class BookCollectionView extends View
{
	protected TableView<BookTableModel> tableOfAccounts;
	protected Button cancelButton;
	protected Button submitButton;

	protected MessageView statusLog;


	//--------------------------------------------------------------------------
	public BookCollectionView(IModel wsc)
	{
		super(wsc, "bookCollectionView");

		// create a container for showing the contents
		VBox container = new VBox(10);
		container.setPadding(new Insets(15, 5, 5, 5));

		// create our GUI components, add them to this panel
		container.getChildren().add(createTitle());
		container.getChildren().add(createFormContent());

		// Error message area
		container.getChildren().add(createStatusLog("                                            "));

		getChildren().add(container);
		
		populateFields();
	}

	//--------------------------------------------------------------------------
	protected void populateFields()
	{
		getEntryTableModelValues();
	}

	//--------------------------------------------------------------------------
	protected void getEntryTableModelValues()
	{
		
		ObservableList<BookTableModel> tableData = FXCollections.observableArrayList();
		try
		{
			BookCatalog bookCollection = (BookCatalog)myModel.getState("BookList");

	 		Vector entryList = (Vector)bookCollection.getState("Books");
			Enumeration entries = entryList.elements();

			while (entries.hasMoreElements() == true)
			{
				Book nextBook = (Book)entries.nextElement();
				Vector<String> view = nextBook.getEntryListView();

				// add this list entry to the list
				BookTableModel nextTableRowData = new BookTableModel(view);
				tableData.add(nextTableRowData);
				
			}
			
			tableOfAccounts.setItems(tableData);
		}
		catch (Exception e) {//SQLException e) {
			// Need to handle this exception
		}
	}

	// Create the title container
	//-------------------------------------------------------------
	private Node createTitle()
	{
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);	

		Text titleText = new Text(" Brockport Library");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setWrappingWidth(300);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKGREEN);
		container.getChildren().add(titleText);
		
		return container;
	}

	// Create the main form content
	//-------------------------------------------------------------
	private VBox createFormContent()
	{
		VBox vbox = new VBox(10);

		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
       	grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text prompt = new Text("LIST OF BOOKS");
        prompt.setWrappingWidth(350);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

		tableOfAccounts = new TableView<BookTableModel>();
		tableOfAccounts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	
		TableColumn bookIdColumn = new TableColumn("bookId") ;
		bookIdColumn.setMinWidth(100);
		bookIdColumn.setCellValueFactory(
	                new PropertyValueFactory<PatronTableModel, String>("bookId"));
		
		TableColumn authorColumn = new TableColumn("author") ;
		authorColumn.setMinWidth(100);
		authorColumn.setCellValueFactory(
	                new PropertyValueFactory<PatronTableModel, String>("author"));
		  
		TableColumn titleColumn = new TableColumn("title") ;
		titleColumn.setMinWidth(100);
		titleColumn.setCellValueFactory(
	                new PropertyValueFactory<PatronTableModel, String>("title"));
		
		TableColumn pubYearColumn = new TableColumn("pubYear") ;
		pubYearColumn.setMinWidth(100);
		pubYearColumn.setCellValueFactory(
	                new PropertyValueFactory<PatronTableModel, String>("pubYear"));
		
		TableColumn statusColumn = new TableColumn("status") ;
		statusColumn.setMinWidth(100);
		statusColumn.setCellValueFactory(
	                new PropertyValueFactory<PatronTableModel, String>("status"));


		tableOfAccounts.getColumns().addAll(bookIdColumn, 
				authorColumn, titleColumn, pubYearColumn, statusColumn);

		tableOfAccounts.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event)
			{
				if (event.isPrimaryButtonDown() && event.getClickCount() >=2 ){
					processAccountSelected();
				}
			}
		});
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(115, 150);
		scrollPane.setContent(tableOfAccounts);

		submitButton = new Button("Submit");
 		submitButton.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		  public void handle(ActionEvent e) {
       		     	processAction(e);    
            	     }
        	});

		cancelButton = new Button("Back");
 		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

 			  public void handle(ActionEvent e) {
 	  		     	processAction(e);    
 	       	     }
        	});

		HBox btnContainer = new HBox(100);
		btnContainer.setAlignment(Pos.CENTER);
		btnContainer.getChildren().add(submitButton);
		btnContainer.getChildren().add(cancelButton);
		
		vbox.getChildren().add(grid);
		vbox.getChildren().add(scrollPane);
		vbox.getChildren().add(btnContainer);
	
		return vbox;
	}

	

	protected void processAction(ActionEvent e) {
		// TODO Auto-generated method stub
		myModel.stateChangeRequest("LibrarianView", null);
	}

	//--------------------------------------------------------------------------
	public void updateState(String key, Object value)
	{
	}

	//--------------------------------------------------------------------------
	protected void processAccountSelected()
	{
		BookTableModel selectedItem = tableOfAccounts.getSelectionModel().getSelectedItem();
		
		if(selectedItem != null)
		{
			String selectedAcctNumber = selectedItem.getbookId();

			myModel.stateChangeRequest("AccountSelected", selectedAcctNumber);
		}
	}

	//--------------------------------------------------------------------------
	protected MessageView createStatusLog(String initialMessage)
	{
		statusLog = new MessageView(initialMessage);

		return statusLog;
	}


	/**
	 * Display info message
	 */
	//----------------------------------------------------------
	public void displayMessage(String message)
	{
		statusLog.displayMessage(message);
	}

	/**
	 * Clear error message
	 */
	//----------------------------------------------------------
	public void clearErrorMessage()
	{
		statusLog.clearErrorMessage();
	}
	/*
	//--------------------------------------------------------------------------
	public void mouseClicked(MouseEvent click)
	{
		if(click.getClickCount() >= 2)
		{
			processAccountSelected();
		}
	}
   */
	
}
