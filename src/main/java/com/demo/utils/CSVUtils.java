package com.demo.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVUtils {

    public static Object[][] readCsvData(String filePath) {
        List<Object[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            // Skip header
            reader.readNext(); 
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return data.toArray(new Object[0][]);
    }

    /**
     * Reads CSV data and filters rows based on a specific TestCase ID.
     * @param filePath Path to the CSV file
     * @param testCaseId The ID of the test case to filter by
     * @return Data array excluding the testCaseId column
     */
    public static Object[][] getDataByTestCase(String filePath, String testCaseId) {
        List<Object[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] header = reader.readNext();
            int tcIdIndex = -1;
            
            // Find index of testCaseId column
            for (int i = 0; i < header.length; i++) {
                if (header[i].equalsIgnoreCase("testCaseId")) {
                    tcIdIndex = i;
                    break;
                }
            }
            
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (tcIdIndex != -1 && line[tcIdIndex].equalsIgnoreCase(testCaseId)) {
                    List<String> rowData = new ArrayList<>(Arrays.asList(line));
                    rowData.remove(tcIdIndex); // Remove testCaseId from the parameters
                    data.add(rowData.toArray());
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return data.toArray(new Object[0][]);
    }
}
