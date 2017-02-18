package com.juliazluo.www.pokedex;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Random;

import static android.R.attr.bitmap;
import static android.R.attr.label;
import static android.R.attr.numbersBackgroundColor;

public class ProfileActivity extends AppCompatActivity {

    private Pokedex.Pokemon pokemon;
    private TextView name, number, hp, dp, attack, species, types;
    private ImageView image;
    private Button buttonSearchTheWeb;
    private Bitmap bitmap;

    BarChart barChart;


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

        //PALETTE
        /*
        bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                int darkVibrant = p.getDarkVibrantColor(Integer.parseInt("c1292e", 16));
                findViewById(R.id.activity_profile).setBackgroundColor(Color.parseColor(Integer.toHexString(darkVibrant).substring(1)));

            }
        });
        */





        buttonSearchTheWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = pokemon.name;
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH );
                intent.putExtra(SearchManager.QUERY, q);
                startActivity(intent);
            }
        });

        //barchart stuff here
        /*
        barChart = (BarChart) findViewById(R.id.bargraph);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(Float.parseFloat("" + number.getText()), 0));
        barEntries.add(new BarEntry(Float.parseFloat(("" + attack.getText()).substring(4)), 1));
        barEntries.add(new BarEntry(Float.parseFloat(("" + dp.getText()).substring(4)), 2));

        BarDataSet dataSet = new BarDataSet(barEntries, "Data");

        ArrayList<BarEntry> labels = new ArrayList<>();
        labels.add(new BarEntry());
        labels.add("DP");
        labels.add("AP");

        BarChart chart = new BarChart(this);
        setContentView(chart);

        BarData data = new BarData(dataSet);

        chart.setData(data);
        barChart.setData(data);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.setPinchZoom(true);
        barChart.setDoubleTapToZoomEnabled(true);
        */
    }
}
