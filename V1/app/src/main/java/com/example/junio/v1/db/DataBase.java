package com.example.junio.v1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by junio on 03/10/17.
 */

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context) {
        super(context, "database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table category ( id_category int not null, name text, primary key (id_category) )");

        db.execSQL("create table car " +
                "( nro_chassi int not null, " +
                "id_category int not null, " +
                "brand text, " +
                "model text, " +
                "primary key (nro_chassi), " +
                "FOREIGN KEY(id_category) REFERENCES category(id_category) )");
//
        db.execSQL("insert into category (id_category, name) values ('1', 'teste')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table car");
//        db.execSQL("drop table category");
        onCreate(db);
    }
}
