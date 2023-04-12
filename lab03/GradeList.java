package lab03;

import java.util.ArrayList;

public class GradeList {
    private ArrayList<Double> listOfGrades = new ArrayList<>();

    public GradeList() {
    }

    public void addGrade(double grade) {
        this.listOfGrades.add(grade);
    }

    public double getAvgGrade() {
        if (this.listOfGrades.isEmpty()) {
            return -1;
        }

        double sumOfGrades = 0;
        for (double grade : this.listOfGrades) {
            sumOfGrades += grade;
        }

        double avgGrade = sumOfGrades / this.listOfGrades.size();

        return avgGrade;
    }

    public double getMinGrade() {
        if (this.listOfGrades.isEmpty()) {
            return -1;
        }

        double minGrade = this.listOfGrades.get(0);
        for (double grade : this.listOfGrades) {
            if (minGrade < grade) {
                minGrade = grade;
            }
        }

        return minGrade;
    }

    public double getMaxGrade() {
        if (this.listOfGrades.isEmpty()) {
            return -1;
        }

        double maxGrade = this.listOfGrades.get(0);
        for (double grade : this.listOfGrades) {
            if (maxGrade > grade) {
                maxGrade = grade;
            }
        }

        return maxGrade;
    }
}
