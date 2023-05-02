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
    String lessonID;
    
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
    
   void comboBoxLoad2(ComboBox<String> cmb3) {
    	
    	try {	
    		db.connectOpen();
        	query="SELECT * FROM lesson";
        	statement=db.connection.prepareStatement(query);
        	resultSet=statement.executeQuery();
        	teacherLessonName_text.getItems().clear();
        	while (resultSet.next())
            { 
              cmb3.getItems().addAll(resultSet.getString("lessonName"));
            }
        	db.connectClose();
            statement.close();
            resultSet.close();	
       
    		
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		} 
    	
    }
   
   void lessonID() {
   	
   	try {	
   		db.connectOpen();
       	query="SELECT * FROM lesson WHERE lessonName=?";
       	statement=db.connection.prepareStatement(query);
       	statement.setString(1,teacherLessonName_text.getSelectionModel().getSelectedItem());
       	resultSet=statement.executeQuery();
       	while (resultSet.next())
           { 
             lessonID=resultSet.getString("lessonID");
           }
       	db.connectClose();
           statement.close();
           resultSet.close();	
      
   		
   	}catch (Exception e) {
   		System.out.println(e.getMessage());
		} 
   	
   }
   
   
    
    
   void teacherAdd(){
	   	try {
			db.connectOpen();
	    	query="INSERT INTO teacher(teacherName, teacherSurname, teacherClass, teacherLessonID, teacherUsername, teacherPassword) VALUES (?,?,?,?,?,?)";
	    	statement=db.connection.prepareStatement(query);
	    	statement.setString(1,teacherName_text.getText().trim());
	    	statement.setString(2,teacherSurname_text.getText().trim());
	    	statement.setString(3,teacherClass_text.getText().trim());
	    	statement.setString(4,lessonID);
	    	statement.setString(5,teacherUsername_text.getText().trim());
	    	statement.setString(6,teacherPassword_text.getText().trim());
	    	statement.execute();
	    	db.connectClose();
	        statement.close();
	        comboBoxLoad(teacherName_cBox,teacherSurname_cBox);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}  
	   }
	   
	   void teacherDelete(){
	   	try {
			db.connectOpen();
	    	query="DELETE FROM teacher WHERE teacherName=? AND teacherSurname=?";
	    	statement=db.connection.prepareStatement(query);
	    	statement.setString(1,teacherName_cBox.getSelectionModel().getSelectedItem());
	    	statement.setString(2,teacherSurname_cBox.getSelectionModel().getSelectedItem());
	    	statement.execute();
	    	db.connectClose();
	        statement.close();
	        comboBoxLoad(teacherName_cBox, teacherSurname_cBox);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		
		   
	   }
 
    

    @FXML
    void teacherAdd_button_Click(ActionEvent event) {
    	
    	if(teacherName_text.getText()!="" & teacherSurname_text.getText()!=""& teacherUsername_text.getText()!="" & teacherPassword_text.getText()!="" & teacherLessonName_text.getSelectionModel().getSelectedItem()!="") {
    		lessonID();
    		teacherAdd();
            comboBoxLoad(teacherName_cBox, teacherSurname_cBox);
    	}
    	else {
    		uyariAdd_label.setVisible(true);
    		
    	}

    }

    @FXML
    void teacherDelete_button_Click(ActionEvent event) {
    	if(teacherName_cBox.getSelectionModel().getSelectedItem()!=null & teacherSurname_cBox.getSelectionModel().getSelectedItem()!=null) {
    		System.out.print(teacherName_cBox.getSelectionModel().getSelectedItem());
    		
    		teacherDelete();
            comboBoxLoad(teacherName_cBox,teacherSurname_cBox);
    	}
    	else {
    		uyaridel_label.setVisible(true);
    	}
    	
       

    }

    @FXML
    void initialize() {
        comboBoxLoad(teacherName_cBox, teacherSurname_cBox);
        comboBoxLoad2(teacherLessonName_text);

    }

}
