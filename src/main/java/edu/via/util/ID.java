package edu.via.util;

public class ID
{

    private static int ID;

    public static int getID()
    {
        return ++ID;
    }

}
