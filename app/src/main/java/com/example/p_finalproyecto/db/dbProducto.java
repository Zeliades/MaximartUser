package com.example.p_finalproyecto.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.p_finalproyecto.entidades.Producto;

import java.util.ArrayList;

public class dbProducto extends DbHelper{

    Context context;

    public dbProducto(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarProducto(String nombre, String tipo, int precio, String descripcion, int iddrawable){
        long id = 0;

        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("tipo", tipo);
            values.put("precio", precio);
            values.put("descripcion", descripcion);
            values.put("iddrawable", iddrawable);

            //retorna id insertado
            id = db.insert(TABLE_PRODUCTOS, null, values);
        }
        catch (Exception e) {
            e.toString();
        }

        return id;
    }

    //OBTENCION DE PRODUCTOS SEGUN TIPO

    public ArrayList<Producto> obtieneEnvasados(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Producto> listaProductos = new ArrayList<>();
        Producto producto = null;
        Cursor cursorProducto = null;

        cursorProducto = db.rawQuery("SELECT * FROM "+TABLE_PRODUCTOS+" WHERE tipo='Envasados'", null);

        //pasar cursor a 1er resultado de la consulta
        if(cursorProducto.moveToFirst()){
            do{
                producto = new Producto();

                producto.setId(cursorProducto.getInt(0));
                producto.setNombre(cursorProducto.getString(1));
                producto.setTipo(cursorProducto.getString(2));
                producto.setPrecio(cursorProducto.getInt(3));
                producto.setDescripcion(cursorProducto.getString(4));
                producto.setIddrawable(cursorProducto.getInt(5));

                listaProductos.add(producto);
            }
            while(cursorProducto.moveToNext());

        }

        cursorProducto.close();

        return listaProductos;
    }

    public ArrayList<Producto> obtieneLacteos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Producto> listaProductos = new ArrayList<>();
        Producto producto = null;
        Cursor cursorProducto = null;

        cursorProducto = db.rawQuery("SELECT * FROM "+TABLE_PRODUCTOS+" WHERE tipo='Lacteos'", null);

        //pasar cursor a 1er resultado de la consulta
        if(cursorProducto.moveToFirst()){
            do{
                producto = new Producto();

                producto.setId(cursorProducto.getInt(0));
                producto.setNombre(cursorProducto.getString(1));
                producto.setTipo(cursorProducto.getString(2));
                producto.setPrecio(cursorProducto.getInt(3));
                producto.setDescripcion(cursorProducto.getString(4));
                producto.setIddrawable(cursorProducto.getInt(5));

                listaProductos.add(producto);
            }
            while(cursorProducto.moveToNext());

        }

        cursorProducto.close();

        return listaProductos;
    }

    public ArrayList<Producto> obtieneVerduras(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Producto> listaProductos = new ArrayList<>();
        Producto producto = null;
        Cursor cursorProducto = null;

        cursorProducto = db.rawQuery("SELECT * FROM "+TABLE_PRODUCTOS+" WHERE tipo='Verduras'", null);

        //pasar cursor a 1er resultado de la consulta
        if(cursorProducto.moveToFirst()){
            do{
                producto = new Producto();

                producto.setId(cursorProducto.getInt(0));
                producto.setNombre(cursorProducto.getString(1));
                producto.setTipo(cursorProducto.getString(2));
                producto.setPrecio(cursorProducto.getInt(3));
                producto.setDescripcion(cursorProducto.getString(4));
                producto.setIddrawable(cursorProducto.getInt(5));

                listaProductos.add(producto);
            }
            while(cursorProducto.moveToNext());

        }

        cursorProducto.close();

        return listaProductos;
    }
}
