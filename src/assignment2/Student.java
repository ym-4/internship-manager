 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.io.Serializable;

/**
 *
 * @author User
 * 
 * Student 1: 
 * Class: DIT2B21
 * Student ID: 2537159
 * Name: PAY YU MI
 * 
 * Student 2: 
 * Class: DIT2B21
 * Student ID: 2523398
 * Name: TAN MU LING
 * 
 */
public class Student implements Serializable {
    // ---------------------------------------------
    // Instance Variables
    // ---------------------------------------------
    private String studentID; 
    private String name;
    private String courseOfStudy;
    private Company assignedCompany; 
    private String internshipRole;

    // Performance rating is an integer between 0 - 100 (inclusive)
    private int[] performanceRating;

    // To calculate overall performance rating
    private int[] weeksAssessed;

    
    // ---------------------------------------------
    // Constructor
    // ---------------------------------------------
    public Student(String studentID, String name, String courseOfStudy, Company assignedCompany, String internshipRole, 
                    int[] performanceRating, int[] weeksAssessed) {
        
        this.studentID = studentID;
        this.name = name; 
        this.courseOfStudy = courseOfStudy;
        this.assignedCompany = assignedCompany;
        this.internshipRole = internshipRole;
        // IMPORTANT: LENGTH OF performanceRating and weeksAssessed should be the same
        // Before calling: Check that all the performance ratings in the array are within range (0 - 100 inclusive)
        this.performanceRating = performanceRating;    
        // Before calling: Check that all the weeks assessed in the array are more than 0 (>0) 
        this.weeksAssessed = weeksAssessed;
    }
    
    // ---------------------------------------------
    // Get methods
    // ---------------------------------------------    
    
    public String getStudentID() {
        return this.studentID;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getCourseOfStudy() {
        return this.courseOfStudy;
    }
    
    public Company getAssignedCompany() {
        return this.assignedCompany;
    }
    
    public String getInternshipRole() {
        return this.internshipRole;
    }
    
    public int[] getPerformanceRating() {
        return this.performanceRating;
    }
    
    public int[] getWeeksAssessed() {
        return this.weeksAssessed;
    }
    
    // ---------------------------------------------
    // Set methods
    // ---------------------------------------------
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
        
    public void setName(String name) {
        this.name = name;
    }
    
    public void setCourseOfStudy(String courseOfStudy) {
        this.courseOfStudy = courseOfStudy;
    }
    
    public void setAssignedCompany(Company assignedCompany) {
        this.assignedCompany = assignedCompany; 
    }
    
    public void setInternshipRole(String internshipRole) {
        this.internshipRole = internshipRole;
    }
    
    public void setPerformanceRatings(int[] performanceRating) {
        this.performanceRating = performanceRating;
    }
    
    public void setWeeksAssessed(int[] weeksAssessed) {
        this.weeksAssessed = weeksAssessed;
    }
   
    // ---------------------------------------------
    // Add methods
    // ---------------------------------------------
    
    // IMPORTANT: Before calling: Check that performanceRating being added is within range (0 - 100)
    public void addPerformanceRatings(int performanceRating) {
        // Resize arrays manually 
        int[] resizedPerformanceRating = new int[this.performanceRating.length + 1]; 
        
        // Store previous ratings
        for (int i = 0; i < this.performanceRating.length; i++) {
            resizedPerformanceRating[i] = this.performanceRating[i];
        }
        
        // Add new rating
        resizedPerformanceRating[resizedPerformanceRating.length - 1] = performanceRating;
        
        // Override old performanceRating
        this.performanceRating = resizedPerformanceRating;
    }
    
    // IMPORTANT: Before calling: Check that weeksAssessed being added is within range (>0)
    public void addWeeksAssessed(int weeksAssessed) {
        // Resize arrays manually 
        int[] resizedWeeksAssessed = new int[this.weeksAssessed.length + 1]; 

        // Store previous weeks
        for (int i = 0; i < this.weeksAssessed.length; i++) {
            resizedWeeksAssessed[i] = this.weeksAssessed[i];
        }
        
        // Add new week
        resizedWeeksAssessed[resizedWeeksAssessed.length - 1] = weeksAssessed;
        
        // Override old weeksAssessed
        this.weeksAssessed = resizedWeeksAssessed;

    }
    
    // ---------------------------------------------
    // Other methods
    // ---------------------------------------------
    
    public int toGradePoint(int mark) {
        if (mark >= 80) {
            return 4;
        
        } else if (mark  >= 70) { 
            return 3;
            
        } else if (mark >= 60) {
            return 2;

        } else if (mark >= 50) {
            return 1;

        } else { 
            return 0; 
        }
        
    }
    
    public double averagePerformanceRating() {
        if (performanceRating.length == 0) {
            return 0.0;
        
        } else {
            double total = 0;
        
            for (int i = 0; i < this.performanceRating.length; i++) {
                total += this.performanceRating[i];
            }
        
            return total / this.performanceRating.length;
        }
    }
    
    public double calcOverallInternshipScore() {

        if (this.weeksAssessed.length == 0) {
            return 0.0;
        
        } else {
            
            // FORMULA
            // Overall Score = Σ(ratingᵢ × weeksᵢ) / Σ(weeksᵢ)

            // Performance rating to grade points 
            // Marks            Grade Points
            // 80 and above	4
            // 70 - 79          3
            // 60 - 69          2
            // 50 - 59      	1
            // Below 50         0
            
            // E.g.
            // Performance ratings of 75, 63, 72 and 89 for internship periods of 4, 4, 6, and 2 weeks
            // Overall internship score = (4 × 3 + 4 × 2 + 6 × 3 + 2 × 4) / (4 + 4 + 6 + 2) = 2.88
        
            
            // STEPS TO CALCULATE INTERNSHIP SCORE
            // 1. Calculate grade for each performance rating 
            // 2. Get corresponding grade point
            // 3. Multiply grade point by weeksAssessed
            // 4. Divide by total weeks assessed
            
            double totalScoreValue = 0.0;
            int totalWeeksAssessed = 0;
            
            for (int i = 0; i < this.performanceRating.length; i++) {
                int currPerformanceRating = this.performanceRating[i];
                int currGradePoint;
                
                // 1. Calculate grade for each performance rating 
                // 2. Get corresponding grade point
                currGradePoint = toGradePoint(currPerformanceRating);
                
                // 3. Multiply grade point by weeksAssessed
                int currScoreValue = currGradePoint * this.weeksAssessed[i];
                
                // Add current score value to totalScoreValue
                totalScoreValue += currScoreValue;
                
                // Add current weeks assessed to totalWeeksAssessed
                totalWeeksAssessed += this.weeksAssessed[i];
                
            }
            
            // totalScoreValue is double so double/double, preserves fractional part
            double internshipScore = totalScoreValue / totalWeeksAssessed;
            
            // Rounding to 2dp
            String internshipScoreStr = String.format("%.2f", internshipScore);
            // Convert string to int
            double roundedInternshipScore = Double.parseDouble(internshipScoreStr);
            
            return roundedInternshipScore;
        }
        
    }
    
    @Override
    public String toString() {
        return String.format(
            "Name: %s | ID: %s | Course: %s\nCompany: %s | Role: %s | Allowance: $%.2f/month",
            name, studentID, courseOfStudy, assignedCompany.getCompanyName(), internshipRole, assignedCompany.getInternshipAllowance()
        );
    }
    
}
