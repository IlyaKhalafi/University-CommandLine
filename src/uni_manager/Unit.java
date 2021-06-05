package uni_manager;

class Unit {
    private double grade;
    private Course course;
    private Boolean assigned;

    public Unit(Course course) {
        this.course = course;
        this.grade = 0d;
        this.assigned = false;
    }

    public void assign(double grade) {
        this.grade = grade;
        this.assigned = true;
    }

    public double getGrade() {
        return this.grade;
    }

    public Course getCourse() {
        return this.course;
    }

    public Boolean getSituation() {
        return this.assigned;
    }
}