package model;
import controller.FileContentsReader;

import java.util.ArrayList;

public class FXControlDataLoader {
    public static final String CLIENTS_FILE_NAME = "clients.txt";
    public static final String PROTOCOLS_FILE_NAME = "protocols.txt";
    FileContentsReader contentsLoader;
    ArrayList<String> comboModel;


    public ArrayList<String> setComboModel() {
        contentsLoader = new FileContentsReader(CLIENTS_FILE_NAME);
        comboModel = new ArrayList<String>();
        String allRecords = contentsLoader.fetchAllRecordsFromFile();
        String[] allRecordsArray = allRecords.split("\n");
        //comboModel = new ArrayList<String>();
        for (int i = 0; i < allRecordsArray.length; i++) {
            String[] rowArray = allRecordsArray[i].split("\t");
            comboModel.add(rowArray[0]);
        }
        return comboModel;
    }
}
