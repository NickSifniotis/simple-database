package NickSifniotis.SimpleDatabase.Columns;

/**
 * Created by nsifniotis on 9/11/15.
 *
 * This is Java's equivalent of a tuple.
 *
 */
public class ColumnPair
{
    public String ColumnName;
    public Column Column;

    public ColumnPair (String name, Column column)
    {
        this.Column = column;
        this.ColumnName = name;
    }
}
