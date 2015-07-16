package com.example.marcos.myspotifyplayer.vista.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marcos.myspotifyplayer.R;
import com.example.marcos.myspotifyplayer.negocio.Cancion;
import com.example.marcos.myspotifyplayer.vista.holder.CancionViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos on 15/07/15.
 */
public class CancionesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public  List<Cancion> canciones = new ArrayList<>();
    Context context = null;
    public CancionesAdapter(Context context){
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_cancion,parent,false);
        CancionViewHolder cancionViewHolder = new CancionViewHolder(view);
        return cancionViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Cancion cancion = canciones.get(position);
        CancionViewHolder cancionViewHolder = (CancionViewHolder) holder;
        cancionViewHolder.cancion.setText(cancion.nombre);
        cancionViewHolder.album.setText(cancion.album);
        Picasso.with(context).load(cancion.fotoAlbum).into(cancionViewHolder.albumFoto);
        //TODO margomez falta agregar la imagen del album


    }

    public void agregarCanciones(List<Cancion> canciones){
        this.canciones.clear();
        this.canciones.addAll(canciones);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return canciones.size();
    }
}
