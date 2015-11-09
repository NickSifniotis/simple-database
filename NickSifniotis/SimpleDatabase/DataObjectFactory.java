package NickSifniotis.SimpleDatabase;

import NickSifniotis.SimpleDatabase.Columns.Column;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nsifniotis on 6/11/15.
 *
 * This class contains all the methods for loading and saving DataObjects and their children
 * to the database itself.
 */
public class DataObjectFactory {
    private static HashMap<String, String> field_mapping;
    private static boolean initialised = false;


    public static void Initialise() {
        field_mapping = new HashMap<>();
        field_mapping.put("IntegerColumn", "INTEGER");
        field_mapping.put("boolean", "INTEGER");
        field_mapping.put("float", "REAL");
        field_mapping.put("double", "REAL");
        field_mapping.put("StringColumn", "TEXT");

        initialised = true;
    }


    /**
     * Nick Sifniotis u5809912
     * 07/11/2015
     * <p>
     * Loads all items from this class's table. Maybe.
     *
     * @param object_type - the type of object to load / the table to load from
     * @return an array of all the loaded things.
     */
    public static DataObject[] LoadAll(Class object_type) {
        if (!initialised)
            Initialise();


        List<DataObject> result_list = new LinkedList<>();
        String query = "SELECT * FROM " + object_type.getSimpleName();

        Connection connection = null;
        ResultSet results = null;
        try {
            connection = DBManager.Connect();
            results = DBManager.ExecuteQuery(query, connection);
            while (results.next())
                result_list.add(Load(object_type, results));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.Disconnect(results);
            DBManager.Disconnect(connection);
        }

        DataObject[] res = new DataObject[result_list.size()];
        return result_list.toArray(res);
    }


    /**
     * Nick Sifniotis u5809912
     * 07/11/2015
     * <p>
     * Loads all of this object's fields from the given dataset.
     * The field names - which it obtains through a bit of reflection -
     * form the names of the database columns. Brilliant, no?
     *
     * @param object_type - the type of object to load / the table to load from
     * @param dataset     - the database recordset to load data from.
     */
    public static DataObject Load(Class object_type, ResultSet dataset) {
        if (!initialised)
            Initialise();


        DataObject result = null;
        try {
            result = (DataObject) object_type.newInstance();

            Field[] fields = object_type.getFields();
            for (Field f : fields) {
                switch (f.getType().getSimpleName()) {
                    case "int":
                        f.setInt(result, dataset.getInt(f.getName()));
                        break;
                    case "short":
                        f.setShort(result, dataset.getShort(f.getName()));
                        break;
                    case "long":
                        f.setLong(result, dataset.getLong(f.getName()));
                        break;
                    case "byte":
                        f.setByte(result, dataset.getByte(f.getName()));
                        break;
                    case "float":
                        f.setFloat(result, dataset.getFloat(f.getName()));
                        break;
                    case "double":
                        f.setDouble(result, dataset.getDouble(f.getName()));
                        break;
                    case "boolean":
                        f.setBoolean(result, dataset.getInt(f.getName()) == 1);
                        break;
                    case "char":
                        char[] raw_string = dataset.getString(f.getName()).toCharArray();
                        f.setChar(result, raw_string[0]);
                        break;
                    case "String":
                        f.set(result, dataset.getString(f.getName()));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * Nick Sifniotis u5809912
     * 06/11/2015
     * <p>
     * Saves the object into the database.
     *
     * @param object - the object to save.
     */
    public static void Save(DataObject object) {
        if (!initialised)
            Initialise();


        // the structure of INSERT and UPDATE queries is fundamentally different,
        // so there will be two largish blocks of code here.


        // first step - extract the relevant data from the class and the instance itself.
        String table_name = object.getClass().getSimpleName();
        Field[] fields = object.getClass().getDeclaredFields();
        String[] col_names = new String[fields.length];
        String[] col_values = new String[fields.length];

        try {
            for (int i = 0; i < fields.length; i++) {
                Object f = fields[i].get(object);
                try {
                    Column ff = (Column) f;
                    col_values[i] = ff.SQLFieldValue();
                    col_names[i] = fields[i].getName();
                } catch (Exception e) {
                    // carry on as though nothing is wrong ..
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }


        // second step - build and execute the SQL queries.
        // as I said above, it's fundamentally different for INSERT and UPDATE
        if (object.PrimaryKey == -1) {
            // this is an insert operation
            String query = "INSERT INTO " + table_name + "(";

            for (int i = 0; i < col_names.length; i++)
                query += col_names[i] + (i < (col_names.length - 1) ? ", " : "");

            query += ") VALUES (";

            for (int i = 0; i < col_values.length; i++)
                query += col_values[i] + (i < (col_values.length - 1) ? ", " : "");

            query += ");";

            try {
                object.PrimaryKey = DBManager.ExecuteReturnKey(query);
            } catch (Exception e) {
                System.out.println("SQL error on insert.");
                e.printStackTrace();
            }
        } else {
            // this is an update
            String query = "UPDATE " + table_name + " SET ";

            for (int i = 0; i < col_names.length; i++)
                query += col_names[i] + " = " + col_values[i] + (i < (col_names.length - 1) ? ", " : "");

            query += " WHERE PrimaryKey = " + String.valueOf(object.PrimaryKey);

            try {
                DBManager.Execute(query);
            } catch (Exception e) {
                System.out.println("SQL error on update.");
                e.printStackTrace();
            }
        }
    }


    /**
     * Nick Sifniotis u5809912
     * 06/11/2015
     * <p>
     * Saves all of the objects into the database.
     *
     * @param objects - the list of objects to save.
     */
    public static void SaveAll(DataObject[] objects) {
        if (!initialised)
            Initialise();


        for (DataObject o : objects)
            Save(o);
    }


    /**
     * Nick Sifniotis u5809912
     * 06/11/2015
     * <p>
     * Drops the table for the given object type from the database. Bye bye data.
     *
     * @param object_type - the class that corresponds to the table to drop.
     */
    public static void DeleteTable(Class object_type) {
        if (!initialised)
            Initialise();


        String table_name = object_type.getSimpleName();
        DeleteTable(table_name);
    }


    /**
     * Nick Sifinotis u5809912
     * 06/11/2015
     * <p>
     * Drops a table from the database, but by table name, not class type.
     *
     * @param table_name - the table to delete.
     */
    public static void DeleteTable(String table_name) {
        if (!initialised)
            Initialise();


        String query = "DROP TABLE IF EXISTS " + table_name;

        try {
            DBManager.Execute(query);
        } catch (SQLException e) {
            System.out.println("SQL error on drop table " + table_name);
            e.printStackTrace();
        }
    }


    public static void CreateTable(Class object_type) {
        if (!initialised)
            Initialise();


        // pull out all of the relevant data about the class that we are saving
        String table_name = object_type.getSimpleName();
        Field[] columns = object_type.getFields();
        String[] column_declarations = new String[columns.length];

        for (int i = 0; i < columns.length; i++) {
            Field f = columns[i];
            String name = f.getName();
            String type = f.getType().getSimpleName();

            if (name.equals("PrimaryKey"))
                type = "INTEGER PRIMARY KEY";
            else
                type = field_mapping.get(type);

            column_declarations[i] = name + " " + type;
        }


        // if it exists, drop the old table
        DeleteTable(table_name);


        // construct the SQL query.
        String query = "CREATE TABLE " + table_name + "(";
        for (int i = 0; i < column_declarations.length; i++)
            query += column_declarations[i] + (i < (column_declarations.length - 1) ? ", " : "");
        query += ");";


        // GO GO GO
        try {
            DBManager.Execute(query);
        } catch (Exception e) {
            System.out.println("SQL error on create table " + table_name);
            e.printStackTrace();
        }
    }


    /**
     * Nick Sifniotis u5809912
     * 06/11/2015
     * <p>
     * Tests to determine whether or not the class that has been passed to this function
     * is a descendant of DataObject.
     * <p>
     * DataObjectFactory is only able to work with descendants of that class.
     *
     * @param unknown - the mystery class
     * @return True if the class is a valid descendant, false otherwise.
     */
    private static boolean __validate_descendant_of_dataobject(Class unknown) {
        boolean res = false;

        Class parent = unknown.getSuperclass();
        if (parent != null)
            if (parent.getSimpleName().equals("DataObject"))
                res = true;

        return res;
    }


    /**
     * Nick Sifniotis u5809912
     * 06/11/2015
     * <p>
     * Creates a new instance of the type of object specified by the user.
     * Autosets the default values, and saves to the database.
     * Do not call this method if you do not intend to save the object.
     *
     * @return - a new object of that type
     */
    public static DataObject New(Class object_type) {
        if (!initialised)
            Initialise();

        if (!__validate_descendant_of_dataobject(object_type))
            return null;

        DataObject new_instance;
        try {
            new_instance = (DataObject) object_type.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (new_instance != null) {
            new_instance.SetDefaults();
            Save(new_instance);
        }

        return new_instance;
    }


    /**
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
     * Returns a list of Column objects for the given DataObject instance
     * @param object - the DataObject who's fields we want
     * @return - an array of all Columns (or descendants) found in that object.
     */
    private static Column[] __get_table_fields(DataObject object) {
        // perform basic validation to ensure that we are not trying to save a class type that is
        // not a child class of DataObject.
        if (!__validate_descendant_of_dataobject(object.getClass()))
            return null;

        Field[] holding_array = object.getClass().getDeclaredFields();
        List<Column> holding_list = new LinkedList<>();

        try
        {
            for (Field f: holding_array)
                holding_list.add((Column) f.get(object));
        }
        catch (Exception e)
        {
            // do nothing
        }

        Column[] results = new Column[holding_list.size()];
        return holding_list.toArray(results);
    }


    /**
     * Nick Sifniotis u5809912
     * 09/11/2015
     *
     * Returns an array containing the names of every Column type field in the class
     *
     * @param class_type - the class that we are listing the fields for
     * @return the array of strings
     */
    private static String[] __get_column_names(Class class_type)
    {
        // perform basic validation to ensure that we are not trying to save a class type that is
        // not a child class of DataObject.
        if (!__validate_descendant_of_dataobject(class_type))
            return null;

        Field[] holding_array = class_type.getDeclaredFields();
        List<String> holding_list = new LinkedList<>();

        for (Field f: holding_array)
            if (field_mapping.containsKey(f.getClass().getSimpleName()))
                holding_list.add(f.getName());

        String[] results = new String[holding_list.size()];
        return holding_list.toArray(results);
    }
}
