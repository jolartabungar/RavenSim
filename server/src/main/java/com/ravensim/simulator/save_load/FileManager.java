package com.ravensim.simulator.save_load;

import com.ravensim.simulator.model.CircuitModel;

import java.io.*;

public class FileManager {
    public FileManager() {
    }

    public void saveToFile(Object object, String fileName) {
        try {
        	String filePath = ".//" + fileName + ".ser";
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
            System.out.println("Saved changes: " + object.toString() + " to " + filePath);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public CircuitModel loadFromFile(String fileName) {
        try {
        	String filePath = ".//" + fileName + ".ser";	
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            CircuitModel model = (CircuitModel) ois.readObject();
            System.out.println("Reading circuit file from " + filePath);
            return model;
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            // send message to client saying file does not exist or file cannot be loaded
        }
        return null;
    }
}
