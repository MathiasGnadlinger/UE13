package model;
/**
 * @author Mathias Gnadlinger
 * @version 12, 24.02.2021
 */
public class Input
{
    private String name;
    private String address;
    private String phone;

    public Input(String name, String address, String phone)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPhone()
    {
        return phone;
    }
}
