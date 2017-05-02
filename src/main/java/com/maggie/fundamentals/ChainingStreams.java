package com.maggie.fundamentals;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by z001hqv on 2/8/17.
 */
public class ChainingStreams {
    public static void main(String[] args) {
        boolean chain = false;
        boolean writeData = false;
        boolean readData = false;
        boolean readDataAlternative = false;
        boolean zipFileSystem = true;

        String[] data = {
                "abd dafdf dafadf adfa ",
                "asdfa jl;kdajfs l;kadjfas j;lksadjf",
                "alkdjfa lkjdafsd jl;kajfaf j;lksdjaf "
        };

        if(chain) {
            try {
                ChainingStreams.doChain(new FileInputStream("./data/bank_data_dictionary.txt"));
            } catch (IOException e) {
                System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }else if(writeData){
            ChainingStreams.writeData(data);
        }else if (readData){
            try {
                ChainingStreams.readData("./data/data.txt");
            } catch (IOException e){
                System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }else if (readDataAlternative){
            try {
                ChainingStreams.readDataAlternative("./data/data.txt");
            } catch (IOException e){
                System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }else if (zipFileSystem){
            try(FileSystem fs = openZip(Paths.get("./data/Data.zip"))){
                //ChainingStreams.copyToFile(fs);
                ChainingStreams.writeToFileInZip(fs, data);
                ChainingStreams.bulkWriteToFileInZip(fs, data);
            } catch (URISyntaxException e){
                System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
            } catch (IOException e){
                System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }


    }

    public static FileSystem openZip(Path filename) throws IOException, URISyntaxException{
        Map<String, String> providerProps = new HashMap<>();
        providerProps.put("create", "true");

        URI zipFile = new URI("jar:file", filename.toUri().getPath(), null);
        FileSystem zipFs = FileSystems.newFileSystem(zipFile, providerProps);

        return zipFs;
    }

    public static void copyToFile(FileSystem zipFile) throws IOException{
        Path sourceFiles = Paths.get("./data/bank_data_dictionary.txt");

        //Alternative to default Paths file system
        //Path sourceFile = FileSystems.getDefault().getPath("./data/data.txt");

        Path destinationFile = zipFile.getPath("bankDataDictionaryCopied.txt");

        Files.copy(sourceFiles, destinationFile, StandardCopyOption.REPLACE_EXISTING);

    }

    public static void writeToFileInZip(FileSystem zipFs, String[] data) throws IOException{

        try(BufferedWriter writer = Files.newBufferedWriter(zipFs.getPath("/newFile.txt"), StandardOpenOption.TRUNCATE_EXISTING)){
            for (String line: data){
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public static void bulkWriteToFileInZip(FileSystem zipFs, String[] data) throws IOException{
        //Need Arrays.asList because the input type is Iterable
        //So we need to convert the Array to a List because it
        //implements iterable.
        Files.write(zipFs.getPath("/newFile2.txt"), Arrays.asList(data),
                Charset.defaultCharset(), StandardOpenOption.CREATE);
    }

    public static void doChain(InputStream in) throws IOException{
        int length;
        char[] charBuff = new char[128];

        try(InputStreamReader reader = new InputStreamReader(in)){
            while((length = reader.read(charBuff)) >= 0){
                System.out.println("\nlength: " + length);
                for(int i = 0; i < length; i++){
                    System.out.print(charBuff[i]);
                }
            }
        } catch (IOException e){
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    public static void writeData(String[] data){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./data/data.txt"))){
            for (String line : data){
                System.out.println(line);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e){
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    public static void readData(String filename) throws IOException{
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                System.out.println("line=" + line);
            }
        }
    }

    public static void readDataAlternative(String filename) throws IOException{
        try(BufferedReader br = Files.newBufferedReader(Paths.get(filename))){
            String line;
            while((line = br.readLine()) != null){
                System.out.println("alt.reader.line=" + line);
            }
        }
    }
}
