package Factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class TXTFactory implements DataFrameFactory {
    private final DataFrame map = new DataFrame();

    /**
     * Read method for TXT files
     *
     * @param string name of the file
     */
    @Override
    public DataFrame read(String string) {
        File file;
        FileReader fr;
        BufferedReader br;
        try {
            ArrayList<String> list;
            file = new File(string);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String line;
            line = br.readLine();
            String[] values = line.split(";");

            map.head = values;
            for (int j = 0; j < map.head.length; j++) {
                map.structure.put(j, new ArrayList<>());
            }
            while ((line = br.readLine()) != null) {
                values = line.split(";");
                for (int j = 0; j < map.head.length; j++) {
                    list = new ArrayList<>(map.structure.get(j));
                    list.add(values[j].trim());
                    this.map.structure.put(j, list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
