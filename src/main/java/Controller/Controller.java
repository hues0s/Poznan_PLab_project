package Controller;

import Logic.PeselLogic;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Controller {

    private PeselLogic peselLogic;
    private static final int MAX_PESEL_NUMBERS_PROCESSED = 30;

    public Controller(InputStream source){
        this.peselLogic = new PeselLogic(source);
    }

    public void run(){

        System.out.println("Welcome to the PESEL program!");
        System.out.println("WARNING: only 30 PESEL numbers will be processed.");
        System.out.println(".........................................................");

        //we get a list of the pesel numbers from the input.
        List<String> peselNumberList = peselLogic.getPeselNumbers();

        System.out.println("The PESEL numbers introduced are: ");

        peselNumberList
                .stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println(".........................................................");

        // Now I use a set in order to avoid duplicated pesel numbers.
        Set<String> uniquePeselNumberSet;
        uniquePeselNumberSet = peselNumberList
                .stream()
                .limit(MAX_PESEL_NUMBERS_PROCESSED)
                .collect(Collectors.toSet());

        //We remove all the pesel numbers that are wrong
        uniquePeselNumberSet.removeIf(peselNumber -> !peselLogic.checkPeselCorrectness(peselNumber));

        if(uniquePeselNumberSet.size() == 0)
            System.out.println("You don't have any correct PESEL numbers.");
        else{
            System.out.println(".........................................................");
            System.out.println("The correct PESEL numbers will be written in the file called \"PESEL_number_result.txt\".");
            System.out.println(".........................................................");
            StringBuilder peselNumberResult = new StringBuilder();
            for(String peselNumber: uniquePeselNumberSet)
                peselNumberResult.append(peselNumber).append(" ");
            saveToFile("PESEL_number_result", peselNumberResult.toString());
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
