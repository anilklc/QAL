package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import DB.DBHelper;
import Models.tableViewResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ResultAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<tableViewResult, String> correctQuestion_clm;

    @FXML
    private TableColumn<tableViewResult, String> examName_clm;

    @FXML
    private ComboBox<String> find_cBox;

    @FXML
    private Label find_label;

    @FXML
    private Label info;

    @FXML
    private Label info1;

    @FXML
    private ComboBox<String> lesson_cBox;

    @FXML
    private TableColumn<tableViewResult, String> loginDate_clm;

    @FXML
    private Label password_label;

    @FXML
    private PasswordField password_text;

    @FXML
    private TableColumn<tableViewResult, String> point_clm;

    @FXML
    private AnchorPane questionPane;

    @FXML
    private Button resultFind_button;

    @FXML
    private Button resultGet_button;

    @FXML
    private DatePicker start_date;

    @FXML
    private TableColumn<tableViewResult, String> studentName_clm;

    @FXML
    private TableView<tableViewResult> tableview;

    @FXML
    private Label username_label;

    @FXML
    private TextField username_text;

    @FXML
    private TextField username_text1;

    @FXML
    private Label uyari_label;

    @FXML
    private TableColumn<tableViewResult, String> wrongQuestion_clm;
    
    DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;
    

       void comboBoxLoad(ComboBox<String> cmb) {
    	
    	try {	
    		db.connectOpen();
        	query="";
        	statement=db.connection.prepareStatement(query);
        	resultSet=statement.executeQuery();
        	lesson_cBox.getItems().clear();
        	
        	while (resultSet.next())
            { cmb.getItems().addAll(resultSet.getString("lessonName")); 
             
            }
        	db.connectClose();
            statement.close();
            resultSet.close();	
       
    		
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		} 
    	
    }
    


    @FXML
    void resultFind_button_Click(ActionEvent event) {

    }

    @FXML
    void resultGet_button_Click(ActionEvent event) {

    }

    @FXML
    void initialize() {
        

    }

}
