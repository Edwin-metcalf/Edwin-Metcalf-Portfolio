import java.util.Arrays;
import java.util.Objects;

/**
 * A course object.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Course implements Comparable<Course> {
    String dept;
    int courseNum;
    int section;
    String title;
    double credits;
    int maxEnrollment;
    String daysOfTheWeek;
    int startTime;
    int endTime;
    int duration;
    String timeString;
    String loc;
    String instructor;

    /**
     * Constructor takes in all values from the CSV.
     */
    public Course(String dept, int courseNum, int section, String title, double credits, int maxEnrollment, String daysOfTheWeek, int startTime,int endTime,int duration,String timeString, String loc, String instructor){
        //AFRS,100,51,Intro to Africana Studies,1,20,TR,810,885,75,0130PM-0245PM,BH 305,"Harriford, Diane"
        this.dept = dept;
        this.courseNum = courseNum;
        this.section = section;
        this.title = title;
        this.credits = credits;
        this.maxEnrollment = maxEnrollment;
        this.daysOfTheWeek = daysOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.timeString = timeString;
        this.loc = loc;
        this.instructor = instructor;
    }

    /**
     * Key is a string representation of department-courseNumber-section.
     * Example: CMPU-102-51
     */
    public String getKey(){
        return dept + "-" + courseNum + "-" + section;
    }


    /**
     * Returns true if key is the same.  (Department, Course Number, and Section).
     * 
     */


    public boolean equals(Object o){
        //TODO
        if (o instanceof Course) {
            Course other = (Course) o;
            if ((Objects.equals(this.dept, other.dept)) && (this.courseNum == other.courseNum)){
                return true;

            }
        }
        return false;
    }

    /**
     * String representation as it might appear on askBanner.
     */
    public String toString(){
        return getKey() + " " + title + "\t" + credits + "\t" + daysOfTheWeek + " " + timeString;
    }

    /**
     * Should sort classes by department, then course number, then section. (just like
     * askBanner)
     */
    public int compareTo(Course o){
        //TODO
        int deptComparison = this.dept.compareTo(o.dept);
        if (deptComparison != 0) {
            return deptComparison;
        }

        int courseNumComparison = Integer.compare(this.courseNum, o.courseNum);
        if (courseNumComparison != 0) {
            return courseNumComparison;
        }

        return Integer.compare(this.section, o.section);
    }

    /**
     * a method that checks times and days to determine whether they overlap.
     */
    public boolean conflictsWith(Course maybe){
        //TODO
        boolean timesOverlap = ((this.startTime < maybe.startTime) && (this.endTime < maybe.startTime)) || ((this.startTime > maybe.startTime)&& (this.startTime > maybe.endTime) );
        boolean daysOverlap = false;
        char [] thisDays = this.daysOfTheWeek.toCharArray();
        char [] maybeDays = maybe.daysOfTheWeek.toCharArray();


        for (int i = 0; i < thisDays.length; i++) {
            for(int j = 0; j < maybeDays.length; j++){
                if (thisDays[i] == maybeDays[j] ) {
                    daysOverlap = true;
                    break;
                }
            }
        }

        if (((daysOverlap) && (!timesOverlap)) || (this.equals(maybe))) {return true;} else{
        return false;}
    }
}
