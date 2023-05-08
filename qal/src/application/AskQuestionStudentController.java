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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AskQuestionStudentController {

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
    private TableColumn<tableViewAskQuestion, String> lessonName_clm;

    @FXML
    private ComboBox<String> lesson_cBox;

    @FXML
    private Label lesson_label;

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
    private TableView<tableViewAskQuestion> tableview;

    @FXML
    private TableColumn<tableViewAskQuestion, String> teacherName_clm;

    @FXML
    private TextField username_text;

    @FXML
    private Label username_label;
    
    @FXML
    private Label uyari_label;
    
    @FXML
    private Label questionName_label;

    @FXML
    private TextField questionName_text;
    
    DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;
    tableViewAskQuestion tbl=null;
    ObservableList<tableViewAskQuestion> tblList=FXCollections.observableArrayList();
     void comboBoxLoad(ComboBox<String> cmb) {
    	
    	try {	
    		db.connectOpen();
        	query="SELECT * FROM lesson";
        	statement=db.connection.prepareStatement(query);
        	resultSet=statement.executeQuery();
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
     String studentName,studentClass;
     
     void userInfo() {
     	
     	try {	
     		db.connectOpen();
     		query="SELECT * FROM student where studentUsername=? and studentPassword=?";
         	statement=db.connection.prepareStatement(query);
        	statement.setString(1, username_text.getText().trim());
        	statement.setString(2, password_text.getText().trim());
        	resultSet=statement.executeQuery();
        	resultSet.next();
        	
        	if (username_text.getText().equals(resultSet.getString("studentUsername")) && password_text.getText().equals(resultSet.getString("studentPassword")))
            {  studentName=resultSet.getString("studentName");
            studentName=studentName+" "+resultSet.getString("studentSurname");
               studentClass=resultSet.getString("studentClass");
            }
        	db.connectClose();
            statement.close();
            resultSet.close();
     	}catch (Exception e) {
     		System.out.println(e.getMessage());
 		} 
     	
     }
     
     
     void questionAdd(){
    	   	try {
    			db.connectOpen();
    	    	query="INSERT INTO privatequestion(questionName, question,studentClass,lessonName,studentName,questionDate) VALUES (?,?,?,?,?,(SELECT CURRENT_DATE))";
    	    	statement=db.connection.prepareStatement(query);
    	    	statement.setString(1,questionName_text.getText().trim());
    	    	statement.setString(2,question_textarea.getText());
    	    	statement.setString(3,studentClass);
    	    	statement.setString(4,lesson_cBox.getSelectionModel().getSelectedItem());
    	    	statement.setString(5,studentName);
    	    	statement.execute();
    	    	db.connectClose();
    	        statement.close();
    		}catch (Exception e) {
    			System.out.println(e.getMessage());
    		}  
    	   }
    	   
    
     private void refreshTable() {
     	  try { userInfo();
     		    db.connectOpen();
     	        query="SELECT * FROM privatequestion WHERE studentName=?";
     	        statement=db.connection.prepareStatement(query);
     	        statement.setString(1,studentName);
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
         	teacherName_clm.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
         	questionName_clm.setCellValueFactory(new PropertyValueFactory<>("questionName"));
         	lessonName_clm.setCellValueFactory(new PropertyValueFactory<>("lessonName"));
         	questionDate_clm.setCellValueFactory(new PropertyValueFactory<>("questionDate"));
         	answerDate_clm.setCellValueFactory(new PropertyValueFactory<>("answerDate"));

         	
         }
     
     

    @FXML
    void answerAdd_button_Click(ActionEvent event) {
    	if(lesson_cBox.getSelectionModel().getSelectedItem()!=null & questionName_text.getText()!="" & question_textarea.getText()!="") {
    	userInfo();
    		if(studentClass!=null & studentName!=null) {
    			questionAdd();}
    		else {
        		uyari_label.setVisible(true);
        	}
    		
    		
     }
    	else {
    		uyari_label.setVisible(true);
    	}
    }

    @FXML
    void answerRefresh_button_Click(ActionEvent event) {
    	userInfo();
    	if(studentClass!=null & studentName!=null) {
    		loadData();}
		else {
    		uyari_label.setVisible(true);
    	}

    	
    }

    @FXML
    void tableview_Click(MouseEvent event) {
    	tbl=tableview.getSelectionModel().getSelectedItem();
    	questionName_text.setText(tbl.getQuestionName());
      	answer_textarea.setText(tbl.getAnswer());
      	question_textarea.setText(tbl.getQuestion());
    }
      
    

    @FXML
    void initialize() {
    	lesson_cBox.getItems().clear();
    	comboBoxLoad(lesson_cBox);
    	
    
    }

}
