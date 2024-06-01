import java.io.FileWriter;     // For writing to a file
import java.io.IOException;

// Define a class called Ticket
public class Ticket {
    char euro='€';    // Define a char variable for the euro symbol
    // Declare a private variables
    private char row;
    private int seat;
    private int price;
    private Person person;   // Declare a private Person object called person to store the person's information

    // Constructor that takes row, seat, price, and person as parameters
    public Ticket(char row, int seat, int price, Person person) {
        // Assign the parameters
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
        save();   // Call the save method to save ticket information to a file
    }

    // Method to save ticket information to a file
    private void save(){
        int seatnumber=seat;    // Assign the seat number
        String fileName = row + "" + seatnumber + ".txt";   // Generate the file name based on row and seat number
        try (FileWriter writer = new FileWriter(fileName)) {   // Try to create a FileWriter object
            // Write ticket information to the file
            writer.write("Ticket info:- \n");
            writer.write("Row: " + row + "\n");
            writer.write("Seat: " + seatnumber + "\n");
            writer.write("Price: "+euro + price + "\n");
            writer.write("Person Information:-\n");
            writer.write("Name: " + person.getName() + "\n");
            writer.write("Surname: " + person.getSurname() + "\n");
            writer.write("Email: " + person.getEmail() + "\n");
            System.out.println("You bought the ticket.");
            System.out.println("Ticket information is saved to " + fileName+".\n"); // Print confirmation message
        }
        catch (IOException e) {    // Catch IOException if an error occurs
            System.out.println("An error occurred while saving the ticket information.");
            e.printStackTrace();  // Print the stack trace for debugging
        }
    }

    // Getter method for retrieving the row
    public char getRow() {
        return row;
    }

    // Setter method for setting the row
    public void setRow(char row) {
        this.row = row;
    }

    // Getter method for retrieving the seat
    public int getSeat() {
        return seat;
    }

    // Setter method for setting the seat
    public void setSeat(int seat) {
        this.seat = seat;
    }

    // Getter method for retrieving the price
    public int getPrice() {
        return price;
    }

    // Setter method for setting the price
    public void setPrice(int price) {
        this.price = price;
    }

    // Getter method for retrieving the person object
    public Person getPerson() {
        return person;
    }

    // Setter method for setting the person object
    public void setPerson(Person person) {
        this.person = person;
    }

    // Method to print ticket information
    public void printInfo() {
        System.out.println("");
        System.out.println("Ticket Information:-");
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: "+euro + price);
        System.out.println("Person Information:-");
        person.printInfo();   // Call the printInfo method of the person object to print person information
    }
}