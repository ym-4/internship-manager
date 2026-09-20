/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.io.Serializable;

public class Company implements Serializable {
    // ---------------------------------------------
    // Instance Variables
    // ---------------------------------------------
    private String companyName;
    private double internshipAllowance; 
    private String contactPerson;
    
    // ---------------------------------------------
    // Constructor
    // ---------------------------------------------
    public Company(String companyName, double internshipAllowance, String contactPerson) {
        this.companyName = companyName;
        // Check that allowance is more than or equal to 0 (Before calling)
        this.internshipAllowance = internshipAllowance; 
        this.contactPerson = contactPerson;
    }
    
    public Company() {}
    
    // ---------------------------------------------
    // Get methods
    // ---------------------------------------------
    
    public String getCompanyName() {
        return this.companyName;
    }
    
    public double getInternshipAllowance() {
        return this.internshipAllowance;
    }
    
    public String getContactPerson() {
        return this.contactPerson;
    }
    
    // ---------------------------------------------
    // Set methods
    // ---------------------------------------------
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public void setInternshipAllowance(double internshipAllowance) {
        this.internshipAllowance = internshipAllowance;
    }
    
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    
    // ---------------------------------------------
    // Other methods
    // ---------------------------------------------
    @Override
    public String toString() {
        return String.format("Company: %s | Allowance: $%.2f/month | Contact: %s",
                                companyName, internshipAllowance, contactPerson);
    }
}
