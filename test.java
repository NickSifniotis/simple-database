import NickSifniotis.SimpleDatabase.DataObject;
import NickSifniotis.SimpleDatabase.SimpleDB;

/**
 * Created by nsifniotis on 8/11/15.
 *
 * Testing 'framework' for this library.
 */
public class test
{
    public static void main(String[] args)
    {
        SimpleDB.CreateTable(TestDataObject.class);
        TestDataObject item = (TestDataObject) SimpleDB.New(TestDataObject.class);

        if (item != null)
        {
            item.FirstName.Value = "John";
            item.Age.Value = 10;
            SimpleDB.Save(item);

            DataObject[] items = SimpleDB.LoadAll(TestDataObject.class);

            for (DataObject t : items)
            {
                TestDataObject thing = (TestDataObject) t;
                System.out.println(thing.FirstName.Value + " " + thing.Surname.Value + " is " + thing.Age.Value + " years old.");
            }
        }
    }
}
