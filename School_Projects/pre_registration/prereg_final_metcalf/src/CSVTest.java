import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
public class CSVTest {
    public CSVTest() {
    }

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void shortRequestsTest() {
        HashMap<Course,Integer> enrollment = new HashMap<>();
        StudentLoader sl = new StudentLoader("data/shortRoster.csv");
        List<Student> students = sl.getStudents();

        CourseLoader cl = new CourseLoader("data/course_shortRequests.csv");
        List<Course> courses = cl.getCourses();

        RequestLoader rl = new RequestLoader("data/shortRequests.csv", students, courses);
        students = rl.mapStudentRequests();

        for(Course c : courses){
            enrollment.put(c,0); //The number of students currently enrolled in each Course.
        }

        //The fully loaded students, associated with all of their requests, and the list of courses
        //initialized to 0 students enrolled.
        Algorithm a = new Algorithm(students,enrollment);
        a.run();
        a.printEnrollment();
        System.out.println(a.mapStudents);
        System.out.println(a.mapStudents.get(0).schedule);
        System.out.println(a.mapStudents.get(0).requests);
        System.out.println(a.enrollment);



    }

    @AfterEach
    public void tearDown()
    {
    }
}
