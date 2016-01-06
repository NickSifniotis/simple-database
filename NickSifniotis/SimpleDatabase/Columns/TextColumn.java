package NickSifniotis.SimpleDatabase.Columns;

/**
 * <p>
 *      A class that is a string field / table column.
 * </p>
 *
 * @author Nick Sifniotis u5809912
 * @since 09/11/2015
 * @version 1.2.0
 */
public class TextColumn extends Column
{
    public String Value;


    /**
     * Default constructor. Sets the default value stored in this object to the empty string.
     */
    public TextColumn()
    {
        this.Value = "";
    }


    /**
     * <p>
     *     Constructor that initialises this object's value to a number given by the user/programmer.
     * </p>
     *
     * @param v The value to initialise to.
     */
    public TextColumn (String v)
    {
        this.Value = v;
    }


    /**
     * @return The current value of the String, in an SQL-happy format.
     */
    @Override
    public String SQLFieldValue()
    {
        return "\"" + this.Value + "\"";
    }


    /**
     * <p>
     *     Updates the value stored in this field.
     * </p>
     *
     * @param new_value The value returned from the database.
     */
    @Override
    public void DBUpdateValue(String new_value)
    {
        this.Value = new_value;
    }


    /**
     * <p>
     *     This method returns the column type that the database will use to store these columns.
     * </p>
     *
     * @return An SQL fragment that can be injected directly into queries.
     */
    public static String SQLColumnDescriptor()
    {
        return "TEXT";
    }
}
