package cephalopod.board.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vladimircvetanov on 11.03.17.
 */

/**
 * Database Adapter class, responsible for managing data inserting, getting data about users,
 * when the application calls methods it has access to all private fields of the inner class.
 */
public class DbAdapter {

    /**
     * Declaration of fields of the adapter class. A reference to innerclass will executes queries.
     */
    private DbHelper helper;

    /**
     * Constructor of the Adapter class. An object with reference to the inner class is instanciated with a context parameter.
     * The adapter creates and performs database management tasks.
     *
     * @param context
     */

    public DbAdapter(Context context) {
        helper = new DbHelper(context);
    }

    /**
     * A method used to insert data
     *
     * @param username
     * @param password
     * @return long id if the method was successful and -1 if it fails.
     */
    public long insertData(String username, String password) {
        SQLiteDatabase db = helper.getWritableDatabase();

        /**
         * An instance of ContentValues class is created. To insert data the reference takes a key and a value. We specify the key as the column name. The value is the data we want ot put inside.
         */
        ContentValues values = new ContentValues();

        /**
         * The first column is inserted.
         */
        values.put(DbHelper.COLUMN_USERNAME, username);
        values.put(DbHelper.COLUMN_PASS, password);

        /**
         * The insert method with three parameters(String TableName, String NullColumnHAck, ContentValues object) is called on the SQL object of the class.
         * It returns the ID of the inserted row or -1 if the operation fails.
         */
        long id = db.insert(DbHelper.TABLE_NAME, null, values);
        return id;
    }

    /**
     * A method which returns all data in the table
     *
     * @return
     */
    public String getAllData() {

        /**
         * The method getWritableDatabase() is called on the reference of helper inner class instance returns SQLiteDatabase object.
         * This object is used in the class to perform operations.
         */
        SQLiteDatabase db = helper.getWritableDatabase();

        /**
         * select _id,username,pass from CEPHALOPOD table
         */
        String[] columns = {DbHelper.COLUMN_ID, DbHelper.COLUMN_USERNAME, DbHelper.COLUMN_PASS};

        /**
         * Query method. Cursor object returned by the query method. The cursor object's reference is the control which let's us move from the top to the bottom of the table's result sets.
         * The method query takes seven parameters: String table, String[] columns (list of columns to process, null returns all); extra conditions on the SQL statement to return rows satisfying certain criteria, String selection, String [] selectionArgs, String groupBy, String having, String orderby
         */
        Cursor cursor = db.query(DbHelper.TABLE_NAME, columns, null, null, null, null, null);

        StringBuffer buffer = new StringBuffer();
        /**
         * Call moveToNext(), if results are not empty, and we are not at the bottom it returns true; otherwise returns false;
         */
        while (cursor.moveToNext()) {
            /**
             * Cid keeps a reference to the int value of _id of element in the table on column _id (index 0).
             */
            int cid = cursor.getInt(0);
            /**
             * String reference to the String username value from the same entry set at column username (index 1).
             */
            String username = cursor.getString(1);
            /**
             * String reference to the String password value from the same entry set at column password (index 2).
             */
            String pass = cursor.getString(2);

            buffer.append(cid + " " + username + " " + pass + "\n");
        }
        return buffer.toString();
    }

    /**
     * @param username
     * @return
     */
    public String getData(String username) {

        /**
         * A reference from inner class is used to create a Database object.
         *
         */
        SQLiteDatabase db = helper.getWritableDatabase();
        /**
         * select username,pass from CEPHALOPOD table
         */
        String[] columns = {DbHelper.COLUMN_USERNAME, DbHelper.COLUMN_PASS};

        /**
         * A call to the query method. Cursor object returned by the query method. The cursor object's reference is the control which let's us move from the top to the bottom of the table's result sets.
         * The method query takes seven parameters: String table, String[] columns (list of columns to process, null returns all); extra conditions on the SQL statement to return rows satisfying certain criteria, String selection, String [] selectionArgs, String groupBy, String having, String orderby
         */
        Cursor cursor = db.query(DbHelper.TABLE_NAME, columns, DbHelper.COLUMN_USERNAME + " = '" + username + "'", null, null, null, null);

        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(DbHelper.COLUMN_USERNAME);
            int index2 = cursor.getColumnIndex(DbHelper.COLUMN_PASS);
            String personUsername = cursor.getString(index1);
            String pass = cursor.getString(index2);
            buffer.append(username + " " + pass + "\n");
        }
        return buffer.toString();
    }

    /**
     * Method to verify user data if user is already in database.
     *
     * @param username
     * @param pass
     * @return
     */
    public boolean getUser(String username, String pass) {
        String details = username + " " + pass + "\n";
        if (details.equals(getData(username))) {
            return true;
        }
        return false;
    }

    /**
     * Inner static class which is responsible for the creation of  database.
     * A custom class implementation of SQLiteOpenHelper is created. Database's schema is defined programatically.
     * This class takes care of opening the database if it exists, creating it if it does not exist and upgrading it if necessary.
     */
    static class DbHelper extends SQLiteOpenHelper {

        /**
         * Definition of unique for the application database name. Specify a String constant.
         */
        private static final String DB_NAME = "cephalopod.db";
        /**
         * Definition of the database's table name. Specify a String constant.
         */
        private static final String TABLE_NAME = "CEPHALOPOD";
        /**
         * Constant String SQL statement for erasing old version of table.
         */
        public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        /**
         * Definition of database version int constant.When database is changed version should be modified to next int.
         */
        private static final int DB_VERSION = 6;

        /**
         * Definition of the primary key. The key constant autoincrements.
         */
        private static final String COLUMN_ID = "_id";

        /**
         * Definition of the name of column 1; Specify a String constant.
         */
        private static final String COLUMN_USERNAME = "username";

        /**
         * Definition of the name of column 2; Specify a String constant.
         */
        private static final String COLUMN_PASS = "password";

        /**
         * create database of users(
         * id integer primary key autoincrement,
         * username text,
         * password text);
         */
        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " VARCHAR(255), " +
                COLUMN_PASS + " VARCHAR(255));";
        /**
         * Definition of context;
         */
        private Context context;

        /**
         * Declaration of constructor the supertype class. Object is responsible for the creation of database and
         * editing of database. Constructor takes context as parameter.
         * Super constructor takes four parameters(context, database name, custom cursor object and version of database.
         * Since we do not create custom cursor factory, we pass null.
         *
         * @param context
         */
        public DbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            this.context = context;
        }

        /**
         * {@inheritDoc}
         * The method is called when the database is first created. Creation of tables and initial data inside tables is put here.
         *
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                /**
                 * Executes a single SQL statement that is NOT a SELECT and does not return data.
                 */
                db.execSQL(CREATE_TABLE);
                /**
                 * If the SQL statement is invalid it throws an exception.
                 */
            } catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }

        /**
         * {@inheritDoc}
         * Method is called when database needs to be upgraded. It is triggered when updates are made.
         * This method is used to drop tables, add tables, do anything that needs to upgrade to new version of schema.
         * It deletes the old table and creates the new one with new parameters with query.
         *
         * @param db         - the database.
         * @param oldVersion - go from old version
         * @param newVersion - to new version.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                /**
                 * When there are edits, the old table is deleted.
                 */
                db.execSQL(DROP_TABLE);
                /**
                 * Once the table is deleted the new database is created once again with new statements.
                 */
                onCreate(db);
                /**
                 * If the SQL statement is invalid it throws an exception.
                 */
            } catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }
    }
}

