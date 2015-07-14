package com.example.marcos.myspotifyplayer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by marcos on 14/07/15.
 */
public class BandaPequenoCard extends RecyclerView.ViewHolder{

    TextView nombre;
    ImageView foto;
    public BandaPequenoCard(View view) {
        super(view);
        nombre = (TextView) view.findViewById(R.id.textview_nombre);
        foto = (ImageView) view.findViewById(R.id.image_foto);
    }
}
