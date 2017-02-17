package com.juliazluo.www.pokedex;

import android.content.Context;
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
                for (Pokedex.Pokemon pokemon : pokemonList) {
                    // Note: change the "contains" to "startsWith" if you only want starting matches
                    if (pokemon.name.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        Log.d("DEBUG", "constraint is " + constraint);
                        suggestions.add(pokemon);
                    }
                }

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
                addAll(pokemonList);
            }
            notifyDataSetChanged();
        }

    };

    public SearchSuggestionsAdapter(Context context, int textViewResourceId, List<Pokedex.Pokemon> pokemon) {
        super(context, textViewResourceId, pokemon);
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

        Pokedex.Pokemon pokemon = getItem(position);

        TextView name = (TextView) view.findViewById(R.id.search_list_name);
        name.setText(pokemon.name);

        return view;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }
}
