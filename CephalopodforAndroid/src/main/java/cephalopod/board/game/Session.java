package cephalopod.board.game;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vladimircvetanov on 11.03.17.
 */

/**
 * Session class stores data from login.
 */
public class Session {

    /**
     * Reference to the SharedPreferences object.
     */
    SharedPreferences prefs;

    /**
     * Reference to the SharedPreferences Editor.
     */
    SharedPreferences.Editor editor;

    /**
     * Reference to Context.
     */
    Context ctx;

    /**
     * Constructor of a Session instance. In the constructor getSharedPreferences() method is called with two arguments. The first one is the name of the file and the second is the mode. The access mode to the file is set as private.
     * Checking if user is logged in. Remember user settings if user is logged in. Only our app can access the file
     *
     * @param ctx
     */

    public Session(Context ctx) {
        /**
         * Set context
         */
        this.ctx = ctx;

        /**
         * Get reference to the Shared Preferences object. Set mode.
         */
        prefs = ctx.getSharedPreferences("cephalopod", Context.MODE_PRIVATE);

        /**
         * In order to store data in the file. Call to editor object is made. Reference to an editor will be used to commet all edits..
         */
        editor = prefs.edit();
    }

    /**
     * Setter for the login state of user. Add data using String "loggedInmode" as a key. The boolean value will be pulled by setLoggedin(), from logIn() method in LoginActivity.If login is successful the value is true.
     *
     * @param loggedin
     */
    public void setLoggedin(boolean loggedin) {
        editor.putBoolean("loggedInmode", loggedin);

        /**
         * Commit changes.
         */
        editor.commit();
    }

    /**
     * Reference to the Shared Preference and the key are used to load values by key is String. If user is logged it returns true. Else returns a default value false.
     *
     * @return boolean
     */
    public boolean loggedIn() {
        return prefs.getBoolean("loggedInmode", false);
    }
}
