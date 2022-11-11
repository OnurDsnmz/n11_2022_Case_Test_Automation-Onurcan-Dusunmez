package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Helper {
    public static void CSVPrinter(String csvTitle, List<String> rows){
        try (PrintWriter writer = new PrintWriter(csvTitle+".csv")) {
            for(String row: rows){
                writer.write(row);
                writer.write("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public static boolean CSVAndTextIsMatch(String fileName, List<String> text) throws FileNotFoundException {
        File f = new File(fileName + ".csv");
        int index = 0;
        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String CSVTextLine = s.nextLine();
            String listTextLine = text.get(index);
            if (!Objects.equals(CSVTextLine, listTextLine)){
                return false;
            }
            index++;
        }
        s.close();
        return true;
    }
}
