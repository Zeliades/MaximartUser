package com.example.p_finalproyecto;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CartActivity extends AppCompatActivity {
    //For Tab navigation
    Intent nav;

    //Scroll
    ListView listView;
    ArrayList<String> shopCart;

    //Cart
    ArrayList<String> cartNombres;
    ArrayList<String> cartPrecios;
    ArrayList<String> cartDescripciones;

    //PDF

    //Page size
    int pageHeight = 1120;
    int pagewidth = 792;

    // bitmap for images
    Bitmap bmp, scaledbmp;

    // constant code for runtime permissions
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //REQUEST PERMISSIONS
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.app_icon);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);

        // below code is used for
        // checking our permissions.
        if (checkPermission()) {
            Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }

        //Link Scroll
        listView = (ListView)findViewById(R.id.listView);

        //Recieve shopcart
        cartNombres = (ArrayList<String>)getIntent().getSerializableExtra("cartNombres");
        cartPrecios = (ArrayList<String>)getIntent().getSerializableExtra("cartPrecios");
        cartDescripciones = (ArrayList<String>)getIntent().getSerializableExtra("cartDescripciones");

        //Start array and info for listview
        String item = "";
        int total = 0;
        shopCart = new ArrayList<>();

        for(int i = 0; i < cartNombres.size(); i++){
            item = cartNombres.get(i)+"\n"+"Bs."+cartPrecios.get(i);
            total += Integer.parseInt(cartPrecios.get(i));
            shopCart.add(item);
        }

        final ArrayAdapter<String> adaptaVector = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shopCart);
        listView.setAdapter(adaptaVector);

        ((TextView)findViewById(R.id.textView4)).setText("TOTAL: Bs. "+total);

        //Set Navigation Buttons
        ((Button)findViewById(R.id.btn_CEnvasados)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), EnvasadosActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_CVerduras)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), VerdurasActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_CLacteos)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), LacteosActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        ((Button)findViewById(R.id.btn_COfertas)).setOnClickListener(v -> {
            nav = new Intent(getApplicationContext(), StartActivity.class);

            //send shopcart
            nav.putExtra("cartNombres",cartNombres);
            nav.putExtra("cartPrecios",cartPrecios);
            nav.putExtra("cartDescripciones",cartDescripciones);

            this.finish();
            startActivity(nav);
        });

        //DELETE ITEMS FROM LIST
        listView.setOnItemClickListener((AdapterViewParent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(AdapterViewParent.getContext());
            builder.setCancelable(true);
            builder.setTitle("Eliminar de Carrito");
            builder.setMessage("¿Desea eliminar " + cartNombres.get(position)+"?");
            builder.setPositiveButton("Confirmar", (dialogInterface, whichInt) -> {
                //Remove from shopcart
                shopCart.remove(position);
                cartNombres.remove(position);
                cartPrecios.remove(position);
                cartDescripciones.remove(position);

                //UPDATE LABELS AND FLAGS
                int totalUpdate = 0;

                for(int i = 0; i < cartNombres.size(); i++){
                    totalUpdate += Integer.parseInt(cartPrecios.get(i));
                }

                ((TextView)findViewById(R.id.textView4)).setText("TOTAL: Bs. "+totalUpdate);

                adaptaVector.notifyDataSetChanged();
            });

            builder.setNegativeButton(android.R.string.cancel, (dialogInterface, whichInt) -> {
                Toast.makeText(this, "ELIMINACION CANCELADA", Toast.LENGTH_SHORT).show();
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        //BUY LIST AND GENERATE PDF
        ((Button)findViewById(R.id.btn_CBuyCart)).setOnClickListener(v -> {
            generatePDF();
        });
    }

    //GENERATE PDF (pls)
    private void generatePDF() {
        PdfDocument pdfDocument = new PdfDocument();

        // Generate images and text.
        Paint paint = new Paint();
        Paint title = new Paint();

        // Create page
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();

        // Start Page
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        // Canvas from our page of PDF.
        Canvas canvas = myPage.getCanvas();

        // Draw image.
        canvas.drawBitmap(scaledbmp, 56, 40, paint);

        // Typeface for the file.
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // Text size
        title.setTextSize(15);

        // Text color
        title.setColor(ContextCompat.getColor(this, R.color.black));

        // Draw bill information
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String fecha = "Fecha: "+formatter.format(date);

        canvas.drawText("MINIMARKET", 209, 100, title);
        canvas.drawText("Tienda Online", 209, 80, title);
        canvas.drawText(fecha, 209, 60, title);


        // File content.
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.black));
        title.setTextSize(15);

        // DRAW TEXT FOR BILL
        int yAxis = 200;
        title.setTextAlign(Paint.Align.CENTER);

        //Print content
        for(String item : shopCart){
            canvas.drawText(item, 396, yAxis, title);
            yAxis+=20;
        }

        //Calculate total value
        int totalPrice = 0;
        for(String price : cartPrecios){
            totalPrice += Integer.parseInt(price);
        }

        canvas.drawText("_____________________________________________________________", 396, yAxis, title);
        canvas.drawText("TOTAL: Bs."+totalPrice, 396, (yAxis+20), title);

        // Finish adding attributes.
        pdfDocument.finishPage(myPage);

        // Filename
        formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        date = new Date();
        String fileName = "Factura "+formatter.format(date)+".pdf";

        //File file = new File(Environment.getExternalStorageDirectory(),fileName);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),fileName);


        //Write pdf
        try {
            pdfDocument.writeTo(new FileOutputStream(file));

            Toast.makeText(CartActivity.this, "Factura generada", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(CartActivity.this, "Error en la creacion de la factura", Toast.LENGTH_SHORT).show();
        }

        // Close pdf.
        pdfDocument.close();
    }

    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // Message for perms
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permiso accedido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}