package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import DB.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label info;

    @FXML
    private Label info1;

    @FXML
    private Button studentAdd_button;

    @FXML
    private TextField studentClassDel_text;

    @FXML
    private TextField studentClass_text;

    @FXML
    private ComboBox<String> studentName_cBox;

    @FXML
    private TextField studentName_text;

    @FXML
    private TextField studentPassword_text;

    @FXML
    private ComboBox<String> studentSurname_cBox;

    @FXML
    private TextField studentSurname_text;

    @FXML
    private TextField studentUsername_text;

    @FXML
    private Button teacherDelete_button;

    @FXML
    private Label uyariAdd_label;

    @FXML
    private Label uyaridel_label;
    
    
    DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;
    
    
    void comboBoxLoad(ComboBox<String> cmb,ComboBox<String> cmb2) {
    	
    	try {	
    		db.connectOpen();
        	query="SELECT * FROM student";
        	statement=db.connection.prepareStatement(query);
        	resultSet=statement.executeQuery();
        	studentName_cBox.getItems().clear();
        	studentSurname_cBox.getItems().clear();
        	while (resultSet.next())
            { cmb.getItems().addAll(resultSet.getString("studentName")); 
              cmb2.getItems().addAll(resultSet.getString("studentSurname")); 
            }
        	db.connectClose();
            statement.close();
            resultSet.close();	
       
    		
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		} 
    	
    }
   

    @FXML
    void studentAdd_button_Click(ActionEvent event) {
    	comboBoxLoad(studentName_cBox, studentSurname_cBox);

    }

    @FXML
    void studentDelete_button_Click(ActionEvent event) {
    	comboBoxLoad(studentName_cBox, studentSurname_cBox);


    }

    @FXML
    void initialize() {
    	comboBoxLoad(studentName_cBox, studentSurname_cBox);
    }

}
