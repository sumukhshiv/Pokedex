package com.juliazluo.www.pokedex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sumukhshivakumar on 2/16/17.
 */

public class SearchSuggestionsAdapter extends ArrayAdapter<Pokedex.Pokemon> {
    private LayoutInflater layoutInflater;
    List<Pokedex.Pokemon> pokemonList;
    Context context;

    private Filter mFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            return ((Pokedex.Pokemon)resultValue).name;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null) {
                ArrayList<Pokedex.Pokemon> suggestions = new ArrayList<>();
                try {
                    int num = Integer.parseInt(constraint.toString());
                    // do filtering for int
                    for (Pokedex.Pokemon pokemon : pokemonList) {
                        // Note: change the "contains" to "startsWith" if you only want starting matches
                        if (pokemon.number.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            suggestions.add(pokemon);
                        }
                    }
                } catch (NumberFormatException e) {
                    // the constraint was not an int, search by name
                    for (Pokedex.Pokemon pokemon : pokemonList) {
                        // Note: change the "contains" to "startsWith" if you only want starting matches
                        if (pokemon.name.toLowerCase().startsWith(constraint.toString().toLowerCase())) {

                            suggestions.add(pokemon);
                        }
                    }
                }
                Log.d("DEBUG", "constraint is " + constraint);

                Log.d("DEBUG", "suggestions size is " + suggestions.size());
                results.values = suggestions;
                results.count = suggestions.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            if (results != null && results.count > 0) {
                // we have filtered results
                addAll((ArrayList<Pokedex.Pokemon>) results.values);
            } else {
                // no filter, add entire original list back in
                //addAll();
            }
            notifyDataSetChanged();
        }

    };

    public SearchSuggestionsAdapter(Context context, int textViewResourceId, List<Pokedex.Pokemon> pokemon) {
        super(context, textViewResourceId, pokemon);
        this.context = context;
        // copy all the customers into a master list
        pokemonList = new ArrayList<>(pokemon.size());
        pokemonList.addAll(pokemon);
        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.search_list_item, null);
        }

        final Pokedex.Pokemon pokemon = getItem(position);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("POKEMON", pokemon);
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        TextView name = (TextView) view.findViewById(R.id.search_list_name);
        TextView hp = (TextView) view.findViewById(R.id.search_list_hp); // change this to number
        name.setText(pokemon.name);
        hp.setText(pokemon.number);


        return view;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }
}
