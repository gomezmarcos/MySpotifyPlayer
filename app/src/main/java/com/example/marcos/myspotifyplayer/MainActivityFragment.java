package com.example.marcos.myspotifyplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.marcos.Banda;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    BandasAdapter mBandasAdapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vistaRaiz = (View) inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView renglon = (RecyclerView) vistaRaiz.findViewById(R.id.lista_bandas);

        renglon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(getActivity(), CancionesPopularesActivity.class);
                intent.putExtra("bandId", "sasdf");
                startActivityForResult(intent, 23);
                return false;
            }

        });

        EditText nombre = (EditText) vistaRaiz.findViewById(R.id.text_buscar);
        nombre.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_ENTER == keyCode){

                EditText v1 = (EditText) v;
                String artista = v1.getText().toString();
                mockBandas(artista);
                }
                return true;
            }
        });

        mBandasAdapter = new BandasAdapter(getActivity());
        mockBandas("pearl jam");
        RecyclerView listaBandas = (RecyclerView) vistaRaiz.findViewById(R.id.lista_bandas);

        listaBandas.setHasFixedSize(true);
        listaBandas.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        listaBandas.setAdapter(mBandasAdapter);
        mBandasAdapter.notifyDataSetChanged();

        return vistaRaiz;
    }

    private void mockBandas(String artista) {
        artista = artista.trim().replaceAll(" ", "_");
        String spotify_url = String.format("https://api.spotify.com/v1/search?q=%s&type=artist&limit=10", artista);
        final List<Banda> bandas = new ArrayList<>();

        final String finalArtista = artista;
        JsonObjectRequest r = new JsonObjectRequest(
                Request.Method.GET,
                spotify_url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        JSONArray artists = null;
                        try {
                            JSONObject artistJSONObject = jsonObject.getJSONObject("artists");
                            artists = artistJSONObject.getJSONArray("items");
                            for (int i = 0; i < artists.length(); i++){
                                JSONObject artist = artists.getJSONObject(i);
                                JSONArray images = artist.getJSONArray("images");


                                JSONObject o = null;
                                String url = "";
                                if (images.length() > 0){
                                    o = (JSONObject) images.get(0);
                                    url = o.getString("url");
                                }
                                String nombre = artist.getString("name");
                                String oid = artist.getString("id");
                                Banda banda = new Banda();
                                banda.name = nombre;
                                banda.fotoUrl = url;
                                banda.oid = oid;
                                bandas.add(banda);
                            }
                            if (bandas.size()>0)
                                mBandasAdapter.setNewBandas(bandas);
                            else {
                                Toast.makeText(getActivity(),String.format("No hay resultados para %s", finalArtista),Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            if (bandas.size()>0)
                                mBandasAdapter.setNewBandas(bandas);
                            Log.e(LOG_TAG, "error con fucking parse");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e(LOG_TAG, "error con fucking volley");

                    }
                }
        );
        Volley.newRequestQueue(getActivity()).add(r);

    }
}
