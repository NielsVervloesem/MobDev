package com.example.a11600624.myapplication.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.a11600624.myapplication.Models.Character;
import com.example.a11600624.myapplication.Network.NetworkUtils;
import com.example.a11600624.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSeeder {
    private Context context;
    private DatabaseHelper databaseHelper;
    private List<Character> characters;
    private int currentIndex;

    public DatabaseSeeder(Context context) {
        this.context = context;

        databaseHelper = new DatabaseHelper(context);
        databaseHelper.dropCharacterTable();

        characters = new ArrayList<>();
        characters.add(new Character("Thanos",R.drawable.thanos_character));
        characters.add(new Character("Thor",R.drawable.thor_character));
        characters.add(new Character("Hulk",R.drawable.hulk_character));

        try {
            for(Character c : characters) {
                URL characterApiUrl = new URL(context.getResources().getString(R.string.marvel_api_url) + "&name=" + c.getName());
                new characterApiTask().execute(characterApiUrl);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public class characterApiTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String descriptionResults = null;
            try {
                descriptionResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return descriptionResults;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s != null && !s.equals("")) {
                try {
                    JSONObject json = new JSONObject(s);
                    JSONObject data = json.getJSONObject("data");
                    JSONArray resultsArray = data.getJSONArray("results");
                    JSONObject resultsJson = resultsArray.getJSONObject(0);

                    String name = resultsJson.getString("name");
                    String description = resultsJson.getString("description");

                    JSONObject thumbnail = resultsJson.getJSONObject("thumbnail");

                    String thumbnailurl = thumbnail.getString("path") + "/portrait_xlarge." + thumbnail.getString("extension");

                    databaseHelper.addCharacter(name,description,thumbnailurl);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
