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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ProfilEditAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton admin_rbutton;

    @FXML
    private RadioButton name_surname_rbutton;

    @FXML
    private TextField name_text;

    @FXML
    private PasswordField password_text;

    @FXML
    private Button profil_button;

    @FXML
    private Button profil_find_button;

    @FXML
    private RadioButton student_rbutton;

    @FXML
    private TextField surname_text;

    @FXML
    private RadioButton teacher_rbutton;

    @FXML
    private TextField username_text;
    
    @FXML
    private TextField id_text;

    @FXML
    private ToggleGroup uye_tip;

    @FXML
    private Label uye_tipi;
    
    @FXML
    private Label info;
    
    DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;
   
    void profilGet() {
    	if(username_text.getText()!="" & password_text.getText()!="") {
        	if(student_rbutton.isSelected()) {
        		try {
            		db.connectOpen();
                	query="SELECT * FROM student where studentUsername=? and studentPassword=?";
                	statement=db.connection.prepareStatement(query);
                	statement.setString(1,username_text.getText().trim());
                	statement.setString(2,password_text.getText().trim());
                	resultSet=statement.executeQuery();
                	while (resultSet.next())
                    { id_text.setText(resultSet.getString("studentID"));
                	  name_text.setText(resultSet.getString("studentName"));
                      surname_text.setText(resultSet.getString("studentSurname"));
                      username_text.setText(resultSet.getString("studentUsername"));
                      password_text.setText(resultSet.getString("studentPassword"));
                      profil_button.setDisable(false);
                    }
                	db.connectClose();
                    statement.close();
                    resultSet.close();	
                    
            		
            	}catch (Exception e) {
            		System.out.println(e.getMessage());
        		}
        		
    	     
            }
            else if(teacher_rbutton.isSelected()) {
            	
            	try {
            		db.connectOpen();
                	query="SELECT * FROM teacher where teacherUsername=? and teacherPassword=?";
                	statement=db.connection.prepareStatement(query);
                	statement.setString(1,username_text.getText().trim());
                	statement.setString(2,password_text.getText().trim());
                	resultSet=statement.executeQuery();
                	while (resultSet.next())
                    { id_text.setText(resultSet.getString("teacherID"));
                	  name_text.setText(resultSet.getString("teacherName"));
                      surname_text.setText(resultSet.getString("teacherSurname"));
                      username_text.setText(resultSet.getString("teacherUsername"));
                      password_text.setText(resultSet.getString("teacherPassword"));
                      profil_button.setDisable(false);

                    }
                	db.connectClose();
                    statement.close();
                    resultSet.close();	
               
            		
            	}catch (Exception e) {
            		System.out.println(e.getMessage());
        		}
            }
            else if(admin_rbutton.isSelected()) {
            	try {
            		db.connectOpen();
                	query="SELECT * FROM admin where adminUsername=? and adminPassword=?";
                	statement=db.connection.prepareStatement(query);
                	statement.setString(1,username_text.getText().trim());
                	statement.setString(2,password_text.getText().trim());
                	resultSet=statement.executeQuery();
                	while (resultSet.next())
                    { id_text.setText(resultSet.getString("adminID"));
                	  name_text.setText(resultSet.getString("adminName"));
                      surname_text.setText(resultSet.getString("adminSurname"));
                      username_text.setText(resultSet.getString("adminUsername"));
                      password_text.setText(resultSet.getString("adminPassword"));
                      profil_button.setDisable(false);

                    }
                	db.connectClose();
                    statement.close();
                    resultSet.close();	
               
            		
            	}catch (Exception e) {
            		System.out.println(e.getMessage());
        		}
            }
           }
            
            
            
	}
    
    void profilUpdate() {
    	
    	if(username_text.getText()!="" & password_text.getText()!="") {
        	if(student_rbutton.isSelected()) {
        		try {
        		db.connectOpen();
            	query="UPDATE student SET studentUsername=? , studentPassword=? WHERE studentName=? and studentSurname=?";
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
        		
    	     
            }
            else if(teacher_rbutton.isSelected()) {
            	
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
            }
            else if(admin_rbutton.isSelected()) {
            	try {
        		db.connectOpen();
                	query="UPDATE admin SET adminUsername=? , adminPassword=? WHERE adminName=? and adminSurname=?";
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
            }
           }
            
            
    	
           }
            
    void profilUpdateName() {
    	
        	
        	if(username_text.getText()!="" & password_text.getText()!="") {
            	if(student_rbutton.isSelected()) {
            		try {
            		db.connectOpen();
                	query="UPDATE student SET studentUsername=? , studentPassword=? , studentName=? , studentSurname=? WHERE studentID=?";
                	statement=db.connection.prepareStatement(query);
                	statement.setString(1,username_text.getText().trim());
                	statement.setString(2,password_text.getText().trim());
                	statement.setString(3,name_text.getText().trim());
                	statement.setString(4,surname_text.getText().trim());
                	statement.setString(5,id_text.getText().trim());
                    statement.execute();
                    System.out.println("dd");
                	db.connectClose();
                    statement.close();
                    resultSet.close();	
               
            		
            	}catch (Exception e) {
            		System.out.println(e.getMessage());
        		}
            		
        	     
                }
                else if(teacher_rbutton.isSelected()) {
                	
                	try {
            		db.connectOpen();
                    	query="UPDATE teacher SET teacherUsername=? , teacherPassword=?,teacherName=? , teacherSurname=? WHERE teacherID=?";
                    	statement=db.connection.prepareStatement(query);
                    	statement.setString(1,username_text.getText().trim());
                    	statement.setString(2,password_text.getText().trim());
                    	statement.setString(3,name_text.getText().trim());
                    	statement.setString(4,surname_text.getText().trim());
                    	statement.setString(5,id_text.getText().trim());
                        statement.execute();
                    	db.connectClose();
                        statement.close();
                        resultSet.close();	
               
            		
            	}catch (Exception e) {
            		System.out.println(e.getMessage());
        		}
                }
                else if(admin_rbutton.isSelected()) {
                	try {
            		db.connectOpen();
                    	query="UPDATE admin SET adminUsername=? , adminPassword=? ,adminName=? , adminSurname=? WHERE adminID=?";
                    	statement=db.connection.prepareStatement(query);
                    	statement.setString(1,username_text.getText().trim());
                    	statement.setString(2,password_text.getText().trim());
                    	statement.setString(3,name_text.getText().trim());
                    	statement.setString(4,surname_text.getText().trim());
                    	statement.setString(5,id_text.getText().trim());
                        statement.execute();
                    	db.connectClose();
                        statement.close();
                        resultSet.close();	
                   	
               
            		
            	}catch (Exception e) {
            		System.out.println(e.getMessage());
        		}
                }
               }
                
                
        	
               }
    
    
 
    @FXML
    void name_surname_rbutton_Click(ActionEvent event) {
    	
        if(name_surname_rbutton.isSelected()) {
    	name_text.setDisable(false); 
        surname_text.setDisable(false);
        
        }
        else {
        	 name_text.setDisable(true); 
             surname_text.setDisable(true);
        }
    }

    @FXML
    void profil_button_Click(ActionEvent event) {
    	if(name_surname_rbutton.isSelected()) {
    		profilUpdateName();
            
            }
            else {
            	profilUpdate();
            }
    	
    }

    @FXML
    void profil_find_button_Click(ActionEvent event) {
     profilGet();
    }

    @FXML
    void initialize() {
     profil_button.setDisable(true); 
     name_text.setDisable(true); 
     surname_text.setDisable(true);
     id_text.setDisable(true);
    }

}
