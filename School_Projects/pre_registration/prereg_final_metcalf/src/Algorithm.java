import java.util.*;

/**
 * This will hold all the logic for our preregistration algorithm.  To be done in Part 2.
 *
 * @author (Edwin Metcalf)
 * @version (2.0)
 */
public class Algorithm
{
    HashMap<Integer,PriorityQueue<Student>> theQueue = new HashMap<>();
    HashMap<Integer,Stack<Student>> theStack = new HashMap<>();
    HashMap<Course,Integer> enrollment;
    List<Student> mapStudents;


    /**
     * this consructor is suposed to take in the students and the classes and their respective enrollment
     * then it creats the first hashmap that is sorted by grad year and creates priority ques in them
     * @param mapStudents
     * @param enrollment
     */
    public Algorithm(List<Student> mapStudents,  HashMap<Course,Integer> enrollment){
        Collections.sort(mapStudents);
        for (Student student : mapStudents) {
            int gradYear = student.gradYear;
            PriorityQueue<Student> queue = theQueue.get(gradYear);

            if(queue == null){
                queue = new PriorityQueue<>();
                theQueue.put(gradYear,queue);
            }
            queue.add(student);
        }
        for (Integer gradYear : theQueue.keySet()){
            Stack<Student> emptyStack = new Stack<>();
            theStack.put(gradYear,emptyStack);
        }
        this.enrollment = enrollment;
        this.mapStudents = mapStudents;
    }

    /**
     * process students takes a student and proccess their requests until it finds a class to add for them
     * @param student
     */
    public void processRequests(Student student) {
        boolean classAdded = false;
        for (int i = 0; i < student.requests.size(); i++) {
            Course currentCourse = student.requests.get(i);

            if (currentCourse == null){student.requests.remove(i);}
            else if (((student.totalRegisteredCredits() + currentCourse.credits) <= 4.5) && (enrollment.get(currentCourse) < currentCourse.maxEnrollment) && (!student.isRegisteredFor(currentCourse)) && (!student.hasAConflict(currentCourse))) {
                student.schedule.add(currentCourse);
                enrollment.put(currentCourse, enrollment.getOrDefault(currentCourse,0) + 1);
                classAdded = true;
            }
            if (classAdded){
                return;
            }
        }
    }

    /**
     * this method takes a boolean and either goes through the queue and adds classes to students or goes through the
     * stack and adds classes that way. When it is iterating through one it is adding the students to the other
     * @param forwards
     */
    public void processPass(boolean forwards){
        if (forwards) {
            theQueue.forEach((gradYear, queue)-> {
                while (!queue.isEmpty()) {
                    Student student = queue.poll();
                    processRequests(student);
                    theStack.get(gradYear).add(student);
                }
            });
        } else {
            theStack.forEach((gradYear, stack)-> {
                while(!stack.isEmpty()) {
                    Student student = stack.pop();
                    processRequests(student);
                    theQueue.get(gradYear).add(student);
                }

            });
        }
    }


    public void run(){
        // TODO
        // You may use whatever helper methods and data structures you think would be
        // appropriate.
        
        /* Description of the algorithm from the Registrar's website:
         * 
         * Entry into a section is determined by the combination of your class year, the priority you give each section, and your draw number.
         * Seniors� requests are processed first followed sequentially by juniors�, sophomores�, and first-years requests.
         * Your requests are considered in the order that you list them on the registration screen, with the first item having the highest priority. If one of your requests cannot be filled, then the next item in your list will be considered instead.
         * For your class year, your draw number determines when one of your requests is considered. Your top request is considered immediately after the top requests of all of the students in your class with lower draw numbers. As mentioned above, if your top request cannot be granted you will be enrolled in the first request on your list that can be.
         *
         * In a second pass through the requests from your class, your top request among your remaining requests will be considered immediately before all of the students in your class with lower draw numbers. That is, the draw numbers work in reverse compared to the first pass. The remaining passes through the requests from your class continue the pattern of the first two passes, switching direction through the draw numbers on each pass.
         * You may list multiple sections of the same course among your requests but you will be enrolled only in the first one on your list that is available. You will not be enrolled in multiple sections of the same course.
         * You may also list sections of different courses that meet at the same time but you will be enrolled only in the first one on your list that is available. You will not be enrolled in sections with time conflicts.
         * 
         */
        int numPasses = 7;
        for (int i = 0; i < numPasses; i++){
            processPass(i % 2 == 0);
        }

    }

   
    public void printEnrollment(){
        //TODO
        //Print the toString of the student, followed by their schedule (using course toString).
        /*
         * Hector Tran 2023 1
         * CMPU-145-51 Foundations/Computer Science	1.0	TRF 1200PM-0115PM
         * EDUC-361-51 Sem: Math/Science/Elem Classrm	1.0	R 0310PM-0610PM
         * ECON-235-51 Sports Economics	1.0	TR 1030AM-1145AM
         * PHED-105-51 Foundations of Wellness	0.5	TR 0900AM-1015AM
         * --------------------
         * Chace Sanford 2023 2
         * GNCS-355-51 Childhood/Childrn 19C Britain	1.0	R 0310PM-0510PM
         * ART-318-51 Building the Museum	1.0	T 0100PM-0300PM
         * CHEM-352-51 Phys Chem-Molec Structr	1.0	MW 1030AM-1145AM
         * INTL-109-51 A Lexicon of Forced Migration	1.0	TR 1030AM-1145AM
         * --------------------
         * etc...
         */
        for(int i = 0; i < mapStudents.size();i++) {
            Student currentStudent = mapStudents.get(i);
            System.out.println(currentStudent);
            for(int j = 0; j < currentStudent.schedule.size(); j++){
                Course currentCourse = currentStudent.schedule.get(j);
                System.out.println(currentCourse);
            }
            System.out.println("--------------------------");
        }


    }
}
