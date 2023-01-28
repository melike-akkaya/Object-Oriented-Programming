public class UPlayer extends User{
    private int currentSquare = 1;
    private int turnsInPrison;
    private int turnsInParking;
    protected StringBuilder deeds = new StringBuilder("");
    private boolean bankrupt = false;

    UPlayer(String n) {
        money = 15000;
        name = n;
    }

    public String getName() {
        return name;
    }

    public int getCurrentSquare() {
        return currentSquare;
    }
    public void setCurrentSquare(int square) {
        this.currentSquare = square;
    }

    public int getTurnsInPrison() {
        return turnsInPrison;
    }
    public void setTurnsInPrison(int turnsInPrison) {
        this.turnsInPrison = turnsInPrison;
    }

    public int getTurnsInParking() {
        return turnsInParking;
    }
    public void setTurnsInParking(int turnsInParking){
        this.turnsInParking = turnsInParking;
    }

    public boolean isBankrupt() {
        return bankrupt;
    }
    public void setBankrupt(boolean bankrupt) {
        this.bankrupt = bankrupt;
    }
}
