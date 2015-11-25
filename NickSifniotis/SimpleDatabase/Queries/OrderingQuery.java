package NickSifniotis.SimpleDatabase.Queries;

import java.lang.reflect.Field;

/**
 * This query fragment allows you to select the sort ordering of the data
 * extracted from the database.
 *
 * @author Nick Sifniotis u5809912
 * @since 09/11/2015
 * @version 1.1.0
 *
 */
public class OrderingQuery extends Query
{
    private Ordering __order;
    private Field __column;


    /**
     * Constructor for this OrderingQuery object. Accepts the column that this ordering is to apply
     * to, and the enumeration that indicates whether it is to be ascending or descending.
     *
     * @param object The data object's class object.
     * @param col_name The column to sort by.
     * @param order - whether or not to sort small-to-large or large-to-small.
     */
    public OrderingQuery (Class object, String col_name, Ordering order)
    {
        try
        {
            __order = order;
            __column = object.getField(col_name);
        }
        catch (Exception e)
        {
            // do nothing
        }
    }


    /**
     * @return An SQL sub-query that can be used to query the database.
     */
    @Override
    public String SQL()
    {
        return __column.getName() + " " + __order.SQL();
    }
}
