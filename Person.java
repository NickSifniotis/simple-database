import NickSifniotis.SimpleDatabase.Columns.IntegerColumn;
import NickSifniotis.SimpleDatabase.Columns.TextColumn;
import NickSifniotis.SimpleDatabase.DataObject;

/**
 * Created by nsifniotis on 8/11/15.
 *
 * Realllllllly simple test object to make sure the DBManager is working ok.
 *
 */
public class Person extends DataObject
{
    public TextColumn FirstName = new TextColumn("Nick");
    public TextColumn Surname = new TextColumn("Sifniotis");
    public IntegerColumn Age = new IntegerColumn();
}
