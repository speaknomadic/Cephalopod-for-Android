package cephalopod.board.game;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by vladimircvetanov on 13.03.17.
 */

/**
 * Class with a static method which prints Toast with the context, message and shows .
 */

public class Message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
