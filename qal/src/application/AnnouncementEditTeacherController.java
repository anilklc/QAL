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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AnnouncementEditTeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button announcementAdd_button;

    @FXML
    private Button announcementDelete_button;

    @FXML
    private Button announcementGet_button;

    @FXML
    private Label announcementName_label;

    @FXML
    private TextField announcementName_text;

    @FXML
    private TextField passwordDel_text;
    
    @FXML
    private ComboBox<String> announcementName_cBox;

    @FXML
    private Label announcement_label;

    @FXML
    private TextArea announcement_textarea;

    @FXML
    private Label info;

    @FXML
    private Label info1;

    @FXML
    private Label lesson_label;

    @FXML
    private ComboBox<String> lesson_text;

    @FXML
    private Label passwordAdd_label;

    @FXML
    private Label password_label;

    @FXML
    private Label usernameAdd_label;

    @FXML
    private TextField usernameAdd_text;

    @FXML
    private TextField usernameDel_text;

    @FXML
    private Label username_label;

    @FXML
    private Label uyariAdd_label;

    @FXML
    private Label uyaridel_label;

    @FXML
    private Label uyaridel_label1;
    
    @FXML
    private ComboBox<String> announcementDate_cBox;

    @FXML
    private Label announcementDate_label;

    DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;
    void comboBoxLoad(ComboBox<String> cmb,String query,String columnName) {
    	
    	try {	
    		db.connectOpen();
        	statement=db.connection.prepareStatement(query);
        	resultSet=statement.executeQuery();
        	while (resultSet.next())
            { cmb.getItems().addAll(resultSet.getString(columnName)); 
            }
        	
        	db.connectClose();
            statement.close();
            resultSet.close();
       
    		
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		} 
    	
    }
    
    
    void announcementAdd(){
	   	try {
			db.connectOpen();
	    	query="INSERT INTO announcement(announcementName, announcementText, announcementUsername, announcementClass,announcementDate,announcementType) VALUES (?,?,?,?,(SELECT CURRENT_DATE),?)";
	    	statement=db.connection.prepareStatement(query);
	    	statement.setString(1,announcementName_text.getText().trim());
	    	statement.setString(2,announcement_textarea.getText());
	    	statement.setString(3,usernameAdd_text.getText().trim());
	    	statement.setString(4,lesson_text.getSelectionModel().getSelectedItem());
	    	statement.setString(5,"0");
	    	statement.execute();
	    	db.connectClose();
	        statement.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}  
	   }
    
    
    void announcementGet(){
       	try {
    		db.connectOpen();
        	query="SELECT * FROM announcement WHERE announcementUsername=?";
        	statement=db.connection.prepareStatement(query);
        	statement.setString(1,usernameDel_text.getText().trim());
        	resultSet=statement.executeQuery();
            announcementDate_cBox.getItems().clear();
        	while (resultSet.next())
            { announcementName_cBox.getItems().addAll(resultSet.getString("announcementName"));
              announcementDate_cBox.getItems().addAll(resultSet.getString("announcementDate"));
            }
        	db.connectClose();
            statement.close();
            resultSet.close();
            
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
    	}  
       }
       
       void announcementDelete(){
       	try {
    		db.connectOpen();
        	query="DELETE FROM announcement WHERE announcementName=? and announcementDate=?;";
        	statement=db.connection.prepareStatement(query);
        	statement.setString(1,announcementName_cBox.getSelectionModel().getSelectedItem());
        	statement.setString(2,announcementDate_cBox.getSelectionModel().getSelectedItem());
        	statement.execute();
        	db.connectClose();
            statement.close();
            announcementName_cBox.getItems().clear();
            announcementDate_cBox.getItems().clear();
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
    	} 
    	
    	   
       }
    
       
       void userControl() {
    	   
    	   try {db.connectOpen();
       	query="SELECT * FROM teacher where teacherUsername=? and teacherPassword=?";
       	statement=db.connection.prepareStatement(query);
       	statement.setString(1, usernameDel_text.getText().trim());
       	statement.setString(2, passwordDel_text.getText().trim());
       	resultSet=statement.executeQuery();
       	resultSet.next();
       	if (usernameDel_text.getText().equals(resultSet.getString("teacherUsername")) && passwordDel_text.getText().equals(resultSet.getString("teacherPassword")))
           {announcementName_cBox.getItems().clear();
            announcementDate_cBox.getItems().clear();
       		announcementGet();
           }
       	
       	db.connectClose();
           statement.close();
           resultSet.close();
       	}catch (Exception e) {
       		uyaridel_label.setVisible(true);
       	}
       }
    
    @FXML
    void announcementAdd_button_Click(ActionEvent event) {
    	if(announcementName_text.getText()!="" & announcement_textarea.getText()!="" & usernameAdd_text.getText()!="" & lesson_text.getSelectionModel().getSelectedItem()!=null) {
    		announcementAdd();
    	}
    	else {
    		uyariAdd_label.setVisible(true);
    	}
    }

    @FXML
    void announcementDelete_button_Click(ActionEvent event) {
    	if(announcementName_cBox.getSelectionModel().getSelectedItem()!=null & announcementDate_cBox.getSelectionModel().getSelectedItem()!=null) {
    		announcementDelete();
    		announcementDelete_button.setDisable(true);
    	}
    	else {
    		uyaridel_label.setVisible(true);
    	}
    }

    @FXML
    void announcementGet_button_Click(ActionEvent event) {
    	
    	if(usernameDel_text.getText()!="" & passwordDel_text.getText()!="") {
    	userControl(); 
    	announcementDelete_button.setDisable(false);}
    	else {
    		uyaridel_label.setVisible(true);
    	}
    	}

    @FXML
    void initialize() {
    	comboBoxLoad(lesson_text,"SELECT DISTINCT(studentClass) FROM student","studentClass");
         announcementDelete_button.setDisable(true);
        
    }

}
