import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
public class Test2 {
    public Test2() {
    }

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void algorithmConstructorTest() {
        HashMap<Integer,PriorityQueue<Student>> theQueue = new HashMap<>();
        List<Student> students = new ArrayList<>();
        students.add(new Student("Pika chu", 999888777, 2023, 10));
        students.add(new Student("Croa Gunk", 999777888, 2024, 20));
        students.add(new Student("Mud Kip", 999777666, 2024, 700));

        HashMap<Course, Integer> enrollment = new HashMap<>();
        enrollment.put(new Course("CMPU",145,51,"Foundations/Computer Science",1,24,"TRF",720,795,75,"1200PM-0115PM","SP 309","Gomerschdat Anna"),25);

        Algorithm algorithm = new Algorithm(students,enrollment);

        assertEquals(algorithm.theQueue.size(),2);
        assertEquals(algorithm.theQueue.get(2024).size(),2);
        assertEquals(algorithm.theQueue.get(2024).peek().name,"Croa Gunk");

        assertEquals(algorithm.theStack.size(),2);
        assertEquals(algorithm.theStack.get(2024).size(),0);
    }
    @Test
    public void processRequestsTest() {
        List<Student> students = new ArrayList<>();

        Student student = new Student("Comb bee", 999888777, 2023, 10);
        Course course1 = new Course("CMPU",145,51,"Foundations/Computer Science",1,24,"TRF",720,795,75,"1200PM-0115PM","SP 309","Gomerschdat Anna");
        Course course2 = new Course("CMPU",102,51,"Computer Science 102",1,24,"MW",720,795,75,"1200PM-0115PM","SP 309","Lemieszewski Peter");
        Course course3 = new Course("HIST",268,51,"VietNam War",1,24,"TRF",720,795,75,"1200PM-0115PM","SW 201","Bob B");
        Course course4 = new Course("HIST",268,51,"VietNam War",1,24,"TRF",720,795,75,"1200PM-0115PM","SW 201","Bob B");
        Course course5 = new Course("HIST",201,51," Histoy of the world",1,1,"MW",640,715,75,"1200PM-0115PM","SW 201","Bob B");

        student.requests.add(course1);
        student.requests.add(course2);
        student.requests.add(course3);
        student.requests.add(course4);
        student.requests.add(course5);

        HashMap<Course, Integer> enrollment = new HashMap<>();
        enrollment.put(course1,0);
        enrollment.put(course2,0);
        enrollment.put(course3,0);
        enrollment.put(course4,0);
        enrollment.put(course5,1);
        students.add(student);

        Algorithm algo = new Algorithm(students,enrollment);
        algo.processRequests(student);
        algo.printEnrollment();


        assertEquals(2,student.schedule.size());



    }
    @Test
    public void processPassTest() {
        List<Student> students = new ArrayList<>();

        Student student = new Student("Comb bee", 999888777, 2023, 10);
        Course course1 = new Course("CMPU",145,51,"Foundations/Computer Science",1,24,"TRF",720,795,75,"1200PM-0115PM","SP 309","Gomerschdat Anna");
        Course course2 = new Course("CMPU",102,51,"Computer Science 102",1,24,"MW",720,795,75,"1200PM-0115PM","SP 309","Lemieszewski Peter");
        Course course3 = new Course("HIST",268,51,"VietNam War",1,24,"TRF",720,795,75,"1200PM-0115PM","SW 201","Bob B");
        Course course4 = new Course("HIST",268,51,"VietNam War",1,24,"TRF",720,795,75,"1200PM-0115PM","SW 201","Bob B");

        student.requests.add(course1);
        student.requests.add(course2);
        student.requests.add(course3);
        student.requests.add(course4);

        HashMap<Course, Integer> enrollment = new HashMap<>();
        enrollment.put(course1,24);
        enrollment.put(course2,24);
        enrollment.put(course3,24);
        enrollment.put(course4,24);
        students.add(student);

        Algorithm algo = new Algorithm(students,enrollment);
        algo.processPass(true);
        PriorityQueue<Student> queue = algo.theQueue.get(2023);
        assertTrue(queue.isEmpty());
        assertEquals(algo.theStack.get(2023).peek(),student);
    }
    @Test
    public void printEnrollmentTest() {
        List<Student> students = new ArrayList<>();

        Student student = new Student("Comb bee", 999888777, 2023, 10);
        Course course1 = new Course("CMPU",145,51,"Foundations/Computer Science",1,24,"TRF",720,795,75,"1200PM-0115PM","SP 309","Gomerschdat Anna");
        Course course2 = new Course("CMPU",102,51,"Computer Science 102",1,24,"MW",720,795,75,"1200PM-0115PM","SP 309","Lemieszewski Peter");
        Course course3 = new Course("HIST",268,51,"VietNam War",1,24,"TRF",720,795,75,"1200PM-0115PM","SW 201","Bob B");
        Course course4 = new Course("HIST",268,51,"VietNam War",1,24,"TRF",720,795,75,"1200PM-0115PM","SW 201","Bob B");

        student.requests.add(course1);
        student.requests.add(course2);
        student.requests.add(course3);
        student.requests.add(course4);

        HashMap<Course, Integer> enrollment = new HashMap<>();
        enrollment.put(course1,24);
        enrollment.put(course2,24);
        enrollment.put(course3,24);
        enrollment.put(course4,24);
        students.add(student);

        Algorithm algo = new Algorithm(students,enrollment);
        algo.run();
        algo.printEnrollment();


    }

    @AfterEach
    public void tearDown()
    {
    }
}
