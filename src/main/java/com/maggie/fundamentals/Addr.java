package com.maggie.fundamentals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

/**
 * Created by z001hqv on 2/16/17.
 */
public class Addr implements Runnable, Callable<Integer>{
    private String inFile;
    private String outFile;

    public String getInFile() {
        return inFile;
    }

    public void setInFile(String inFile) {
        this.inFile = inFile;
    }

    public String getOutFile() {
        return outFile;
    }

    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    public Addr(String inFile, String outFile){
        this.inFile=inFile;
        this.outFile=outFile;
    }

    @Override
    public void run(){
        //Since we need to manage exceptions that occur
        //on each thread we need to add a try/catch
        try{
            doAdd();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void doAdd() throws IOException{
        int total = 0;
        String line = null;

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(this.inFile))){
            while ((line = reader.readLine()) != null){
                total += Integer.parseInt(line);
            }
        }

        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.outFile))){
            writer.write("Total = " + total);
        }
    }

    public int doAddReturnResult() throws IOException{
        int total = 0;
        String line = null;

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(this.inFile))){
            while ((line = reader.readLine()) != null){
                total += Integer.parseInt(line);
            }
        }

        return total;
    }

    @Override
    public Integer call() throws Exception {
        return doAddReturnResult();
    }
}
