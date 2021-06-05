package uni_manager;

import java.util.ArrayList;

class Student extends Person {
    private static ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Unit> units = new ArrayList<Unit>();

    public Student(String name, String family, int year) {
        super(name, family, getNewId(year));
        System.out.println(this.getId());
        students.add(this);
    }

    public double getAverage(String sem) {
        double sum = 0d;
        double weights = 0;

        for( Unit unit : units ) {
            if (unit.getSituation() == true && unit.getCourse().getSem().equals(sem)) {
                sum += unit.getGrade() * unit.getCourse().getWeight();
                weights += unit.getCourse().getWeight();
            }
        }
        if( weights == 0 ) return 0;
        return sum/weights;
    }

    public int getWeight(String sem) {
        int weight = 0;
        for( Unit unit : units )
            if( unit.getCourse().getSem().equals(sem) )
                weight += unit.getCourse().getWeight();
        return weight;
    }

    public void AddUnit(Course course) {
        this.units.add(new Unit(course));
    }

    public int calcMoneyChange(String sem) {
        int tuition = 0;
        for( Unit unit : units )
            if( sem == null || unit.getCourse().getSem().equals(sem) )
                tuition += unit.getCourse().getWeight() * 50;
        return tuition;
    }

    public static Student FindStudent(String id) {
        for( Student student : students )
            if( student.getId().equals(id) )
                return student;
        return null;
    }

    public static ArrayList<Student> getStudents() { return students; }

    public ArrayList<Unit> FilterUnits(String name, String username, String sem) {
        ArrayList<Unit> filtered = new ArrayList<>();
        for( Unit unit : units )
            if( (name == null || name.equals(unit.getCourse().getId())) && (username == null || username.equals(unit.getCourse().getUsername())) && (sem == null || sem.equals(unit.getCourse().getSem())) )
                filtered.add(unit);
        return filtered;
    }

    public void RemoveCourse(Unit unit) {
        this.units.remove(unit);
    }

    public static String getNewId(int year) {
        int place = 1;
        for( Student student : students )
            if( student.getId().equals(String.valueOf( year*1000 + place )) )
                place++;
        return String.valueOf( year*1000 + place );
    }

    public Boolean hasGrade(String sem) {
        for( Unit unit : units )
            if( unit.getCourse().getSem().equals(sem) && unit.getSituation() == true )
                return true;
        return false;
    }

    public String toString() {
        return this.getName() + " " + this.getFamily() + " " + this.getId();
    }
}

class Professor extends Person {
    private static ArrayList<Professor> professors = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();

    public Professor(String username, String name, String family) {
        super(name, family, username);
        professors.add(this);
    }

    public static Professor FindProfessor(String id) {
        for( Professor professor : professors )
            if( professor.getId().equals(id) )
                return professor;
        return null;
    }

    public int calcMoneyChange(String sem) {
        int salary = 0;
        for( Course course : this.courses )
            if( sem == null || course.getSem().equals(sem) )
                salary += course.getWeight() * 800;
        return salary;
    }

    public void AddCourse(Course course) {
        this.courses.add(course);
    }

    public static ArrayList<Professor> getProfessors() { return professors; }

    public String toString() {
        return this.getId() + " " + this.getName() + " " + this.getFamily();
    }
}
