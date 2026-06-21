package Domain;
import Interface.Searchable;

// Course Class
public class Course implements Searchable{
    private int courseID;
    private String course_name;
    private String course_code;

    public Course(int courseID, String course_name, String course_code){
        this.courseID = courseID;
        this.course_name = course_name;
        this.course_code = course_code;
    }

    @Override
    public int getID() { return courseID; }

    public String getCourseName() { return course_name; }

    public String getCourseCode() { return course_code; }

    public void display(){
        System.out.println("");
        System.out.println("Course ID: " + courseID);
        System.out.println("Course Name: " + course_name);
        System.out.println("Course Code: " + course_code);
    }
}
