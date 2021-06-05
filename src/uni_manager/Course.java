package uni_manager;

import java.util.ArrayList;

class Course {
    private String name, username, sem;  // name is also called id in this class
    private int weight, student_count;
    private static ArrayList<Course> courses = new ArrayList<>();

    public Course(String name, String username, String sem, int weight) {
        this.name = name;
        this.username = username;
        this.sem = sem;
        this.weight = weight;
        courses.add(this);
    }

    public static Course FindCourse(String name, String username, String sem) {
        for (Course course : courses)
            if( (name == null || course.getId().equals(name)) && (username == null || course.getUsername().equals(username)) && (course.getSem() == null || course.getSem().equals(sem)) )
                return course;
        return null;
    }

    public static ArrayList<Course> getCourses() { return courses; }

    public String getId() { return this.name; }

    public String getSem() { return this.sem; }

    public void increaseCount() {
        this.student_count += 1;
    }

    public String getUsername() { return this.username; }

    public int getWeight() { return this.weight; }

    public int getStudentCount() { return this.student_count; }

    public String toString() {
        return this.getId() + " " + this.getUsername() + " " + this.getSem() + " " + this.getStudentCount();
    }
}
