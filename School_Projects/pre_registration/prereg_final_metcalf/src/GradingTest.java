
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.*;

/**
 * The test class GradingTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GradingTest
{
    /**
     * Default constructor for test class GradingTest
     */
    public GradingTest()
    {
    }




    @Test
    public void studentLoaderCorrectTest(){
        StudentLoader sl = new StudentLoader("data/shortRoster.csv");
        List<Student> students = sl.getStudents();

        assertEquals(12,students.size());
        Student zero = students.get(0);
        assertEquals("Hector Tran", zero.name);
    }


    @Test
    public void courseTesterCorrect(){
        CourseLoader cl = new CourseLoader("data/course_shortRequests.csv");
        List<Course> courses = cl.getCourses();
        assertEquals(101, courses.size());
    }

    @Test
    public void requestTester(){
        StudentLoader sl = new StudentLoader("data/shortRoster.csv");
        List<Student> students = sl.getStudents();

        CourseLoader cl = new CourseLoader("data/course_shortRequests.csv");
        List<Course> courses = cl.getCourses();

        RequestLoader rl = new RequestLoader("data/shortRequests.csv", students, courses);
        students = rl.mapStudentRequests();

        for(Student s : students){
            //Some have duplicate classes, and it is okay to put them in or not.
            assertTrue(s.requests.size() >= 6);
        }
    }

    //I can't possibly automate incorrect tests.  That will be manually reviewed.

    @Test
    public void sortStudentsTest(){
        List<Student> myList = new ArrayList<Student>();
        Student last = new Student("Mark Peters",999250876,2026,3);
        Student first = new Student("Hector Tran",999248624,2023,1);
        myList.add(last);
        myList.add(new Student("Chace Sanford",999248625,2023,2));
        myList.add(first);
        myList.add(new Student("Cade Young",999248626,2023,3));
        myList.add(new Student("Desirae Welch",999249374,2024,1));
        myList.add(new Student("Alina Kidd",999249375,2024,2));
        myList.add(new Student("Randall Reid",999249376,2024,3));
        myList.add(new Student("Conrad Mckenzie",999250124,2025,1));
        myList.add(new Student("Shania Kent",999250125,2025,2));
        myList.add(new Student("Stanley Young",999250126,2025,3));
        myList.add(new Student("Savion Durham",999250875,2026,2));
        myList.add(new Student("Aiden Zamora",999250874,2026,1));

        Collections.sort(myList);
        assertEquals(first, myList.get(0), "if this is Mark Peters, your compareTo is sorting in reverseOrder");
        assertEquals(last, myList.get(myList.size()-1));
    }

    @Test
    public void courseEqualsTest(){
        Course first = new Course("AMST", 160, 51, "viz art and storytelling",1,17,"WF",0,0,0, "","","");
        Course art10656 = new Course("ART",106,56,"Intro to Hist of Art/Architect",1,24,"MWF",720,770,50,"1200PM-1250PM","","");
        Course art106562 = new Course("ART",106,56,"Intro to Hist of Art/Architect",1,24,"MWF",720,770,50,"1200PM-1250PM","","");
        Course art10658 = new Course("ART",106,58,"Intro to Hist of Art/Architect",1,24,"MWF",720,770,50,"1200PM-1250PM","","");
        Course art10354 = new Course("ART",103,54,"Drawing I: Visual Language",1,24,"TR", 0,0,120, "0310PM-0510PM","","");

        assertNotEquals(art10354,art10656);
        assertNotEquals(art10656,art10658);
        assertEquals(art106562,art10656);
    }
    @Test
    public void sortCoursesTest(){
        CourseLoader cl = new CourseLoader("data/course_shortRequests.csv");
        List<Course> courses = cl.getCourses();
        Collections.sort(courses);
        // for(int i=0; i<10; i++)
            // System.out.println(courses.get(i));

        Course first = new Course("AMST", 160, 51, "viz art and storytelling",1,17,"WF",0,0,0, "","","");
        Course art10656 = new Course("ART",106,56,"Intro to Hist of Art/Architect",1,24,"MWF",720,770,50,"1200PM-1250PM","","");
        Course art10658 = new Course("ART",106,58,"Intro to Hist of Art/Architect",1,24,"MWF",720,770,50,"1200PM-1250PM","","");
        Course art10354 = new Course("ART",103,54,"Drawing I: Visual Language",1,24,"TR", 0,0,120, "0310PM-0510PM","","");

        assertEquals(101, courses.size());
        assertEquals(first, courses.get(0), "if this is 'WFQS-381-51 How Queer is That?' , your compareTo is sorting in reverse order");
        assertTrue(courses.indexOf(first) < courses.indexOf(art10656));
        assertTrue(courses.indexOf(art10354) < courses.indexOf(art10658));
        assertTrue(courses.indexOf(art10656) < courses.indexOf(art10658));
    }

    @Test
    public void conflictsWithTest(){
        Course c1 = new Course("CMPU",102,51,"",1,17,"TRF",630,705,75,"1030AM-1145AM","","");
        Course c2 = new Course("MATH",221,52,"",1,50,"MWF",600,650,50,"1000AM-1100PM","","");
        Course c3 = new Course("MATH",221,51,"",1,50,"MWF",660,710,50,"1100AM-1150PM","","");
        Course c4 = new Course("CMPU",241,51,"",1,17,"MW",630,705,75,"1030AM-1145AM","","");


        assertEquals(true, c1.conflictsWith(c2),"Overlaps on Friday c2 starts first");
        assertEquals(true, c1.conflictsWith(c3),"Overlaps on Friday c1 starts first");
        assertEquals(true, c4.conflictsWith(c4),"Should conflict with itself");
        assertEquals(false, c2.conflictsWith(c3),"10 and 11 50 minute classes do not overlap");
        assertEquals(false, c1.conflictsWith(c4), "Same time different days");
    }


}
