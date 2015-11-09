package NickSifniotis.SimpleDatabase.Fields;

/**
 * Created by nsifniotis on 9/11/15.
 *
 * A class that represents an integer field / table column.
 */
public class IntegerField extends Field
{
    public int Value;

    public IntegerField(String name)
    {
        super(name);
    }


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
