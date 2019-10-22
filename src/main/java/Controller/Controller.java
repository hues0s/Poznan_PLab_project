package Controller;

import Logic.PeselLogic;

import javax.xml.bind.SchemaOutputResolver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Controller {

    private PeselLogic peselLogic;

    public Controller(InputStream source){
        this.peselLogic = new PeselLogic(source);
    }

    public void run(){

        String peselNumber = peselLogic.getPeselNumber();
        if(peselLogic.checkPeselCorrectness(peselNumber)){

        }
        else{
            System.out.println("PESEL number introduced is wrong. The program will end.");
        }


    }

}
