import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Tests {

    public Tests() {
    }

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testStudentLoader1() {
        String data = "Hector Tran,999248624,2023,1";
        //I dont know how to do this without a file so I am putting in a placeholder that gives an
        //error but should not really matter
        StudentLoader loader = new StudentLoader("Input CSV FILE");
        loader.parseAndLoadLine(data);
        List<Student> students = loader.getStudents();
        Student student = students.get(0);

        assertEquals("Hector Tran", student.name);
        assertEquals(999248624, student.idNum);
        assertEquals(2023, student.gradYear);
        assertEquals(1, student.drawNumber);
    }

    @Test
    public void testStudentLoader2() {
        String data = "Hector Tran,999248624,2023";

        StudentLoader loader = new StudentLoader("Input CSV FILE");


        try {
            loader.parseAndLoadLine(data);
            fail("Expected IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception was thrown

        }
    }

    @Test
    public void testCourseLoader1() {
        String data = "CMPU,145,51,Foundations/Computer Science,1,24,TRF,720,795,75,1200PM-0115PM,SP 309,Gomerschdat Anna";
        //I dont know how to do this without a file so I am putting in a placeholder that gives an
        //error but should not really matter
        CourseLoader loader = new CourseLoader("Input CSV FILE");
        loader.parseAndLoadLine(data);
        List<Course> Courses = loader.getCourses();
        Course course = Courses.get(0);

        assertEquals("CMPU", course.dept);
        assertEquals(75, course.duration);
        assertEquals(1.0, course.credits);
        assertEquals("SP 309", course.loc);

    }

    @Test
    public void testCourseLoader2()
    {
        String data = "CMPU,145,51,Foundations/Computer Science,1,24,TRF,720,795,75,1200PM-0115PM,SP 309";

        CourseLoader loader = new CourseLoader("Input CSV FILE");


        try {
            loader.parseAndLoadLine(data);
            fail("Expected IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception was thrown

        }
    }


    @Test
    public void testRequestLoader()
    {
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("Jeremy Jones", 999888777,2024,15));

        ArrayList<Course> courses = new ArrayList<Course>();
        courses.add(new Course("CMPU",145,51,"Foundations/Computer Science",1,24,"TRF",720,795,75,"1200PM-0115PM","SP 309","Gomerschdat Anna"));
        courses.add(new Course("HIST",301,51,"History of Computer Science",1,24,"TRF",720,795,75,"1200PM-0115PM","SW 101","Mr History"));

        HashMap<Integer,Student> studentMap = new HashMap<>();
        for (Student s : students) {
            studentMap.put(s.idNum,s);
        }

        HashMap<String,Course> courseMap = new HashMap<>();
        for (Course c : courses){
            courseMap.put(c.getKey(),c);

        }
        RequestLoader loader = new RequestLoader("no file have :(",students,courses);
        loader.mapStudents = studentMap;
        loader.mapCourses = courseMap;

        String data = "999888777,CMPU-145-51,HIST-301-51";

        loader.parseAndLoadLine(data);

        Student student = loader.mapStudents.get(999888777);

        System.out.println(student);
        System.out.print(courseMap);
        System.out.println(student.requests.get(0).courseNum);

        assertNotNull(student);
        assertEquals(student.requests.size(), 2);
        assertEquals(student.requests.get(0).title, "Foundations/Computer Science");
        assertEquals(student.requests.get(1).loc, "SW 101");
    }
    @Test public void testCourseTest() {
        Course c1 = new Course("CMPU",145,51,"Foundations/Computer Science",1,24,"TRF",720,795,75,"1200PM-0115PM","SP 309","Gomerschdat Anna");
        Course c2 = new Course("CMPU",145,51,"Foundations/Computer Science",1,24,"TRF",720,795,75,"1200PM-0115PM","SP 309","Gomerschdat Anna");
        Course c3 = new Course("CMPU",145,52,"Foundations/Computer Science",1,24,"TRF",720,795,75,"1200PM-0115PM","SP 309","Gomerschdat Anna");

        assertEquals(c1.equals(c2),true);
        assertEquals(c1.equals(c3),false);

        assertEquals(c1.compareTo(c2),0);
        assertEquals(c1.compareTo(c3),-1);

        assertEquals(c1.conflictsWith(c2), true);


    }
    @Test
    public void testStudentTest()
    {
        Student s1 = new Student("Jeremy Jones", 999888777,2024,15);
        Student s2 = new Student("Martin Macky", 999888666,2026,11);
        Student s3 = new Student("Timothy Turnip", 999888555,2026,1);


        int compare1 = s1.compareTo(s2);
        int compare2 = s1.compareTo(s3);
        int compare3 = s2.compareTo(s3);


        assertEquals(compare1,-1);
        assertEquals(compare2,-1);
        assertEquals(compare3, 1);
    }







    @AfterEach
    public void tearDown()
    {
    }
}
