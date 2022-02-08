package Factory;

import com.google.gson.*;

import java.io.*;
import java.util.*;


public class JSONFactory implements DataFrameFactory {
    private final DataFrame map = new DataFrame();

    /**
     * Read method for JSON files
     *
     * @param string name of the file
     */
    @Override
    public DataFrame read(String string) {
        JsonParser parser = new JsonParser();
        try {
            ArrayList<String> list;
            JsonArray ar = (JsonArray) parser.parse(new FileReader(string));
            JsonObject j = ar.get(0).getAsJsonObject();
            Object k = j.keySet();
            String r = k.toString();
            r = r.replace("[", "").replace("]", "").replace(" ", "");
            map.head = r.split(",");
            for (int l = 0; l < map.head.length; l++) {
                map.structure.put(l, new ArrayList<>());
            }

            for (int i = 0; i < ar.size(); i++) {
                j = (JsonObject) ar.get(i);

                for (int l = 0; l < map.head.length; l++) {
                    JsonElement x = j.get(map.head[l]);
                    list = new ArrayList<>(map.structure.get(l));
                    list.add(x.toString().trim().replace("\"", ""));
                    this.map.structure.put(l, list);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }


}
