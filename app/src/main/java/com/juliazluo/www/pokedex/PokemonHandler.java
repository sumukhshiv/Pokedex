package com.juliazluo.www.pokedex;

import java.util.ArrayList;

/**
 * Created by julia on 2017-02-14.
 */

public class PokemonHandler {

    private Pokedex pokedex;
    private ArrayList<Pokedex.Pokemon> pokemonLst;
    private ArrayList<String> pokemonTypes;

    public PokemonHandler() {
        pokedex = new Pokedex();
        pokemonLst = pokedex.getPokemon();
        pokemonTypes = populateTypes();
    }

    public ArrayList<String> populateTypes() {
        ArrayList<String> types = new ArrayList<>();
        for (Pokedex.Pokemon pokemon : pokemonLst) {
            if (!types.contains(pokemon.species)) {
                types.add(pokemon.species);
            }
        }
        return types;
    }

    public ArrayList<Pokedex.Pokemon> filter(boolean[] types, int minAttack, int minDP, int minHP) {
        ArrayList<Pokedex.Pokemon> filtered = new ArrayList<>();
        for (Pokedex.Pokemon pokemon : pokemonLst) {
            if (Integer.parseInt(pokemon.attack) >= minAttack && Integer.parseInt(pokemon.defense) >= minDP
                    && Integer.parseInt(pokemon.hp) >= minHP) {
                filtered.add(pokemon);
            }
        }
        return filtered;
    }

    public ArrayList<String> getPokemonTypes() {
        return pokemonTypes;
    }

}
