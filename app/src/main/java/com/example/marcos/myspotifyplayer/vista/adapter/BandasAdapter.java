package com.example.marcos.myspotifyplayer.vista.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marcos.myspotifyplayer.CancionesPopularesActivity;
import com.example.marcos.myspotifyplayer.R;
import com.example.marcos.myspotifyplayer.negocio.Banda;
import com.example.marcos.myspotifyplayer.vista.MainActivityFragment;
import com.example.marcos.myspotifyplayer.vista.holder.BandaViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos on 14/07/15.
 */
public class BandasAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final String LOG_TAG = BandasAdapter.class.getSimpleName();
    private List<Banda> bandas;
    private Context context;

    private BandasAdapter(){}

    public BandasAdapter(Context context){
        this.bandas = new ArrayList<Banda>();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder resulViewHolder;
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_banda, viewGroup, false);
        vista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = MainActivityFragment.listaBandas.getChildLayoutPosition(view);
                Banda banda = bandas.get(position);
                Intent songsIntent = new Intent(context, CancionesPopularesActivity.class);
                songsIntent.putExtra("artista", banda.oid);
                songsIntent.putExtra("photoUrl", banda.fotoUrl);
                songsIntent.putExtra("name",banda.name);
                context.startActivity(songsIntent);
            }
        });

        resulViewHolder = new BandaViewHolder(vista);


        return resulViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        Banda banda = bandas.get(i);
        BandaViewHolder card = (BandaViewHolder) viewHolder;
        card.nombre.setText(banda.name);
        if (!banda.fotoUrl.isEmpty())
            Picasso.with(context).load(banda.fotoUrl).into(card.foto);
    }

    @Override
    public int getItemCount() {
        return bandas.size();
    }

    public void setNewBandas(List<Banda> bandas) {
        this.bandas = bandas;
        notifyDataSetChanged();
    }
}
