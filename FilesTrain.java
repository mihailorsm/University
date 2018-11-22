package com.company;

import java.io.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class FilesTrain {

    public static final String FILE_NAME = "map.txt";


    public static void main(String[] args) {
    readTry(FILE_NAME);
    }

    public static void readTry(String filename){
        byte[] bytes = new byte[50];
        FileInputStream in = null;
        try {
            in = new FileInputStream(filename);
            int count = in.read(bytes);
            for (byte b: bytes)
                System.out.println(b);
            in.close();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.getMessage();
                e.printStackTrace();
            }
        }

    }

    public static void multiRead(){
        ArrayList<FileInputStream> arr = new ArrayList<>();
        FileInputStream temp = null;
        SequenceInputStream seq = null;
        FileOutputStream out = null;
        try {
            arr.add(new FileInputStream("1.txt"));
            arr.add(new FileInputStream("2.txt"));
            arr.add(new FileInputStream("3.txt"));
            arr.add(new FileInputStream("4.txt"));
            arr.add(new FileInputStream("5.txt"));
            Enumeration<FileInputStream> e = Collections.enumeration(arr);
            while ((temp = e.nextElement()) != null){
                seq = new SequenceInputStream(seq, temp);
            }
            out = new FileOutputStream("out.txt");
            int rb = seq.read();
            while(rb != -1){
                out.write(rb);
                rb = seq.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try { seq.close(); } catch ( IOException e ) { };
            try { out.close(); } catch ( IOException e ) { };
        }
    }
}
