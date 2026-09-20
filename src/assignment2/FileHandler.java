/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.io.*;

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
public class FileHandler {
    // Stores student and company data
    private Student[] students;
    private Company[] companies;

// DATA FORMAT
// The first section contains company records:
//	First line: total number of companies
//	Each subsequent company line: Company Name; Contact Person; Monthly Allowance
//
// The second section contains student records:
//	First line: total number of students
//	Each subsequent student line: Course; Student ID; Student Name; Company Name; Internship Role; 
//    Number of Rating Periods; then for each rating period: Weeks Assessed; Marks

    
    // Reads company and student data from text file
    public void readCompanyAndStudent(String file) {

        // To catch File not found and IO exceptions
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            // --------------------------------------------------
            // Read Company data
            // --------------------------------------------------
            
            // Read first line (number of companies) 
            int numOfCompanies = Integer.parseInt(br.readLine());
            // To store the company data being read
            Company[] companies = new Company[numOfCompanies];
            
            // Read company data and create company object in arr of obj
            for (int i = 0; i < numOfCompanies; i++) {
                // Read line
                String line = br.readLine();

                // Split data at ;
                String[] data = line.split(";");

                // Data format 
                // Company Name; Contact Person; Monthly Allowance
                String companyName = data[0];
                String contactPerson = data[1];
                // Convert to double (initially a string)
                double monthlyAllowance = Double.parseDouble(data[2]);

                // Create the company object
                companies[i] = new Company(
                    companyName,
                    monthlyAllowance, 
                    contactPerson
                );
            }
            
            // Store company data in private variable
            this.companies = companies;
            
            // --------------------------------------------------
            // Read student data
            // --------------------------------------------------
            
            // Read number of students 
            int numOfStudents = Integer.parseInt(br.readLine());

            // To store student data
            Student[] students = new Student[numOfStudents];
            
            // Get Student data
            for (int i = 0; i < numOfStudents; i++) {
                // Read line
                String line = br.readLine();
                
                // Split data at ;
                String[] data = line.split(";");
                
                // Data format
                // Course; Student ID; Student Name; Company Name; Internship Role; 
                // Number of Rating Periods; then for each rating period: Weeks Assessed; Marks
                
                String course = data[0];
                String studentID = data[1];
                String studentName = data[2];
                String companyName = data[3];
                String internshipRole = data[4];
                // Convert to integer (initially a String) 
                int numRatingPeriods = Integer.parseInt(data[5]);
                int[] weeksAssessed = new int[numRatingPeriods];
                int[] marksArr = new int[numRatingPeriods];
                
                int currIndex = 6;
                
                // Loop to get the weeks and marks for each rating period
                for (int j = 0; j < numRatingPeriods; j++) {
                    // Get data
                    int weeks = Integer.parseInt(data[currIndex]);
                    int marks = Integer.parseInt(data[currIndex + 1]);
                
                    // Increment index by 2 for the next pair of weeks and marks
                    currIndex += 2;
                    
                    // Store data
                    weeksAssessed[j] = weeks;
                    marksArr[j] = marks;
                    
                }
                
                // Store company that matches the company name
                Company company = null;
                boolean isFound = false;
                
                // Find the company (using companyName given)
                for (int k = 0; k < companies.length; k++) {
                    if (companies[k].getCompanyName().equals(companyName)) {
                        company = companies[k];
                        isFound = true;
                        // Exit for loop
                        break;
                    }
                }
                
                // Company not found (error)
                if (!isFound) {
                    throw new Exception();
                    
                } else {
                    // Create student object
                    students[i] = new Student(
                        studentID, 
                        studentName, 
                        course, 
                        company, 
                        internshipRole, 
                        marksArr, 
                        weeksAssessed
                    );
                }

                
            }
            
            // Store student data in private variable
            this.students = students;
            
            br.close();
            
        } catch (IOException e) {
            System.out.println(e);
            
        } catch (Exception e) {
            System.out.println(e);

        }
    }
    
    // Reads company and student data from object serialized file
    public void readCompanyAndStudentDataFromSerializedFile(String file) {
        try {
            FileInputStream fis =
                new FileInputStream(file);

            ObjectInputStream ois =
                new ObjectInputStream(fis);

            // Read company and student data (cast object)
            Company[] company = (Company[]) ois.readObject();
            Student[] student = (Student[]) ois.readObject();

            ois.close();
            
            // Store company and student data
            this.companies = company;
            this.students = student;
            
        } catch (IOException e) {
            System.out.println(e);
            
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    
    // Saves company and student data to text file
    public void saveCompanyAndStudentData(String file) {
        // To catch IO exception
        try {
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);

            // --------------------------------------------------
            // Save Company data
            // --------------------------------------------------                
            
            // Save number of companies
            pw.println(companies.length);
            
            // Save individual companies data
            for (int i = 0; i < companies.length; i++) {
                // Get data 
                String companyName = companies[i].getCompanyName();
                String contactPerson = companies[i].getContactPerson();
                Double monthlyAllowance = companies[i].getInternshipAllowance();
                
                // Format data
                String formattedCompanyData = companyName + ";" + contactPerson + ";" + monthlyAllowance;
                
                // Store data 
                pw.println(formattedCompanyData);
                
            }
            
            // --------------------------------------------------
            // Save Student data
            // --------------------------------------------------
            
            // Save number of students
            pw.println(students.length);
            
            // Save individual students data
            for (int i = 0; i < students.length; i++) {
                Student currStudent = students[i];
                
                // Get data 
                String studentName = currStudent.getName();
                String studentID = currStudent.getStudentID();
                String course = currStudent.getCourseOfStudy();
                Company company = currStudent.getAssignedCompany();
                String internshipRole = currStudent.getInternshipRole();
                int[] ratings = currStudent.getPerformanceRating();
                int[] weeks = currStudent.getWeeksAssessed();

                int numOfRatingPeriods = ratings.length;
                
                // Get company name
                String companyName = company.getCompanyName();
                
                // Format data
                String formattedStudentData = course + ";" + studentID + ";" + 
                        studentName + ";" + companyName + ";" + internshipRole + ";" + numOfRatingPeriods;
                
                // Alternating weeks and ratings 
                for (int j = 0; j < numOfRatingPeriods; j++) {
                    // Add weeks
                    formattedStudentData += ";" + weeks[j] + ";";
                    // Add rating
                    formattedStudentData += ratings[j];
                }
                
                // Store data 
                pw.println(formattedStudentData);
                
            }
            
            pw.close();
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    // Save company and student data to object serialized file 
    public void saveCompanyAndStudentDataToSerializedFile(String file) {
        
        try {
            FileOutputStream fos = new FileOutputStream(file);

            ObjectOutputStream os = new ObjectOutputStream(fos);
            
            // Save Company array
            os.writeObject(this.companies);

            // Save Student array
            os.writeObject(this.students);

            os.close();
        
        } catch (IOException e) {
            System.out.println(e);
        }
        
    }
    
    public Student[] getStudents() {
        return this.students;
    }
    
    public Company[] getCompanies() {
        return this.companies;
    }
    
    public void setStudents(Student[] students) {
        this.students = students;
    }
    
    public void setCompanies(Company[] companies) {
        this.companies = companies;
    }
}
