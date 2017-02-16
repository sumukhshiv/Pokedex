package com.juliazluo.www.pokedex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by julia on 2017-02-14.
 */

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.CustomViewHolder> {
    Context context;
    ArrayList<Pokedex.Pokemon> pokemonList;

    public SearchListAdapter(Context context, ArrayList<Pokedex.Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }


    @Override
    public SearchListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchListAdapter.CustomViewHolder holder, int position) {
        Pokedex.Pokemon pokemon = pokemonList.get(position);
        //In the onBindViewHolder, you want to set each of the parameters of ComputerCompanies very similiar
        //to what you did to the layout manager.
        //can change properties based on position (alternate colors, etc)
        holder.nameText.setText(pokemon.name);
        holder.hpText.setText("HP: " + pokemon.hp);
        Glide.with(context)
                .load("http://assets.pokemon.com/assets/cms2/img/pokedex/full/" + pokemon.number + ".png")
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public void setFilter(ArrayList<Pokedex.Pokemon> newList) {
        pokemonList.clear();
        pokemonList.addAll(newList);
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView nameText, hpText;
        ImageView imageView;

        public CustomViewHolder(View view) {
            super(view);
            nameText = (TextView) view.findViewById(R.id.search_list_name); //need view. because not in an activity
            imageView = (ImageView) view.findViewById(R.id.search_list_image);
            hpText = (TextView) view.findViewById(R.id.search_list_hp);
        }
    }
}
