package com.maggie.fundamentals;

import java.io.*;

/**
 * Created by z001hqv on 2/8/17.
 */
public class Main {

    public static void main(String[] args) {
        //doTryCatchFinally();
        //doTryWithResources();
        //doAutoCloseable();
        CreateRandomIntegerFile file = new CreateRandomIntegerFile();
        file.CreateFile("./data/random_integers.txt", 10000, 6);
    }

    public static void doTryCatchFinally() {
        char[] buff = new char[8];
        int length;
        Reader reader = null;

        try{
            reader = new FileReader("./data/file.txt");
            while((length = reader.read(buff)) >= 0){

                System.out.println("\nlength=" + length);

                for (int i = 0; i < length; i++){
                    System.out.print(buff[i]);
                }
            }
        } catch (IOException e){
            System.out.println(e.getClass().getSimpleName() + "-" + e.getMessage());
        } finally {
            try{
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e2){
                System.out.println(e2.getClass().getSimpleName() + "-" + e2.getMessage());
            }
        }
    }

    public static void doTryWithResources(){
        char[] buff = new char[8];
        int length;

        try (Reader reader = new FileReader("./data/file.txt");
                Writer writer = new FileWriter("./data/output.txt", true)){

            while((length = reader.read(buff)) >= 0){

                writer.write("\nlength=" + length);

                for (int i = 0; i < length; i++){
                    writer.write(buff[i]);
                }
            }
        } catch (IOException e){
            System.out.println(e.getClass().getSimpleName() + "-" + e.getMessage());
        }
    }

    //This proves that when you implement something with
    //the AutoCloseable interface it'll auto-magically call
    //the close method.
    public static void doAutoCloseable(){
        try (AutoCloseableImpl autoCloseable = new AutoCloseableImpl()){
            autoCloseable.saySomething();
        } catch (IOException e){
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
            for (Throwable t: e.getSuppressed()){
                System.out.println("Suppressed: "+ t.getMessage());
            }
        }
    }
}
