package NickSifniotis.SimpleDatabase.Fields;

/**
 * Created by nsifniotis on 9/11/15.
 *
 * A class that is a string field / table column.
 */
public class StringField extends Field
{
    public String Value;
    

    public StringField (String name)
    {
        super(name);
    }


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
