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
 * Database Adapter class, responsible for managing data inserting, getting data about users, when our application calls methods.
 */

public class DbAdapter {

    /**
     * An instance of Database Adapter class. A reference of instance of the Database Adapter class executes queries.
     */
    private DbHelper helper;

    /**
     * @param context
     */
    public DbAdapter(Context context) {
        helper = new DbHelper(context);
    }

    /**
     * @param username
     * @param password
     * @return
     */
    public long insertData(String username, String password) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_USERNAME, username);
        values.put(DbHelper.COLUMN_PASS, password);
        long id = db.insert(DbHelper.TABLE_NAME, null, values);
        return id;
    }

    /**
     * @return
     */
    public String getAllData() {
        /**
         * The method getWritableDatabase()called on helper inner class instance returns SQLiteDatabase object.
         * This object is used in the class to perform operations. It has methods to create, delete, execute SQL commands, and perform database management tasks.
         */
        SQLiteDatabase db = helper.getWritableDatabase();
        /**
         * select _id,username,pass from CEPHALOPOD table
         */
        String[] columns = {DbHelper.COLUMN_ID, DbHelper.COLUMN_USERNAME, DbHelper.COLUMN_PASS};
        //this Cursor object is containing a subset of a table that contains the results.
        Cursor cursor = db.query(DbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(0);
            String username = cursor.getString(1);
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
        SQLiteDatabase db = helper.getWritableDatabase();
        /**
         * select username,pass from CEPHALOPOD table
         */
        String[] columns = {DbHelper.COLUMN_USERNAME, DbHelper.COLUMN_PASS};
        //this Cursor object is containing a subset of a table that contains the results.
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
     * A custom class implementation of SQLiteOpenHelper is created. Database's schema is defined programatically.
     * This class takes care of opening the database if it exists, creating it if it does not exist and upgrading it if necessary.
     */
    static class DbHelper extends SQLiteOpenHelper {

        /**
         * Definition of context;
         */
        private Context context;

        /**
         * Definition of unique for the application database name. Specify a String constant.
         */
        private static final String DB_NAME = "cephalopod.db";

        /**
         * Definition of the database's table name. Specify a String constant.
         */
        private static final String TABLE_NAME = "CEPHALOPOD";

        /**
         * Definition of database version int constant.
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
         *
         */
        public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

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
            } catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }
    }
}

