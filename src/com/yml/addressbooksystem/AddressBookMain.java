import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

class AddressBookMain{
    public static void main(String args[]) {
        PrintWriter out = new PrintWriter(System.out,true);
  
        AddressBook addressBook = AddressBook.getInstance();
        Contact c = new Contact();
        c.setFirstName("Sujay");
        c.setLastName("Shenoy");
        c.setAddress("Karkala");
        c.setCity("Karkala");
        c.setState("Karnataka");
        c.setZip(574104);
        c.setPhone(new BigInteger("9874832457"));
        c.setEmail("sujay@sujay.com");
        addressBook.addContact(c);
        

        out.println("Welcome to Address Book Program");   

        for (Contact contact : addressBook.getAddressBook()) {
            out.println(contact);
        }

        out.close();
    }


}