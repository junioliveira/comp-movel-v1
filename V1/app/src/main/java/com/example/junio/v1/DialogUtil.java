package com.example.junio.v1;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by junio on 03/10/17.
 */

public class DialogUtil {
    public static void showMessage(Context context, String title, String msg){
        AlertDialog.Builder alert = new AlertDialog.Builder(context)
                .setPositiveButton("OK", null)
                .setTitle(title)
                .setMessage(msg);

        alert.show();
    }
}
