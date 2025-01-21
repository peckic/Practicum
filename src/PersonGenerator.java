import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {

    public static void main(String[] args) {

        ArrayList<String> people = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

        boolean done = false;
        String personRec = "";
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int yearOfBirth = 0;

        do {
            ID = SafeInput.getNonZeroLenString(in,"Enter the ID [6 digits]: ");
            firstName = SafeInput.getNonZeroLenString(in,"Enter the First Name: ");
            lastName = SafeInput.getNonZeroLenString(in,"Enter the Last Name: ");
            title = SafeInput.getNonZeroLenString(in,"Enter the Title: ");
            yearOfBirth = SafeInput.getRangedInt(in, "Enter the Year of Birth", 1000, 9999);

            personRec = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + yearOfBirth;
            people.add(personRec);

            done = SafeInput.getYNConfirm(in, "Are you done?");
        } while(!done);

        for (String p: people)
            System.out.println(p);

        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : people){
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e){
            e.printStackTrace();;
        }

    }
}