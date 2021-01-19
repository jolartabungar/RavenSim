package com.ravensim.simulator.save_load;

import com.ravensim.simulator.model.CircuitModel;

import java.io.*;

public class FileManager {
    public FileManager() {
    }

    public void saveToFile(Object object) {
        try {
            FileOutputStream fos = new FileOutputStream(".//test-save.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
            System.out.println("Saved changes: " + object.toString());
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public CircuitModel loadFromFile() {
        try {
            FileInputStream fis = new FileInputStream(".//test-save.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            CircuitModel model = (CircuitModel) ois.readObject();
            System.out.println("Reading Circuit File: ");
            model.printModel();
            return model;
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
