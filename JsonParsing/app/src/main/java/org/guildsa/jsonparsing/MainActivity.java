package org.guildsa.jsonparsing;

import android.support.annotation.RawRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            parseJsonTest1();
            parseJsonTest2();

        } catch(JSONException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String readJSONFromRes(@RawRes int id) throws JSONException {

        StringBuilder builder = new StringBuilder();

        try {

            InputStream stream = getResources().openRawResource(id);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));

            String line = "";

            while((line = buffer.readLine()) != null) {
                builder.append(line);
            }

        } catch(IOException e1) {
            e1.printStackTrace();
        }

        return builder.toString();
    }

    public void parseJsonTest1() throws JSONException, IOException {

        // Example 1:
        //
        // Located in: JsonParsing/app/src/main/res/raw/json1.txt
        //
        //{
        //    "name": "The WarL0rd",
        //    "maps": [ 12, 23, 55 ]
        //}

        // Get our JSON data our of a file in the res/raw folder and into a String.
        // In a real app, your JSON data would probably come from a web service or URL.
        String jsonStr1 = readJSONFromRes(R.raw.json1);

        JSONObject rootJsonObject1 = new JSONObject(jsonStr1);

        String name = rootJsonObject1.getString("name");

        Log.i(TAG, "name = " + name);

        JSONArray maps = rootJsonObject1.getJSONArray("maps");

        for(int i = 0; i < maps.length(); ++i) {

            int mapId = maps.getInt(i);
            Log.i(TAG, "value = " + String.valueOf(mapId));
        }
    }

    public void parseJsonTest2() throws JSONException, IOException {

        // Example 2:
        //
        // Located in: JsonParsing/app/src/main/res/raw/json2.txt
        //
        //{
        //    "weapons": {
        //        "swords": [
        //            {
        //                "name": "Short Sword",
        //                "damage": 25
        //            },
        //            {
        //                "name": "Broad Sword",
        //                "damage": 100
        //            },
        //            {
        //                "name": "Skull Cleaver",
        //                "damage": 150
        //            }
        //        ],
        //        "spears": [
        //            {
        //                "name": "Wooden Spear",
        //                "damage": 15
        //            },
        //            {
        //                "name": "Iron Spear",
        //                "damage": 20
        //            }
        //        ]
        //    }
        //}

        // Get our JSON data our of a file in the res/raw folder and into a String.
        // In a real app, your JSON data would probably come from a web service or URL.
        String jsonStr2 = readJSONFromRes(R.raw.json2);

        JSONObject rootJsonObject2 = new JSONObject(jsonStr2);

        JSONObject weapons = rootJsonObject2.getJSONObject("weapons");

        JSONArray swords = weapons.getJSONArray("swords");

        for(int i = 0; i < swords.length(); ++i) {

            JSONObject sword = swords.getJSONObject(i);

            int damage = sword.getInt("damage");
            String name = sword.getString("name");

            Log.i(TAG, "damage = " + String.valueOf(damage));
            Log.i(TAG, "name = " + name);
        }

        JSONArray spears = weapons.getJSONArray("spears");

        for(int i = 0; i < spears.length(); ++i) {

            JSONObject spear = spears.getJSONObject(i);

            int damage = spear.getInt("damage");
            String name = spear.getString("name");

            Log.i(TAG, "damage = " + String.valueOf(damage));
            Log.i(TAG, "name = "+ name);
        }
    }
}
