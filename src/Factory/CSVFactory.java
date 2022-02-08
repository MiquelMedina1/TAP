package Factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVFactory implements DataFrameFactory {
    final String SEPARATOR = ",";
    private final DataFrame map = new DataFrame();

    /**
     * Read method for CSV files
     *
     * @param string name of the file
     */
    @Override
    public DataFrame read(String string) {
        BufferedReader br = null;

        try {
            ArrayList<String> list;
            br = new BufferedReader(new FileReader(string));
            String line = br.readLine().replace("\"", "").replace(" ", "");

            map.head = line.split(SEPARATOR);
            for (int j = 0; j < map.head.length; j++) {
                map.structure.put(j, new ArrayList<>());
            }
            line = br.readLine();
            do {
                line = line.replace("\"", "");
                String[] fields = line.split(SEPARATOR);
                for (int j = 0; j < map.head.length; j++) {
                    list = new ArrayList<>(map.structure.get(j));
                    list.add(fields[j].trim());
                    this.map.structure.put(j, list);
                }
                line = br.readLine();
            } while (null != line);
            //System.out.println(CSVmap);

        } catch (Exception ignored) {

        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return map;
    }


}
