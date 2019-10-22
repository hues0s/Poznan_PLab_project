package Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PeselLogic {

    private InputStream source;

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

        return true;
    }

}
