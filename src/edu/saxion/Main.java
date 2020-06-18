package edu.saxion;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        String filename= scanner.next();
        SpellingDictionary example = new SpellingDictionary();

        Path path = Paths.get(filename);
        Path outPath= Paths.get("outFile.txt");

        try (BufferedReader br = Files.newBufferedReader(path);
             BufferedWriter bw = Files.newBufferedWriter(outPath)){
            String line ;
            String[] arrayLine = new String[0];
           // int i=0;
            while ((line = br.readLine()) != null){
             //   args[i].equals(line);
                arrayLine = line.split(" ");
                bw.write(line);
                bw.newLine();
               // i++;
                // System.out.println(line);
            }
            for (String splittedWord : arrayLine ){
                System.out.println(splittedWord);
            }

        }catch (IOException iox){
            System.out.println("Here is an error");
        }
        // TODO: 17/06/2020 Figure out how to use args[] to store the txt input file
        for(int i = 0; i<args.length; i++) {
            System.out.println("args[" + i + "]: " + args[i]);
        }
      //  BufferedReader bufferedReader= new BufferedReader(new FileReader(filename));
    }
}
