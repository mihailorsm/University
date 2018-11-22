package com.company;

import java.io.*;

import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class SqlParse {
    public static void main(String[] args) {
        saveRequestFromTravianServer("https://ts5.travian.ru/map.sql", "map.txt");
        createDateBase("map.txt");
    }

    public static void saveRequestFromTravianServer(String url, String fileName){
        try {
            URL website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            rbc.close();
            fos.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void createDateBase(String filename) {
        String requestForCreate = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("map.txt"));
            String str;
            Class.forName("com.mysql.driver");
            while((str = reader.readLine())!= null)
            {

            }
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }catch (IOException e){
            e.getMessage();
        }
        catch (ClassNotFoundException e){
            System.out.println("Не подключен драйвер");
        }
    }

}
