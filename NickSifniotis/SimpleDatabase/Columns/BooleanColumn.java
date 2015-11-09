package NickSifniotis.SimpleDatabase.Columns;

/**
 * Created by nsifniotis on 9/11/15.
 *
 * Provides a generic boolean column type.
 *
 */
public class BooleanColumn extends Column
{
    public boolean Value;

    /**
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
     * @return an SQL-friendly representation of this field's current value
     */
    @Override
    public String SQLFieldValue()
    {
        return Value ? "1" : "0";
    }


    /**
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
     * Updates the current value of this field from the string returned by the DB.
     * @param new_value - the value returned from the database.
     */
    @Override
    public void DBUpdateValue(String new_value)
    {
        this.Value = (new_value.equals("1"));
    }
}
