import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JFileChooser;


public class PersonReader {

    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        String line;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());

        try {
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();


                inFile = new Scanner(target);

                System.out.println("ID#  FirstName LastName Title YOB");
                System.out.println("==================================");

                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    System.out.println(line);
                }
                inFile.close();
            }
            else {
                System.out.println("Sorry you must select a file, terminating");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File Not Found Error");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("IOException Error");
            e.printStackTrace();
        }

    }

}
