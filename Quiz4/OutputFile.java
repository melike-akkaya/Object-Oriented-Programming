import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class OutputFile {
    public static String createText(Collection<Contact> contactList) {
        StringBuilder text = new StringBuilder("");
        for (Contact i : contactList) {
            text.append(i.getPhoneNumber()).append(" ").append(i.getFirstName()).append(" ").
                    append(i.getLastName()).append(" ").append(i.getSocialSecurityNumber()).append("\n");
        }
        return String.valueOf(text);
    }

    public static void createFile(String name, String text) throws IOException {
        FileWriter writer = new FileWriter(name);
        writer.write(text);
        writer.close();
    }
}
