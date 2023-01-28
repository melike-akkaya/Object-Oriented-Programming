import java.util.ArrayList;

public interface Motion {
    ArrayList<Integer[]> match(int row, int column, ArrayList<ArrayList<Jewel>> board, String type1, String type2);
}
