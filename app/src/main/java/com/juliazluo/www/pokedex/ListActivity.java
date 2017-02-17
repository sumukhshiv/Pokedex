package com.juliazluo.www.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    protected static PokemonHandler pokemonHandler;
    protected ArrayList<Pokedex.Pokemon> pokemonList;
    protected FilteredListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.filter_recycler);
        pokemonHandler = new PokemonHandler();
        pokemonList = new ArrayList<>();
        ArrayList<Pokedex.Pokemon> copy = pokemonHandler.getPokemonList();
        pokemonList.addAll(copy);
        adapter = new FilteredListAdapter(getApplicationContext(), pokemonList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        boolean randomize = intent.getBooleanExtra("RANDOMIZE", true);

        if (randomize) {
            pokemonList = pokemonHandler.generateRandom();
        } else {
            boolean[] chosenTypes = intent.getBooleanArrayExtra("CHOSEN_TYPES");
            int minAttack = intent.getIntExtra("MIN_ATTACK", 0);
            int minDP = intent.getIntExtra("MIN_DP", 0);
            int minHP = intent.getIntExtra("MIN_HP", 0);
            pokemonList = pokemonHandler.filter(chosenTypes, minAttack, minDP, minHP);
        }

        Log.i("New list size", pokemonList.size() + "");
        adapter.setFilter(pokemonList);
    }
}
