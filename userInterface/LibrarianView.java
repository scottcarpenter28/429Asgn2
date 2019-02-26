package userInterface;

import impresario.IModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class LibrarianView extends View {
	private Button insertNewBookBTN;
	private Button insertNewPatronBTN;
	private Button searchBooksBTN;
	private Button searchPatronBTN;
	private Button doneBTN;

	public LibrarianView(IModel model) {
		super(model, "Librarian View");
		VBox container = new VBox(10);

		container.setPadding(new Insets(15, 5, 5, 5));

		// create a Node (Text) for showing the title
		container.getChildren().add(createTitle());

		// create a Node (GridPane) for showing data entry fields
		container.getChildren().add(createFormContents());
		getChildren().add(container);

		populateFields();

		// STEP 0: Be sure you tell your model what keys you are interested in
		myModel.subscribe("LoginError", this);

	}

	private void populateFields() {
		// TODO Auto-generated method stub
		
	}

	private Node createTitle() {		
		Text titleText = new Text("       Brockport Library          ");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKGREEN);
		
	
		return titleText;
	}

	private Node createFormContents() {
		GridPane grid = new GridPane();
    	grid.setAlignment(Pos.CENTER);
   		grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(25, 25, 25, 25));
    	
    	//Insert new book

    	insertNewBookBTN= new Button("Insert a new book");
    	grid.add(insertNewBookBTN,0,0);
    	
    	//Insert new patron
    	insertNewPatronBTN=new Button("Insert a new patron");
    	grid.add(insertNewPatronBTN, 0, 1);
    	
    	//Search for book
    	searchBooksBTN=new Button("Search for a book");
    	grid.add(searchBooksBTN, 1, 0);
    	
    	//Search for patron
    	searchPatronBTN=new Button("Search for a patron");
    	grid.add(searchPatronBTN, 1, 1);
    	
    	//Done
    	doneBTN=new Button("Done");
    	grid.add(doneBTN, 2, 0);
    	
		return grid;
	}

	@Override
	public void updateState(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

}
