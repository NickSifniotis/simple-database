package NickSifniotis.SimpleDatabase.Queries;

import NickSifniotis.SimpleDatabase.Columns.Column;

/**
 * @author Nick Sifniotis u5809912
 * @since 09/11/2015
 * @version 1.0.0
 *
 * This query fragment allows you to select the sort ordering of the data
 * extracted from the database.
 *
 */
public class OrderingQuery extends Query
{
    private Ordering __order;
    private Column __column;


    /**
     * Constructor for this OrderingQuery object. Accepts the column that this ordering is to apply
     * to, and the enumeration that indicates whether it is to be ascending or descending.
     *
     * @param column - the Column to apply this ordering to
     * @param order - whether or not to sort small-to-large or large-to-small.
     */
    public OrderingQuery (Column column, Ordering order)
    {
        __order = order;
        __column = column;
    }


    /**
     * @return the SQL that this ordering object represents
     */
    @Override
    public String SQL()
    {
        return __column.Name() + " " + __order.SQL();
    }
}
