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

public class VerdurasActivity extends AppCompatActivity {
    //For Tab navigation
    Intent nav;

    //Show Products
    RecyclerView listaVerduras;

    //Retrieved info
    ArrayList<Producto> listaArrVerduras;

    //Cart
    ArrayList<String> cartNombres;
    ArrayList<String> cartPrecios;
    ArrayList<String> cartDescripciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verduras);

        //Recieve cart
        cartNombres = (ArrayList<String>)getIntent().getSerializableExtra("cartNombres");
        cartPrecios = (ArrayList<String>)getIntent().getSerializableExtra("cartPrecios");
        cartDescripciones = (ArrayList<String>)getIntent().getSerializableExtra("cartDescripciones");

        //Set recycler
        listaVerduras = findViewById(R.id.rv_listaVerduras);
        listaVerduras.setLayoutManager(new LinearLayoutManager(VerdurasActivity.this));

        //Set database
        dbProducto dbProducto = new dbProducto(VerdurasActivity.this);
        listaArrVerduras = dbProducto.obtieneVerduras();

        //Set adapter
        ListaAdapter adapter = new ListaAdapter(listaArrVerduras);

        adapter.setOnClickListener(v -> {
            //Retrieve information from clicked element
            String transferName = listaArrVerduras.get
                    (listaVerduras.getChildAdapterPosition(v)).getNombre();
            String transferDetails = listaArrVerduras.get
                    (listaVerduras.getChildAdapterPosition(v)).getDescripcion();
            int transferPrice = listaArrVerduras.get
                    (listaVerduras.getChildAdapterPosition(v)).getPrecio();
            int transferImage = listaArrVerduras.get
                    (listaVerduras.getChildAdapterPosition(v)).getIddrawable();

            //Send retrieved information
            nav = new Intent(VerdurasActivity.this, DetailsActivity.class);

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

        listaVerduras.setAdapter(adapter);

        //Set Navigation Buttons
        ((Button)findViewById(R.id.btn_VEnvasados)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), EnvasadosActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_VLacteos)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), LacteosActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_VOfertas)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), StartActivity.class);

            //send cart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_VCarrito)).setOnClickListener(v -> {
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