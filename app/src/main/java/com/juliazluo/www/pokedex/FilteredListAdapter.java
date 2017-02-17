package com.juliazluo.www.pokedex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class FilteredListAdapter extends RecyclerView.Adapter<FilteredListAdapter.CustomViewHolder> {
    private Context context;
    private ArrayList<Pokedex.Pokemon> pokemonList;

    public FilteredListAdapter(Context context, ArrayList<Pokedex.Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }


    @Override
    public FilteredListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilteredListAdapter.CustomViewHolder holder, int position) {
        final Pokedex.Pokemon pokemon = pokemonList.get(position);
        //In the onBindViewHolder, you want to set each of the parameters of ComputerCompanies very similiar
        //to what you did to the layout manager.
        //can change properties based on position (alternate colors, etc)
        holder.nameText.setText(pokemon.name);
        holder.hpText.setText("HP: " + pokemon.hp);
        Glide.with(context)
                .load("http://assets.pokemon.com/assets/cms2/img/pokedex/full/" + pokemon.number + ".png")
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("POKEMON", pokemon);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    public void setFilter(ArrayList<Pokedex.Pokemon> newList) {
        pokemonList.clear();
        pokemonList.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView nameText, hpText;
        ImageView imageView;

        public CustomViewHolder(View view) {
            super(view);
            nameText = (TextView) view.findViewById(R.id.filter_list_name); //need view. because not in an activity
            imageView = (ImageView) view.findViewById(R.id.filter_list_image);
            hpText = (TextView) view.findViewById(R.id.filter_list_hp);
        }
    }
}