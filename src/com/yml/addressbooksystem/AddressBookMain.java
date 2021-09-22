import java.io.PrintWriter;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class AddressBookMain {
    static Map<String, AddressBook> addressBooks = new HashMap<String, AddressBook>();
    static AddressBook currentBook = null;
    public static void main(String args[]) {
        PrintWriter out = new PrintWriter(System.out, true);
        Scanner in = new Scanner(System.in);

        out.println("Welcome to Address Book Program");

        chooseAddressBook();

        boolean flag = true;
        while (flag) {     
            out.println("Select option\n1. Create contact\n2. Display Address Book\n3. Edit Contact\n4. Delete Contact\n5. Change Address Book\n6. Exit");
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
                    editContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    chooseAddressBook();
                    break;
                case 6:
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

    private static void chooseAddressBook() {
        PrintWriter out = new PrintWriter(System.out,true);
        Scanner in = new Scanner(System.in);

        out.println();
        out.println("1. Create New Address Book\n2. Select from existing");
        int choice = in.nextInt();
        in.nextLine();
        
        if (choice == 1) {
            out.println("Enter the name for Address Book");
            String bookName = in.nextLine();
            if (addressBooks.get(bookName) != null) {
                out.println("Address Book already exists");
                chooseAddressBook();
                return;
            }
            addressBooks.put(bookName, new AddressBook());
            currentBook = addressBooks.get(bookName);
            out.println("New address book created");
        }
        else {
            if (addressBooks.size() == 0) {
                out.println("No Address books found");
                chooseAddressBook();
                return;
            }

            int index = 1;
            for (String book : addressBooks.keySet()) {
                out.println(index + ". " + book);
                index++;
            }
            out.println("Enter the name of address book");
            String bookName = in.nextLine();

            if (addressBooks.get(bookName) == null) {
                out.println("Address Book "+bookName+" does not exist");
                chooseAddressBook();
                return;
            }
            currentBook = addressBooks.get(bookName);
        }
    }

    private static void deleteContact() {
        Contact deleteContact = null;
        List<Contact> contacts = currentBook.getAddressBook();
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out,true);

        out.println();
        out.println("Editing Menu");
        out.println("Enter the First Name");
        String firstName = in.nextLine();
        out.println("Enter the Last Name");
        String lastName = in.nextLine();

        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                deleteContact = contact;
            }
        }

        if (deleteContact == null) {
            out.println("Contact Not Found");
        }
        else {
            contacts.remove(deleteContact);
            out.println("Contact Deleted");
            out.println();
        }
    }

    private static void editContact() {
        Contact editContact = null;
        List<Contact> contacts = currentBook.getAddressBook();
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out,true);

        out.println();
        out.println("Editing Menu");
        out.println("Enter the First Name");
        String firstName = in.nextLine();
        out.println("Enter the Last Name");
        String lastName = in.nextLine();

        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                editContact = contact;
            }
        }

        if (editContact == null) {
            out.println("Contact Not Found");
            return;
        }
        
        while (true) {
            out.println("Select Parameter to edit");
            out.println(
                    "1. First Name\n2. Last Name\n3. Address\n4. City\n5. State\n 6. Zip\n7. Phone Number\n8. Email");
            out.println("9. Back");
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    out.println("Enter the new First Name");
                    editContact.setFirstName(in.nextLine());
                    out.println("First Name Changed");
                    break;
                case 2:
                    out.println("Enter the new Last Name");
                    editContact.setLastName(in.nextLine());
                    out.println("Last Name Changed");
                    break;
                case 3:
                    out.println("Enter the new Address");
                    editContact.setAddress(in.nextLine());
                    out.println("Address Changed");
                    break;
                case 4:
                    out.println("Enter the new City");
                    editContact.setCity(in.nextLine());
                    out.println("City Changed");
                    break;
                case 5:
                    out.println("Enter the new State");
                    editContact.setState(in.nextLine());
                    out.println("State Changed");
                    break;
                case 6:
                    out.println("Enter the new Zip");
                    editContact.setZip(in.nextInt());
                    in.nextLine();
                    out.println("Zip Changed");
                    break;
                case 7:
                    out.println("Enter the new Phone Number");
                    editContact.setPhone(in.nextBigInteger());
                    in.nextLine();
                    out.println("Phone Number Changed");
                    break;
                case 8:
                    out.println("Enter the new Email Address");
                    editContact.setEmail(in.nextLine());
                    out.println("Email Changed");
                    break;
                case 9:
                    out.println();
                    return;
                default:
                    out.println("Invalid Choice");
                    break;
            }
            out.println();
        }
    }

    private static void displayAddressBook() {
        List<Contact> contacts = currentBook.getAddressBook();
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
        String firstName = in.nextLine();
        out.println("Enter the Last Name");
        String lastName = in.nextLine();

        contact.setLastName(lastName);
        contact.setFirstName(firstName);
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

        currentBook.addContact(contact);
    }   
}