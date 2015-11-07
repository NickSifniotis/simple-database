import NickSifniotis.SimpleDatabase.Repository;

/**
 * Created by nsifniotis on 8/11/15.
 *
 * Testing 'framework' for this library.
 */
public class test
{
    public static void main(String[] args)
    {
        Repository.CreateTable(TestDataObject.class);
        TestDataObject item = (TestDataObject) Repository.New(TestDataObject.class);

        item.name = "John";
        item.age = 10;
        Repository.Save(item);

        TestDataObject[] items = (TestDataObject[]) Repository.LoadAll(TestDataObject.class);

        for (TestDataObject thing: items)
            System.out.println(thing.name + ":" + thing.age);
    }
}
