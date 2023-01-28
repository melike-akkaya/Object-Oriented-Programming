import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // to store contacts to an arraylist
        ArrayList<Contact> contactList = new ArrayList<>();

        File file = new File(args[0]);
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            // 0- number, 1- name, 2- surname, 3-  social security number
            String [] personInfoList = scan.nextLine().split(" ");
            Contact c = new Contact(personInfoList[3], personInfoList[1], personInfoList[2], personInfoList[0]);
            contactList.add(c);
        }

        // to create the first txt file (contactsArrayList.txt)
        OutputFile.createFile("contactsArrayList.txt", OutputFile.createText(contactList));

        // to create the second txt file (contactsArrayListOrderByLastName.txt)
        LastNameComparator comparator = new LastNameComparator();
        Collections.sort(contactList, comparator);
        OutputFile.createFile("contactsArrayListOrderByLastName.txt", OutputFile.createText(contactList));

        // to store contacts to a HashSet
        HashSet<Contact> contactHashSet = new HashSet<>(contactList);

        // to create the third txt file (contactsHashSet.txt)
        OutputFile.createFile("contactsHashSet.txt", OutputFile.createText(contactHashSet));

        // to store contacts to a TreeSet
        TreeSet<Contact> contactTreeSet = new TreeSet<>(contactList);

        // to create the fourth txt file (contactsTreeSet.txt)
        OutputFile.createFile("contactsTreeSet.txt", OutputFile.createText(contactTreeSet));

        // to create fifth txt file (contactsTreeSetOrderByLastName.txt)
        TreeSet<Contact> contactTreeSet2 = new TreeSet<>(comparator);
        contactTreeSet2.addAll(contactList);
        OutputFile.createFile("contactsTreeSetOrderByLastName.txt", OutputFile.createText(contactTreeSet2));

        // to store contacts to a HashMap using a phone number as a key and a Contact object as a value
        HashMap<String, Contact> contactHashMap = new HashMap<>();
        for (Contact contact : contactList) {
            contactHashMap.put(contact.getPhoneNumber(), contact);
        }

        StringBuilder lasttext = new StringBuilder();
        for (Map.Entry<String, Contact> m : contactHashMap.entrySet()) {
            lasttext.append(m.getKey()).append(" ").append(m.getValue().getFirstName()).
                    append(" ").append(m.getValue().getLastName()).append(" ").append(m.getValue().getSocialSecurityNumber()).append("\n");
        }
        OutputFile.createFile("contactsHashMap.txt", String.valueOf(lasttext));
    }
}
