import Controller.Controller;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {

    /*
    I am using Apache Commons CLI java library from the maven repository,
    in order to parse the command line args
     */

    public static void main(String [ ] args) throws ParseException {

        //command line parser
        Options options = new Options();
        options.addOption("c", false, "console as source");
        options.addOption("f", true, "file as source");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            InputStream source = null;

            if(cmd.hasOption('c')){
                source = System.in;
            }
            else if(cmd.hasOption('f')){
                File f = new File(cmd.getOptionValue('f'));
                try {
                    source = new FileInputStream(f);
                } catch (FileNotFoundException e) {
                    System.err.println("Error while loading the input file");
                }
            }
            if(cmd.hasOption('c') || cmd.hasOption('f')){
                Controller controller = new Controller(source);
                controller.run();
            }
            else{
                System.out.println("You have to choose at least one of -c or -f option in order to choose the source");
            }

        } catch (UnrecognizedOptionException uoe){
            System.err.println(uoe.getMessage());
        }

    }
}