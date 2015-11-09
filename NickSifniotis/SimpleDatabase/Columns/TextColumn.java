package NickSifniotis.SimpleDatabase.Columns;

/**
 * Created by nsifniotis on 9/11/15.
 *
 * A class that is a string field / table column.
 */
public class TextColumn extends Column
{
    public String Value;


    /**
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
     * @return the current value of the String, in an SQL-happy format.
     */
    @Override
    public String SQLFieldValue()
    {
        return "\"" + this.Value + "\"";
    }


    /**
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
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
