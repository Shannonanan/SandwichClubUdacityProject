package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView tv_description;
    TextView tv_ingredients;
    TextView tv_origin;
    TextView tv_known_as;
    ScrollView scrollDescription;
    ScrollView scrollIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        scrollDescription = findViewById(R.id.scrollDescription);
        tv_description = findViewById(R.id.description_tv);
        tv_origin = findViewById(R.id.origin_tv);
        tv_ingredients = findViewById(R.id.ingredients_tv);
        tv_known_as = findViewById(R.id.also_known_tv);
        scrollIngredients = findViewById(R.id.scrollIngredients);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        tv_description.setText(sandwich.getDescription());
        tv_origin.setText(sandwich.getPlaceOfOrigin());

        if(!sandwich.getAlsoKnownAs().isEmpty()){
        String joinedKnownAs = TextUtils.join(", ", sandwich.getAlsoKnownAs());
        tv_known_as.setText(joinedKnownAs);}
        if(!sandwich.getIngredients().isEmpty()){
        String joinedIngredients = TextUtils.join(", ", sandwich.getIngredients());
        tv_ingredients.setText(joinedIngredients);};


    }
}
