import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

class AddressBookMain {
    static Map<String, AddressBook> addressBooks = new HashMap<String, AddressBook>();
    static Map<String, List<Contact>> stateMap = new HashMap<String, List<Contact>>();
    static Map<String, List<Contact>> cityMap = new HashMap<String, List<Contact>>();
    static AddressBook currentBook = null;
    public static void main(String args[]) {
        PrintWriter out = new PrintWriter(System.out, true);
        Scanner in = new Scanner(System.in);

        out.println("Welcome to Address Book Program");
        addSomeContacts();

        chooseAddressBook();

        boolean flag = true;
        while (flag) {     
            out.println("Select option\n1. Create contact\n2. Display Address Book\n3. View By State/City\n4. Edit Contact\n5. Delete Contact\n6. Search Contact\n7. Change Address Book\n8. Exit");
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
                    viewBy();
                    break;
                case 4:
                    editContact();
                    break;
                case 5:
                    deleteContact();
                    break;
                case 6:
                    search();
                    break;
                case 7:
                    chooseAddressBook();
                    break;
                case 8:
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

    private static void viewBy() {
        PrintWriter out = new PrintWriter(System.out, true);
        Scanner in = new Scanner(System.in);
        out.println("View By \n1. State\n2. City");
        int choice = in.nextInt();
        in.nextLine();

        switch (choice) {
            case 1:
                stateMap.clear();
                currentBook.getAddressBook().stream().forEach(c -> {
                    addToMap("STATE", c);
                });

                for (Map.Entry<String, List<Contact>> element : stateMap.entrySet()) {
                    out.println("State: " + element.getKey());
                    out.println("Number of Records: "+element.getValue().size());
                    for (Contact contact : element.getValue()) {
                        out.println(contact);
                        out.println();
                    }
                    out.println();
                }
                break;
            case 2:
                cityMap.clear();
                currentBook.getAddressBook().stream().forEach(c -> {
                    addToMap("CITY", c);
                });

                for (Map.Entry<String, List<Contact>> element : cityMap.entrySet()) {
                    out.println("City: " + element.getKey());
                    out.println("Number of Records: "+element.getValue().size());
                    for (Contact contact : element.getValue()) {
                        out.println(contact);
                        out.println();
                    }
                    out.println();
                }
                break;
        }
    }

    private static void addSomeContacts() {
        addressBooks.put("book1", new AddressBook());
        addressBooks.put("book2", new AddressBook());
        currentBook = addressBooks.get("book1");

        Contact contact1 = new Contact();
        contact1.setFirstName("Thor");
        contact1.setLastName("Odinson");
        contact1.setAddress("Asgard");
        contact1.setEmail("thor@gmail.com");
        contact1.setPhone(new BigInteger("9837283827"));
        contact1.setState("Karnataka");
        contact1.setCity("Bangalore");
        contact1.setZip(513212);

        currentBook.addContact(contact1);

        Contact contact2 = new Contact();
        contact2.setFirstName("Tony");
        contact2.setLastName("Stark");
        contact2.setAddress("abc , Maharashtra");
        contact2.setEmail("thor@gmail.com");
        contact2.setPhone(new BigInteger("9837283827"));
        contact2.setState("Maharashtra");
        contact2.setCity("Mumbai");
        contact2.setZip(513212);

        currentBook.addContact(contact2);

        Contact contact3 = new Contact();
        contact3.setFirstName("Bjorn");
        contact3.setLastName("Ironside");
        contact3.setAddress("Jayanagar, Karnataka");
        contact3.setEmail("thor@gmail.com");
        contact3.setPhone(new BigInteger("9837283827"));
        contact3.setState("Karnataka");
        contact3.setCity("Bangalore");
        contact3.setZip(513212);
        currentBook.addContact(contact3);

        currentBook = addressBooks.get("book2");

        Contact contact4 = new Contact();
        contact4.setFirstName("John");
        contact4.setLastName("Snow");
        contact4.setAddress("Dombivli, Maharashtra");
        contact4.setEmail("thor@gmail.com");
        contact4.setPhone(new BigInteger("9837283827"));
        contact4.setState("Maharashtra");
        contact4.setCity("Mumbai");
        contact4.setZip(513212);
        currentBook.addContact(contact4);
    }

    private static void search() {
        PrintWriter out = new PrintWriter(System.out,true);
        Scanner in = new Scanner(System.in);

        out.println();
        out.println("1. Search By State\n2. Search By City");
        int choice = in.nextInt();
        in.nextLine();

        switch (choice) {
            case 1:
                out.println("Enter the name of the State");
                String stateName = in.nextLine();
                long stateCount = 0;
                for (AddressBook addressBook : addressBooks.values()) {
                    List<Contact> sameStateContacts = addressBook.getAddressBook().stream().filter((c) -> {
                        return c.getState().equals(stateName);
                    }).collect(Collectors.toList());

                    stateCount += sameStateContacts.size();

                    for (Contact contact : sameStateContacts) {
                        out.println(contact);
                    }
                }

                out.println();
                out.println("Number of Records: " + stateCount);
                break;
            case 2:
                out.println("Enter the name of the City");
                String cityName = in.nextLine();
                long cityCount = 0;
                for (AddressBook addressBook : addressBooks.values()) {
                    List<Contact> sameCityContacts = addressBook.getAddressBook().stream().filter((c) -> {
                        return c.getCity().equals(cityName);
                    }).collect(Collectors.toList());

                    cityCount += sameCityContacts.size();

                    for (Contact contact : sameCityContacts) {
                        out.println(contact);
                    }
                }

                out.println();
                out.println("Number of Records: " + cityCount);
                break;
        }
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
        Collections.sort(contacts);
        if (contacts.size() == 0) {
            System.out.println("Address Book is Empty");
        }
        else{
            contacts.stream().forEach(c -> {
                System.out.println(c);
            });
        } 
    }

    private static void getContactInput() {
        PrintWriter out = new PrintWriter(System.out, true);
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
    
    public static void addToMap(String mapName, Contact contact) {
        if(mapName == "CITY"){
            List<Contact> contacts = cityMap.get(contact.getCity());
            if (contacts == null) {
                List<Contact> contacts2 = new ArrayList<>();
                contacts2.add(contact);
                cityMap.put(contact.getCity(), contacts2);
            }
            else {
                contacts.add(contact);
                cityMap.put(contact.getCity(), contacts);
            }
        }
        else{
            List<Contact> contacts = stateMap.get(contact.getState());
            if (contacts == null) {
                List<Contact> contacts2 = new ArrayList<>();
                contacts2.add(contact);
                stateMap.put(contact.getState(), contacts2);
            }
            else {
                contacts.add(contact);
                stateMap.put(contact.getState(), contacts);
            }
        }
    }
}