package ohtu;

import java.util.Arrays;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;
    private String course;
    private Course courseInfo;

    public void setCourseInfo(Course courseInfo) {
        this.courseInfo = courseInfo;
    }

    public Course getCourseInfo() {
        return courseInfo;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getHours() {
        return hours;
    }

    public int[] getExercises() {
        return exercises;
    }

    public String getCourse() {
        return course;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    @Override
    public String toString() {
        String newLine = System.getProperty("line.separator");
        return "viikko " + week + ":" + newLine
                + "tehtyjä tehtäviä yhteensä " + exercises.length + "/" + courseInfo.getExercises()[week] + " aikaa kului " + hours + " tehdyt tehtävät: " + Arrays.toString(exercises);
    }
    
}