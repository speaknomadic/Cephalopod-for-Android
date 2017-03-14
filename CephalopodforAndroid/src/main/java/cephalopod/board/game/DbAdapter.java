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

public class DbAdapter {

    DbHelper helper;

    public DbAdapter(Context context){
        helper = new DbHelper(context);
    }
    public long insertData(String username,String password){

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_USERNAME,username);
        values.put(DbHelper.COLUMN_PASS,password);

        long id = db.insert(DbHelper.TABLE_NAME,null,values);
        return id;
    }
    public String getAllData(){
        SQLiteDatabase db = helper.getWritableDatabase();
        /**
         * select _id,username,pass from CEPHALOPOD table
         */
        String [] columns = {DbHelper.COLUMN_ID,DbHelper.COLUMN_USERNAME,DbHelper.COLUMN_PASS};
        //this Cursor object is containing a subset of a table that contains the results.
        Cursor cursor = db.query(DbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
            int cid = cursor.getInt(0);
            String username = cursor.getString(1);
            String pass = cursor.getString(2);
            buffer.append(cid+" "+username+" "+pass+"\n");
        }
        return buffer.toString();
    }
    public String getData(String username){
        SQLiteDatabase db = helper.getWritableDatabase();
        /**
         * select username,pass from CEPHALOPOD table
         */
        String [] columns = {DbHelper.COLUMN_USERNAME,DbHelper.COLUMN_PASS};
        //this Cursor object is containing a subset of a table that contains the results.
        Cursor cursor = db.query(DbHelper.TABLE_NAME,columns,DbHelper.COLUMN_USERNAME+" = '" +username+"'",null,null,null,null);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
           int index1 = cursor.getColumnIndex(DbHelper.COLUMN_USERNAME);
            int index2 = cursor.getColumnIndex(DbHelper.COLUMN_PASS);
            String personUsername = cursor.getString(index1);
            String pass = cursor.getString(index2);
            buffer.append(username+" "+pass+"\n");
        }
        return buffer.toString();
    }

    public boolean getUser(String username, String pass) {
        String details = username+" "+pass+"\n";
        if(details.equals(getData(username))){
           return true;
       }
        return false;
    }

    static class DbHelper extends SQLiteOpenHelper{
       private Context context;
       private static final String DB_NAME = "cephalopod.db";
       private static final String TABLE_NAME = "CEPHALOPOD";
       private static final int DB_VERSION = 6;

       private static final String COLUMN_ID = "_id";
       private static final String COLUMN_USERNAME = "username";
       private static final String COLUMN_PASS = "password";

       /**
        * create table users(
        *      id integer primary key autoincrement,
        *      username text,
        *      passwort text);
        */
       public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +
               " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USERNAME + " VARCHAR(255), " +
               COLUMN_PASS + " VARCHAR(255));";

       public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

       public DbHelper(Context context) {

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
   }

}

