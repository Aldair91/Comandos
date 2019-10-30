/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Diego
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button button;
    
    @FXML
    private TextArea textArea;
    
    @FXML
    private TextField textField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //textArea.setText("Canción");
        //button.setStyle("");
    }    
    
    @FXML
    private void handleButtontwo(ActionEvent event) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        //processBuilder.command("cmd.exe", "/c", "ping -n 1 google.com");
        //processBuilder.command("cmd.exe", "/c", "ipconfig");
        //processBuilder.command("cmd.exe", "/c", "start www.google.com");
        //processBuilder.command("cmd.exe", "/c", "chcp 65001");
        
        processBuilder.command("cmd.exe", "/c", textField.getText());
        textArea.setText("");
        
        try {
                
            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                
                /*String string = Normalizer.normalize(line, Normalizer.Form.NFD);
                //string = string.replaceAll("[^\\p{ASCII}]", "");
                string.replaceAll("á","a")
                .replaceAll("é","e")
                .replaceAll("í","i")
                .replaceAll("ó","o")
                .replaceAll("ú","u");*/
                System.out.print(line);
                System.out.print("\n");
                
                textArea.appendText(line);
                textArea.appendText("\n");
            }
            
            //processBuilder.command("cmd.exe", "/c", "chcp 1252");

            int exitCode = process.waitFor();
            textArea.appendText("Debe introducir un comando.");
            System.out.println("\nExited with error code : " + exitCode);
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }   
    
}
