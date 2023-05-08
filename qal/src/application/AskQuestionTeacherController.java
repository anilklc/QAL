package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DB.DBHelper;
import Models.tableViewAskQuestion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AskQuestionTeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button answerAdd_button;

    @FXML
    private TableColumn<tableViewAskQuestion, String> answerDate_clm;

    @FXML
    private Button answerRefresh_button;

    @FXML
    private Label answer_label;

    @FXML
    private TextArea answer_textarea;

    @FXML
    private Label info;

    @FXML
    private Label password_label;

    @FXML
    private PasswordField password_text;

    @FXML
    private TableColumn<tableViewAskQuestion, String> questionDate_clm;

    @FXML
    private TableColumn<tableViewAskQuestion, String> questionName_clm;

    @FXML
    private Label question_label;

    @FXML
    private TextArea question_textarea;

    @FXML
    private TableColumn<tableViewAskQuestion, String> studentClass_clm;

    @FXML
    private TableColumn<tableViewAskQuestion, String> studentName_clm;

    @FXML
    private TableView<tableViewAskQuestion> tableview;

    @FXML
    private TextField username_text;

    @FXML
    private Label username_label;
    
    @FXML
    private Label uyari_label;
    
    
    DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;
    tableViewAskQuestion tbl=null;
    ObservableList<tableViewAskQuestion> tblList=FXCollections.observableArrayList();
    String teacherName,lessonID,teacherLesson;
    void userInfo() {
     	
     	try {	
     		db.connectOpen();
         	query="SELECT * FROM teacher";
         	statement=db.connection.prepareStatement(query);
         	resultSet=statement.executeQuery();
         	query="SELECT * FROM teacher where teacherUsername=? and teacherPassword=?";
        	statement=db.connection.prepareStatement(query);
        	statement.setString(1, username_text.getText().trim());
        	statement.setString(2, password_text.getText().trim());
        	resultSet=statement.executeQuery();
        	resultSet.next();
        	
        	if (username_text.getText().equals(resultSet.getString("teacherUsername")) && password_text.getText().equals(resultSet.getString("teacherPassword")))
            {  teacherName=resultSet.getString("teacherName");
               teacherName=teacherName+" "+resultSet.getString("teacherSurname");
               lessonID=resultSet.getString("teacherLessonID");
               
            }
        	db.connectClose();
            statement.close();
            resultSet.close();
            lessonInfo();
     	}catch (Exception e) {
     		System.out.println(e.getMessage());
 		} 
     	
     }
     
    
    void lessonInfo() {
     	
     	try {
     		db.connectOpen();
         	query="SELECT * FROM lesson WHERE lessonID=?";
         	statement=db.connection.prepareStatement(query);
        	statement.setString(1, lessonID);
        	resultSet=statement.executeQuery();
        	while(resultSet.next())
            {
               teacherLesson=resultSet.getString("lessonName");
            }
        	db.connectClose();
            statement.close();
            resultSet.close();
     	}catch (Exception e) {
     		System.out.println(e.getMessage());
 		} 
     	
     }
    
     private void refreshTable() {
	  try {
		 db.connectOpen();
	    query="SELECT * FROM privatequestion WHERE lessonName=?";
	    statement=db.connection.prepareStatement(query);
	    statement.setString(1,teacherLesson);
  		resultSet=statement.executeQuery();
  		tblList.clear();
        while(resultSet.next()) {
       	 
       	 tblList.add(new tableViewAskQuestion(resultSet.getString("questionID"),resultSet.getString("questionName"),resultSet.getString("question"),resultSet.getString("answer"),resultSet.getString("studentClass"),resultSet.getString("lessonName"),resultSet.getString("studentName"),resultSet.getString("teacherName"),resultSet.getString("questionDate"),resultSet.getString("answerDate")));
       	 tableview.setItems(tblList);
        }
        db.connectClose();
        statement.close();
        resultSet.close();			
  	}catch (SQLException e) {
			System.out.println(e.getMessage());
		}

   }
  
    private void loadData() {
   	refreshTable();
   	studentName_clm.setCellValueFactory(new PropertyValueFactory<>("studentName"));
   	studentClass_clm.setCellValueFactory(new PropertyValueFactory<>("studentClass"));
   	questionName_clm.setCellValueFactory(new PropertyValueFactory<>("questionName"));
   	questionDate_clm.setCellValueFactory(new PropertyValueFactory<>("questionDate"));
   	answerDate_clm.setCellValueFactory(new PropertyValueFactory<>("answerDate"));
   }
    
    String questionID;
    void answerUpdate() {
    	
    	try {
        		db.connectOpen();
            	query="UPDATE privatequestion SET answer=? , answerDate=(SELECT CURRENT_DATE) WHERE lessonName=? AND questionID=?";
            	statement=db.connection.prepareStatement(query);
            	statement.setString(1,answer_textarea.getText());
            	statement.setString(2,teacherLesson);
            	statement.setString(3,questionID);
                statement.execute();
            	db.connectClose();
                statement.close();
                resultSet.close();	
           
        		
        	}catch (Exception e) {
        		System.out.println(e.getMessage());
    		}
    	
    }
    

    @FXML
    void answerAdd_button_Click(ActionEvent event) {
    	if(answer_textarea.getText()!="" &teacherLesson!=null) {	
    		answerUpdate();
    	}
    	else {
    		uyari_label.setVisible(true);
    	}
    }

    @FXML
    void answerRefresh_button_Click(ActionEvent event) {
    	userInfo();
        if(username_text.getText()!="" & password_text.getText()!="" & lessonID!=null) {
        	loadData();}
        else {
        	uyari_label.setVisible(true);
        }
    }

    @FXML
    void tableview_Click(MouseEvent event) {
    	tbl=tableview.getSelectionModel().getSelectedItem();
      	answer_textarea.setText(tbl.getAnswer());
      	question_textarea.setText(tbl.getQuestion());
      	questionID=tbl.getQuestionID();
    }

    @FXML
    void initialize() {
       

    }

}
