package org.guildsa.jsonparsing;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parseJsonTest1();
        parseJsonTest2();
        parseJsonTest3();
    }

    public String readFileFromRes(@RawRes int id) {

        StringBuilder builder = new StringBuilder();

        try {

            InputStream stream = getResources().openRawResource(id);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));

            String line = "";

            while((line = buffer.readLine()) != null) {
                builder.append(line);
            }

        } catch(IOException e1) {

            // Something went wrong while attempting to read the file's data.
            // In a real app, you would need to take action here and handle it!
            e1.printStackTrace();
        }

        return builder.toString();
    }

    public void parseJsonTest1() {

        // Example 1:
        //
        // Located in: JsonParsing/app/src/main/res/raw/json1.txt
        //
        // [ "Cloe", "Bob", "Jennifer", "Robert" ]

        try {

            // Get our JSON data our of a file in the res/raw folder and into a String.
            // In a real app, your JSON data would probably come from a web service or URL.
            String jsonStr = readFileFromRes(R.raw.json1);

            JSONArray rootJsonArray = new JSONArray(jsonStr);

            for(int i = 0; i < rootJsonArray.length(); ++i) {

                String playerName = rootJsonArray.getString(i);
                Log.i(TAG, "playerName = " + String.valueOf(playerName));
            }

        } catch(JSONException e) {

            // Something went wrong while attempting to parse the JSON.
            // In a real app, you would need to take action here and handle it!
            e.printStackTrace();
        }
    }

    public void parseJsonTest2() {

        // Example 2:
        //
        // Located in: JsonParsing/app/src/main/res/raw/json2.txt
        //
        // {
        //     "name": "The WarL0rd",
        //     "maps": [ 12, 23, 55 ]
        // }

        try {

            // Get our JSON data our of a file in the res/raw folder and into a String.
            // In a real app, your JSON data would probably come from a web service or URL.
            String jsonStr = readFileFromRes(R.raw.json2);

            JSONObject rootJsonObject = new JSONObject(jsonStr);

            String name = rootJsonObject.getString("name");

            Log.i(TAG, "name = " + name);

            JSONArray maps = rootJsonObject.getJSONArray("maps");

            for(int i = 0; i < maps.length(); ++i) {

                int mapId = maps.getInt(i);
                Log.i(TAG, "map = " + String.valueOf(mapId));
            }

        } catch(JSONException e) {

            // Something went wrong while attempting to parse the JSON.
            // In a real app, you would need to take action here and handle it!
            e.printStackTrace();
        }
    }

    public void parseJsonTest3() {

        // Example 3:
        //
        // Located in: JsonParsing/app/src/main/res/raw/json3.txt
        //
        // {
        //     "weapons": {
        //         "swords": [
        //             {
        //                 "name": "Short Sword",
        //                 "damage": 25
        //             },
        //             {
        //                 "name": "Broad Sword",
        //                 "damage": 100
        //             },
        //             {
        //                 "name": "Skull Cleaver",
        //                 "damage": 150
        //             }
        //         ],
        //         "spears": [
        //             {
        //                 "name": "Wooden Spear",
        //                 "damage": 15
        //             },
        //             {
        //                 "name": "Iron Spear",
        //                 "damage": 20
        //             }
        //         ]
        //     }
        // }

        try {

            // Get our JSON data our of a file in the res/raw folder and into a String.
            // In a real app, your JSON data would probably come from a web service or URL.
            String jsonStr = readFileFromRes(R.raw.json3);

            JSONObject rootJsonObject = new JSONObject(jsonStr);

            JSONObject weapons = rootJsonObject.getJSONObject("weapons");

            JSONArray swords = weapons.getJSONArray("swords");

            for(int i = 0; i < swords.length(); ++i) {

                JSONObject sword = swords.getJSONObject(i);

                int damage = sword.getInt("damage");
                String name = sword.getString("name");

                Log.i(TAG, "name = " + name + ", damage = " + String.valueOf(damage));
            }

            JSONArray spears = weapons.getJSONArray("spears");

            for(int i = 0; i < spears.length(); ++i) {

                JSONObject spear = spears.getJSONObject(i);

                int damage = spear.getInt("damage");
                String name = spear.getString("name");

                Log.i(TAG, "name = " + name + ", damage = " + String.valueOf(damage));
            }

        } catch(JSONException e) {

            // Something went wrong while attempting to parse the JSON.
            // In a real app, you would need to take action here and handle it!
            e.printStackTrace();
        }
    }
}
