package com.example.marcos.myspotifyplayer.negocio;

/**
 * Created by marcos on 15/07/15.
 */
public class Cancion {
    public String nombre;
    public String album;
    public String fotoAlbum;

    public Cancion(String nombre, String album, String photoUrl){
        this.nombre=nombre;
        this.album=album;
        this.fotoAlbum=photoUrl;
    }
}
