import NickSifniotis.SimpleDatabase.DataObject;
import NickSifniotis.SimpleDatabase.Queries.Ordering;
import NickSifniotis.SimpleDatabase.Queries.OrderingQuery;
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

        OrderingQuery query = new OrderingQuery(Person.class, "FirstName", Ordering.ASCENDING);
        System.out.println(query.SQL());

        DataObject[] objs = SimpleDB.LoadAll(Person.class);

        for (DataObject o: objs)
        {
            Person obj = (Person) o;
            System.out.println(obj.PrimaryKey + ": " + obj.FirstName.Value);
        }
    }
}
