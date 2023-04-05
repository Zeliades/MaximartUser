package com.example.p_finalproyecto.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p_finalproyecto.R;
import com.example.p_finalproyecto.entidades.Producto;

import java.util.ArrayList;

public class ListaAdapter
        extends RecyclerView.Adapter<ListaAdapter.ProductoViewHolder>
        implements View.OnClickListener {
    ArrayList<Producto> listaProductosDb;

    //For onclick event
    private View.OnClickListener listener;

    public ListaAdapter(ArrayList<Producto> listaProductos){
        listaProductosDb = listaProductos;
    }

    //Design for every list item
    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_producto, null, false);

        view.setOnClickListener(this);

        return new ProductoViewHolder(view);
    }

    //Asign elements for each option
    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        String nombre = listaProductosDb.get(position).getNombre();
        String descripcion = listaProductosDb.get(position).getDescripcion();
        String precio = String.valueOf(listaProductosDb.get(position).getPrecio());

        int imagen = listaProductosDb.get(position).getIddrawable();

        holder.producto.setText(nombre);
        holder.descripcion.setText(descripcion);
        holder.precio.setText(precio);

        holder.imagen.setImageResource(imagen);
    }

    @Override
    public int getItemCount() {
        return listaProductosDb.size();
    }


    //Methods for onclick on list elements

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    //CLASS FOR MANAGING VIEWS
    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView producto, descripcion, precio;
        ImageView imagen;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);

            producto = itemView.findViewById(R.id.tvw_producto);
            descripcion = itemView.findViewById(R.id.tvw_descripcion);
            precio = itemView.findViewById(R.id.tvw_precio);

            imagen = itemView.findViewById(R.id.img_imagen);

        }

        //Getters
        public String getProducto(){
            return producto.getText().toString();
        }
    }
}
