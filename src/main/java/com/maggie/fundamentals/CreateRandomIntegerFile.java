package com.maggie.fundamentals;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Created by z001hqv on 2/16/17.
 */
public class CreateRandomIntegerFile {

    public void CreateFile(String outFilePattern, int numbers, int files){
        Path path = Paths.get(outFilePattern);
        String file = path.getFileName().toString();
        //Parse file name and extension
        String fileName = file.split("\\.")[0];
        String fileExt = file.split("\\.")[1];
        //Get number of elements in path
        Integer nameCnt = path.getNameCount();
        //Parse of the file and extension
        String filePath = path.subpath(0, nameCnt-1).toString();
        //Set first file number
        Integer fileNumber = 1;
        //Create the new file name with path
        String outFile = filePath + "/" + fileName + "_" + fileNumber + "." + fileExt;
        //System.out.println(outFile);
        Integer recsPerFile = numbers/files;
        BufferedWriter writer = null;

        try{
            writer = Files.newBufferedWriter(Paths.get(outFile));

            Random random = new Random();
            for (int i = 0; i <= numbers; i++){
                //System.out.println(recsPerFile);
                if (i != 0 && (i % recsPerFile == 0) && (numbers - i > recsPerFile)){
                    //Need to close the stream so it writes.
                    writer.close();
                    //Increment the file number
                    fileNumber++;
                    //Create the new file path and name
                    outFile = filePath + "/" + fileName + "_" + fileNumber + "." + fileExt;
                    writer = Files.newBufferedWriter(Paths.get(outFile));
                }
                writer.write(String.valueOf(random.nextInt(9999)));
                writer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try {
                //Need to close the last writer
                writer.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
