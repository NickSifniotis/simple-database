package NickSifniotis.SimpleDatabase.Columns;

/**
 * <p>
 *     Represents a real number column in the database.
 * </p>
 *
 * @author Nick Sifniotis u5809912
 * @since 09/11/2015
 * @version 1.2.0
 */
public class NumberColumn extends Column
{
    public double Value;


    /**
     * Default constructor. Sets the default value stored in this object to zero.
     */
    public NumberColumn()
    {
        this.Value = 0;
    }


    /**
     * <p>
     *     Constructor that initialises this object's value to a number given by the user/programmer.
     * </p>
     *
     * @param v The value to initialise to.
     */
    public NumberColumn (double v)
    {
        this.Value = v;
    }


    /**
     * @return The current value of this field, in a DB-friendly way.
     */
    @Override
    public String SQLFieldValue()
    {
        return String.valueOf(this.Value);
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
        this.Value = Double.parseDouble(new_value);
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
        return "REAL";
    }
}
