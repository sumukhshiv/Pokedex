package com.juliazluo.www.pokedex;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by julia on 2017-02-14.
 */

public class PokemonHandler {

    private Pokedex pokedex;
    private ArrayList<Pokedex.Pokemon> pokemonList;
    private ArrayList<String> pokemonTypes;

    public PokemonHandler() {
        pokedex = new Pokedex();
        pokemonList = pokedex.getPokemon();
        pokemonTypes = populateTypes();
    }

    public ArrayList<String> populateTypes() {
        ArrayList<String> types = new ArrayList<>();
        for (Pokedex.Pokemon pokemon : pokemonList) {
            if (!types.contains(pokemon.species)) {
                types.add(pokemon.species);
            }
        }
        return types;
    }

    public ArrayList<Pokedex.Pokemon> filter(boolean[] types, int minAttack, int minDP, int minHP) {
        ArrayList<Pokedex.Pokemon> filtered = new ArrayList<>();
        for (Pokedex.Pokemon pokemon : pokemonList) {
            if (Integer.parseInt(pokemon.attack) >= minAttack && Integer.parseInt(pokemon.defense) >= minDP
                    && Integer.parseInt(pokemon.hp) >= minHP) {
                filtered.add(pokemon);
            }
        }
        return filtered;
    }

    public ArrayList<Pokedex.Pokemon> generateRandom() {
        int numPokemon = pokemonList.size();
        ArrayList<Pokedex.Pokemon> random = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int randNum = (int) (Math.random() * numPokemon);
            while (random.contains(pokemonList.get(randNum))) {
                randNum = (int) (Math.random() * numPokemon);
            }
            random.add(pokemonList.get(randNum));
        }
        return random;
    }

    public ArrayList<String> getPokemonTypes() {
        return pokemonTypes;
    }

    public ArrayList<Pokedex.Pokemon> getPokemonList() {
        return pokemonList;
    }

}
