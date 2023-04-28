package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import DB.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ProfilEditTeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name_text;

    @FXML
    private PasswordField password_text;

    @FXML
    private Button profil_button;

    @FXML
    private TextField surname_text;

    @FXML
    private TextField username_text;
    
    @FXML
    private Button profil_find_button;
    
    @FXML
    private Label info;

    DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;

    void profilGet() {
    	if(username_text.getText()!="" & password_text.getText()!="") {	
    	try {
        		db.connectOpen();
            	query="SELECT * FROM teacher where teacherUsername=? and teacherPassword=?";
            	statement=db.connection.prepareStatement(query);
            	statement.setString(1,username_text.getText().trim());
            	statement.setString(2,password_text.getText().trim());
            	resultSet=statement.executeQuery();
            	while (resultSet.next())
                { name_text.setText(resultSet.getString("teacherName"));
                  surname_text.setText(resultSet.getString("teacherSurname"));
                  username_text.setText(resultSet.getString("teacherUsername"));
                  password_text.setText(resultSet.getString("teacherPassword"));
                }
            	db.connectClose();
                statement.close();
                resultSet.close();	
           
        		
        	}catch (Exception e) {
        		System.out.println(e.getMessage());
    		}
    	}}

    void profilUpdate() {
    	if(username_text.getText()!="" & password_text.getText()!="") {	
    	try {
    			db.connectOpen();
            	query="UPDATE teacher SET teacherUsername=? , teacherPassword=? WHERE teacherName=? and teacherSurname=?";
            	statement=db.connection.prepareStatement(query);
            	statement.setString(1,username_text.getText().trim());
            	statement.setString(2,password_text.getText().trim());
            	statement.setString(3,name_text.getText().trim());
            	statement.setString(4,surname_text.getText().trim());
                statement.execute();
            	db.connectClose();
                statement.close();
                resultSet.close();	
        		
        	}catch (Exception e) {
        		System.out.println(e.getMessage());
    		}
    	}}
    
    
    
    
    @FXML
    void profil_button_Click(ActionEvent event) {
    	 profilUpdate();
    }
    
    @FXML
    void profil_find_button_Click(ActionEvent event) {
    	   	
    	profilGet();
    }
    
   
    
    @FXML
    void initialize() {
    name_text.setDisable(true);
    surname_text.setDisable(true);
    }
    


}
