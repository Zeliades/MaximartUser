package com.example.p_finalproyecto.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    //Database parameters

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "Productos.db";
    public static final String TABLE_PRODUCTOS = "t_productos";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_PRODUCTOS+"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "tipo TEXT," +
                "precio INTEGER," +
                "descripcion TEXT," +
                "iddrawable integer)");
    }

    //When changing database version, change version when creating tables,etc
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TABLE_PRODUCTOS);
        onCreate(db);
    }
}
