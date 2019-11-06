package Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PeselLogic {

    private static final int PESEL_NUMBER_LENGTH = 11;

    public List<String> getPeselNumbers(InputStream source){
        List<String> ret = new ArrayList<String>();
        try( BufferedReader br = new BufferedReader(new InputStreamReader(source))){
            ret = Arrays.asList(br.readLine().split(" "));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return ret;
    }

    public boolean checkPeselCorrectness(String peselNumber){

        if(peselNumber == null || peselNumber.length() != PESEL_NUMBER_LENGTH) return false;

        if(checkIfItHasLetter(peselNumber)) return false;

        int sum = 0;
        for(int i = 0; i < PESEL_NUMBER_LENGTH - 1; ++i){
            sum += char2Int(peselNumber.charAt(i)) * getCorrectMultiplier(i);
        }
        int module = sum % 10;
        int lastDigit = char2Int(peselNumber.charAt(PESEL_NUMBER_LENGTH - 1));

        return (module == 0) && lastDigit == 0 || lastDigit == 10 - module;
    }

    private boolean checkIfItHasLetter(String pesel){
        for(int i = 0; i < pesel.length(); ++i)
            if(Character.isLetter(pesel.charAt(i))) return true;
        return false;
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
