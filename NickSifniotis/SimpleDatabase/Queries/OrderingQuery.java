package NickSifniotis.SimpleDatabase.Queries;

import NickSifniotis.SimpleDatabase.Columns.Column;

/**
 * Created by nsifniotis on 9/11/15.
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
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
     * Constructor.
     */
    public OrderingQuery (Column col, Ordering ord)
    {
        __order = ord;
        __column = col;
    }


    /**
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
     * @return the bit of SQL that this object represents
     */
    @Override
    public String SQL()
    {
        return __column.Name() + " " + __order.SQL();
    }
}
