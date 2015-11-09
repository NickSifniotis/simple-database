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
}
