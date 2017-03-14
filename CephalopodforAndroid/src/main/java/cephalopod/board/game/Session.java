package cephalopod.board.game;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vladimircvetanov on 11.03.17.
 */

public class Session {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("cephalopod",Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin(boolean loggedin){
        editor.putBoolean("loggedInmode",loggedin);
        editor.commit();
    }

    public boolean loggedIn(){
        return prefs.getBoolean("loggedInmode",false);
    }
}
