package ohtu;

public class Course {
    private int[] exercises;
    private String name;
    private String fullName;

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public int[] getExercises() {
        return exercises;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
