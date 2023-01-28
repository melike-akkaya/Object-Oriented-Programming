import java.util.ArrayList;

public abstract class Jewel {
    Jewel() {}
    protected int point;
    protected String jewelType;
    protected Motion motion;

    public abstract ArrayList<Integer[]> tripleMatch(int row, int column, ArrayList<ArrayList<Jewel>> board);
}