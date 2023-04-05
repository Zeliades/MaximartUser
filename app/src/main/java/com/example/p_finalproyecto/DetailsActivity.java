package com.example.p_finalproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    //For Tab navigation
    Intent nav;

    //Modify quantity
    Button buttonAddOne, buttonRemoveOne;

    //Cart
    ArrayList<String> cartNombres;
    ArrayList<String> cartPrecios;
    ArrayList<String> cartDescripciones;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Set buttons
        quantity = 1;
        buttonAddOne = (Button)findViewById(R.id.buttonAddOne);
        buttonRemoveOne = (Button)findViewById(R.id.buttonRemoveOne);

        //Collect transferred info and locate it
        String transferName = getIntent().getStringExtra("transferName");
        String transferDetails = getIntent().getStringExtra("transferDetails");
        int transferPrice = getIntent().getIntExtra("transferPrice",0);
        int transferImage = getIntent().getIntExtra("transferImage", 0);

        ((ImageView)findViewById(R.id.imageView14)).setImageResource(transferImage);
        ((TextView)findViewById(R.id.textViewDetailName)).setText(transferName);
        ((TextView)findViewById(R.id.textViewDetails)).setText(transferDetails);
        ((TextView)findViewById(R.id.textViewDetailPrice)).setText("Bs. "+transferPrice);

        //Recieve shopcart
        cartNombres = (ArrayList<String>)getIntent().getSerializableExtra("cartNombres");
        cartPrecios = (ArrayList<String>)getIntent().getSerializableExtra("cartPrecios");
        cartDescripciones = (ArrayList<String>)getIntent().getSerializableExtra("cartDescripciones");

        //BUTTONS

        //Add one
        buttonAddOne.setOnClickListener(v -> {
            quantity++;
            ((TextView)findViewById(R.id.textViewBundleItems)).setText("Cantidad Articulos: "+quantity);
            ((TextView)findViewById(R.id.textViewDetailPrice)).setText("Bs. "+(transferPrice * quantity));

        });

        //Remove one
        buttonRemoveOne.setOnClickListener(v -> {
            if(quantity > 1){
                quantity--;
                ((TextView)findViewById(R.id.textViewBundleItems)).setText("Cantidad Articulos: "+quantity);
                ((TextView)findViewById(R.id.textViewDetailPrice)).setText("Bs. "+(transferPrice * quantity));
            }
        });

        //Buy Button
        ((Button)findViewById(R.id.buttonDetailsBuy)).setOnClickListener(v -> {
            //add article to shopcart
            cartNombres.add(transferName+" x "+quantity);
            cartPrecios.add(""+(transferPrice*quantity));
            cartDescripciones.add(transferDetails);

            nav = new Intent(getApplicationContext(), CartActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        //Set Navigation Buttons
        ((Button)findViewById(R.id.btn_DEnvasados)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), EnvasadosActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_DVerduras)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), VerdurasActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_DLacteos)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), LacteosActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_DOfertas)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), StartActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_DCarrito)).setOnClickListener(v -> {
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