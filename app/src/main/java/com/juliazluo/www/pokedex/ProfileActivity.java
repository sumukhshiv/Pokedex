package com.juliazluo.www.pokedex;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {

    private Pokedex.Pokemon pokemon;
    private TextView name, number, hp, dp, attack, species, types;
    private ImageView image;
    private Button buttonSearchTheWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = (TextView) findViewById(R.id.profile_name);
        number = (TextView) findViewById(R.id.profile_number);
        hp = (TextView) findViewById(R.id.profile_hp);
        dp = (TextView) findViewById(R.id.profile_dp);
        attack = (TextView) findViewById(R.id.profile_attack);
        species = (TextView) findViewById(R.id.profile_species);
        types = (TextView) findViewById(R.id.profile_types);
        image = (ImageView) findViewById(R.id.profile_image);
        buttonSearchTheWeb = (Button) findViewById(R.id.buttonSearchTheWeb);

        Log.i("started", "this activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        pokemon = (Pokedex.Pokemon) bundle.getSerializable("POKEMON");
        name.setText(pokemon.name);
        number.setText(pokemon.number);
        hp.setText("HP: " + pokemon.hp);
        dp.setText("DP: " + pokemon.defense);
        attack.setText("AP: " + pokemon.attack);
        species.setText("Species: " + pokemon.species);
        String typeNames = "Types: ";
        for (int i = 0; i < pokemon.types.length; i++) {
            typeNames += pokemon.types[i];
            if (i != pokemon.types.length - 1) {
                typeNames += ", ";
            }
        }
        types.setText(typeNames);
        Glide.with(this)
                .load("http://assets.pokemon.com/assets/cms2/img/pokedex/full/" + pokemon.number + ".png")
                .into(image);

        buttonSearchTheWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = pokemon.name;
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH );
                intent.putExtra(SearchManager.QUERY, q);
                startActivity(intent);
            }
        });
    }
}
