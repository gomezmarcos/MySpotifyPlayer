package com.example.marcos.myspotifyplayer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marcos.Banda;
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
        this.bandas = bandas == null ? new ArrayList<Banda>() : bandas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder resulViewHolder;
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_banda, viewGroup, false);

        resulViewHolder = new BandaPequenoCard(vista);

        return resulViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        Banda banda = bandas.get(i);
        BandaPequenoCard card = (BandaPequenoCard) viewHolder;
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
