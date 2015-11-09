package NickSifniotis.SimpleDatabase.Columns;

/**
 * Created by nsifniotis on 9/11/15.
 *
 * A class that represents an integer field / table column.
 */
public class IntegerColumn extends Column
{
    public int Value;


    /**
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
     * @return the current value of this field, in an SQL-happy format.
     */
    @Override
    public String SQLFieldValue()
    {
        return String.valueOf(Value);
    }


    /**
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
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
