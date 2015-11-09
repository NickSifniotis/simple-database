package NickSifniotis.SimpleDatabase.Columns;

/**
 * A simple Integer column type for SimpleDB DataObjects.
 *
 * @author Nick Sifniotis u5809912
 * @since 09/11/2015
 * @version 1.0.0
 */
public class IntegerColumn extends Column
{
    public int Value;


    /**
     * @return the current value of this field, in an SQL-happy format.
     */
    @Override
    public String SQLFieldValue()
    {
        return String.valueOf(Value);
    }


    /**
     * Updates this field with data
     *
     * @param new_value - the value returned from the database.
     */
    @Override
    public void DBUpdateValue(String new_value)
    {
        this.Value = Integer.parseInt(new_value);
    }
}
