public class Company extends Property {

    Company(String n, double c, int id) {
        super (n,c,id);
    }

    @Override
    public void calculateRent(int dice) {
        rent = dice * 4;
    }
}
