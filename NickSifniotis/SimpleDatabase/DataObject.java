package NickSifniotis.SimpleDatabase;


import java.util.HashMap;

/**
 * Created by nsifniotis on 6/11/15.
 *
 * Base class for all objects that will reside in the database.
 *
 * There is no need to specify a database schema. The schema is inferred from
 * the structure of the class itself. Any public member variable will be stored
 * in the database.
 */
public abstract class DataObject
{
    public int PrimaryKey;


    /**
     * Nick Sifniotis u5809912
     * 06/11/2015
     *
     * Called on creation of a new instance of this object - set default values to all relevant
     * public fields.
     *
     * PrimaryKey = -1 is a major flag that this is a new object that has not been saved yet.
     */
    public void SetDefaults()
    {
        PrimaryKey = -1;
    }
}
