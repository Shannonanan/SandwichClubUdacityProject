package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static Sandwich sandwich = new Sandwich();


    public static Sandwich parseSandwichJson(String json) {

          List<String> ingredientsList = new ArrayList<>();
          List<String> alsoKnownAsList = new ArrayList<>();
        try {
            JSONObject jsonString = new JSONObject(json);
            JSONObject jsonString2 = jsonString.getJSONObject("name");
            sandwich.setImage(jsonString.getString("image"));
            sandwich.setDescription(jsonString.getString("description"));

            if(!jsonString2.getString("mainName").isEmpty()){
            sandwich.setMainName(jsonString2.getString("mainName"));}

            if(!jsonString.getString("placeOfOrigin").isEmpty()){
            sandwich.setPlaceOfOrigin(jsonString.getString("placeOfOrigin"));}



            JSONArray getAllIngredients = jsonString.getJSONArray("ingredients");
            JSONArray getAlsoKnownAs = jsonString2.getJSONArray("alsoKnownAs");


            for (int i = 0; i < getAllIngredients.length(); i++) {
                ingredientsList.add(getAllIngredients.getString(i));
            }

            for (int i = 0; i < getAlsoKnownAs.length(); i++) {
                alsoKnownAsList.add(getAlsoKnownAs.getString(i));
            }

            sandwich.setIngredients(ingredientsList);
            sandwich.setAlsoKnownAs(alsoKnownAsList);
        } catch (JSONException ex) {
            String temp = ex.getMessage();
            return null;
        }

        if(sandwich == null)
        {
            return null;
        }
       else { return sandwich;}
    }
}
