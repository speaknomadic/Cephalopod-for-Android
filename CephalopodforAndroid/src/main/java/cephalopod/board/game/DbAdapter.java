package cephalopod.board.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by vladimircvetanov on 11.03.17.
 */

public class DbAdapter extends SQLiteOpenHelper{

    private Context context;
    private static final String TAG = DbAdapter.class.getSimpleName();
    private static final String DB_NAME = "cephalopod.db";
    private static final String TABLE_NAME = "CEPHALOPOD";
    private static final int DB_VERSION = 1;

    private static final String USER_TABLE = "users";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASS = "password";

    /**
     * create table users(
     *      id integer primary key autoincrement,
     *      username text,
     *      passwort text);
     */
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " VARCHAR(255), " +
             COLUMN_PASS + " VARCHAR(255));";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS" + TABLE_NAME;

    public DbAdapter(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    /**
     * Creating the database table with query;
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        }catch (SQLException e){
            Message.message(context,"" + e);
        }

    }

    /**
     * If we want to make updates, deletes the old table and create the new one with new parameters with query.
     * @param db - the database.
     * @param oldVersion - go from old version
     * @param newVersion - to new version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (SQLException e){
            Message.message(context,"" + e);
        }
    }
    /**
     * Storing user details in database.
     */
    public void addUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME,username);
        values.put(COLUMN_PASS,password);

        long id = db.insert(USER_TABLE,COLUMN_USERNAME,values);
        db.close();
        Log.d(TAG,"user inserted " + id );
    }

    public boolean getUser(String username,String password){
        String selectQuery = "select * from " + USER_TABLE + " where " +
                COLUMN_USERNAME + " = " + " ' " + username + " ' " + " and " + COLUMN_PASS + " = " +
                " ' " + password + " ' ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0){

            return true;
        }
        cursor.close();
        db.close();


        return false;
    }
}

