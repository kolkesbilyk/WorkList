package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private VBox vBox;

    @FXML
    private TextField textField;

    @FXML
    private TextArea text;

    DataBase dataBase = null;

    @FXML
    void initialize() {
        dataBase = new DataBase();
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                if (!textField.getText().trim().equals("")){
                    dataBase.insertTask(textField.getText());
                    loadInfo();
                    textField.setText("");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        });
        loadInfo();
    }
    void loadInfo(){
        try {
            ArrayList<String> tasks = dataBase.getTasks();
            for (String task: tasks) text.appendText(task + "\n");
            System.out.println(tasks.toString());
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}

