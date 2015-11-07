package NickSifniotis.SimpleDatabase;

import java.sql.*;


/**
 * Created by nsifniotis on 31/08/15.
 *
 * Wrapper class for interfacing with the database.
 * No special functionality is supplied other than abstracting away the connection deets
 *
 */
public class DBManager
{
    /**
     * Nick Sifniotis u5809912
     * 31/08/2015
     *
     * Executes the given query
     * No safety checks whatsoever are conducted on the query string. Use with caution!
     *
     * @param query - the query to execute
     */
    public static void Execute (String query)
    {
        Connection connection = null;
        Statement statement = null;

        try
        {
            connection = Connect();
            statement = connection.createStatement();
            statement.execute(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeQuietly(statement);
            closeQuietly(connection);
        }
    }


    /**
     * Nick Sifniotis u5809912
     * 31/08/2015
     *
     * Executes the SQL and return the key of the affected row
     * Obviously .. the query needs to be one INSERT only.
     *
     * @param query the query to execute
     * @return the pri key of the newly created row
     */
    public static int ExecuteReturnKey (String query)
    {
        int res = -1;
        Connection connection = Connect();

        if (connection != null)
        {
            try
            {
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                int affected_rows = statement.executeUpdate();

                if (affected_rows != 1)
                {
                    String error = "DBManager.ExecuteReturnKey - Insert into database failed. Affected rows: " + affected_rows + ": " + query;
                    System.out.println(error);
                }
                else
                {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys())
                    {
                        if (generatedKeys.next())
                        {
                            res = generatedKeys.getInt(1);
                        }
                        else
                        {
                            throw new SQLException("DBManager.ExecuteReturnKey - Creating user failed, no ID obtained.");
                        }
                    }
                }

                Disconnect (connection);
            }
            catch (Exception e)
            {
                String error = "DBManager.ExecuteReturnKey - Error executing SQL query: " + query + ": " + e;
                System.out.println (error);
            }
        }

        return res;
    }


    /**
     * Nick Sifniotis u5809912
     * 31/08/2015
     *
     * Executes a query and returns the results in a resultSet
     * These queries are executed in three stages
     * connect -> executeQuery -> disconnect
     *
     * @param query - the SELECT query to execute
     * @return - the results of the query
     */
    public static ResultSet ExecuteQuery (String query, Connection connection)
    {
        ResultSet results = null;

        if (connection != null)
        {
            try
            {
                Statement statement = connection.createStatement();
                results = statement.executeQuery(query);
            }
            catch (Exception e)
            {
                String error = "DBManager.ExecuteQuery - Error executing SQL query. Query: " + query + " Exception: " + e;
                System.out.println(error);
            }
        }

        return results;
    }


    /**
     * Nick Sifniotis u5809912
     * 31/08/2015
     *
     * Connects to the tournament database.
     * Returns a connection object that can be used to process SQL and so forth.
     *
     * @return a connection object that is connected to the database. Remember to close when finished!
     */
    public static Connection Connect()
    {
        Connection connection = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database/database.db");
        }
        catch (Exception e)
        {
            String error = "DBManager.Connect - Exception connecting to tournament database: " + e;
            System.out.println(error);
        }

        return connection;
    }


    /**
     * Nick Sifniotis u5809912
     * 31/08/2015
     *
     * A variety of methods for disconnecting from the database.
     *
     * @param connection - the connection to disconnect
     */
    public static void Disconnect (Connection connection)
    {
        closeQuietly(connection);
    }

    public static void Disconnect (ResultSet results)
    {
        closeQuietly(results);
    }


    /**
     * Nick Sifniotis u5809912
     * 31/08/2015
     *
     * A quick and dirty function for converting between java booleans and SQL tinyints
     *
     * @param r the bool to convert
     * @return an int that the database can understand
     */
    public static String BoolValue(boolean r)
    {
        return r ? "1" : "0";
    }
    public static String StringValue (String s) { return (s == null || s.equals("")) ? "''" : "'" + s + "'"; }


    /**
     * Nick Sifniotis u5809912
     * 08/11/2015
     *
     * Sssh!
     *
     * Utility functions to close database connections without a fuss.
     *
     * @param res - the database entity to close
     */
    private static void closeQuietly (ResultSet res)
    {
        if (res == null)
            return;

        try
        {
            res.close();
        }
        catch (Exception e)
        {
            // ignore it
        }
    }

    private static void closeQuietly (Connection res)
    {
        if (res == null)
            return;

        try
        {
            res.close();
        }
        catch (Exception e)
        {
            // ignore it
        }
    }

    private static void closeQuietly (Statement res)
    {
        if (res == null)
            return;

        try
        {
            res.close();
        }
        catch (Exception e)
        {
            // ignore it
        }
    }
}
