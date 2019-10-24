package Controller;

import Logic.PeselLogic;

import javax.xml.bind.SchemaOutputResolver;
import java.io.*;

public class Controller {

    private PeselLogic peselLogic;

    public Controller(InputStream source){
        this.peselLogic = new PeselLogic(source);
    }

    public void run(){

        System.out.println("Welcome to the PESEL program!");

        String peselNumber = peselLogic.getPeselNumber();
        if(peselLogic.checkPeselCorrectness(peselNumber)){
            System.out.println("PESEL number introduced is correct. It will be saved in a file.");
            saveToFile("PESEL_number", peselNumber);
        }
        else{
            System.out.println("PESEL number introduced is wrong. The program will end.");
        }


    }

    private void saveToFile(String name, String stringToWrite){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(name + ".txt", "UTF-8");
            writer.println(stringToWrite);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println("ERROR: file could not be created.");
        }
    }

}
