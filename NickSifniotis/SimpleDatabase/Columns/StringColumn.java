package NickSifniotis.SimpleDatabase.Columns;

/**
 * Created by nsifniotis on 9/11/15.
 *
 * A class that is a string field / table column.
 */
public class StringColumn extends Column
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
}
