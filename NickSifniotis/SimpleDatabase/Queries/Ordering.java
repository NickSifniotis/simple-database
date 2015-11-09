package NickSifniotis.SimpleDatabase.Queries;

/**
 * Created by nsifniotis on 9/11/15.
 *
 * Simple enumeration constants for SQL ORDER BY clauses.
 */
public enum Ordering
{
    ASCENDING, DESCENDING;

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
