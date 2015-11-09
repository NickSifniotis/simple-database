import NickSifniotis.SimpleDatabase.DataObject;
import NickSifniotis.SimpleDatabase.DataObjectFactory;

/**
 * Created by nsifniotis on 8/11/15.
 *
 * Testing 'framework' for this library.
 */
public class test
{
    public static void main(String[] args)
    {
        DataObjectFactory.CreateTable(TestDataObject.class);
        TestDataObject item = (TestDataObject) DataObjectFactory.New(TestDataObject.class);

        item.name = "John";
        item.age = 10;
        DataObjectFactory.Save(item);

        DataObject[] items = DataObjectFactory.LoadAll(TestDataObject.class);

        for (DataObject t: items)
        {
            TestDataObject thing = (TestDataObject) t;
            System.out.println(thing.name + ":" + thing.age);
        }
    }
}
