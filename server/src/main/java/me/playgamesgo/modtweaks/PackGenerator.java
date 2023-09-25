package me.playgamesgo.modtweaks;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.Request;


public class PackGenerator {

    public PackGenerator(String jsonData) {
        String category;
        JSONArray filesArray;
        int version;

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonData);

            JSONObject dataObject = (JSONObject) jsonObject.get("data");

            for (Object key : dataObject.keySet()) {
                category = (String) key;
                filesArray = (JSONArray) dataObject.get(category);

                System.out.println("Category: " + category);
                System.out.println("Files: " + filesArray);
            }

            version = (int) jsonObject.get("version");
            System.out.println("Version: " + version);

        } catch (ParseException e) {
            Main.logger.warning("Error while parsing json: " + jsonData + "\n" + e.getMessage());
        }

        //TODO: Генерация пака

    }
}