package controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import db.DataBase;
import db.TacheData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;



public class AccueilController implements Initializable {
	//Composants of main table
	
	@FXML
	private AnchorPane accueilmain;
	@FXML
    private Button close;
	
	@FXML
    private Button minimize;
	
	@FXML
    private Button iubtn;
	
	@FXML
	private Button ibnubtn;

	@FXML
	private Button nibubtn;
	
	@FXML
    private Button nianubtn;
	
	@FXML
    private Button deletedbtn_main;
	
	@FXML
    private Button archivedbtn_main;
	
	@FXML
	private Button morebtn;

	//Composants de la fenetre IU

    @FXML
    private AnchorPane ImportantandUrgent;
    
    @FXML
    private TableView<TacheData> tableauofiu;
    
    @FXML
    private TableColumn<TacheData, String> task_colonneIU; 
    
    @FXML
    private TableColumn<TacheData, String> descrip_colonneIU;

    @FXML
    private TableColumn<TacheData, Date> deadline_colonneIU; 
    
    @FXML
    private TableColumn<TacheData, String> time_colonneIU; 
    
    @FXML
    private TableColumn<TacheData, String> done_colonneIU; 
    
    @FXML
    private Button addnewtaskbtnIU;
    
	//Composants de la fenetre IbnU

    
    @FXML
    private AnchorPane ImportantbutnotUrgent;
    
    @FXML
    private TableView<?> tableauofibnu;
    
    @FXML
    private TableColumn<?, ?> task_colonneIBNU;
    
    @FXML
    private TableColumn<?, ?> descrip_colonneIBNU;
    
    @FXML
    private TableColumn<?, ?> deadline_colonneIBNU;
    
    @FXML
    private TableColumn<?, ?> time_colonneIBNU;
    
    @FXML
    private TableColumn<?, ?> done_colonneIBNU;
    
    @FXML
    private Button addnewtaskbtnIBNU;
    
	//Composants de la fenetre NIBU

    @FXML
    private AnchorPane NotImportantbutUrgent;
    
    @FXML
    private TableView<?> tableauofnibu;
    
    @FXML
    private TableColumn<?, ?> task_colonneNIBU;
    
    @FXML
    private TableColumn<?, ?> descrip_colonneNIBU;
    
    @FXML
    private TableColumn<?, ?> deadline_colonneNIBU;
    
    @FXML
    private TableColumn<?, ?> time_colonneNIBU;
    
    @FXML
    private TableColumn<?, ?> done_colonneNIBU;
    
    @FXML
    private Button addnewtaskbtnNIBU;
    
	//Composants de la fenetre NIANU

    
    @FXML
    private AnchorPane NotImportantAndNotUrgent;
    
    @FXML
    private TableView<?> tableauofnianu;
    
    @FXML
    private TableColumn<?, ?> task_colonneNIANU;
    
    @FXML
    private TableColumn<?, ?> descrip_colonneNIANU;
    
    @FXML
    private TableColumn<?, ?> deadline_colonneNIANU;
    
    @FXML
    private TableColumn<?, ?> time_colonneNIANU;
    
    @FXML
    private TableColumn<?, ?> done_colonneNIANU;
    
    @FXML
    private Button addnewtaskbtnNIANU;
    //Composants de la table deleted
    @FXML
    private AnchorPane Deletetab;
    
    @FXML
    private TableView<TacheData> tableauofdeled;
    
    @FXML
    private TableColumn<TacheData, String> taskcoldel; 
    
    @FXML
    private TableColumn<TacheData, String> descripcoldel;

    @FXML
    private TableColumn<TacheData, Date> deadlinecoldel; 
    
    @FXML
    private TableColumn<TacheData, String> timecoldel; 
    
    @FXML
    private TableColumn<TacheData, String> donecoldel; 
    
    @FXML
    private Button archivedbtndel;
    
    @FXML
    private Button defdeleted;
    
    @FXML
    private MenuButton recoverchoicedel;
    
    @FXML
    private MenuItem choiceiudel;
    
    @FXML
    private MenuItem choiceibnudel;
    
    @FXML
    private MenuItem choicenibudel;
    
    @FXML
    private MenuItem choicenianudel;
    
    @FXML
    private TextField TaskDelfield;
    
    @FXML
    private TextField DescripDelfield;
    
    @FXML
    private DatePicker DeadDelfield;
    
    @FXML
    private TextField TimeDelfield;
    
    @FXML
    private ComboBox<String> DoneDelfield;

    //Composants de la fenetre archived
    
    @FXML
    private AnchorPane archivedtab;
    
    @FXML
    private TableView<TacheData> tableauarchived;
    
    @FXML
    private TableColumn<TacheData, String> taskarchi; 
    
    @FXML
    private TableColumn<TacheData, String> descriparchi;

    @FXML
    private TableColumn<TacheData, Date> deadarchi; 
    
    @FXML
    private TableColumn<TacheData, String> timearchi; 
    
    @FXML
    private TableColumn<TacheData, String> donearchi;
    
    @FXML
    private MenuButton recoverchoicearchi;
    
    @FXML
    private MenuItem choiceiuarchi;
    
    @FXML
    private MenuItem choiceibnuarchi;
    
    @FXML
    private MenuItem choicenibuarchi;
    
    @FXML
    private MenuItem choicenianuarchi;
    
    @FXML
    private Button deletedbtnarchi;
    
    @FXML
    private TextField TaskArchivedfield;
    
    @FXML
    private TextField DescripArchivedfield;
    
    @FXML
    private DatePicker DeadArchivedfield;
    
    @FXML
    private TextField TimeArchivedfield;
    
    @FXML
    private ComboBox<String> DoneArchivedfield;
    //composant de la fenetre help
    @FXML
    private AnchorPane helptab;
    
    //composants de la fenetre viewtaskIU
    
    @FXML
    private AnchorPane viewtaskofiu;
    
    @FXML
    private TextField taskmodifIU;
    
    @FXML
    private TextField descripmodifIU;
    
    @FXML
    private DatePicker deadlinemodifIU;
    
    @FXML
    private TextField timemodifIU;
    
    @FXML
    private ComboBox<String> donemodifIU;
    
    @FXML
    private Button modifOKiu;
    
    @FXML
    private Button archivedtaskbtniu;
    
    @FXML
    private Button deletedtaskbtniu;
    
    @FXML
    private MenuButton changefolderiubtn;
    
    @FXML
    private MenuItem choiceibnuIU;
    
    @FXML
    private MenuItem choicenibuIU;
    
    @FXML
    private MenuItem choicenianuIU;
    
//composants de la fenetre viewtaskIBNU
    
    @FXML
    private AnchorPane viewtaskofibnu;
    
    @FXML
    private TextField taskmodifIBNU;
    
    @FXML
    private TextField descripmodifIBNU;
    
    @FXML
    private DatePicker deadlinemodifIBNU;
    
    @FXML
    private TextField timemodifIBNU;
    
    @FXML
    private ComboBox<String> donemodifIBNU;
    
    @FXML
    private Button modifOKibnu;
    
    @FXML
    private Button archivedtaskbtnibnu;
    
    @FXML
    private Button deletedtaskbtnibnu;
    
    @FXML
    private MenuButton changefolderibnubtn;
    
    @FXML
    private MenuItem choiceiuIBNU;
    
    @FXML
    private MenuItem choicenibuIBNU;
    
    @FXML
    private MenuItem choicenianuIBNU;
    
//composants de la fenetre viewtaskNIBU
    
    @FXML
    private AnchorPane viewtaskofnibu;
    
    @FXML
    private TextField taskmodifNIBU;
    
    @FXML
    private TextField descripmodifNIBU;
    
    @FXML
    private DatePicker deadlinemodifNIBU;
    
    @FXML
    private TextField timemodifNIBU;
    
    @FXML
    private ComboBox<String> donemodifNIBU;
    
    @FXML
    private Button modifOKnibu;
    
    @FXML
    private Button archivedtaskbtnnibu;
    
    @FXML
    private Button deletedtaskbtnnibu;
    
    @FXML
    private MenuButton changefoldernibubtn;
    
    @FXML
    private MenuItem choiceiuNIBU;
    
    @FXML
    private MenuItem choiceibnuNIBU;
    
    @FXML
    private MenuItem choicenianuNIBU;
    
//composants de la fenetre viewtasNIANU
    
    @FXML
    private AnchorPane viewtaskofnianu;
    
    @FXML
    private TextField taskmodifNIANU;
    
    @FXML
    private TextField descripmodifNIANU;
    
    @FXML
    private DatePicker deadlinemodifNIANU;
    
    @FXML
    private TextField timemodifNIANU;
    
    @FXML
    private ComboBox<String> donemodifNIANU;
    
    @FXML
    private Button modifOKnianu;
    
    @FXML
    private Button archivedtaskbtnnianu;
    
    @FXML
    private Button deletedtaskbtnnianu;
    
    @FXML
    private MenuButton changefoldernianubtn;
    
    @FXML
    private MenuItem choiceiuNIANU;
    
    @FXML
    private MenuItem choiceibnuNIANU;
    
    @FXML
    private MenuItem choicenibuNIANU;
 //composants de la fenetre addtaskofiu
    @FXML
    private AnchorPane addtaskofiu;

    @FXML
    private TextField taskIU;
    
    @FXML
    private TextField descripIU;
    
    @FXML
    private DatePicker deadlineIU;
    
    @FXML
    private TextField timeIU;
    
    @FXML
    private ComboBox<String> doneIU;
    
    @FXML
    private Button addtaskofiubtn;
    
    @FXML
    private Button abandoniu;
    
    //composants de la fenetre addtaskofiBNu
    @FXML
    private AnchorPane addtaskofibnu;

    @FXML
    private TextField taskIBNU;
    
    @FXML
    private TextField descripIBNU;
    
    @FXML
    private DatePicker deadlineIBNU;
    
    @FXML
    private TextField timeIBNU;
    
    @FXML
    private ComboBox<String> doneIBNU;
    
    @FXML
    private Button addtaskbtnIBNU;
    
    @FXML
    private Button abandonibnu;
    
    //composants de la fenetre addtaskofiu
    @FXML
    private AnchorPane addtaskofnibu;

    @FXML
    private TextField taskNIBU;
    
    @FXML
    private TextField descripNIBU;
    
    @FXML
    private DatePicker deadlineNIBU;
    
    @FXML
    private TextField timeNIBU;
    
    @FXML
    private ComboBox<String> doneNIBU;
    
    @FXML
    private Button addtaskbtnNIBU;
    
    @FXML
    private Button abandonnibu;
    
    //composants de la fenetre addtaskofiu
    @FXML
    private AnchorPane addtaskofnianu;

    @FXML
    private TextField taskNIANU;
    
    @FXML
    private TextField descripNIANU;
    
    @FXML
    private DatePicker deadlineNIANU;
    
    @FXML
    private TextField timeNIANU;
    
    @FXML
    private ComboBox<String> doneNIANU;
    
    @FXML
    private Button addtaskbtnNIANU;
    
    @FXML
    private Button abandonnianu;
   // 

    @FXML
    private Button addsubibnubtn;

    @FXML
    private Button addsubiubtn;

    @FXML
    private Button addsubnianubtn;

    @FXML
    private AnchorPane addsubtaskofibnu;

    @FXML
    private Button addsubtaskofibnubtn;

    @FXML
    private AnchorPane addsubtaskofiu;

    @FXML
    private Button addsubtaskofiubtn;

    @FXML
    private AnchorPane addsubtaskofnianu;

    @FXML
    private Button addsubtaskofnianubtn;

    @FXML
    private AnchorPane addsubtaskofnibu;

    @FXML
    private Button addsubtaskofnibubtn;

    @FXML
    private Button archivedbtn;

    @FXML
    private TableColumn<?, ?> deadlinesubcolibnu;

    @FXML
    private TableColumn<?, ?> deadlinesubcoliu;

    @FXML
    private TableColumn<?, ?> deadlinesubcolnianu;

    @FXML
    private TableColumn<?, ?> deadlinesubcolnibu;

    @FXML
    private DatePicker deadlinesubibnu;

    @FXML
    private DatePicker deadlinesubiu;

    @FXML
    private DatePicker deadlinesubnianu;

    @FXML
    private DatePicker deadlinesubnibu;

    @FXML
    private Button deletedbtn; 

    @FXML
    private Button deletestibnu;

    @FXML
    private Button deletestnianu;

    @FXML
    private Button deletestnibu;

    @FXML
    private Button deletesubtiu;

    @FXML
    private TableColumn<?, ?> descripsubtaskcolibnu;

    @FXML
    private TableColumn<?, ?> descripsubtaskcoliu;

    @FXML
    private TableColumn<?, ?> descripsubtaskcolnianu;

    @FXML
    private TableColumn<?, ?> descripsubtaskcolnibu;

    @FXML
    private TextField descripsubtaskibnu;

    @FXML
    private TextField descripsubtasknianu;

    @FXML
    private TextField descripsubtasknibu;

    @FXML
    private TableColumn<?, ?> donesubcolibnu;

    @FXML
    private TableColumn<?, ?> donesubcoliu;

    @FXML
    private TableColumn<?, ?> donesubcolnianu;

    @FXML
    private TableColumn<?, ?> donesubcolnibu;

    @FXML
    private ComboBox<?> donesubtaskibnu;

    @FXML
    private ComboBox<?> donesubtaskiu;

    @FXML
    private ComboBox<?> donesubtasknianu;

    @FXML
    private ComboBox<?> donesubtasknibu;

    @FXML
    private TableColumn<?, ?> subtask_colibnu;

    @FXML
    private TableColumn<?, ?> subtask_coliu;

    @FXML
    private TableColumn<?, ?> subtask_colnianu;

    @FXML
    private TableColumn<?, ?> subtask_colnibu;

    @FXML
    private TextField subtask_nianu;

    @FXML
    private TextField subtaskibnu;

    @FXML
    private TextField subtaskiu;

    @FXML
    private TextField subtasknibu;

    @FXML
    private TableView<?> tableausubtaskibnu;

    @FXML
    private TableView<?> tableausubtaskiu;

    @FXML
    private TableView<?> tableausubtasknianu;

    @FXML
    private TableView<?> tableausubtasknibu;
    
    @FXML
    private Button updatestnianu;

    @FXML
    private Button updatestnibu;

    @FXML
    private Button updateviewstibnu;

    @FXML
    private Button updateviewstiu;

    @FXML
    private DatePicker viewdeadlinefieldibnu;

    @FXML
    private DatePicker viewdeadlinefieldiu;

    @FXML
    private DatePicker viewdeadlinefieldnianu;

    @FXML
    private DatePicker viewdeadlinefieldnibu;

    @FXML
    private TextField viewdescripfieldibnu;

    @FXML
    private TextField viewdescripfieldiu;

    @FXML
    private TextField viewdescripfieldnianu;

    @FXML
    private TextField viewdescripfieldnibu;

    @FXML
    private ComboBox<?> viewdonefieldibnu;

    @FXML
    private ComboBox<?> viewdonefieldiu;

    @FXML
    private ComboBox<?> viewdonefieldnianu;

    @FXML
    private ComboBox<?> viewdonefieldnibu;

    @FXML
    private TextField viewsubtaskfieldibnu;

    @FXML
    private TextField viewsubtaskfieldiu;

    @FXML
    private TextField viewsubtaskfieldnianu;

    @FXML
    private TextField viewsubtaskfieldnibu;

    @FXML
    private AnchorPane viewsubtaskofibnu;

    @FXML
    private AnchorPane viewsubtaskofiu;

    @FXML
    private AnchorPane viewsubtaskofnianu;

    @FXML
    private AnchorPane viewsubtaskofnibu;


    /*	Idée d'amélioration de l'app
     * ------------------------------
     * Mettre l'option de création d'un compte et de déconnection
     * ajouter une tache faire en sorte que le user n'aie pas d'autres choix que de mettre la date d'aujourd'hui
     *     
     *  et si il met la date d'aujourd'hui, l'obliger à mettre l'heure après l'heure actuelle
     *     
     *  rajouter la condition que si il reste 2 jours avant le deadline ou 2h avant le time line mettre la tache en haut en rouge


     */
    
    //cette section de code est reservée la partie des IU
    
    private Connection connect;
  
    private PreparedStatement prepare;
    private ResultSet result;
    
   
    //Ajouter une tache
    
    
    public void addTaskIU() {
        String sql = "INSERT INTO tache (name, description, deadline, time, done, folder) VALUES (?, ?, ?, ?, ?,'Important and Urgent')";

        connect = DataBase.connectDb();
   
        try {
            Alert alert;
            if (taskIU.getText().isEmpty() || timeIU.getText().isEmpty()
                    || deadlineIU.getValue() == null
                    || doneIU.getSelectionModel().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill * fields");
                alert.showAndWait();
            } else {
                String check = "SELECT name FROM tache WHERE name = ? AND folder = 'Important and Urgent'";
                prepare = connect.prepareStatement(check);
                prepare.setString(1, taskIU.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Task Name: " + taskIU.getText() + " already exists!");
                    alert.showAndWait();
                } else {
                    
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, taskIU.getText());
                    prepare.setString(2, descripIU.getText());
                    prepare.setString(3, deadlineIU.getValue().toString());
                    prepare.setString(4, timeIU.getText());
                    prepare.setString(5, doneIU.getSelectionModel().getSelectedItem().toString());
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // Affichage et masquage des éléments
                    
                    ImportantandUrgent.setVisible(true);
                    ImportantbutnotUrgent.setVisible(false);
                    NotImportantbutUrgent.setVisible(false);
                    NotImportantAndNotUrgent.setVisible(false);
                    Deletetab.setVisible(false);
                    archivedtab.setVisible(false);
                    helptab.setVisible(false);
                    addtaskofiu.setVisible(false);
                    addtaskofibnu.setVisible(false);
                    addtaskofnibu.setVisible(false);
                    addtaskofnianu.setVisible(false);
                    viewtaskofiu.setVisible(false);
                    viewtaskofibnu.setVisible(false);
                    viewtaskofnibu.setVisible(false);
                    viewtaskofnianu.setVisible(false);
                    addsubtaskofiu.setVisible(false);
                    addsubtaskofibnu.setVisible(false);
                    addsubtaskofnibu.setVisible(false);
                    addsubtaskofnianu.setVisible(false);
                    viewsubtaskofiu.setVisible(false);
                    viewsubtaskofibnu.setVisible(false);
                    viewsubtaskofnibu.setVisible(false);
                    viewsubtaskofnianu.setVisible(false);

                    ShowTaskIU();
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
// cette fonction permet de selectionner les taches de IU dans la bd
    
    public ObservableList<TacheData> SelectInBdTaskIU(){
    	
    	ObservableList<TacheData> listData = FXCollections.observableArrayList();
    	// on selectionne dans la bd la liste des taches pour IU
    	
    	String sql = "SELECT * FROM tache WHERE folder = 'important and urgent'"; //maybe add where folder=IU for IU ?
    			
    	connect = DataBase.connectDb();
    	
    	try {
    		prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            TacheData TacheD;
            
            while (result.next()) {
            	TacheD = new TacheData(
                        result.getString("name"),
                        result.getString("description"),
                        result.getDate("deadline"),
                        result.getString("time"),
                        result.getString("done"),
                        result.getString("folder"));
                listData.add(TacheD);}
            
    	}catch (Exception e) {e.printStackTrace();}
    	return listData;
    			
    			
    }

  //cette fonction permet d'afficher les taches prise dans la fonction précédentes sur le tableau IU
    
    private ObservableList<TacheData> addTacheList;
        
    public void ShowTaskIU() {
    	
    	addTacheList = SelectInBdTaskIU();
    	task_colonneIU.setCellValueFactory(new PropertyValueFactory<>("name"));
    	descrip_colonneIU.setCellValueFactory(new PropertyValueFactory<>("description"));
    	deadline_colonneIU.setCellValueFactory(new PropertyValueFactory<>("deadline"));
    	time_colonneIU.setCellValueFactory(new PropertyValueFactory<>("time"));
    	done_colonneIU.setCellValueFactory(new PropertyValueFactory<>("done"));

    	tableauofiu.setItems(addTacheList);
    }
 
    //selectionner une ligne d'une colonne IU 
    
    public void SelectTaskIU() {
    	
    	//récupère l'élément sélectionné dans un TableView et le stocke dans TacheD.
    	
    	TacheData TacheD = tableauofiu.getSelectionModel().getSelectedItem();
    	int num = tableauofiu.getSelectionModel().getSelectedIndex();

    	//si aucun élément n'est séléctionné
    	
        if ((num - 1) < -1) {
            return;}
        
        ImportantandUrgent.setVisible(false);
    	ImportantbutnotUrgent.setVisible(false);
    	NotImportantbutUrgent.setVisible(false);
    	NotImportantAndNotUrgent.setVisible(false);
    	Deletetab.setVisible(false);
    	archivedtab.setVisible(false);
    	helptab.setVisible(false);
    	addtaskofiu.setVisible(false);
    	addtaskofibnu.setVisible(false);
    	addtaskofnibu.setVisible(false);
    	addtaskofnianu.setVisible(false);
    	viewtaskofiu.setVisible(true);
    	viewtaskofibnu.setVisible(false);
    	viewtaskofnibu.setVisible(false);
    	viewtaskofnianu.setVisible(false);
    	addsubtaskofiu.setVisible(false);
    	addsubtaskofibnu.setVisible(false);
    	addsubtaskofnibu.setVisible(false);
    	addsubtaskofnianu.setVisible(false);
    	viewsubtaskofiu.setVisible(false);
    	viewsubtaskofibnu.setVisible(false);
    	viewsubtaskofnibu.setVisible(false);
    	viewsubtaskofnianu.setVisible(false);

        
        taskmodifIU.setText(TacheD.getName());
        taskmodifIU.setDisable(true);
        descripmodifIU.setText(TacheD.getDescription());
        deadlinemodifIU.setValue(TacheD.getDeadline().toLocalDate());
        timemodifIU.setText(TacheD.getTime());
        donemodifIU.setValue(TacheD.getDone());
    }
    
    
    
    
    //Mettre à jour les données
    
    public void UpdateTaskIU() {
    	String sql = "UPDATE tache SET description = ?, deadline = ?, time = ?, done = ? WHERE folder = 'Important and Urgent' AND name= ?";


        connect = DataBase.connectDb();
            
            try {
                Alert alert;
                if (deadlinemodifIU.getValue() == null
                        || donemodifIU.getSelectionModel().isEmpty()|| timemodifIU.getText().isEmpty()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill * fields");
                    alert.showAndWait();}
                else {
                        prepare = connect.prepareStatement(sql);
                        prepare.setString(1, descripmodifIU.getText());
                        prepare.setString(2, deadlinemodifIU.getValue().toString());
                        prepare.setString(3, timemodifIU.getText());
                        prepare.setString(4, donemodifIU.getSelectionModel().getSelectedItem().toString());
                        prepare.setString(5, taskmodifIU.getText()); 
                        prepare.executeUpdate();

                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Update!");
                        alert.showAndWait();
                        ImportantandUrgent.setVisible(true);
                        ImportantbutnotUrgent.setVisible(false);
                        NotImportantbutUrgent.setVisible(false);
                        NotImportantAndNotUrgent.setVisible(false);
                        Deletetab.setVisible(false);
                        archivedtab.setVisible(false);
                        helptab.setVisible(false);
                        addtaskofiu.setVisible(false);
                        addtaskofibnu.setVisible(false);
                        addtaskofnibu.setVisible(false);
                        addtaskofnianu.setVisible(false);
                        viewtaskofiu.setVisible(false);
                    	viewtaskofibnu.setVisible(false);
                    	viewtaskofnibu.setVisible(false);
                    	viewtaskofnianu.setVisible(false);
                    	addsubtaskofiu.setVisible(false);
                    	addsubtaskofibnu.setVisible(false);
                    	addsubtaskofnibu.setVisible(false);
                    	addsubtaskofnianu.setVisible(false);
                    	viewsubtaskofiu.setVisible(false);
                    	viewsubtaskofibnu.setVisible(false);
                    	viewsubtaskofnibu.setVisible(false);
                    	viewsubtaskofnianu.setVisible(false);

                    	ShowTaskIU();
                       
                    }

                

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    
    //supprimer une tache
    
    public void DeleteTaskIU() {
        String sql = "INSERT INTO tache (name, description, deadline, time, done, folder) VALUES (?, ?, ?, ?, ?, 'Deleted')";
        connect = DataBase.connectDb();
        try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE task: " + taskmodifIU.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, taskmodifIU.getText());
                prepare.setString(2, descripmodifIU.getText());
                prepare.setString(3, deadlinemodifIU.getValue().toString());
                prepare.setString(4, timemodifIU.getText());
                prepare.setString(5, donemodifIU.getSelectionModel().getSelectedItem().toString());
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();

                // Suppression de la tâche dans la table principale
                String sqldel = "DELETE FROM tache WHERE folder = 'Important and Urgent' AND name = ?";
                prepare = connect.prepareStatement(sqldel);
                prepare.setString(1, taskmodifIU.getText());
                prepare.executeUpdate();
                        
                // Masquer les onglets et autres éléments UI non pertinents
                ImportantandUrgent.setVisible(true);
                ImportantbutnotUrgent.setVisible(false);
                NotImportantbutUrgent.setVisible(false);
                NotImportantAndNotUrgent.setVisible(false);
                Deletetab.setVisible(false);
                archivedtab.setVisible(false);
                helptab.setVisible(false);
                addtaskofiu.setVisible(false);
                addtaskofibnu.setVisible(false);
                addtaskofnibu.setVisible(false);
                addtaskofnianu.setVisible(false);
                viewtaskofiu.setVisible(false);
                viewtaskofibnu.setVisible(false);
                viewtaskofnibu.setVisible(false);
                viewtaskofnianu.setVisible(false);
                addsubtaskofiu.setVisible(false);
                addsubtaskofibnu.setVisible(false);
                addsubtaskofnibu.setVisible(false);
                addsubtaskofnianu.setVisible(false);
                viewsubtaskofiu.setVisible(false);
                viewsubtaskofibnu.setVisible(false);
                viewsubtaskofnibu.setVisible(false);
                viewsubtaskofnianu.setVisible(false);
                        
                // Actualiser la liste des tâches
                ShowTaskIU();
                ShowDeletedTask();
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    
    
    //archiver une tache
    
    public void ArchivedTaskIU() {
    	String sql = "INSERT INTO tache (name, description, deadline, time, done, folder) VALUES (?, ?, ?, ?, ?, 'Archived')";
        connect = DataBase.connectDb();
        try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to ARCHIVE task " + taskmodifIU.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, taskmodifIU.getText());
                prepare.setString(2, descripmodifIU.getText());
                prepare.setString(3, deadlinemodifIU.getValue().toString());
                prepare.setString(4, timemodifIU.getText());
                prepare.setString(5, donemodifIU.getSelectionModel().getSelectedItem().toString());
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Archived!");
                alert.showAndWait();

                // Suppression de la tâche dans la table principale
                String sqldel = "DELETE FROM tache WHERE folder = 'Important and Urgent' AND name = ?";
                prepare = connect.prepareStatement(sqldel);
                prepare.setString(1, taskmodifIU.getText());
                prepare.executeUpdate();
                        
                // Masquer les onglets et autres éléments UI non pertinents
                ImportantandUrgent.setVisible(true);
                ImportantbutnotUrgent.setVisible(false);
                NotImportantbutUrgent.setVisible(false);
                NotImportantAndNotUrgent.setVisible(false);
                Deletetab.setVisible(false);
                archivedtab.setVisible(false);
                helptab.setVisible(false);
                addtaskofiu.setVisible(false);
                addtaskofibnu.setVisible(false);
                addtaskofnibu.setVisible(false);
                addtaskofnianu.setVisible(false);
                viewtaskofiu.setVisible(false);
                viewtaskofibnu.setVisible(false);
                viewtaskofnibu.setVisible(false);
                viewtaskofnianu.setVisible(false);
                addsubtaskofiu.setVisible(false);
                addsubtaskofibnu.setVisible(false);
                addsubtaskofnibu.setVisible(false);
                addsubtaskofnianu.setVisible(false);
                viewsubtaskofiu.setVisible(false);
                viewsubtaskofibnu.setVisible(false);
                viewsubtaskofnibu.setVisible(false);
                viewsubtaskofnianu.setVisible(false);
                        
                // Actualiser la liste des tâches
                ShowTaskIU();
                ShowArchivedTask();
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }

    	
  //Fin de la section IU    
    
    
    //Pour remettre une tache supprimée
    public void RecovertaskDeleted(String folder) {
    
    	//Quand je clique sur le bouton recover, je choisis un dossier, et on me met un message de confirmafation et ensuite le dossier et mis dans le dossier de base
    	
    	String sql = "DELETE FROM tache WHERE folder = 'Deleted'AND name = ?";  
        connect = DataBase.connectDb();
    	TacheData TacheD = tableauofdeled.getSelectionModel().getSelectedItem();
    	int num = tableauofdeled.getSelectionModel().getSelectedIndex();

    	//si aucun élément n'est séléctionné
    	
        if ((num - 1) < -1) {
            return;}
    
        TaskDelfield.setText(TacheD.getName());
        DescripDelfield.setText(TacheD.getDescription());
        DeadDelfield.setValue(TacheD.getDeadline().toLocalDate());
        TimeDelfield.setText(TacheD.getTime());
        DoneDelfield.setValue(TacheD.getDone());
    	
        try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to recover task: " + TaskDelfield.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
            	
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, TaskDelfield.getText());
                
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Recovered!");
                alert.showAndWait();
                
              //If on choisi Important et urgent
            	String sqlrecover = "INSERT INTO tache (name, description, deadline, time, done, folder) VALUES (?, ?, ?, ?, ?, ?)";
            	prepare = connect.prepareStatement(sqlrecover);
                prepare.setString(1, TaskDelfield.getText());
                prepare.setString(2, DescripDelfield.getText());
                prepare.setString(3, DeadDelfield.getValue().toString());
                prepare.setString(4, TimeDelfield.getText());
                prepare.setString(5, DoneDelfield.getSelectionModel().getSelectedItem().toString());
                prepare.setString(6, folder);
                prepare.executeUpdate();
                        
                // Masquer les onglets et autres éléments UI non pertinents
                ImportantandUrgent.setVisible(false);
                ImportantbutnotUrgent.setVisible(false);
                NotImportantbutUrgent.setVisible(false);
                NotImportantAndNotUrgent.setVisible(false);
                Deletetab.setVisible(true);
                archivedtab.setVisible(false);
                helptab.setVisible(false);
                addtaskofiu.setVisible(false);
                addtaskofibnu.setVisible(false);
                addtaskofnibu.setVisible(false);
                addtaskofnianu.setVisible(false);
                viewtaskofiu.setVisible(false);
                viewtaskofibnu.setVisible(false);
                viewtaskofnibu.setVisible(false);
                viewtaskofnianu.setVisible(false);
                addsubtaskofiu.setVisible(false);
                addsubtaskofibnu.setVisible(false);
                addsubtaskofnibu.setVisible(false);
                addsubtaskofnianu.setVisible(false);
                viewsubtaskofiu.setVisible(false);
                viewsubtaskofibnu.setVisible(false);
                viewsubtaskofnibu.setVisible(false);
                viewsubtaskofnianu.setVisible(false);
                        
                // Actualiser la liste des tâches
                ShowTaskIU();
                ShowDeletedTask();
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    
  //Pour remettre une tache archivée
    public void RecovertaskArchived(String folder) {
    
    	//Quand je clique sur le bouton recover, je choisis un dossier, et on me met un message de confirmafation et ensuite le dossier et mis dans le dossier de base
    	
    	String sql = "DELETE FROM tache WHERE folder = 'Deleted'AND name = ?";  
        connect = DataBase.connectDb();
    	TacheData TacheD = tableauarchived.getSelectionModel().getSelectedItem();
    	int num = tableauarchived.getSelectionModel().getSelectedIndex();

    	//si aucun élément n'est séléctionné
    	
        if ((num - 1) < -1) {
            return;}
    
        TaskArchivedfield.setText(TacheD.getName());
        DescripArchivedfield.setText(TacheD.getDescription());
        DeadArchivedfield.setValue(TacheD.getDeadline().toLocalDate());
        TimeArchivedfield.setText(TacheD.getTime());
        DoneArchivedfield.setValue(TacheD.getDone());
    	
        try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to recover task: " + TaskArchivedfield.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
            	
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, TaskArchivedfield.getText());
                
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Recovered!");
                alert.showAndWait();
                
              //If on choisi Important et urgent
            	String sqlrecover = "INSERT INTO tache (name, description, deadline, time, done, folder) VALUES (?, ?, ?, ?, ?, ?)";
            	prepare = connect.prepareStatement(sqlrecover);
                prepare.setString(1, TaskArchivedfield.getText());
                prepare.setString(2, DescripArchivedfield.getText());
                prepare.setString(3, DeadArchivedfield.getValue().toString());
                prepare.setString(4, TimeArchivedfield.getText());
                prepare.setString(5, DoneArchivedfield.getSelectionModel().getSelectedItem().toString());
                prepare.setString(6, folder);
                prepare.executeUpdate();
                        
                // Masquer les onglets et autres éléments UI non pertinents
                ImportantandUrgent.setVisible(false);
                ImportantbutnotUrgent.setVisible(false);
                NotImportantbutUrgent.setVisible(false);
                NotImportantAndNotUrgent.setVisible(false);
                Deletetab.setVisible(true);
                archivedtab.setVisible(false);
                helptab.setVisible(false);
                addtaskofiu.setVisible(false);
                addtaskofibnu.setVisible(false);
                addtaskofnibu.setVisible(false);
                addtaskofnianu.setVisible(false);
                viewtaskofiu.setVisible(false);
                viewtaskofibnu.setVisible(false);
                viewtaskofnibu.setVisible(false);
                viewtaskofnianu.setVisible(false);
                addsubtaskofiu.setVisible(false);
                addsubtaskofibnu.setVisible(false);
                addsubtaskofnibu.setVisible(false);
                addsubtaskofnianu.setVisible(false);
                viewsubtaskofiu.setVisible(false);
                viewsubtaskofibnu.setVisible(false);
                viewsubtaskofnibu.setVisible(false);
                viewsubtaskofnianu.setVisible(false);
                        
                // Actualiser la liste des tâches
                ShowTaskIU();
                ShowArchivedTask();
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    
    //supprimer une tache archivée
    public void FromArchivedToDeleted() {
    	String sql = "INSERT INTO tache (name, description, deadline, time, done, folder) VALUES (?, ?, ?, ?, ?, 'Deleted')";
        connect = DataBase.connectDb();
        TacheData TacheD = tableauarchived.getSelectionModel().getSelectedItem();
    	int num = tableauarchived.getSelectionModel().getSelectedIndex();

    	//si aucun élément n'est séléctionné
    	
        if ((num - 1) < -1) {
            return;}
    
        TaskArchivedfield.setText(TacheD.getName());
        DescripArchivedfield.setText(TacheD.getDescription());
        DeadArchivedfield.setValue(TacheD.getDeadline().toLocalDate());
        TimeArchivedfield.setText(TacheD.getTime());
        DoneArchivedfield.setValue(TacheD.getDone());
        try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete task: " + TaskArchivedfield.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, TaskArchivedfield.getText());
                prepare.setString(2, DescripArchivedfield.getText());
                prepare.setString(3, DeadArchivedfield.getValue().toString());
                prepare.setString(4, TimeArchivedfield.getText());
                prepare.setString(5, DoneArchivedfield.getSelectionModel().getSelectedItem().toString());
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();

                // Suppression de la tâche dans la table principale
                String sqldel = "DELETE FROM tache WHERE folder = 'Archived' AND name = ?";
                prepare = connect.prepareStatement(sqldel);
                prepare.setString(1, TaskArchivedfield.getText());
                prepare.executeUpdate();
                        
                // Masquer les onglets et autres éléments UI non pertinents
                ImportantandUrgent.setVisible(false);
                ImportantbutnotUrgent.setVisible(false);
                NotImportantbutUrgent.setVisible(false);
                NotImportantAndNotUrgent.setVisible(false);
                Deletetab.setVisible(false);
                archivedtab.setVisible(true);
                helptab.setVisible(false);
                addtaskofiu.setVisible(false);
                addtaskofibnu.setVisible(false);
                addtaskofnibu.setVisible(false);
                addtaskofnianu.setVisible(false);
                viewtaskofiu.setVisible(false);
                viewtaskofibnu.setVisible(false);
                viewtaskofnibu.setVisible(false);
                viewtaskofnianu.setVisible(false);
                addsubtaskofiu.setVisible(false);
                addsubtaskofibnu.setVisible(false);
                addsubtaskofnibu.setVisible(false);
                addsubtaskofnianu.setVisible(false);
                viewsubtaskofiu.setVisible(false);
                viewsubtaskofibnu.setVisible(false);
                viewsubtaskofnibu.setVisible(false);
                viewsubtaskofnianu.setVisible(false);
                        
                // Actualiser la liste des tâches
                ShowArchivedTask();
                ShowDeletedTask();
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    
    }
  //archiver une tache supprimée
    public void FromDeletedToArchived() {
    	String sql = "INSERT INTO tache (name, description, deadline, time, done, folder) VALUES (?, ?, ?, ?, ?, 'Archived')";
        connect = DataBase.connectDb();
        TacheData TacheD = tableauofdeled.getSelectionModel().getSelectedItem();
    	int num = tableauofdeled.getSelectionModel().getSelectedIndex();

    	//si aucun élément n'est séléctionné
    	
        if ((num - 1) < -1) {
            return;}
    
        TaskDelfield.setText(TacheD.getName());
        DescripDelfield.setText(TacheD.getDescription());
        DeadDelfield.setValue(TacheD.getDeadline().toLocalDate());
        TimeDelfield.setText(TacheD.getTime());
        DoneDelfield.setValue(TacheD.getDone());
        try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete task: " + TaskDelfield.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, TaskDelfield.getText());
                prepare.setString(2, DescripArchivedfield.getText());
                prepare.setString(3, DeadArchivedfield.getValue().toString());
                prepare.setString(4, TimeArchivedfield.getText());
                prepare.setString(5, DoneArchivedfield.getSelectionModel().getSelectedItem().toString());
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();

                // Suppression de la tâche dans la table principale
                String sqldel = "DELETE FROM tache WHERE folder = 'Deleted' AND name = ?";
                prepare = connect.prepareStatement(sqldel);
                prepare.setString(1, TaskDelfield.getText());
                prepare.executeUpdate();
                        
                // Masquer les onglets et autres éléments UI non pertinents
                ImportantandUrgent.setVisible(false);
                ImportantbutnotUrgent.setVisible(false);
                NotImportantbutUrgent.setVisible(false);
                NotImportantAndNotUrgent.setVisible(false);
                Deletetab.setVisible(true);
                archivedtab.setVisible(false);
                helptab.setVisible(false);
                addtaskofiu.setVisible(false);
                addtaskofibnu.setVisible(false);
                addtaskofnibu.setVisible(false);
                addtaskofnianu.setVisible(false);
                viewtaskofiu.setVisible(false);
                viewtaskofibnu.setVisible(false);
                viewtaskofnibu.setVisible(false);
                viewtaskofnianu.setVisible(false);
                addsubtaskofiu.setVisible(false);
                addsubtaskofibnu.setVisible(false);
                addsubtaskofnibu.setVisible(false);
                addsubtaskofnianu.setVisible(false);
                viewsubtaskofiu.setVisible(false);
                viewsubtaskofibnu.setVisible(false);
                viewsubtaskofnibu.setVisible(false);
                viewsubtaskofnianu.setVisible(false);
                        
                // Actualiser la liste des tâches
                ShowArchivedTask();
                ShowDeletedTask();
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    //Changer la tache IU de dossier
    public void ChangeFolderIU(String folder) {
    	String sql = "INSERT INTO tache (name, description, deadline, time, done, folder) VALUES (?, ?, ?, ?, ?, ?)";
    
    	connect = DataBase.connectDb();
    
    	try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to change folder task ?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
            	
            	
            	prepare = connect.prepareStatement(sql);
            	prepare.setString(1, taskmodifIU.getText());
            	prepare.setString(2, descripmodifIU.getText());
            	prepare.setString(3, deadlinemodifIU.getValue().toString());
            	prepare.setString(4, timemodifIU.getText());
            	prepare.setString(5, donemodifIU.getSelectionModel().getSelectedItem().toString());
            	prepare.setString(6, folder);
        		prepare.executeUpdate();
        		
        		alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Changed!");
                alert.showAndWait();
                
             // Suppression de la tâche dans la table IU
                String sqldel = "DELETE FROM tache WHERE folder = 'Important and Urgent' AND name = ?";
                prepare = connect.prepareStatement(sqldel);
                prepare.setString(1, taskmodifIU.getText());
                prepare.executeUpdate();
                
                ShowTaskIU();
                //ShowTaskIU();
             }
         } catch (Exception e) {
             e.printStackTrace();
         
         }
     }
     
    //supprimer définitivement une tache

    public void DefDeleteTask() {
 	   //récupère l'élément sélectionné dans un TableView et le stocke dans TacheD.
     	
     	TacheData TacheD = tableauofdeled.getSelectionModel().getSelectedItem();
     	int num = tableauofdeled.getSelectionModel().getSelectedIndex();

     	//si aucun élément n'est séléctionné
     	
         if ((num - 1) < -1) {
             return;}
         
         String sql = "DELETE FROM tache WHERE folder = 'Deleted'AND name = ?";  
         connect = DataBase.connectDb();
         TaskDelfield.setText(TacheD.getName()); //sera un champs que je vais créer
         
         try {
         	Alert alert;
             alert = new Alert(AlertType.CONFIRMATION);
             alert.setTitle("Confirmation Message");
             alert.setHeaderText(null);
             alert.setContentText("Are you sure you want definitelly DELETE task: " + TaskDelfield.getText() + "?");
             Optional<ButtonType> option = alert.showAndWait();
             
                         
             if (option.isPresent() && option.get() == ButtonType.OK) {
                 prepare = connect.prepareStatement(sql);
                 prepare.setString(1, TaskDelfield.getText());
                 prepare.executeUpdate();

                 alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("Information Message");
                 alert.setHeaderText(null);
                 alert.setContentText("Successfully Deleted!");
                 alert.showAndWait();
                         
                 // Masquer les onglets et autres éléments UI non pertinents
                 ImportantandUrgent.setVisible(false);
                 ImportantbutnotUrgent.setVisible(false);
                 NotImportantbutUrgent.setVisible(false);
                 NotImportantAndNotUrgent.setVisible(false);
                 Deletetab.setVisible(true);
                 archivedtab.setVisible(false);
                 helptab.setVisible(false);
                 addtaskofiu.setVisible(false);
                 addtaskofibnu.setVisible(false);
                 addtaskofnibu.setVisible(false);
                 addtaskofnianu.setVisible(false);
                 viewtaskofiu.setVisible(false);
                 viewtaskofibnu.setVisible(false);
                 viewtaskofnibu.setVisible(false);
                 viewtaskofnianu.setVisible(false);
                 addsubtaskofiu.setVisible(false);
                 addsubtaskofibnu.setVisible(false);
                 addsubtaskofnibu.setVisible(false);
                 addsubtaskofnianu.setVisible(false);
                 viewsubtaskofiu.setVisible(false);
                 viewsubtaskofibnu.setVisible(false);
                 viewsubtaskofnibu.setVisible(false);
                 viewsubtaskofnianu.setVisible(false);
                         
                 // Actualiser la liste des tâches
                 ShowDeletedTask();
             }
         } catch (Exception e) {
             e.printStackTrace();
         
         }
     }
         
    
  //Selectionner les taches supprimées dans la bd
    
	public ObservableList<TacheData> SelectInBdDeletedTask(){
	
	
	ObservableList<TacheData> listDataDelete = FXCollections.observableArrayList();
	
	// on selectionne dans la bd la liste des taches pour Deleted
	
	String sql = "SELECT * FROM tache WHERE folder = 'Deleted'"; 
			
	connect = DataBase.connectDb();
	
	try {
		prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
        
        TacheData TacheD;
        
        while (result.next()) {
        	TacheD = new TacheData(
                    result.getString("name"),
                    result.getString("description"),
                    result.getDate("deadline"),
                    result.getString("time"),
                    result.getString("done"),
                    result.getString("folder"));
        	listDataDelete.add(TacheD);}
        
	}catch (Exception e) {e.printStackTrace();}
	return listDataDelete;
			
			
}

	private ObservableList<TacheData> DeleteTacheList;

	//cette fonction permet d'afficher les taches prise dans la fonction précédentes sur le tableau Deleted

	public void ShowDeletedTask() {
	
	DeleteTacheList = SelectInBdDeletedTask();
	taskcoldel.setCellValueFactory(new PropertyValueFactory<>("name"));
	descripcoldel.setCellValueFactory(new PropertyValueFactory<>("description"));
	deadlinecoldel.setCellValueFactory(new PropertyValueFactory<>("deadline"));
	timecoldel.setCellValueFactory(new PropertyValueFactory<>("time"));
	donecoldel.setCellValueFactory(new PropertyValueFactory<>("done"));

	tableauofdeled.setItems(DeleteTacheList);
}

	// cette fonction permet de selectionner les taches  Archivée
    
    public ObservableList<TacheData> SelectInBdArchivedTaskIU(){
    	
    	
    	ObservableList<TacheData> listDataArchive = FXCollections.observableArrayList();
    	
    	// on selectionne dans la bd la liste des taches pour Deleted
    	
    	String sql = "SELECT * FROM tache WHERE folder = 'Archived'"; 
    			
    	connect = DataBase.connectDb();
    	
    	try {
    		prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            TacheData TacheD;
            
            while (result.next()) {
            	TacheD = new TacheData(
                        result.getString("name"),
                        result.getString("description"),
                        result.getDate("deadline"),
                        result.getString("time"),
                        result.getString("done"),
                        result.getString("folder"));
            	listDataArchive.add(TacheD);}
            
    	}catch (Exception e) {e.printStackTrace();}
    	return listDataArchive;
    			
    			
    }
    private ObservableList<TacheData> ArchiveTaskList;
  //cette fonction permet d'afficher les taches prise dans la fonction précédentes sur le tableau Arcchived
    public void ShowArchivedTask() {
    	
    	ArchiveTaskList = SelectInBdArchivedTaskIU();
    	taskarchi.setCellValueFactory(new PropertyValueFactory<>("name"));
    	descriparchi.setCellValueFactory(new PropertyValueFactory<>("description"));
    	deadarchi.setCellValueFactory(new PropertyValueFactory<>("deadline"));
    	timearchi.setCellValueFactory(new PropertyValueFactory<>("time"));
    	donearchi.setCellValueFactory(new PropertyValueFactory<>("done"));

    	tableauarchived.setItems(ArchiveTaskList);
    }
    
    
    //cette fonction mets du contenue au combo box done 
    
    private String[] listDone = {"Yes", "No"};
    
    public void DoneList() {
    	
        List<String> listD= new ArrayList<>();

        for (String data : listDone) {
        	listD.add(data);
        }

        ObservableList<String> listData = FXCollections.observableArrayList(listD);
        
        doneIU.setItems(listData); //pour le combo box addTask
        donemodifIU.setItems(listData); //pour le combo box modif Task
    }
    //passer d'une fenetre à l'autre
    
    public void switchWindow(ActionEvent event) {
    	
    	//passer à la fentre iu
    	
        if (event.getSource() == iubtn || event.getSource() == abandoniu) {
        	ImportantandUrgent.setVisible(true);
        	ImportantbutnotUrgent.setVisible(false);
        	NotImportantbutUrgent.setVisible(false);
        	NotImportantAndNotUrgent.setVisible(false);
        	Deletetab.setVisible(false);
        	archivedtab.setVisible(false);
        	helptab.setVisible(false);
        	addtaskofiu.setVisible(false);
        	addtaskofibnu.setVisible(false);
        	addtaskofnibu.setVisible(false);
        	addtaskofnianu.setVisible(false);
        	viewtaskofiu.setVisible(false);
        	viewtaskofibnu.setVisible(false);
        	viewtaskofnibu.setVisible(false);
        	viewtaskofnianu.setVisible(false);
        	addsubtaskofiu.setVisible(false);
        	addsubtaskofibnu.setVisible(false);
        	addsubtaskofnibu.setVisible(false);
        	addsubtaskofnianu.setVisible(false);
        	viewsubtaskofiu.setVisible(false);
        	viewsubtaskofibnu.setVisible(false);
        	viewsubtaskofnibu.setVisible(false);
        	viewsubtaskofnianu.setVisible(false);
        	
        	
        	

        	iubtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	ibnubtn.setStyle("-fx-background-color:transparent");
        	nibubtn.setStyle("-fx-background-color:transparent");
        	nianubtn.setStyle("-fx-background-color:transparent");
        	deletedbtn_main.setStyle("-fx-background-color:transparent");
        	archivedbtn_main.setStyle("-fx-background-color:transparent");
        	morebtn.setStyle("-fx-background-color:transparent");

        	//passer à la fentre ibnu
        	
        } else if (event.getSource() == ibnubtn || event.getSource() == abandonibnu){
        	ImportantandUrgent.setVisible(false);
        	ImportantbutnotUrgent.setVisible(true);
        	NotImportantbutUrgent.setVisible(false);
        	NotImportantAndNotUrgent.setVisible(false);
        	Deletetab.setVisible(false);
        	archivedtab.setVisible(false);
        	helptab.setVisible(false);
        	addtaskofiu.setVisible(false);
        	addtaskofibnu.setVisible(false);
        	addtaskofnibu.setVisible(false);
        	addtaskofnianu.setVisible(false);
        	viewtaskofiu.setVisible(false);
        	viewtaskofibnu.setVisible(false);
        	viewtaskofnibu.setVisible(false);
        	viewtaskofnianu.setVisible(false);
        	addsubtaskofiu.setVisible(false);
        	addsubtaskofibnu.setVisible(false);
        	addsubtaskofnibu.setVisible(false);
        	addsubtaskofnianu.setVisible(false);
        	viewsubtaskofiu.setVisible(false);
        	viewsubtaskofibnu.setVisible(false);
        	viewsubtaskofnibu.setVisible(false);
        	viewsubtaskofnianu.setVisible(false);
        	

        	ibnubtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	iubtn.setStyle("-fx-background-color:transparent");
        	nibubtn.setStyle("-fx-background-color:transparent");
        	nianubtn.setStyle("-fx-background-color:transparent");
        	deletedbtn_main.setStyle("-fx-background-color:transparent");
        	archivedbtn_main.setStyle("-fx-background-color:transparent");
        	morebtn.setStyle("-fx-background-color:transparent");
        	
        	//passer à la fentre nibu   

        } else if (event.getSource() == nibubtn || event.getSource() == abandonnibu){
        	ImportantandUrgent.setVisible(false);
        	ImportantbutnotUrgent.setVisible(false);
        	NotImportantbutUrgent.setVisible(true);
        	NotImportantAndNotUrgent.setVisible(false);
        	Deletetab.setVisible(false);
        	archivedtab.setVisible(false);
        	helptab.setVisible(false);
        	addtaskofiu.setVisible(false);
        	addtaskofibnu.setVisible(false);
        	addtaskofnibu.setVisible(false);
        	addtaskofnianu.setVisible(false);
        	viewtaskofiu.setVisible(false);
        	viewtaskofibnu.setVisible(false);
        	viewtaskofnibu.setVisible(false);
        	viewtaskofnianu.setVisible(false);
        	addsubtaskofiu.setVisible(false);
        	addsubtaskofibnu.setVisible(false);
        	addsubtaskofnibu.setVisible(false);
        	addsubtaskofnianu.setVisible(false);
        	viewsubtaskofiu.setVisible(false);
        	viewsubtaskofibnu.setVisible(false);
        	viewsubtaskofnibu.setVisible(false);
        	viewsubtaskofnianu.setVisible(false);
        	

        	nibubtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	ibnubtn.setStyle("-fx-background-color:transparent");
        	iubtn.setStyle("-fx-background-color:transparent");
        	nianubtn.setStyle("-fx-background-color:transparent");
        	deletedbtn_main.setStyle("-fx-background-color:transparent");
        	archivedbtn_main.setStyle("-fx-background-color:transparent");
        	morebtn.setStyle("-fx-background-color:transparent");
        	
        	//passer à la fentre nianu

        }else if (event.getSource() == nianubtn || event.getSource() == abandonnianu){
        	ImportantandUrgent.setVisible(false);
        	ImportantbutnotUrgent.setVisible(false);
        	NotImportantbutUrgent.setVisible(false);
        	NotImportantAndNotUrgent.setVisible(true);
        	Deletetab.setVisible(false);
        	archivedtab.setVisible(false);
        	helptab.setVisible(false);
        	addtaskofiu.setVisible(false);
        	addtaskofibnu.setVisible(false);
        	addtaskofnibu.setVisible(false);
        	addtaskofnianu.setVisible(false);
        	viewtaskofiu.setVisible(false);
        	viewtaskofibnu.setVisible(false);
        	viewtaskofnibu.setVisible(false);
        	viewtaskofnianu.setVisible(false);
        	addsubtaskofiu.setVisible(false);
        	addsubtaskofibnu.setVisible(false);
        	addsubtaskofnibu.setVisible(false);
        	addsubtaskofnianu.setVisible(false);
        	viewsubtaskofiu.setVisible(false);
        	viewsubtaskofibnu.setVisible(false);
        	viewsubtaskofnibu.setVisible(false);
        	viewsubtaskofnianu.setVisible(false);
        	

        	nianubtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	ibnubtn.setStyle("-fx-background-color:transparent");
        	nibubtn.setStyle("-fx-background-color:transparent");
        	iubtn.setStyle("-fx-background-color:transparent");
        	deletedbtn_main.setStyle("-fx-background-color:transparent");
        	archivedbtn_main.setStyle("-fx-background-color:transparent");
        	morebtn.setStyle("-fx-background-color:transparent");
        	
        	//passer à la fentre deleted
        	
        }else if (event.getSource() == deletedbtn_main) {
        	ImportantandUrgent.setVisible(false);
        	ImportantbutnotUrgent.setVisible(false);
        	NotImportantbutUrgent.setVisible(false);
        	NotImportantAndNotUrgent.setVisible(false);
        	Deletetab.setVisible(true);
        	archivedtab.setVisible(false);
        	helptab.setVisible(false);
        	addtaskofiu.setVisible(false);
        	addtaskofibnu.setVisible(false);
        	addtaskofnibu.setVisible(false);
        	addtaskofnianu.setVisible(false);
        	viewtaskofiu.setVisible(false);
        	viewtaskofibnu.setVisible(false);
        	viewtaskofnibu.setVisible(false);
        	viewtaskofnianu.setVisible(false);
        	addsubtaskofiu.setVisible(false);
        	addsubtaskofibnu.setVisible(false);
        	addsubtaskofnibu.setVisible(false);
        	addsubtaskofnianu.setVisible(false);
        	viewsubtaskofiu.setVisible(false);
        	viewsubtaskofibnu.setVisible(false);
        	viewsubtaskofnibu.setVisible(false);
        	viewsubtaskofnianu.setVisible(false);
        	

        	deletedbtn_main.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	ibnubtn.setStyle("-fx-background-color:transparent");
        	nibubtn.setStyle("-fx-background-color:transparent");
        	nianubtn.setStyle("-fx-background-color:transparent");
        	iubtn.setStyle("-fx-background-color:transparent");
        	archivedbtn_main.setStyle("-fx-background-color:transparent");
        	morebtn.setStyle("-fx-background-color:transparent");
        	
        	//passer à la fentre archived
        }else if (event.getSource() == archivedbtn_main) {
        	ImportantandUrgent.setVisible(false);
        	ImportantbutnotUrgent.setVisible(false);
        	NotImportantbutUrgent.setVisible(false);
        	NotImportantAndNotUrgent.setVisible(false);
        	Deletetab.setVisible(false);
        	archivedtab.setVisible(true);
        	helptab.setVisible(false);
        	addtaskofiu.setVisible(false);
        	addtaskofibnu.setVisible(false);
        	addtaskofnibu.setVisible(false);
        	addtaskofnianu.setVisible(false);
        	viewtaskofiu.setVisible(false);
        	viewtaskofibnu.setVisible(false);
        	viewtaskofnibu.setVisible(false);
        	viewtaskofnianu.setVisible(false);
        	addsubtaskofiu.setVisible(false);
        	addsubtaskofibnu.setVisible(false);
        	addsubtaskofnibu.setVisible(false);
        	addsubtaskofnianu.setVisible(false);
        	viewsubtaskofiu.setVisible(false);
        	viewsubtaskofibnu.setVisible(false);
        	viewsubtaskofnibu.setVisible(false);
        	viewsubtaskofnianu.setVisible(false);
        	
        	
        	archivedbtn_main.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	ibnubtn.setStyle("-fx-background-color:transparent");
        	nibubtn.setStyle("-fx-background-color:transparent");
        	nianubtn.setStyle("-fx-background-color:transparent");
        	deletedbtn_main.setStyle("-fx-background-color:transparent");
        	iubtn.setStyle("-fx-background-color:transparent");
        	morebtn.setStyle("-fx-background-color:transparent");

        	//passer à la fentre more
        	
        }else if (event.getSource() == morebtn) {
        	ImportantandUrgent.setVisible(false);
        	ImportantbutnotUrgent.setVisible(false);
        	NotImportantbutUrgent.setVisible(false);
        	NotImportantAndNotUrgent.setVisible(false);
        	Deletetab.setVisible(false);
        	archivedtab.setVisible(false);
        	helptab.setVisible(true);
        	addtaskofiu.setVisible(false);
        	addtaskofibnu.setVisible(false);
        	addtaskofnibu.setVisible(false);
        	addtaskofnianu.setVisible(false);
        	viewtaskofiu.setVisible(false);
        	viewtaskofibnu.setVisible(false);
        	viewtaskofnibu.setVisible(false);
        	viewtaskofnianu.setVisible(false);
        	addsubtaskofiu.setVisible(false);
        	addsubtaskofibnu.setVisible(false);
        	addsubtaskofnibu.setVisible(false);
        	addsubtaskofnianu.setVisible(false);
        	viewsubtaskofiu.setVisible(false);
        	viewsubtaskofibnu.setVisible(false);
        	viewsubtaskofnibu.setVisible(false);
        	viewsubtaskofnianu.setVisible(false);
        	
        	
        	morebtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	ibnubtn.setStyle("-fx-background-color:transparent");
        	nibubtn.setStyle("-fx-background-color:transparent");
        	nianubtn.setStyle("-fx-background-color:transparent");
        	deletedbtn_main.setStyle("-fx-background-color:transparent");
        	archivedbtn_main.setStyle("-fx-background-color:transparent");
        	iubtn.setStyle("-fx-background-color:transparent");

        }else if (event.getSource() == addnewtaskbtnIU) {
        	ImportantandUrgent.setVisible(false);
        	ImportantbutnotUrgent.setVisible(false);
        	NotImportantbutUrgent.setVisible(false);
        	NotImportantAndNotUrgent.setVisible(false);
        	Deletetab.setVisible(false);
        	archivedtab.setVisible(false);
        	helptab.setVisible(false);
        	addtaskofiu.setVisible(true);
        	addtaskofibnu.setVisible(false);
        	addtaskofnibu.setVisible(false);
        	addtaskofnianu.setVisible(false);
        	viewtaskofiu.setVisible(false);
        	viewtaskofibnu.setVisible(false);
        	viewtaskofnibu.setVisible(false);
        	viewtaskofnianu.setVisible(false);
        	addsubtaskofiu.setVisible(false);
        	addsubtaskofibnu.setVisible(false);
        	addsubtaskofnibu.setVisible(false);
        	addsubtaskofnianu.setVisible(false);
        	viewsubtaskofiu.setVisible(false);
        	viewsubtaskofibnu.setVisible(false);
        	viewsubtaskofnibu.setVisible(false);
        	viewsubtaskofnianu.setVisible(false);
        	
        	
        	iubtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	ibnubtn.setStyle("-fx-background-color:transparent");
        	nibubtn.setStyle("-fx-background-color:transparent");
        	nianubtn.setStyle("-fx-background-color:transparent");
        	deletedbtn_main.setStyle("-fx-background-color:transparent");
        	archivedbtn_main.setStyle("-fx-background-color:transparent");
        	morebtn.setStyle("-fx-background-color:transparent");
        	DoneList();

        }else if (event.getSource() == addnewtaskbtnIBNU) {
        	ImportantandUrgent.setVisible(false);
        	ImportantbutnotUrgent.setVisible(false);
        	NotImportantbutUrgent.setVisible(false);
        	NotImportantAndNotUrgent.setVisible(false);
        	Deletetab.setVisible(false);
        	archivedtab.setVisible(false);
        	helptab.setVisible(false);
        	addtaskofiu.setVisible(false);
        	addtaskofibnu.setVisible(true);
        	addtaskofnibu.setVisible(false);
        	addtaskofnianu.setVisible(false);
        	viewtaskofiu.setVisible(false);
        	viewtaskofibnu.setVisible(false);
        	viewtaskofnibu.setVisible(false);
        	viewtaskofnianu.setVisible(false);
        	addsubtaskofiu.setVisible(false);
        	addsubtaskofibnu.setVisible(false);
        	addsubtaskofnibu.setVisible(false);
        	addsubtaskofnianu.setVisible(false);
        	viewsubtaskofiu.setVisible(false);
        	viewsubtaskofibnu.setVisible(false);
        	viewsubtaskofnibu.setVisible(false);
        	viewsubtaskofnianu.setVisible(false);
        	
        	
        	ibnubtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	iubtn.setStyle("-fx-background-color:transparent");
        	nibubtn.setStyle("-fx-background-color:transparent");
        	nianubtn.setStyle("-fx-background-color:transparent");
        	deletedbtn_main.setStyle("-fx-background-color:transparent");
        	archivedbtn_main.setStyle("-fx-background-color:transparent");
        	morebtn.setStyle("-fx-background-color:transparent");

        }else if (event.getSource() == addnewtaskbtnNIBU) {
        	ImportantandUrgent.setVisible(false);
        	ImportantbutnotUrgent.setVisible(false);
        	NotImportantbutUrgent.setVisible(false);
        	NotImportantAndNotUrgent.setVisible(false);
        	Deletetab.setVisible(false);
        	archivedtab.setVisible(false);
        	helptab.setVisible(false);
        	addtaskofiu.setVisible(false);
        	addtaskofibnu.setVisible(false);
        	addtaskofnibu.setVisible(true);
        	addtaskofnianu.setVisible(false);
        	viewtaskofiu.setVisible(false);
        	viewtaskofibnu.setVisible(false);
        	viewtaskofnibu.setVisible(false);
        	viewtaskofnianu.setVisible(false);
        	addsubtaskofiu.setVisible(false);
        	addsubtaskofibnu.setVisible(false);
        	addsubtaskofnibu.setVisible(false);
        	addsubtaskofnianu.setVisible(false);
        	viewsubtaskofiu.setVisible(false);
        	viewsubtaskofibnu.setVisible(false);
        	viewsubtaskofnibu.setVisible(false);
        	viewsubtaskofnianu.setVisible(false);
        	
        	
        	nibubtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	ibnubtn.setStyle("-fx-background-color:transparent");
        	iubtn.setStyle("-fx-background-color:transparent");
        	nianubtn.setStyle("-fx-background-color:transparent");
        	deletedbtn_main.setStyle("-fx-background-color:transparent");
        	archivedbtn_main.setStyle("-fx-background-color:transparent");
        	morebtn.setStyle("-fx-background-color:transparent");

        }else if (event.getSource() == addnewtaskbtnNIANU) {
        	ImportantandUrgent.setVisible(false);
        	ImportantbutnotUrgent.setVisible(false);
        	NotImportantbutUrgent.setVisible(false);
        	NotImportantAndNotUrgent.setVisible(false);
        	Deletetab.setVisible(false);
        	archivedtab.setVisible(false);
        	helptab.setVisible(false);
        	addtaskofiu.setVisible(false);
        	addtaskofibnu.setVisible(false);
        	addtaskofnibu.setVisible(false);
        	addtaskofnianu.setVisible(true);
        	viewtaskofiu.setVisible(false);
        	viewtaskofibnu.setVisible(false);
        	viewtaskofnibu.setVisible(false);
        	viewtaskofnianu.setVisible(false);
        	addsubtaskofiu.setVisible(false);
        	addsubtaskofibnu.setVisible(false);
        	addsubtaskofnibu.setVisible(false);
        	addsubtaskofnianu.setVisible(false);
        	viewsubtaskofiu.setVisible(false);
        	viewsubtaskofibnu.setVisible(false);
        	viewsubtaskofnibu.setVisible(false);
        	viewsubtaskofnianu.setVisible(false);
        	
        	
        	nianubtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	ibnubtn.setStyle("-fx-background-color:transparent");
        	nibubtn.setStyle("-fx-background-color:transparent");
        	morebtn.setStyle("-fx-background-color:transparent");
        	deletedbtn_main.setStyle("-fx-background-color:transparent");
        	archivedbtn_main.setStyle("-fx-background-color:transparent");
        	iubtn.setStyle("-fx-background-color:transparent");

        }
        }
    
    //fermer la fenetre accueil
    
    public void close() {
    	System.exit(0);
    }
    
    
    
  //réduire la fenetre accueil
    
    public void minimize() {
    	Stage stage= (Stage)accueilmain.getScene().getWindow();
    	stage.setIconified(true);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ShowTaskIU();
		ShowDeletedTask();
		ShowArchivedTask();
		DoneList();
		choiceiudel.setOnAction(event -> RecovertaskDeleted("Important and urgent"));
	    choiceibnudel.setOnAction(event -> RecovertaskDeleted("Important but not urgent"));
	    choicenibudel.setOnAction(event -> RecovertaskDeleted("Not important but urgent"));
	    choicenianudel.setOnAction(event -> RecovertaskDeleted("Not Important and not urgent"));
	    choiceiuarchi.setOnAction(event -> RecovertaskArchived("Important and urgent"));
	    choiceibnuarchi.setOnAction(event -> RecovertaskArchived("Important but not urgent"));
	    choicenibuarchi.setOnAction(event -> RecovertaskArchived("Not important but urgent"));
	    choicenianuarchi.setOnAction(event -> RecovertaskArchived("Not Important and not urgent"));
	    choiceibnuIU.setOnAction(event -> ChangeFolderIU("Important but not urgent"));
	    choicenibuIU.setOnAction(event -> ChangeFolderIU("Not important but urgent"));
	    choicenianuIU.setOnAction(event -> ChangeFolderIU("Not Important and not urgent"));
	   

	    
		
		
	}

}



