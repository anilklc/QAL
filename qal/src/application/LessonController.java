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


public class LessonController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField lessonAdd_text;

    @FXML
    private Label info;

    @FXML
    private Label info1;

    @FXML
    private Label info12;

    @FXML
    private Button lessonAdd_button;


    @FXML
    private Button lessonDelete_button;

    @FXML
    private ComboBox<String> lessonDel_cBox;

    @FXML
    private Button lessonUpdate_button;

    @FXML
    private ComboBox<String> lessonUpdate_cBox;

    @FXML
    private TextField lessonUpdate_text;
    
    @FXML
    private Label uyariAdd_label;
    
    @FXML
    private Label uyaridel_label;
    
    @FXML
    private Label uyariEdit_label;
    
    DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;
    
    
    void comboBoxLoad(ComboBox<String> cmb,ComboBox<String> cmb2) {
    	
    	try {	
    		db.connectOpen();
        	query="SELECT * FROM lesson";
        	statement=db.connection.prepareStatement(query);
        	resultSet=statement.executeQuery();
        	lessonUpdate_cBox.getItems().clear();
        	lessonDel_cBox.getItems().clear();
        	while (resultSet.next())
            { cmb.getItems().addAll(resultSet.getString("lessonName")); 
              cmb2.getItems().addAll(resultSet.getString("lessonName")); 
            }
        	db.connectClose();
            statement.close();
            resultSet.close();	
       
    		
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		} 
    	
    }
   
   void lessonAdd(){
   	try {
		db.connectOpen();
    	query="INSERT INTO lesson(lessonName) VALUES (?)";
    	statement=db.connection.prepareStatement(query);
    	statement.setString(1,lessonAdd_text.getText().trim());
    	statement.execute();
    	db.connectClose();
        statement.close();
        comboBoxLoad(lessonDel_cBox,lessonUpdate_cBox);
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}  
   }
   
   void lessonDelete(String lessonName){
   	try {
		db.connectOpen();
    	query="DELETE FROM lesson WHERE lessonName=?;";
    	statement=db.connection.prepareStatement(query);
    	statement.setString(1,lessonName);
    	statement.execute();
    	db.connectClose();
        statement.close();
        comboBoxLoad(lessonDel_cBox,lessonUpdate_cBox);
	}catch (Exception e) {
		System.out.println(e.getMessage());
	} 
	
	   
   }


   void lessonUpdate(){
	   try {
   		db.connectOpen();
    	query="UPDATE lesson SET lessonName=? WHERE lessonName=?";
    	statement=db.connection.prepareStatement(query);
    	statement.setString(1,lessonUpdate_text.getText().trim());
    	statement.setString(2,lessonUpdate_cBox.getSelectionModel().getSelectedItem());
        statement.execute();
    	db.connectClose();
        statement.close();
        comboBoxLoad(lessonDel_cBox,lessonUpdate_cBox);
         }catch (Exception e) {
	    System.out.println(e.getMessage());
         }
		
		   
	   }
   
   
    @FXML
    void lessonAdd_button_Click(ActionEvent event) {
    	
    	if(lessonAdd_text.getText()!="") {	
    	lessonAdd();
       }
    	else {
    		uyariAdd_label.setVisible(true);
    	}
    }
    
    @FXML
    void lessonDelete_button_Click(ActionEvent event) {
    	if(lessonDel_cBox.getSelectionModel().getSelectedItem()!=null) {	
        	lessonDelete(lessonDel_cBox.getSelectionModel().getSelectedItem());}
        	else {
        	uyaridel_label.setVisible(true);
        	}
    }
    
    @FXML
    void lessonUpdate_button_Click(ActionEvent event) {
    	if(lessonUpdate_cBox.getSelectionModel().getSelectedItem()!=null & lessonUpdate_text.getText()!="" ) {	
        	lessonUpdate();
        	}
        	else {
        	uyariEdit_label.setVisible(true);
        	}
    }
    
    
    @FXML
    void initialize() {
    	comboBoxLoad(lessonDel_cBox,lessonUpdate_cBox);
    }

}
