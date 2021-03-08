package model;

import java.util.ArrayList;
/**
 * @author Mathias Gnadlinger
 * @version 12, 24.02.2021
 */
public class Model
{
    private ArrayList<Input> inputs;
    private int position;

    public Model()
    {
        this.inputs = new ArrayList<Input>();
        this.position = 0;
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public Input getInput(int position)
    {
        return inputs.get(position);
    }

    public void addInput(Input input)
    {
        inputs.add(input);
        position = inputs.indexOf(input);
    }

    public void saveToCSV()
    {
        CSV csv = new CSV();
        csv.saveToFile("excel.csv",inputs);                                                                     //Speichern der inputs in Datei excel.csv
    }

    public void loadFromCSV()
    {
        try
        {
            CSV csv = new CSV();
            inputs = csv.loadFromFile("excel.csv");                                                                 //Auslesen der Datei excel.csv
            position = 1;
        }
        catch (Exception exception)
        {
            System.out.printf("Error while loading");
        }
    }

    public int sizeOfBook()
    {
        return inputs.size();
    }

    public void delete()
    {
        inputs.remove(position);
        position--;
    }

    public void saveChange(Input input)
    {
        inputs.set(position,input);
    }
}
