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
        Person p = (Person) SimpleDB.New(Person.class);

        DataObject[] objs = SimpleDB.LoadAll(Person.class);

        for (DataObject o: objs)
        {
            Person obj = (Person) o;
            System.out.println(obj.PrimaryKey + ": " + obj.FirstName.Value);
        }
    }
}
