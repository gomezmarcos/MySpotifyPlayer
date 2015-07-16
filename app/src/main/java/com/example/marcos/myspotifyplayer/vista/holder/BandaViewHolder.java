package com.example.marcos.myspotifyplayer.vista.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcos.myspotifyplayer.R;

/**
 * Created by marcos on 14/07/15.
 */
public class BandaViewHolder extends RecyclerView.ViewHolder{

    public TextView nombre;
    public ImageView foto;
    public BandaViewHolder(View view) {
        super(view);
        nombre = (TextView) view.findViewById(R.id.textview_nombre);
        foto = (ImageView) view.findViewById(R.id.image_foto);
    }
}
