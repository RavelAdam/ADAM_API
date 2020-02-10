package adam.ravel;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Main {

    //Fonction servant à enlever les ' \" ' des strings
    public static String prettify(Gson p_gson, JsonParser p_jp, String p_jsonString)
    {
        JsonElement je = p_jp.parse(p_jsonString);
        String prettyJsonString = p_gson.toJson(je);
        return prettyJsonString;
    }

    public static void main(String[] args) {

        //Création des strings qui contiennent les données au format JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        String friends = prettify(gson, jp,
                "{\"friends\":[{\"name\":\"Kratos\"},{\"name\":\"Atreus\"},{\"name\":\"Thanos\"},{\"name\":\"Minerva\"}]}"
        );
        String store_games = prettify(gson, jp,
                "{\"games\":[{\"name\":\"Zelda\"},{\"name\":\"Spiderman\"},{\"name\":\"Anno 1800\"},{\"name\":\"Frostpunk\"}]}"
        );
        String library_games = prettify(gson, jp,
                "{\"games\":[{\"name\":\"Call of Duty\"},{\"name\":\"Battlefield\"},{\"name\":\"Homeworld\"},{\"name\":\"Max Payne\"}]}"
        );

        //Création de l'API
        get("/api/friends", (req, res)->friends);
        get("/api/store/games", (req, res)->store_games);
        get("/api/library/games", (req, res)->library_games);
    }
}
