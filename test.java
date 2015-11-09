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
        TestDataObject obj = (TestDataObject) SimpleDB.Load(TestDataObject.class, 2);

    }
}
