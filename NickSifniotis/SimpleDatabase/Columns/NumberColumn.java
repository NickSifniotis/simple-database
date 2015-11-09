package NickSifniotis.SimpleDatabase.Columns;

/**
 * Created by nsifniotis on 9/11/15.
 *
 * Represents a real number column in the database.
 *
 */
public class NumberColumn extends Column
{
    public double Value;


    /**
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
     * @return the current value of this field, in a DB-friendly way.
     */
    @Override
    public String SQLFieldValue()
    {
        return String.valueOf(this.Value);
    }


    /**
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
     * Updates the value of this field with data from the DB.
     *
     * @param new_value - the value returned from the database.
     */
    @Override
    public void DBUpdateValue(String new_value)
    {
        this.Value = Double.parseDouble(new_value);
    }
}
