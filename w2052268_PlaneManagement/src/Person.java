// Define a class called Person
public class Person {
    // Declare a private String variable
    private String name;
    private String surname;
    private String email;

    // Constructor that takes name, surname, and email as parameters
    public Person(String name, String surname, String email) {
        // Assign the parameter name to the instance variable
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Getter method for retrieving the name
    public String getName() {
        return name;
    }

    // Setter method for setting the name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for retrieving the surname
    public String getSurname() {
        return surname;
    }

    // Setter method for setting the surname
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Getter method for retrieving the email
    public String getEmail() {
        return email;
    }

    // Setter method for setting the email
    public void setEmail(String email) {
        this.email = email;
    }

    // Method to print the information of the person
    public void printInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
    }
}