package com.example.p_finalproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.p_finalproyecto.db.DbHelper;
import com.example.p_finalproyecto.db.dbProducto;
import com.example.p_finalproyecto.entidades.Producto;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> cartNombres;
    ArrayList<String> cartPrecios;
    ArrayList<String> cartDescripciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DATABASE AND TABLE CREATION
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //FILL TABLES
        dbProducto dbProducto = new dbProducto(MainActivity.this);

        ArrayList<Producto> startInfo = dbProducto.obtieneEnvasados();

        if(startInfo.isEmpty()){
            //Envasados
            dbProducto.insertarProducto("Cafe Descafeinado Dio", "Envasados", 60, "Cafe descafeinado, 500g, importado", R.drawable.en_cafe);
            dbProducto.insertarProducto("Duraznos Enlatados Arcor", "Envasados", 24, "Duraznos al almibar, contiene 7", R.drawable.en_durazno);
            dbProducto.insertarProducto("Leche Enlatada Nestle", "Envasados", 50, "Leche enlatada para todo uso, especial para reposteria", R.drawable.en_lecheenlatada);
            dbProducto.insertarProducto("Mermelada de Fresa Facundo", "Envasados", 24, "Mermelada de 500g, sabor fresa, producto nacional", R.drawable.en_mermelada);

            //Verduras
            dbProducto.insertarProducto("Zanahorias", "Verduras", 16, "Conservadas frescas, 1 libra", R.drawable.ve_zanahoria);
            dbProducto.insertarProducto("Lechugas", "Verduras", 12, "Sin insecticidas nocivos, 1 cabeza", R.drawable.ve_lechuga);
            dbProducto.insertarProducto("Papas", "Verduras", 60, "1 arroba, produccion nacional, cocecha especial", R.drawable.ve_papa);
            dbProducto.insertarProducto("Tomates", "Verduras", 16, "Frescos, sin pesticidas, 1 libra", R.drawable.ve_tomate);

            //Lacteos
            dbProducto.insertarProducto("Helado Casata", "Lacteos", 17, "De 3 sabores variados, chocolate, vainilla, fresa, conservar en lugar frio", R.drawable.la_helado);
            dbProducto.insertarProducto("Leche Deslactosada", "Lacteos", 9, "De 500ml apto para todo consumo", R.drawable.la_leche);
            dbProducto.insertarProducto("Queso Mozzarella", "Lacteos", 20, "500g altamente procesado, especial para pastas", R.drawable.la_queso);
            dbProducto.insertarProducto("Yogurt Familiar", "Lacteos", 8, "Sabor durazno de 500ml, conservar en lugar frio", R.drawable.la_yogurt);
        }

        /*
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db != null){
            //Borrar luego
            Toast.makeText(this, "Si funca we", Toast.LENGTH_SHORT).show();

            //no en el tuto
            //db.close();

        }
        else {
            Toast.makeText(this, "Ya valio we", Toast.LENGTH_SHORT).show();
        }

        //FILL TABLES
        dbProducto dbProducto = new dbProducto(MainActivity.this);

            //Envasados
        dbProducto.insertarProducto("Cafe Descafeinado Dio", "Envasados", 60, "Cafe descafeinado, 500g, importado", R.drawable.en_cafe);
        dbProducto.insertarProducto("Duraznos Enlatados Arcor", "Envasados", 24, "Duraznos al almibar, contiene 7", R.drawable.en_durazno);
        dbProducto.insertarProducto("Leche Enlatada Nestle", "Envasados", 50, "Leche enlatada para todo uso, especial para reposteria", R.drawable.en_lecheenlatada);
        dbProducto.insertarProducto("Mermelada de Fresa Facundo", "Envasados", 24, "Mermelada de 500g, sabor fresa, producto nacional", R.drawable.en_mermelada);

            //Verduras
        dbProducto.insertarProducto("Zanahorias", "Verduras", 16, "Conservadas frescas, 1 libra", R.drawable.ve_zanahoria);
        dbProducto.insertarProducto("Lechugas", "Verduras", 12, "Sin insecticidas nocivos, 1 cabeza", R.drawable.ve_lechuga);
        dbProducto.insertarProducto("Papas", "Verduras", 60, "1 arroba, produccion nacional, cocecha especial", R.drawable.ve_papa);
        dbProducto.insertarProducto("Tomates", "Verduras", 16, "Frescos, sin pesticidas, 1 libra", R.drawable.ve_tomate);

            //Lacteos
        dbProducto.insertarProducto("Helado Casata", "Lacteos", 17, "De 3 sabores variados, chocolate, vainilla, fresa, conservar en lugar frio", R.drawable.la_helado);
        dbProducto.insertarProducto("Leche Deslactosada", "Lacteos", 9, "De 500ml apto para todo consumo", R.drawable.la_leche);
        dbProducto.insertarProducto("Queso Mozzarella", "Lacteos", 20, "500g altamente procesado, especial para pastas", R.drawable.la_queso);
        dbProducto.insertarProducto("Yogurt Familiar", "Lacteos", 8, "Sabor durazno de 500ml, conservar en lugar frio", R.drawable.la_yogurt);
        */


        //START SHOPCART, will be sent through redirection
        cartNombres = new ArrayList<>();
        cartPrecios = new ArrayList<>();
        cartDescripciones = new ArrayList<>();

        //Start screen timer
        Timer start = new Timer();

        start.scheduleAtFixedRate(new TimerTask(){
            int i = 5;

            public void run(){
                i--;

                if(i < 0){
                    start.cancel();

                    Intent startPage = new Intent(getApplicationContext(), StartActivity.class);

                    //send shopcart
                    startPage.putExtra("cartNombres",cartNombres);
                    startPage.putExtra("cartPrecios",cartPrecios);
                    startPage.putExtra("cartDescripciones",cartDescripciones);

                    startActivity(startPage);

                    finish();
                }
            }
        }, 0, 1000);
    }
}