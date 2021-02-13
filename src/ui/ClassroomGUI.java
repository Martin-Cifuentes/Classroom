package ui;


import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Browser;
import model.Career;
import model.Classroom;
import model.Gender;
import model.UserAccount;

public class ClassroomGUI {
	
	//mainpane
	@FXML
    private BorderPane mainPanel;
	
	//UsersList
	@FXML
    private Label profileName;

    @FXML
    private ImageView profileImage;
    
	@FXML
    private TableView<UserAccount> tvUsersList;

    @FXML
    private TableColumn<UserAccount, String> tcName;

    @FXML
    private TableColumn<UserAccount, String> tcGender;

    @FXML
    private TableColumn<UserAccount, String> tcCareer;

    @FXML
    private TableColumn<UserAccount, String> tcBirthday;

    @FXML
    private TableColumn<UserAccount, Browser> tcBrowser;
	
    //Signup
	ObservableList<String> browsers = FXCollections.observableArrayList("FIREFOX",
			"CHROME", "EDGE", "SAFARI","OPERA","THOR","BRAVE");
	
	 @FXML
    private PasswordField txtNewPassword;

    @FXML
    private TextField txtNewName;

    @FXML
    private TextField txtPicture;

    @FXML
    private DatePicker txtBirthday;

    @FXML
    private ChoiceBox browserChoice;
    @FXML
    private Label warningSignup;
    
    @FXML
    void addAccount(ActionEvent event) {
    	String name = txtNewName.getText();
    	String password = txtNewPassword.getText();
    	String picture = txtPicture.getText();
    	String birthday = txtBirthday.getValue().toString();
    	
    	
    	Gender g = null;
    	if(radMale.isSelected()) {
    		g = Gender.MALE;
    	}else if(radFemale.isSelected()) {
    		g = Gender.FEMALE;
    	}else if(radOther.isSelected()) {
    		g = Gender.OTHER;
    	}else {
    		warningSignup.setOpacity(1);
    		return;
    	}
    	String careers = "";
    	if(boxSoftware.isSelected()) {
    		careers += "Software Engineering";
    	}else if(boxTelematic.isSelected()) {
    		careers += "Telematic Engineering";
    	}else if(boxIndustrial.isSelected()) {
    		careers += "Industrial Engineering";
    	}else {
    		warningSignup.setOpacity(1);
    		return;
    	}
    	Browser browser = null;
    	if(browserChoice.getValue() == "FIREFOX") {
    		browser = Browser.FIREFOX;
    	}else if(browserChoice.getValue() == "CHROME") {
    		browser = Browser.CHROME;
    	}else if(browserChoice.getValue() == "EDGE") {
    		browser = Browser.EDGE;
    	}else if(browserChoice.getValue() == "SAFARI") {
    		browser = Browser.SAFARI;
    	}else if(browserChoice.getValue() == "THOR") {
    		browser = Browser.THOR;
    	}else if(browserChoice.getValue() == "BRAVE") {
    		browser = Browser.BRAVE;
    	}else if(browserChoice.getValue() == "OPERA") {
    		browser = Browser.OPERA;
    	}else {
    		warningSignup.setOpacity(1);
    		return;
    	}
    		
    	if(name.equals("") || password.equals("") || picture.equals("") || birthday.equals("")) {
    		
    		System.out.println("name "+name);
    		System.out.println("password "+password);
    		System.out.println("picture "+picture);
    		System.out.println("birthday "+birthday);
    		warningSignup.setOpacity(1);
    	}else {
    		classroom.createAccount(name, password, picture, g, careers, birthday, browser);
    		
    		if(classroom.findUser(name) >= 0) {
    			
    			warningSignup.setOpacity(1);
    			warningSignup.setText("User created");
    		}
    	}
    	
    	//(txtNewName,txtNewPassword,txtPicture, g, careers, txtBirthday, browser );
    }

    @FXML
    void openFiles(ActionEvent event) throws IOException{
    	
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	Stage stage = new Stage();
		File f = fileChooser.showOpenDialog(stage);
		//txtPicture.setText(f.getAbsolutePath());
		txtPicture.setText(f.getPath());
    }
    @FXML
    private RadioButton radMale;

    @FXML
    private RadioButton radFemale;

    @FXML
    private RadioButton radOther;
    
    @FXML
    private CheckBox boxSoftware;

    @FXML
    private CheckBox boxTelematic;

    @FXML
    private CheckBox boxIndustrial;
    
  //General
	@FXML
  	private void initializeSignUp() {
  		browserChoice.setItems(browsers);
  		ToggleGroup toggle = new ToggleGroup();
  		radMale.setToggleGroup(toggle);
  		radFemale.setToggleGroup(toggle);
  		radOther.setToggleGroup(toggle);
  		
  	}
    //login 
    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;
    
    @FXML
    private Label confirmMsg;
    
    private Classroom classroom;
    
    public ClassroomGUI(Classroom c) {
    	setClassroom(c);
    }
    //mainpane methods
    @FXML
    void changeToLoginScreen(ActionEvent event) throws IOException {
    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("login.fxml"));
    	fxmlloader.setController(this);
    	
    	//Parent loginpane = FXMLLoader.load(getClass().getResource("login.fxml"));
    	Parent loginpane = fxmlloader.load();
    	loginpane.autosize();
    	
    	mainPanel.getChildren().setAll(loginpane);
    	
    }
    //login methods
    @FXML
    void verifyAccount(ActionEvent event) throws IOException {
    	String name = txtUsername.getText();
    	String password = txtPassword.getText();
    	boolean x = classroom.confirmLogIn(name, password);
    	if(x) {
    		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("UsersList.fxml"));
        	fxmlloader.setController(this);
        	
        	//Parent loginpane = FXMLLoader.load(getClass().getResource("login.fxml"));
        	Parent loginpane = fxmlloader.load();
        	loginpane.autosize();
        	
        	mainPanel.getChildren().setAll(loginpane);
        	initializeProfile(name);
        	initializeTableView();
    	}else {
    		confirmMsg.setText("the user doesn't exist");
    	}
    }
    private void initializeProfile(String name) {
    	profileName.setText(name);
    	int x = classroom.findUser(name);
    	//Image image = new Image(classroom.getUsers().get(x).getProfilePicture());
    	//profileImage.setImage(image);
    }
    private void initializeTableView() {
    	ObservableList<UserAccount> observableList;
    	observableList = FXCollections.observableArrayList(classroom.getUsers());
    	
    	tvUsersList.setItems(observableList);
    	tcName.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("Name"));
    	tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("Gender"));
    	tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("Careers"));
    	tcBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("Birthday"));
    	tcBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount,Browser>("FavBrowser"));
    	System.out.println(tvUsersList.toString());
    }
    @FXML
    void changeToSignUpScreen(ActionEvent event) throws IOException {
    	FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Signup.fxml"));
    	fxmlloader.setController(this);
    	
    	//Parent loginpane = FXMLLoader.load(getClass().getResource("login.fxml"));
    	Parent loginpane = fxmlloader.load();
    	loginpane.autosize();
    	
    	mainPanel.getChildren().setAll(loginpane);
    	initializeSignUp();
    	
    }
    
    

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

}