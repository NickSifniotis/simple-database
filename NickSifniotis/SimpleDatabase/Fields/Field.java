package NickSifniotis.SimpleDatabase.Fields;

/**
 * Created by nsifniotis on 9/11/15.
 *
 * This class represents a field in an object and also a column in a table.
 *
 * Too abstract for you? You shouldn't be looking at the implementation of this class to begin with.
 */
public abstract class Field
{
    private String name;

    public Field (String name)
    {
        this.name = name;
    }


    /**
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
     * Returns this field's column name.
     *
     * @return this field's column name.
     */
    public String SQLFieldName()
    {
        return this.name;
    }


    /**
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
     * Children implement this method to return a string that can be injected directly into an
     * SQL statement, representing the current value of this field.
     *
     * @return the current value of this field, as a string that can be inserted into SQL.
     */
    public abstract String SQLFieldValue();
}
