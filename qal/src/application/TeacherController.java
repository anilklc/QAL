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

public class TeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label info;

    @FXML
    private Label info1;

    @FXML
    private Button teacherAdd_button;

    @FXML
    private TextField teacherClass_text;

    @FXML
    private Button teacherDelete_button;

    @FXML
    private ComboBox<String> teacherLessonName_text;

    @FXML
    private ComboBox<String> teacherName_cBox;

    @FXML
    private TextField teacherName_text;

    @FXML
    private TextField teacherPassword_text;

    @FXML
    private ComboBox<String> teacherSurname_cBox;

    @FXML
    private TextField teacherSurname_text;

    @FXML
    private TextField teacherUsername_text;

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
        	query="SELECT * FROM teacher";
        	statement=db.connection.prepareStatement(query);
        	resultSet=statement.executeQuery();
        	teacherName_cBox.getItems().clear();
        	teacherSurname_cBox.getItems().clear();
        	while (resultSet.next())
            { cmb.getItems().addAll(resultSet.getString("teacherName")); 
              cmb2.getItems().addAll(resultSet.getString("teacherSurname")); 
            }
        	db.connectClose();
            statement.close();
            resultSet.close();	
       
    		
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		} 
    	
    }
    
    
     
    

    @FXML
    void teacherAdd_button_Click(ActionEvent event) {
        comboBoxLoad(teacherName_cBox, teacherSurname_cBox);

    }

    @FXML
    void teacherDelete_button_Click(ActionEvent event) {
        comboBoxLoad(teacherName_cBox, teacherSurname_cBox);

    }

    @FXML
    void initialize() {
        comboBoxLoad(teacherName_cBox, teacherSurname_cBox);

    }

}
