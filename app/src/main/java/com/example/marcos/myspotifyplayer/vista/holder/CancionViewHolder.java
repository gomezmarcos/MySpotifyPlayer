package com.example.marcos.myspotifyplayer.vista.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcos.myspotifyplayer.R;

/**
 * Created by marcos on 15/07/15.
 */
public class CancionViewHolder extends RecyclerView.ViewHolder {
    public TextView cancion, album;
    public ImageView albumFoto;
    public CancionViewHolder(View itemView) {
        super(itemView);
        cancion = (TextView) itemView.findViewById(R.id.text_cancion);
        album= (TextView) itemView.findViewById(R.id.text_album);
        albumFoto = (ImageView) itemView.findViewById(R.id.image_albumFoto);
    }
}
