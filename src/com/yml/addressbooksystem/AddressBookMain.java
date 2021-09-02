import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

class AddressBookMain{
    public static void main(String args[]) {
        PrintWriter out = new PrintWriter(System.out, true);
        Scanner in = new Scanner(System.in);

        out.println("Welcome to Address Book Program");   

        boolean flag = true;
        while(flag){
            out.println("Select option\n1. Create contact\n2. Display Address Book\n3. Exit");
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    getContactInput();
                    break;
                case 2:
                    displayAddressBook();
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    out.println("Invalid Option");
                    break;
            }
            out.println();
        }

        out.close();
        in.close();
    }

    private static void displayAddressBook() {
        List<Contact> contacts = AddressBook.getInstance().getAddressBook();
        if (contacts.size() == 0) {
            System.out.println("Address Book is Empty");
        }
        else{
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        } 
    }

    private static void getContactInput() {
        PrintWriter out = new PrintWriter(System.out,true);
        Scanner in = new Scanner(System.in);
        Contact contact = new Contact();

        out.println("Enter the First Name");
        contact.setFirstName(in.nextLine());
        out.println("Enter the Last Name");
        contact.setLastName(in.nextLine());
        out.println("Enter the Address");
        contact.setAddress(in.nextLine());
        out.println("Enter the City");
        contact.setCity(in.nextLine());
        out.println("Enter the State");
        contact.setState(in.nextLine());
        out.println("Enter the Zip Code");
        contact.setZip(in.nextInt());
        in.nextLine();
        out.println("Enter the Phone number");
        contact.setPhone(in.nextBigInteger());
        in.nextLine();
        out.println("Enter the Email address");
        contact.setEmail(in.nextLine());

        AddressBook.getInstance().addContact(contact);
    }   
}