package Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PeselLogic {

    private InputStream source;
    private static final int PESEL_NUMBER_LENGTH = 11;

    public PeselLogic(InputStream source){
        this.source = source;
    }

    public String getPeselNumber(){
        StringBuilder builder = new StringBuilder();
        try( BufferedReader br = new BufferedReader(new InputStreamReader(source))){
            builder.append(br.readLine());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return builder.toString();
    }

    public boolean checkPeselCorrectness(String peselNumber){

        if(peselNumber == null || peselNumber.length() != PESEL_NUMBER_LENGTH) return false;

        int sum = 0;
        for(int i = 0; i < PESEL_NUMBER_LENGTH - 1; ++i){
            sum += char2Int(peselNumber.charAt(i)) * getCorrectMultiplier(i);
        }
        int module = sum % 10;
        int lastDigit = char2Int(peselNumber.charAt(PESEL_NUMBER_LENGTH - 1));

        return (module == 0) && lastDigit == 0 || lastDigit == 10 - module;
    }

    private int char2Int(char c){
        return c - 48;
    }

    private int getCorrectMultiplier(int i){
        int ret = 0;
        switch (i % 4){
            case 0:
                ret = 1;
                break;
            case 1:
                ret = 3;
                break;
            case 2:
                ret = 7;
                break;
            case 3:
                ret = 9;
                break;
        }
        return ret;
    }

}
