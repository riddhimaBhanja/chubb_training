package gradetracker;

public class Topper extends Student {
    private double bonus;

    public Topper(String name, double bonus) {
        super(name);
        this.bonus = bonus;
    }

    @Override
    public double getAverage() {
        return super.getAverage() + bonus;
    }
}
