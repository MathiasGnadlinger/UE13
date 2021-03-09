package Controller;

import com.sun.tools.javac.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Input;
import model.Model;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Mathias Gnadlinger
 * @version 12, 24.02.2021
 */

public class Controller implements Initializable
{
    @FXML private TextField txtf_name;
    @FXML private TextField txtf_address;
    @FXML private TextField txtf_phone;
    @FXML private TextField txtf_site;

    private Model model = new Model();

    int positionmax;
    int maxinput = model.inputs.size();


    @FXML void ExitProgram()
    {
        saveToCSV();
        System.exit(0);
    }



    @FXML void loadFromCSV()
    {
        try
        {
            model.loadFromCSV();
            print();
        }
        catch (Exception exception)
        {
            System.out.printf("Error while loading\n");
        }

    }

    @FXML void saveToCSV()
    {
        try
        {
            model.saveToCSV();
            print();
        }
        catch (Exception exception)
        {
            System.out.printf("Error while saving\n");
        }
    }

    @FXML void forward()                                                                                                // Seitenanzahl um 1 erhöhen und aktualisieren
    {

        try
        {
            if (model.getPosition() >= maxinput)
            {
                if (positionmax < model.inputs.size() -1)
                {
                    model.setPosition(model.getPosition() + 1);
                    positionmax = positionmax +1;

                }
                else
                {
                    model.setPosition(0);
                    positionmax = 0;
                }
                print();
            }

        }
        catch (Exception exception)
        {
            System.out.printf("Error - Page is not available\n");
        }
    }

    private void print ()                                                                                               // Augabe der Seitenanzahl, Tel.Nr, Adresse, und Name falls Einträge vorhanden sind
    {
        int entries = model.sizeOfBook();
        int position = model.getPosition();

        if (entries > 0)
        {
            Input input;
            input = model.getInput(model.getPosition());

            txtf_site.setText((position + 1) + " von " + entries);
            txtf_name.setText(input.getName());
            txtf_address.setText(input.getAddress());
            txtf_phone.setText(input.getPhone());
        }

        else                                                                                                            // Seitenanzahl auf Null setzen wenn keine Einträge vorhanden sind
            {
            txtf_site.setText("0 von 0");
        }
    }


     @FXML void add()
     {
         model.addInput(new Input(txtf_name.getText(), txtf_address.getText(),txtf_phone.getText()));
         print();
     }

     @FXML void delete ()
     {
         try
         {
             model.delete();
             print();
         }
         catch (Exception exception)
         {
             System.out.printf("Error - While deleting\n");
             System.out.printf("Skip a page, then the page is gone\n");
         }

     }

     @FXML void saveChange()
     {
         try
         {
             model.saveChange(new Input(txtf_name.getText(), txtf_address.getText(), txtf_phone.getText()));
             print();
         }
         catch (Exception exception)
         {
             System.out.printf("Error while saving\n");
         }
     }

    @FXML
    public void onlyNumbers(){
        txtf_phone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtf_phone.setText(oldValue);
                }
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            loadFromCSV();
            onlyNumbers();
        }
        catch (Exception exception)
        {
            System.out.printf("Error while loading");
        }

    }
}
