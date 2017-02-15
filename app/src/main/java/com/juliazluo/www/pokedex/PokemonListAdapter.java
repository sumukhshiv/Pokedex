package com.juliazluo.www.pokedex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by julia on 2017-02-14.
 */

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.CustomViewHolder> {
    Context context;
    ArrayList<Pokedex.Pokemon> pokemonList;

    public PokemonListAdapter(Context context, ArrayList<Pokedex.Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }


    @Override
    public PokemonListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonListAdapter.CustomViewHolder holder, int position) {
        Pokedex.Pokemon pokemon = pokemonList.get(position);
        //In the onBindViewHolder, you want to set each of the parameters of ComputerCompanies very similiar
        //to what you did to the layout manager.
        //can change properties based on position (alternate colors, etc)
        holder.textView.setText(pokemon.name);
        Glide.with(context)
                .load("http://assets.pokemon.com/assets/cms2/img/pokedex/full/" + pokemon.number + ".png")
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public CustomViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.list_name); //need view. because not in an activity
            imageView = (ImageView) view.findViewById(R.id.list_image);
        }
    }
}
