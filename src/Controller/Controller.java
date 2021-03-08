package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Input;
import model.Model;
/**
 * @author Mathias Gnadlinger
 * @version 12, 24.02.2021
 */

public class Controller
{
    @FXML private TextField txtf_name;
    @FXML private TextField txtf_address;
    @FXML private TextField txtf_phone;
    @FXML private TextField txtf_site;

    private Model model = new Model();


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
            model.setPosition(model.getPosition() + 1);
            print();
        }
        catch (Exception exception)
        {
            System.out.printf("Error - Page is not available\n");
        }
    }

    @FXML void back()                                                                                                   // Seitenanzahl um 1 veringern und aktualisieren
    {
        try
        {
            model.setPosition(model.getPosition() - 1);
            print();
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
}
