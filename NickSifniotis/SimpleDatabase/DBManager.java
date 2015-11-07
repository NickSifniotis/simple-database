package NickSifniotis.SimpleDatabase;

import java.sql.*;


/**
 * Created by nsifniotis on 31/08/15.
 * Revised on 08/11/2015
 *
 * Wrapper class for interfacing with the SQLite database.
 *
 */
public class DBManager
{
    private static String database_path = "database";
    private static String database_name = "database.db";


    /**
     * Nick Sifniotis u5809912
     * 08/11/2015
     *
     * Changes the location and name of the database file.
     *
     * If you are going to change the database from the default database/database.db,
     * make sure that you call this function when you initialise your program, before you attempt
     * to perform any operations on the database.
     *
     * @param new_path - the path where the database file will be located, without the trailing '/'
     * @param new_name - the name of the database file
     */
    public static void SetDBLocation (String new_path, String new_name)
    {
        database_name = new_name;
        database_path = new_path;
    }


    /**
     * Nick Sifniotis u5809912
     * 31/08/2015
     *
     * Executes the given query
     * No safety checks whatsoever are conducted on the query string. Use with caution!
     *
     * @param query - the query to execute
     * @throws SQLException - this static class handles no exceptions.
     */
    public static void Execute (String query) throws SQLException
    {
        Connection connection = null;
        Statement statement = null;

        try
        {
            connection = Connect();
            statement = connection.createStatement();
            statement.execute(query);
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
     * @throws SQLException - this class does not handle faulty queries
     */
    public static int ExecuteReturnKey (String query) throws SQLException
    {
        int res = -1;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generated_keys = null;

        try
        {
            connection = Connect();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int affected_rows = statement.executeUpdate();

            if (affected_rows != 1)
                throw new SQLException("DBManager.ExecuteReturnKey - Insert into database failed. Affected rows: " + affected_rows + ": " + query);

            generated_keys = statement.getGeneratedKeys();

            if (generated_keys.next())
                res = generated_keys.getInt(1);
            else
                throw new SQLException("DBManager.ExecuteReturnKey - Creating user failed, no ID obtained.");
        }
        finally
        {
            closeQuietly(generated_keys);
            closeQuietly(statement);
            closeQuietly(connection);
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
     * @throws SQLException - this class does not handle exceptions
     */
    public static ResultSet ExecuteQuery (String query, Connection connection) throws SQLException
    {
        ResultSet results = null;

        if (connection != null)
            results = connection.createStatement().executeQuery(query);

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
     * @throws SQLException - SQL Exceptions aren't handled by this class.
     */
    public static Connection Connect() throws SQLException
    {
        Connection connection = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + database_path + "/" + database_name);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
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
