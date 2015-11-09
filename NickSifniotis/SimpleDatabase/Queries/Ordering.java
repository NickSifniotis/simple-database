package NickSifniotis.SimpleDatabase.Queries;

/**
 * Simple enumeration constants for SQL ORDER BY clauses.
 *
 * @author Nick Sifniotis u5809912
 * @since 09/11/2015
 * @version 1.0.0
 */
public enum Ordering
{
    ASCENDING, DESCENDING;


    /**
     * @return The SQL query fragment that this enumeration represents.
     */
    public String SQL()
    {
        String res = null;

        switch (this)
        {
            case ASCENDING:
                res = "ASC";
                break;
            case DESCENDING:
                res = "DESC";
                break;
        }

        return res;
    }
}
