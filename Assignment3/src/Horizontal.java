import java.util.ArrayList;

public class Horizontal implements Motion {
    @Override
    public ArrayList<Integer[]> match (int row, int column, ArrayList<ArrayList<Jewel>> board, String type1, String type2) {
        // In this array, I will keep the indexes of possible triples.
        ArrayList<Integer[]> deletable = new ArrayList<>();

        // I looked left at first because of the Square's feature.
        // So I first checked to see if there was any Jewel on the left that I could check. Column must be more than 0 to look over.
        if (column != 0) {
            if (((board.get(row)).get(column - 1).jewelType).equals(type1)) {
                deletable.add(new Integer[]{row, column - 1});
                if (column - 1 != 0) {
                    if (((board.get(row)).get(column - 2).jewelType).equals(type2)) {
                        deletable.add(new Integer[]{row, column - 2});
                    }
                }
            }
        }

        // I looked on the left side of the selected Square object. If I haven't created a triple from the top,
        // I'm looking on the right side as a last resort. The column must be smaller than the size of the board's row in order to look to the right.
        int numberofColumns = board.get(row).size() - 1;
        if (deletable.size() != 2 && column < numberofColumns) {
            if (((board.get(row)).get(column + 1).jewelType).equals(type1)) {
                deletable = new ArrayList<>();
                deletable.add(new Integer[]{row, column + 1});
                if (column + 1 < numberofColumns) {
                    if (((board.get(row)).get(column + 2).jewelType).equals(type2)) {
                        deletable.add(new Integer[]{row, column + 2});
                    }
                }
            }
        }

        deletable.add(new Integer[]{row, column});
        if (deletable.size() == 3)
            return deletable;
        else
            return null;
    }
}
