//Import necessary libraries
import java.util.*;            //For various utility classes like Scanner,Arrays
import java.io.IOException;    //For handling input/output exceptions
import java.nio.file.Files;    //For working with files
import java.nio.file.Path;     //For representing file paths
import java.nio.file.Paths;    //For creating file paths

// Define a class named PlaneManagement
public class PlaneManagement{
    //Declare variables for row and seat number
    char row;
    int seat_number;
    char euro='€';

    //Arrays to track seat availability for each row
    int[] A = new int[14];
    int[] B = new int[12];
    int[] D = new int[14];
    int[] C = new int[12];

    //Array to store Ticket objects for each seat
    Ticket[] tickets_array = new Ticket[52];

    //Method to initialize all seat arrays to zero
    public void assign_zero (){
        Arrays.fill(A, 0);
        Arrays.fill(B, 0);
        Arrays.fill(C, 0);
        Arrays.fill(D, 0);
    }

    public static void main(String[] args){
        try {
            //Create an instance of PlaneManagement
            PlaneManagement obj1 = new PlaneManagement();
            //Initialize seat arrays to zero
            obj1.assign_zero();
            //Main menu loop
            while (true) {
                System.out.println("Welcome to the Plane Management application");
                System.out.println("**********************************************");
                System.out.println("*                MENU OPTIONS                *");
                System.out.println("**********************************************");
                System.out.println("1) Buy a seat");
                System.out.println("2) Cancel a seat");
                System.out.println("3) Find first available seat");
                System.out.println("4) Show seating plan");
                System.out.println("5) Print tickets information and total sales");
                System.out.println("6) Search ticket");
                System.out.println("0) Quit");
                System.out.println("**********************************************");
                System.out.println("Please select an option: ");

                int option;
                Scanner scanner = new Scanner(System.in);
                //Get user input for menu option
                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();
                }
                else {
                    System.out.println("Invalid input. Please enter a number.\n");
                    scanner.nextLine();
                    continue;
                }
                //Show output based on user input
                if (option == 1) {
                    obj1.buy_seat();
                }
                else if (option == 2) {
                    obj1.cancel_seat();
                }
                else if (option == 3) {
                    obj1.find_first_available();
                }
                else if (option == 4) {
                    obj1.show_seating_plan();
                }
                else if (option == 5) {
                    obj1.print_tickets_info();
                }
                else if (option == 6) {
                    obj1.search_ticket();
                }
                else if (option == 0) {
                    break;
                }
                else {
                    System.out.println("Wrong Choice.\n");
                }
            }
        }
        //Catch any unexpected exceptions
        catch (Exception e){
                System.out.println("An unexpected error occurred.");
                e.printStackTrace();
        }
    }

    //Method to get user input for row and seat number
    public void get_inputs(){
        boolean loop = true;
        //Loop to ensure valid row input
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a Row Letter(A/B/C/D): ");
            String temp_in=scanner.nextLine();
            temp_in=temp_in.toUpperCase();

            //Check if input is valid row letter
            if(temp_in.equals("A") || temp_in.equals("B") || temp_in.equals("C") || temp_in.equals("D")){
                row=temp_in.charAt(0);
                break;
            }
            System.out.println("invalid input");
        }

        //Loop to ensure valid seat number input
        while (loop) {
            loop=false;
            System.out.println("Enter a seat number: ");
            try {
                Scanner scanner = new Scanner(System.in);
                seat_number = scanner.nextInt();

                //Check if seat number is valid for the selected row
                if (row == 'A' || row == 'D') {
                    if (!(0 < seat_number && seat_number <= 14)) {
                        System.out.println("Invalid seat number");
                        loop=true;
                    }
                }
                else if (row == 'B' || row == 'C') {
                    if (!(0 < seat_number && seat_number <= 12)) {
                        System.out.println("Invalid seat number");
                        loop = true;
                    }
                }

            }
            catch(Exception e){
                System.out.println("Invalid seat number");
                loop = true;
            }
        }
    }

    // Method to calculate the price of a seat
    public int calculate_price(int seat_number){
        seat_number++;  // Increment the seat number by 1 to match the seat number
        int price_tag=0;    // Initialize the price tag variable

        // Calculate the price tag
        if(seat_number<=5 && seat_number>=1){
            price_tag=200;
        }
        else if(seat_number>=6 && seat_number<=9){
            price_tag=150;
        }
        else if(seat_number>=10 && seat_number<=14){
            price_tag=180;
        }
        return price_tag;
    }

    // Calculate the seat index based on the row and seat number
    public int set_seat_index(int seat_number,char row){
        int seat_index=seat_number;   // Initialize seat_index with the seat number
        // Increment the seat_index
        if(row=='B'){
            seat_index=seat_index+14;
        }
        else if (row=='C'){
            seat_index=seat_number+26;
        }
        else if(row=='D'){
            seat_index=seat_number+38;
        }
        return seat_index;
    }

    //Method to buy a seat
    public void buy_seat(){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            //Get row and seat number inputs
            get_inputs();
            boolean valid = true;
            seat_number=seat_number-1;
            if(valid){
                //Check if seat is available and mark it as sold
                if (row == 'A' && A[seat_number] == 0) {
                    A[seat_number] = 1;
                    break;
                }
                else if (row == 'B' && B[seat_number] == 0) {
                    B[seat_number] = 1;
                    break;
                }
                else if (row == 'C' && C[seat_number] == 0) {
                    C[seat_number] = 1;
                    break;
                }
                else if (row == 'D' && D[seat_number] == 0) {
                    D[seat_number] = 1;
                    break;
                }
                else {
                    System.out.println("Seat not Available");
                }
            }
        }

        //Get person's information
        System.out.println("Person name: ");
        String name = scanner.next();

        System.out.println("Person surname: ");
        String surname = scanner.next();

        System.out.println("Person email: ");
        String email = scanner.next();

        //Calculate ticket price
        int price_tag=calculate_price(seat_number);
        Person obj_person = new Person(name,surname,email);

        //Calculate seat index
        int seat_index=set_seat_index(seat_number,row);

        //Create and store ticket object
        Ticket obj_ticket = new Ticket(row,(seat_number+1),price_tag,obj_person);
        tickets_array[seat_index]= obj_ticket;
    }

    //Method to cancel a seat booking
    public void cancel_seat(){
        //Get row and seat number inputs
        get_inputs();

        //Generate file name based on row and seat number
        String fileName=String.valueOf(row)+String.valueOf(seat_number)+".txt";
        Path pathToFile = Paths.get(fileName);

        try {
            //Delete file associated with the booking
            Files.delete(pathToFile);
            System.out.println("File deleted successfully.");
        }
        catch (IOException e) {
         //System.out.println(fileName);
        }

        //Decrement seat number to match array indexing
        seat_number=seat_number-1;
        //Calculate seat index
        int seat_index=set_seat_index(seat_number,row);

        //Set the seat as available and remove associated ticket
        tickets_array[seat_index]=null;
        if (row == 'A' && A[seat_number] == 1) {
            A[seat_number] = 0;
            System.out.println("Cancellation Done.\n");
        }
        else if (row == 'B' && B[seat_number] == 1) {
            B[seat_number] = 0;
            System.out.println("Cancellation Done.\n");
        }
        else if (row == 'C' && C[seat_number] == 1) {
            C[seat_number] = 0;
            System.out.println("Cancellation Done.\n");
        }
        else if (row == 'D' && D[seat_number] == 1) {
            D[seat_number] = 0;
            System.out.println("Cancellation Done.\n");
        }
        else {
            System.out.println("Seat Already Available.\n");
        }
    }

    //Method to find the first available seat
    public void find_first_available(){
        //Array of rows
        char[] rows = {'A', 'B', 'C', 'D'};

        //Iterate over each row
        for (char r : rows) {
            //Iterate over each seat in the row
            for (int i = 0; i < A.length; i++) {
                // Check if the seat is available in the current row
                if (r == 'A' && A[i] == 0) {
                    System.out.println("First available seat: " + r + (i + 1)+"\n");
                    return;
                }
                else if (r == 'B' && B[i] == 0) {
                    System.out.println("First available seat: " + r + (i + 1)+"\n");
                    return;
                }
                else if (r == 'C' && C[i] == 0) {
                    System.out.println("First available seat: " + r + (i + 1)+"\n");
                    return;
                }
                else if (r == 'D' && D[i] == 0) {
                    System.out.println("First available seat: " + r + (i + 1)+"\n");
                    return;
                }
            }
        }
        //If no available seats found
        System.out.println("No available seats.\n");
    }

    //Method to show the seating plan
    public void show_seating_plan(){
        // Display seats for row A
        for(int i=0;i< A.length;i++){
            if(A[i]==0){
                System.out.print("O");
            }
            else {
                System.out.print("X");
            }
        }
        System.out.println("");

        // Display seats for row B
        for(int i=0;i< B.length;i++){
            if(B[i]==0){
                System.out.print("O");
            }
            else {
                System.out.print("X");
            }
        }
        System.out.println("\n");

        // Display seats for row C
        for(int i=0;i< C.length;i++){
            if(C[i]==0){
                System.out.print("O");
            }
            else {
                System.out.print("X");
            }
        }
        System.out.println("");

        // Display seats for row D
        for(int i=0;i< D.length;i++){
            if(D[i]==0){
                System.out.print("O");
            }
            else {
                System.out.print("X");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    // Method to print ticket information and total sales
    public void print_tickets_info(){
        int total=0;    // Initialize a variable to store the total sales
        // Iterate over the tickets_array
        for(int i=0;i< tickets_array.length;i++){
            // Check if the current ticket is not null
            if(!(tickets_array[i]==null)){
                // Add the price of the current ticket to the total sales
                total=total+tickets_array[i].getPrice();
                // Print the information of the current ticket
                System.out.println("Ticket Information:");
                System.out.println("Row: " + tickets_array[i].getRow());
                System.out.println("Seat: " + tickets_array[i].getSeat());
                System.out.println("Price: "+euro + tickets_array[i].getPrice());
                System.out.println("-----------------------------------");
            }
        }
        System.out.println("**********************************************");
        System.out.println("Total Sales: "+ euro +total+"\n");
    }

    // Method to search for a ticket
    public void search_ticket(){
        get_inputs();    // Get the row and seat number from the user input

        int seat_number1=seat_number-1;   // Adjust the seat number for array indexing

        int seat_index=set_seat_index(seat_number1,row);   // Calculate the index of the seat in the tickets_array
        // Check if the seat is available
        if(tickets_array[seat_index]==null){
            System.out.println("Seat Available.\n");
        }
        else{
            // If the seat is not available, print the ticket information
            tickets_array[seat_index].printInfo();
            System.out.println("");
        }
    }
}