package NickSifniotis.SimpleDatabase.Columns;

/**
 * A vanilla Boolean column type for SimpleDB DataObjects.
 *
 * @author Nick Sifniotis u5809912
 * @since 09/11/2015
 * @version 1.0.0
 */
public class BooleanColumn extends Column
{
    public boolean Value;

    /**
     * @return an SQL-friendly representation of this field's current value
     */
    @Override
    public String SQLFieldValue()
    {
        return Value ? "1" : "0";
    }


    /**
     * Updates the current value of this field from the string returned by the DB.
     *
     * @param new_value - the value returned from the database.
     */
    @Override
    public void DBUpdateValue(String new_value)
    {
        this.Value = (new_value.equals("1"));
    }
}
