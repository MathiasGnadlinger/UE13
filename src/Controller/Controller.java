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
        try {
            model.loadFromCSV();
            print();
        }
        catch (Exception exception)
        {
            System.out.printf("soldfhosdfiosdiof");
        }

    }

    @FXML void saveToCSV()
    {
        model.saveToCSV();
        print();
    }

    @FXML void forward()
    {
        try {
            model.setPosition(model.getPosition() + 1);
            print();
        }
        catch (Exception exception)
        {
            System.out.printf("Error - Page is not available\n");
        }
    }

    @FXML void back()
    {
        try {
            model.setPosition(model.getPosition() - 1);
            print();
        }
        catch (Exception exception)
        {
            System.out.printf("Error - Page is not available\n");
        }
    }

    private void print ()
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

        else
            {
            txtf_site.setText("0 von 0");
            txtf_name.clear();
            txtf_address.clear();
            txtf_phone.clear();
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
         model.saveChange(new Input(txtf_name.getText(),txtf_address.getText(),txtf_phone.getText()));
         print();
     }
}
