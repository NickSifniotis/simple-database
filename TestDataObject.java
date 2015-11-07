import NickSifniotis.SimpleDatabase.DataObject;

/**
 * Created by nsifniotis on 8/11/15.
 *
 * Realllllllly simple test object to make sure the DBManager is working ok.
 *
 */
public class TestDataObject extends DataObject
{
    public String name;
    public int age;

    @Override
    public void SetDefaults()
    {
        super.SetDefaults();
        name = "Nick";
        age = 34;
    }
}
