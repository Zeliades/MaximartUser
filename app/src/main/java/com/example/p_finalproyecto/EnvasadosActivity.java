package com.example.p_finalproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p_finalproyecto.adaptadores.ListaAdapter;
import com.example.p_finalproyecto.db.dbProducto;
import com.example.p_finalproyecto.entidades.Producto;

import java.util.ArrayList;

public class EnvasadosActivity extends AppCompatActivity {
    //For Tab navigation
    Intent nav;

    //Show Products
    RecyclerView listaEnvasados;

    //Retrieved info
    ArrayList<Producto> listaArrEnvasados;

    //Cart
    ArrayList<String> cartNombres;
    ArrayList<String> cartPrecios;
    ArrayList<String> cartDescripciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envasados);

        //Recieve cart
        cartNombres = (ArrayList<String>)getIntent().getSerializableExtra("cartNombres");
        cartPrecios = (ArrayList<String>)getIntent().getSerializableExtra("cartPrecios");
        cartDescripciones = (ArrayList<String>)getIntent().getSerializableExtra("cartDescripciones");

        //Set recycler
        listaEnvasados = findViewById(R.id.rv_listaEnvasados);
        listaEnvasados.setLayoutManager(new LinearLayoutManager(EnvasadosActivity.this));

        //Set database
        dbProducto dbProducto = new dbProducto(EnvasadosActivity.this);
        listaArrEnvasados = dbProducto.obtieneEnvasados();

        //Set adapter
        ListaAdapter adapter = new ListaAdapter(listaArrEnvasados);

        adapter.setOnClickListener(v -> {
            //Retrieve information from clicked element
            String transferName = listaArrEnvasados.get
                    (listaEnvasados.getChildAdapterPosition(v)).getNombre();
            String transferDetails = listaArrEnvasados.get
                    (listaEnvasados.getChildAdapterPosition(v)).getDescripcion();
            int transferPrice = listaArrEnvasados.get
                    (listaEnvasados.getChildAdapterPosition(v)).getPrecio();
            int transferImage = listaArrEnvasados.get
                    (listaEnvasados.getChildAdapterPosition(v)).getIddrawable();

            //Send retrieved information
            nav = new Intent(EnvasadosActivity.this, DetailsActivity.class);

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

        listaEnvasados.setAdapter(adapter);

        //Set Navigation Buttons
        ((Button)findViewById(R.id.btn_EVerduras)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), VerdurasActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_ELacteos)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), LacteosActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_EOfertas)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), StartActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_ECarrito)).setOnClickListener(v -> {
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