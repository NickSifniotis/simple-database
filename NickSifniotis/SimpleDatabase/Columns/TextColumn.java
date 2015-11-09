package NickSifniotis.SimpleDatabase.Columns;

/**
 * @author Nick Sifniotis u5809912
 * @since 09/11/2015
 * @version 1.0.0
 *
 * A class that is a string field / table column.
 */
public class TextColumn extends Column
{
    public String Value;


    /**
     * @return the current value of the String, in an SQL-happy format.
     */
    @Override
    public String SQLFieldValue()
    {
        return "\"" + this.Value + "\"";
    }


    /**
     * Updates the value stored in this field.
     *
     * @param new_value - the value returned from the database.
     */
    @Override
    public void DBUpdateValue(String new_value)
    {
        this.Value = new_value;
    }
}
