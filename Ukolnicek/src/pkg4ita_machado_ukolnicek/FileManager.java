package pkg4ita_machado_ukolnicek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author THUND
 */
public class FileManager {
    
    private static File file = new File("notes.txt");
    private static File tempFile = new File("temp.txt");
    private static FileWriter fw;
    private static Scanner sc;
    private static String textSeparator = "|~"; // String that determines separation of same-note data
    private static int id = 0;

    public FileManager() throws IOException {
        if(!file.createNewFile()) {
            // File already exists, read the last line and get the id
            id = getLatestId();
        }
    }
    
    public static void Append(String text) throws IOException {
        fw = new FileWriter(file, true);
        text = ++id + textSeparator + text + "\n";
        fw.write(text);
        fw.close();
    }
    
    public static void Clear() throws IOException {
        fw = new FileWriter(file);
        fw.write("");
        fw.close();
        id = 0;
    }
    
    private int getLatestId() {
        try {
            Scanner sc = new Scanner(file);
            String last = "";
            while(sc.hasNextLine()) {
                last = sc.nextLine();
            }
            sc.close();
            return getLineId(last);
        } catch (Exception ex) {
            return 0;
        }
    }
    
    private static int getLineId(String line) {
        int separatorIdx = line.indexOf(textSeparator);
        return Integer.parseInt(line.substring(0, separatorIdx));
    }
    
    public static void removeLineById(int id) {
        try {
            Scanner sc = new Scanner(file);
            tempFile.createNewFile();
            FileWriter fw = new FileWriter(tempFile, true);
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                int curLineId = getLineId(line);
                if(curLineId != id) {
                    fw.write(line + "\n");
                }
            }
            fw.close();
            sc.close();
            file.delete();
            tempFile.renameTo(file);
        } catch (Exception e) {
        }
    }
    
    public static String GetTextSeparator() {
        return textSeparator;
    }
    
    public static ArrayList<Note> GetNotes() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<Note> notes = new ArrayList<>();
        String line;
        
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            
            int separatorIdx = line.indexOf(textSeparator);
            
            int id = Integer.parseInt(line.substring(0, separatorIdx));
            String noId = line.substring(separatorIdx + textSeparator.length());
            
            separatorIdx = noId.indexOf(textSeparator);
            
            String noTimestamp = noId.substring(separatorIdx + textSeparator.length());
            
            String[] split = new String[4];
            split[0] = noId.substring(0, separatorIdx); // TIMESTAMP
            
            separatorIdx = noTimestamp.indexOf(textSeparator);
            split[1] = noTimestamp.substring(0, separatorIdx); // TITLE
            split[2] = noTimestamp.substring(separatorIdx + textSeparator.length()); // BODY
            
            Note note = new Note(split[0], split[1], split[2], id);
            notes.add(note);
        }
        
        sc.close();
        return notes;
    }
}
