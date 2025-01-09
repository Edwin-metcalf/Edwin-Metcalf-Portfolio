import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Reads in a csv file from askbanner of courses and turns them into a List of Course objects. *
 * @author (Edwin Metcalf)
 * @version (1.0)
 */
public class CourseLoader extends DataLoader
{
    private List<Course> courses = new ArrayList<Course>();

    /**
     * Constructor calls the load(file) method in abstract parent class.
     * 
     * @param file: the path to the file.
     */
    CourseLoader(String file){
        load(file);
    }

    /**
     * Parse a single line of CSV in the form:
     * dept, courseNum, section, name, credits, maxEnrol, days, startTime, EndTime, Duration, String time, room, professor
     * CMPU,145,51,Foundations/Computer Science,1,24,TRF,720,795,75,1200PM-0115PM,SP 309,Gomerschdat Anna
     * <p>
     * Method should create a new Course Object and add it to 
     * the students instance variable.
     * 
     * @param data: a single line from the csv file.
     */
    public void parseAndLoadLine(String data){
        //TODO
        String [] parts = data.split(",");
        if (parts.length != 13){
            throw new IndexOutOfBoundsException("index is not 13 it is:" + parts.length);
        }

        String dept = parts[0];

        int courseNum = Integer.parseInt(parts[1]);

        int section = Integer.parseInt(parts[2]);

        String title = parts[3];

        double credits = Double.parseDouble(parts[4]);

        int maxEnrollment = Integer.parseInt(parts[5]);

        String daysOfTheWeek = parts[6];

        int startTime = Integer.parseInt(parts[7]);

        int endTime = Integer.parseInt(parts[8]);

        int duration = Integer.parseInt(parts[9]);

        String timeString = parts[10];

        String loc = parts[11];

        String instructor = parts[12];

        Course course = new Course(dept,courseNum,section,title,credits,maxEnrollment,daysOfTheWeek,startTime,endTime,duration,timeString,loc,instructor);
        courses.add(course);

        //System.out.println("Please implement parseAndLoadLine in CourseLoader.");

    }

    /**
     * Easy getter method to return the completed student roster.
     * @return students: the roster in the form of a List<Course>
     */
    public List<Course> getCourses(){
        return courses;
    }
}
