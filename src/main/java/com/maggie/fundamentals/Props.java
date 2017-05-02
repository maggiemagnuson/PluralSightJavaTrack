package com.maggie.fundamentals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by z001hqv on 2/15/17.
 */
public class Props {
    public static void main(String[] args) {
        Properties props = new Properties();
        try(InputStream in = Props.class.getResourceAsStream("/props.properties")){
            props.load(in);
            System.out.println(props.getProperty("team1"));
            System.out.println(props.getProperty("team2"));

        } catch (IOException e){
            e.printStackTrace();
        }


    }
}
