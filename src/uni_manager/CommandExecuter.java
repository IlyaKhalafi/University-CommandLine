package uni_manager;

import java.util.ArrayList;
import java.util.Locale;

class CommandExecuter {

    private CommandExecuter() {}

    public static void AddStudent(String name, String family, int year) {
        new Student(name, family, year);
    }

    public static void ListStudent(String prefix) {
        Student.getStudents().forEach((a) -> {
            if( prefix == null || a.getId().startsWith(prefix) )
                System.out.println(a);
        });
    }

    public static void TuitionStudent(String number, String sem) {
        Student target = Student.FindStudent(number);
        if( target != null )
            System.out.println(target.calcMoneyChange(sem));
        else
            System.out.println("student with student number " + number + " not found.");
    }

    public static void AddProf(String username, String name, String family) {
        Professor p = Professor.FindProfessor(username);
        if( p != null )
            System.out.println("prof with username " + username + " exists.");
        else {
            new Professor(username, name, family);
        }
    }

    public static void SalaryProf(String username, String sems) {
        Professor target = Professor.FindProfessor(username);
        if( target == null )
            System.out.println("prof with username " + username + " not found.");
        else if( sems == null )
            System.out.println(target.calcMoneyChange(null));
        else if( sems.length() == 0 )
            System.out.println(0);
        else {
            int sum_salary = 0;
            for (String sem : sems.split(" "))
                sum_salary += target.calcMoneyChange(sem);
            System.out.println(sum_salary);
        }
    }

    public static void ListProf() {
        Professor.getProfessors().forEach( (a) -> System.out.println(a) );
    }

    public static void Profit(String sem) {
        int profit = 0;
        for( Student student : Student.getStudents() )
            profit += student.calcMoneyChange(sem);
        for( Professor professor : Professor.getProfessors() )
            profit -= professor.calcMoneyChange(sem);
        System.out.println(profit);
    }

    public static void AddCourse(String name, String username, String sem, int weight) {
        if( Professor.FindProfessor(username) == null ) {
            System.out.println("prof with username " + username + " not found.");
            return;
        }
        Course target = Course.FindCourse(name, username, sem);
        if( target != null )
            System.out.println(String.format("course %s:%s:%s exists.", name, username, sem));
        else
            Professor.FindProfessor(username).AddCourse(new Course(name, username, sem, weight));
    }

    public static void CourseList(String username, String sem) {
        Course.getCourses().forEach( (a) -> {
            if( (username == null || a.getUsername().equals(username)) && (sem == null || a.getSem().equals(sem)) )
                System.out.println(a);
        } );
    }

    public static void AssignCourse(String name, String username, String sem, String number) {
        if( Student.FindStudent(number) == null ) System.out.println("student with student number " + number + " not found.");
        else if( Professor.FindProfessor(username) == null ) System.out.println("prof with username " + username + " not found.");
        else if( Course.FindCourse(name, username, sem) == null ) return;
        else {
            Student target = Student.FindStudent(number);
            Course c = Course.FindCourse(name, username, sem);
            if( target.getAverage(sem) < 12 && target.getWeight(sem) + c.getWeight() > 12  )
                System.out.println("student " + number + " has course limit 12.");
            if( target.getAverage(sem) < 17 && target.getWeight(sem) + c.getWeight() > 20  )
                System.out.println("student " + number + " has course limit 20.");
            else {
                target.AddUnit(c);
                c.increaseCount();
            }
        }
    }

    public static void ShowCourse(String number, String sem) {
        Student target = Student.FindStudent(number);
        if( target == null )
            System.out.println("student with student number " + number + " not found.");
        else
            target.FilterUnits(null, null, sem).forEach( (a) -> System.out.println(a.getCourse()) );
    }

    public static void RemoveCourse(String name, String username, String sem, String number) {
        Student target = Student.FindStudent(number);
        if (target == null)
            System.out.println("student with student number " + number + " not found.");
        else {
            ArrayList<Unit> units = target.FilterUnits(name, username, sem);
            if( units.size() == 0 )
                System.out.println(String.format("student %s doesn't have %s:%s:%s.", number, name, username, sem));
            else
                units.forEach( (a) -> target.RemoveCourse(a) );
        }
    }

    public static void AssignGrade(String name, String username, String sem, String number, double grade) {
        Student target = Student.FindStudent(number);
        if (target == null)
            System.out.println("student with student number " + number + " not found.");
        else {
            ArrayList<Unit> units = target.FilterUnits(name, username, sem);
            if( units.size() == 0 )
                System.out.println(String.format("student %s doesn't have %s:%s:%s.", number, name, username, sem));
            else
                units.get(0).assign(grade);
        }
    }

    public static void Average(String number, String sem) {
        Student target = Student.FindStudent(number);
        if( target != null )
            System.out.println(String.format(Locale.US, "%.2f", Math.floor(target.getAverage(sem) * 100) / 100));
    }

    public static void Ranks(String sem) {
        double[] avgs = new double[3];
        Student[] ranks = new Student[3];
        for( Student student : Student.getStudents() ) {
            if( student.hasGrade(sem) == false )
                continue;
            Student move = student;
            for( int i = 0; i < 3 && move != null; i++)
                if( ranks[i] == null || avgs[i] < move.getAverage(sem) ) {
                    avgs[i] = move.getAverage(sem);
                    Student temp = move;
                    move = ranks[i];
                    ranks[i] = temp;
                }
        }
        for(int i = 0; i < 3 && ranks[i] != null; i++)
            System.out.println(String.format(Locale.US, "%d. %s %s %s %.2f", i+1, ranks[i].getId(), ranks[i].getName(), ranks[i].getFamily(), Math.floor(avgs[i] * 100) / 100));
    }
}
