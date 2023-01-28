public class RailRoad extends Property{
    RailRoad(String n, double c, int id) {
        super (n,c,id);
    }

    @Override
    public void calculateRent(int otherPlayer) {
        rent = 25 * otherPlayer;
    }
}
