package com.example.a11600624.myapplication.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.a11600624.myapplication.Models.Background;
import com.example.a11600624.myapplication.Models.Character;
import com.example.a11600624.myapplication.Network.NetworkUtils;
import com.example.a11600624.myapplication.Pages.MainActivity;
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
    private DatabaseHelper databaseHelper;
    private List<Character> characters;
    private List<Background> backgrounds;
    private int counterTasks = 0;

    public DatabaseSeeder(Context context) {
        databaseHelper = new DatabaseHelper(context);

        if(databaseHelper.getAllBackgrounds().getCount() == 0) {
            backgrounds = new ArrayList<>();
            backgrounds.add(new Background(context.getResources().getString(R.string.avengers_title), context.getResources().getString(R.string.avengers_description), R.drawable.avengers_background));
            backgrounds.add(new Background(context.getResources().getString(R.string.captain_america_shield_title), context.getResources().getString(R.string.captain_america_shield_description), R.drawable.captain_america_background));
            backgrounds.add(new Background(context.getResources().getString(R.string.iron_man_suit_title), context.getResources().getString(R.string.iron_man_suit_description), R.drawable.iron_man_background));
            backgrounds.add(new Background(context.getResources().getString(R.string.shield_title), context.getResources().getString(R.string.shield_description), R.drawable.shield_background));

            for(Background b : backgrounds) {
                databaseHelper.addBackground(b);
            }
        }

        if(databaseHelper.getAllCharacters().getCount() == 0) {
            MainActivity.nextButton.setEnabled(false);
            MainActivity.nextButton.setText("Loading characters...");

            characters = new ArrayList<>();
            characters.add(new Character("Thanos",R.drawable.thanos_character));
            characters.add(new Character("Thor",R.drawable.thor_character));
            characters.add(new Character("Hulk",R.drawable.hulk_character));
            characters.add(new Character("Iron Man",R.drawable.ironman_character));
            characters.add(new Character("Captain America", R.drawable.captainamerica_character));

            try {
                for(Character c : characters) {
                    URL characterApiUrl = new URL(context.getResources().getString(R.string.marvel_api_url) + "&name=" + c.getName().replace(' ', '+'));
                    new getCharacterFromApiTask().execute(characterApiUrl);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public class getCharacterFromApiTask extends AsyncTask<URL, Void, String> {
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
                    JSONObject thumbnail = resultsJson.getJSONObject("thumbnail");

                    String name = resultsJson.getString("name");

                    for (Character c : characters) {
                        if(name.equals(c.getName())) {
                            c.setDescription(resultsJson.getString("description"));
                            c.setThumbnailSource(thumbnail.getString("path") + "/portrait_xlarge." + thumbnail.getString("extension"));

                            databaseHelper.addCharacter(c);
                        }
                    }

                    counterTasks++;

                    if(counterTasks == characters.size()) {
                        Done();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        private void Done() {
            MainActivity.nextButton.setText("PLAY");
            MainActivity.nextButton.setEnabled(true);
        }
    }
}
