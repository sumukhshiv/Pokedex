package com.juliazluo.www.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.pokemon_list);
        Pokedex pokedex = new Pokedex();
        ArrayList<Pokedex.Pokemon> pokemon = pokedex.getPokemon();
        recyclerView.setAdapter(new PokemonListAdapter(getApplicationContext(), pokemon));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
