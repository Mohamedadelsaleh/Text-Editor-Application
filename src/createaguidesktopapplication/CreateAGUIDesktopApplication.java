/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package createaguidesktopapplication;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author moham
 */
public class CreateAGUIDesktopApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        MenuBar bar = new MenuBar();
        TextArea textArea=new TextArea();
        textArea.setPrefRowCount(10);
        textArea.setPrefColumnCount(5);

        String COPY = "\u00a9";
        Label copyRight = new Label(" Copyright "+COPY+" Mohamed Adel Saleh - ITI IoT Track");
/********************************************* File Menu *************************************************/

        Menu file= new Menu("File");
       
        MenuItem newItem = new MenuItem("New");
        newItem.setAccelerator(KeyCombination.keyCombination("Ctrl+n"));
        MenuItem openItem = new MenuItem("Open...");
        openItem.setAccelerator(KeyCombination.keyCombination("Ctrl+o"));
        MenuItem saveItem = new MenuItem("Save");
        saveItem.setAccelerator(KeyCombination.keyCombination("Ctrl+s"));
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+e"));

        file.getItems().addAll(newItem,openItem,saveItem,separator,exitItem);
 

    /********************************************* Edit Menu *************************************************/
        
        Menu edit= new Menu("Edit");

        MenuItem undoItem = new MenuItem("Undo");
        undoItem.setAccelerator(KeyCombination.keyCombination("Ctrl+z"));
        SeparatorMenuItem separator2 = new SeparatorMenuItem();
        MenuItem cutItem = new MenuItem("Cut");
        cutItem.setAccelerator(KeyCombination.keyCombination("Ctrl+x"));
        MenuItem copyItem = new MenuItem("Copy");
        copyItem.setAccelerator(KeyCombination.keyCombination("Ctrl+c"));
        MenuItem pasteItem = new MenuItem("Paste");
        pasteItem.setAccelerator(KeyCombination.keyCombination("Ctrl+e"));
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setAccelerator(KeyCombination.keyCombination("Ctrl+e"));
        SeparatorMenuItem separator3 = new SeparatorMenuItem();  
        MenuItem selectAllItem = new MenuItem("Select All");
        selectAllItem.setAccelerator(KeyCombination.keyCombination("Ctrl+a"));

        edit.getItems().addAll(undoItem,separator2,cutItem,copyItem,pasteItem,deleteItem,separator3,selectAllItem);
        

/********************************************* Help Menu *************************************************/

        Menu help= new Menu("Help");    
        MenuItem aboutItem = new MenuItem("About Notepad");
        help.getItems().addAll(aboutItem);
        bar.getMenus().addAll(file,edit,help);

/*********************************************** Events ****************************************************/
            
            /******************************* New Items  ***********************************/
        newItem.setOnAction(new EventHandler<ActionEvent>() {
            
                           @Override
                          public  void handle(ActionEvent event)
                          {
                                      if(textArea.getText().trim().length() !=0)
                                      {
                                                   FileChooser fileChooser = new FileChooser();

                                                   //Set extension filter for text files
                                                   FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                                                   fileChooser.getExtensionFilters().add(extFilter);

                                                   //Show save file dialog
                                                    try{
                                                                                                 //Show save file dialog
                                                    File file2 = fileChooser.showSaveDialog(primaryStage);
                                                    FileWriter fw = new FileWriter(file2);
                                                    fw.write(textArea.getText());
                                                    fw.close();
                                                        } 
                                               catch (IOException ex) {
                                                                    Logger.getLogger(CreateAGUIDesktopApplication.class.getName()).log(Level.SEVERE, null, ex);
                                                       }

                                                  textArea.clear();
                                      }
                          }
                });

   
            /******************************* Open Items  ***********************************/
        openItem.setOnAction(new EventHandler<ActionEvent>() {
            
                           @Override
                          public  void handle(ActionEvent event)
                          {
                                      FileChooser fileChooser = new FileChooser();
                                      fileChooser.setTitle("Open Resource File");
                                      fileChooser.getExtensionFilters().addAll(
                                              new ExtensionFilter("Text Files", "*.txt"),
                                              new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                                              new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                                              new ExtensionFilter("All Files", "*.*"));
                                      File selectedFile = fileChooser.showOpenDialog(primaryStage);

                             if(selectedFile != null) 
                                  {
                                      String   path = selectedFile.getPath();
                                    try{
                                                     DataInputStream  dataIn = new DataInputStream(new FileInputStream(path));
                                                      byte[] data = new  byte[(int)selectedFile.length()];
                                                      dataIn.read(data);
                                                      textArea.setText(new String(data));
                                                     dataIn.close();
                                          } 
                                              catch (IOException ex) {    
                                              Logger.getLogger(CreateAGUIDesktopApplication.class.getName()).log(Level.SEVERE, null, ex);
                                          }    
                                }
                          }
                });

            /******************************* Save Items  ***********************************/
        saveItem.setOnAction(new EventHandler<ActionEvent>() {
            
                           @Override
                          public  void handle(ActionEvent event)
                          {
                                                   FileChooser fileChooser = new FileChooser();

                                                   //Set extension filter for text files
                                                   FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                                                   fileChooser.getExtensionFilters().add(extFilter);

                                                try{
                                                                                                 //Show save file dialog
                                                    File file2 = fileChooser.showSaveDialog(primaryStage);
                                                    FileWriter fw = new FileWriter(file2);
                                                    fw.write(textArea.getText());
                                                    fw.close();
                                                        } 
                                               catch (IOException ex) {
                                                                    Logger.getLogger(CreateAGUIDesktopApplication.class.getName()).log(Level.SEVERE, null, ex);
                                                       }
                            }
                });

           /******************************* Exit Event  ***********************************/
            exitItem.setOnAction(new EventHandler<ActionEvent>() {
            
                           @Override
                          public  void handle(ActionEvent event)
                          {
                                     Platform.exit();
                          }
                });

           /******************************* Undo Event  ***********************************/
            undoItem.setOnAction(new EventHandler<ActionEvent>() {
            
                           @Override
                          public  void handle(ActionEvent event)
                          {
                                     textArea.undo();
                          }
                });

           /******************************* Cut Event  ***********************************/
            cutItem.setOnAction(new EventHandler<ActionEvent>() {
            
                           @Override
                          public  void handle(ActionEvent event)
                          {
                                     textArea.cut();
                          }
                });

           /******************************* Copy Event  ***********************************/
            copyItem.setOnAction(new EventHandler<ActionEvent>() {
            
                           @Override
                          public  void handle(ActionEvent event)
                          {
                                     textArea.copy();
                          }
                });

           /******************************* Paste Event  ***********************************/
            pasteItem.setOnAction(new EventHandler<ActionEvent>() {
            
                           @Override
                          public  void handle(ActionEvent event)
                          {
                                     textArea.paste();
                          }
                });

           /******************************* Delete Event  ***********************************/
            deleteItem.setOnAction(new EventHandler<ActionEvent>() {
            
                           @Override
                          public  void handle(ActionEvent event)
                          {
                                  textArea.deleteText(textArea.getSelection());
                          }
                });


           /******************************* Select All Event  ***********************************/
            selectAllItem.setOnAction(new EventHandler<ActionEvent>() {
            
                           @Override
                          public  void handle(ActionEvent event)
                          {
                                     textArea.selectAll();
                          }
                });

           /******************************* About Event  ***********************************/
            aboutItem.setOnAction(new EventHandler<ActionEvent>() {
            
                           @Override
                          public  void handle(ActionEvent event)
                          {
                                 Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("About Application");
                                alert.setHeaderText("NotePad  appliction to enable any text you want with many features");
                                alert.setContentText("Created by : Mohamed Adel Saleh \n"+"ITI inTake 42 \n"+"IoT Track \n"+"Java Course");
                                alert.showAndWait();
                          }
                });
/*******************************************************************************************************/
        BorderPane pane = new BorderPane();
        pane.setTop(bar);
        pane.setCenter(textArea);
        pane.setBottom(copyRight);

        Scene scene = new Scene(pane, 800, 500);
        
        primaryStage.setTitle("Text Editor Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
