import java.util.List;
import java.util.ArrayList;

/**
 * A student object.
 *
 * @author (Edwin Metcalf)
 * @version (1.0)
 */
public class Student implements Comparable<Student>{
    String name;
    int idNum;
    int gradYear;
    int drawNumber;
    public List<Course> requests;
    public List<Course> schedule;

    /**
     * @param name: string the student's first and last name;
     * @param idNum: int the student's 999 number.
     * @param gradYear: 4 digit graduation year.
     * @param drawNumber: the draw number determines the student's place in the algorithm.
     */
    public Student(String name, int idNum, int gradYear,int drawNumber){
        this.name = name;
        this.idNum = idNum;
        this.gradYear = gradYear;
        this.drawNumber = drawNumber;
        this.requests = new ArrayList<>();
        this.schedule = new ArrayList<>();
    }
    
    /**
     * Returns true if idNumbers are the same;
     * @param o: any possible object.
     * 
     * @return boolean: true if idNumbers are the same.
     */
   public boolean equals(Object o){
        if((o instanceof Student)){
            return idNum == ((Student)(o)).idNum;
        }
        return false;
    }

    /**
     * ToString returns a string representation including 
     * name, graduation year and draw number.
     */
    public String toString(){
        return name + " " + gradYear + " " + drawNumber;
    }

    /**
     * Write a compareTo that sorts the student by draw number and
     * class year.  
     * The first person should be a 4th year with draw number 1.
     * The last person should be a 1st year with the largest draw number.
     * All 4th years come before all 3rd years, etc.
     * 
     * @return retval: 
     *    -1 if the first thing comes first,
     *    0 if they are equal
     *    1 if the second thing comes first.
     */
    @Override
    public int compareTo(Student s){
        //TODO
        if (this.gradYear < s.gradYear) {
            return -1;
        } else if (this.gradYear > s.gradYear) {
            return 1;
        } else {
            if (this.drawNumber < s.drawNumber) {
                return -1;
            } else if (this.drawNumber > s.drawNumber) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    
    //Optional but suggested:
    
     /**
      * Check to see if the student is registered for any section of a course.
      * @param maybe: Course.  The potential course to register for.
      *
      * @return boolean: true if the student is registered for any section of the course.
      */
     public boolean isRegisteredFor(Course maybe) {
         //TODO
        if (this.schedule.equals(maybe)){return true;} else {
        return false;}

     }
     //Wonder if I have to use the is equals method we wrote


     /**
      * @return the total registered credits (limit is 4.5)
      */
    public double totalRegisteredCredits(){
        //TODO
        double totalCredits = 0.0;
        for (int i = 0; i < this.schedule.size();i++){
            totalCredits += this.schedule.get(i).credits;
        }
        return totalCredits;
     }

     /**
      * @param maybe: Course the potential course
      *
      * @return true if the student already has something at that time.
      */
     public boolean hasAConflict(Course maybe){
         //TODO
         boolean hasConflict = false;
         for(int i = 0; i < this.schedule.size(); i++) {
             hasConflict = this.schedule.get(i).conflictsWith(maybe);
             if (hasConflict == true){
                 return hasConflict;
             }
         }
        return hasConflict;
     }

}
