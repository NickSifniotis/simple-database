package NickSifniotis.SimpleDatabase.Columns;

/**
 * Created by nsifniotis on 9/11/15.
 *
 * This class represents a field in an object and also a column in a table.
 *
 * Too abstract for you? You shouldn't be looking at the implementation of this class to begin with.
 */
public abstract class Column
{
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


    /**
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
     * Children implement this method to update their current value from the data extracted from the DB.
     * The data will be passed through as a string, it is up to the child class to parse the data correctly.
     *
     * @param new_value - the value returned from the database.
     */
    public abstract void DBUpdateValue(String new_value);
}
