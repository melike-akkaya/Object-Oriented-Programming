public class Land extends Property {

    Land(String n, double c, int id) {
        super(n, c, id);
    }

    @Override
    public void calculateRent(int x) {
        if (cost <= 2000)
            rent = cost * 2 / 5;
        else if (cost <= 3000)
            rent = cost * 3 / 10;
        else if (cost <= 4000)
            rent = cost * 7 / 20;
    }
}