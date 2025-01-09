import java.util.ArrayList;
import java.util.List;

/**
 * Loads in student objects from a CSV
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StudentLoader extends DataLoader
{
    private List<Student> students = new ArrayList<>();
    /**
     * Constructor calls the load(file) method in abstract parent class.
     * 
     * @param file: the path to the file.
     */
    public StudentLoader(String file){
        load(file);
    }
    
    /**
     * Parse a single line of CSV in the form:
     * Name, idNum, gradYear, drawNumber
     * Hector Tran,999248624,2023,1
     * <p>
     * Method should create a new Student Object and add it to 
     * the students instance variable.
     * 
     * @param data: a single line from the csv file.
     */
    public void parseAndLoadLine(String data){
        //TODO
        String [] parts = data.split(",");
        if (parts.length != 4){
            throw new IndexOutOfBoundsException("index is not 4 it is:" + parts.length);
        }
        String name = parts[0];

        int idNum = Integer.parseInt(parts[1]);

        int gradYear = Integer.parseInt(parts[2]);

        int drawNum = Integer.parseInt(parts[3]);


        Student student = new Student(name,idNum,gradYear,drawNum);
        students.add(student);

        //System.out.println("Please implement parseAndLoadLine in StudentLoader.");
    }
    
    /**
     * Easy getter method to return the completed student roster.
     * @return students: the roster in the form of a List<Student>
     */
    public List<Student> getStudents(){
        return students;
    }
    
}
