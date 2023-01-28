public class Contact implements Comparable<Contact> {
    private String socialSecurityNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact (String socialsec, String name, String lastname, String number) {
        this.socialSecurityNumber = socialsec;
        this.firstName = name;
        this.lastName = lastname;
        this.phoneNumber = number;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int makePhoneNumberInt(String number) {
        number = number.replace("-", "");
        number = number.replaceAll("\\p{C}","");
        return Integer.parseInt(number);
    }

    @Override
    public int compareTo(Contact o) {
        int n = makePhoneNumberInt(phoneNumber);
        int n2 = makePhoneNumberInt(o.getPhoneNumber());
        return Integer.compare(n, n2);
    }
}
