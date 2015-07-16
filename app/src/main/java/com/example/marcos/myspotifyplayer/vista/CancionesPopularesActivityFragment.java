package com.example.marcos.myspotifyplayer.vista;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.marcos.myspotifyplayer.R;
import com.example.marcos.myspotifyplayer.negocio.Cancion;
import com.example.marcos.myspotifyplayer.vista.adapter.CancionesAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class CancionesPopularesActivityFragment extends Fragment {
    private static final String LOG_TAG = CancionesPopularesActivityFragment.class.getSimpleName();
    CancionesAdapter cancionesAdapter;
    //ProgressDialog progressDialog;
    ProgressBar progressBar;
    RecyclerView canciones;

    public CancionesPopularesActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String artista = getActivity().getIntent().getStringExtra("artista");
        View rootView = inflater.inflate(R.layout.fragment_canciones_populares, container, false);
        TextView nombreBanda = (TextView) rootView.findViewById(R.id.textview_nombre);
        nombreBanda.setText(getActivity().getIntent().getStringExtra("name"));
        ImageView fotoBanda = (ImageView) rootView.findViewById(R.id.image_foto);
        String photoUrl = getActivity().getIntent().getStringExtra("photoUrl");
        Picasso.with(getActivity()).load(photoUrl).into(fotoBanda);

        canciones = (RecyclerView) rootView.findViewById(R.id.recyclerview_canciones);
        canciones.setHasFixedSize(true);
        canciones.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        /*
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("aguanta flaco..");
        progressDialog.setMessage("que aguantes! message");
        progressDialog.show();
         */


        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        cancionesAdapter = new CancionesAdapter(getActivity());
        canciones.setAdapter(cancionesAdapter);
        buscarCancionesPorArtista(artista);

        /*
        Cancion c = cancionesAdapter.canciones.get(0);
        nombreBanda.setText(c.nombre);
        */
        //TODO falta la foto de la banda aca tambien

        //Volley.newRequestQueue(getActivity()).add(new )
        return rootView;
    }

    private void buscarCancionesPorArtista(String artista) {
        final String url = String.format("https://api.spotify.com/v1/artists/%s/top-tracks?country=SE&market=US", artista);
        final List<Cancion> cancionesSinImagen = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(
                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                String albumName = "";
                                String albumFoto = "";
                                String song = "";
                                try {
                                    JSONArray canciones = jsonObject.getJSONArray("tracks");
                                    for (int i = 0; i < canciones.length(); i++) {
                                        JSONObject cancion = canciones.getJSONObject(i);
                                        song = cancion.getString("name");
                                        JSONObject album = cancion.getJSONObject("album");
                                        albumName = album.getString("name");
                                        JSONObject images = (JSONObject) album.getJSONArray("images").get(1);
                                        albumFoto = images.getString("url");

                                        cancionesSinImagen.add(new Cancion(song, albumName, albumFoto));
                                    }
                                    cancionesAdapter.agregarCanciones(cancionesSinImagen);
                                } catch (JSONException e) {
                                    Log.e(LOG_TAG, "Error en el parser del servicio", e);
                                } finally {
                                    //progressDialog.dismiss();
                                    canciones.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.GONE);
                                }


                            }
                        },
                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.e(LOG_TAG, "Error en el servicio", volleyError);

                            }
                        });
        Volley.newRequestQueue(getActivity()).add(request);

    }
}
