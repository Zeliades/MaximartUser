package com.example.p_finalproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.p_finalproyecto.adaptadores.ListaAdapter;
import com.example.p_finalproyecto.db.dbProducto;
import com.example.p_finalproyecto.entidades.Producto;

import java.util.ArrayList;

public class LacteosActivity extends AppCompatActivity {
    //For Tab navigation
    Intent nav;

    //Show Products
    RecyclerView listaLacteos;

    //Retrieved info
    ArrayList<Producto> listaArrLacteos;

    //Cart
    ArrayList<String> cartNombres;
    ArrayList<String> cartPrecios;
    ArrayList<String> cartDescripciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lacteos);

        //Recieve cart
        cartNombres = (ArrayList<String>)getIntent().getSerializableExtra("cartNombres");
        cartPrecios = (ArrayList<String>)getIntent().getSerializableExtra("cartPrecios");
        cartDescripciones = (ArrayList<String>)getIntent().getSerializableExtra("cartDescripciones");

        //Set recycler
        listaLacteos = findViewById(R.id.rv_listaLacteos);
        listaLacteos.setLayoutManager(new LinearLayoutManager(LacteosActivity.this));

        //Set database
        dbProducto dbProducto = new dbProducto(LacteosActivity.this);
        listaArrLacteos = dbProducto.obtieneLacteos();

        //Set adapter
        ListaAdapter adapter = new ListaAdapter(listaArrLacteos);

        adapter.setOnClickListener(v -> {
            //Retrieve information from clicked element
            String transferName = listaArrLacteos.get
                    (listaLacteos.getChildAdapterPosition(v)).getNombre();
            String transferDetails = listaArrLacteos.get
                    (listaLacteos.getChildAdapterPosition(v)).getDescripcion();
            int transferPrice = listaArrLacteos.get
                    (listaLacteos.getChildAdapterPosition(v)).getPrecio();
            int transferImage = listaArrLacteos.get
                    (listaLacteos.getChildAdapterPosition(v)).getIddrawable();

            //Send retrieved information
            nav = new Intent(LacteosActivity.this, DetailsActivity.class);

            nav.putExtra("transferName", transferName);
            nav.putExtra("transferDetails", transferDetails);
            nav.putExtra("transferPrice", transferPrice);
            nav.putExtra("transferImage", transferImage);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        listaLacteos.setAdapter(adapter);


        //Set Navigation Buttons
        ((Button)findViewById(R.id.btn_LEnvasados)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), EnvasadosActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_LVerduras)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), VerdurasActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_LOfertas)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), StartActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_LCarrito)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), CartActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });
    }
}