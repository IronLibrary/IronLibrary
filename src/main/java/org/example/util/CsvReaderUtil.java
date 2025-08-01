package org.example.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReaderUtil {
    public static List<String[]> readCsv(String filePath, boolean ignoreHeader) throws IOException {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            if (ignoreHeader) {
                reader.readLine(); // Ignora la primera línea
            }

            while ((line = reader.readLine()) != null) {
                rows.add(line.split(","));
            }
        }
        return rows;
    }

    public static int getNextId(String filePath) throws IOException {
        int maxId = 0;
        List<String[]> rows = readCsv(filePath, true);
        for (String[] row : rows) {
            try {
                int id = Integer.parseInt(row[0]);
                if (id > maxId) {
                    maxId = id;
                }
            } catch (NumberFormatException e) {
                // Ignorar encabezado u otras líneas mal formateadas
                System.err.println("Error parsing ID from row: " + Arrays.toString(row));
            }
        }
        return maxId + 1;
    }
}
