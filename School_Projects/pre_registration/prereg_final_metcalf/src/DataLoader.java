import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Abstract Class that handles file i/o
 *
 * @author (Solution)
 * @version (a version number or a date)
 */
public abstract class DataLoader
{
    
    /**
     * Utility method that reads a file from disk.
     * @param fileName: string;
     */
    public void load(String fileName){
        String data = "";
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                try {
                    data = myReader.nextLine();
                    parseAndLoadLine(data);
                }catch(NumberFormatException e){
                    System.out.printf("Found malformed data in %s.\n",fileName);
                    System.out.println(data);
                    e.printStackTrace();
                }catch (NullPointerException e){
                    System.out.printf("NullPointer%s.\n",fileName);
                    System.out.println(data);
                    e.printStackTrace();            
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.out.println(data);
            e.printStackTrace();
        }    
    }

    /**
     * Abstract method to be implemented by child class to parse 
     * each line of a csv file and create an object of the appropriate type.
     * 
     * @param data: String each line of the file.
     */
    public abstract void parseAndLoadLine(String data);
}
