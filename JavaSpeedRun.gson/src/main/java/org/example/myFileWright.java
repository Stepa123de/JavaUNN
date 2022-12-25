package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class myFileWright {

    private String path;

    public  myFileWright(String pathSave)
    {
        this.path = pathSave;
    }

    public Shop myRead()
    {
        try {
            FileReader fr = new FileReader(path);
            Scanner scanner = new Scanner(fr);
            String obj = "";
            while (scanner.hasNext())
            {
                obj+=scanner.next();
            }
            fr.close();
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Shop.class, new ShopAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            return gson.fromJson(obj, Shop.class);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void myWrite(Shop sh) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(gson.toJson(sh,Shop.class));
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
