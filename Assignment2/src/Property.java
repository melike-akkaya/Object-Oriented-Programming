public abstract class Property extends Square {
    protected String name;
    protected double cost;
    protected User owner;
    protected double rent;

    Property(String n, double c, int id) {
        this.name = n;
        this.cost = c;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract void calculateRent(int x);
}
