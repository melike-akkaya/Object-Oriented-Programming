import java.util.ArrayList;

public class LeftDiagonal implements Motion{
    @Override
    public ArrayList<Integer[]> match(int row, int column, ArrayList<ArrayList<Jewel>> board, String type1, String type2) {

        // In this array, I will keep the indexes of possible triples.
        ArrayList<Integer[]> deletable = new ArrayList<>();

        int numberofColumns = board.get(row).size() - 1;
        int numberofRows = board.size() - 1;


        // northwest direction (1)
        if (row != 0 && column != 0) {
            if (((board.get(row - 1)).get(column - 1).jewelType).equals(type1)) {
                deletable.add(new Integer[]{row - 1, column - 1});
                if (row - 1 != 0 && column - 1 != 0) {
                    if (((board.get(row - 2)).get(column - 2).jewelType).equals(type2)) {
                        deletable.add(new Integer[]{row - 2, column - 2});
                    }
                }
            }
        }

        // southeast direction (9)
        if (deletable.size() != 2 && row != numberofRows && column != numberofColumns) {
            if (((board.get(row + 1)).get(column + 1).jewelType).equals(type1)) {
                deletable = new ArrayList<>();
                deletable.add(new Integer[]{row + 1, column + 1});
                if (row + 1 != numberofRows && column + 1 != numberofColumns) {
                    if (((board.get(row + 2)).get(column + 2).jewelType).equals(type2)) {
                        deletable.add(new Integer[]{row + 2, column + 2});
                    }
                }
            }
        }
        if (deletable.size() == 2) {
            deletable.add(new Integer[]{row, column});
            return deletable;
        }
        else {
            return null;
        }
    }
}
