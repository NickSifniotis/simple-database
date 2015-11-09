import NickSifniotis.SimpleDatabase.Columns.IntegerColumn;
import NickSifniotis.SimpleDatabase.Columns.StringColumn;
import NickSifniotis.SimpleDatabase.DataObject;

/**
 * Created by nsifniotis on 8/11/15.
 *
 * Realllllllly simple test object to make sure the DBManager is working ok.
 *
 */
public class TestDataObject extends DataObject
{
    public StringColumn FirstName = new StringColumn();
    public StringColumn Surname = new StringColumn();
    public IntegerColumn Age = new IntegerColumn();


    @Override
    public void SetDefaults()
    {
        super.SetDefaults();
        FirstName.Value = "Nick";
        Surname.Value = "Sifniotis";
        Age.Value = 34;
    }
}
