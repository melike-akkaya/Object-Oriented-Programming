import java.util.ArrayList;

public class Vertical implements Motion{
    @Override
    public  ArrayList<Integer[]> match (int row, int column, ArrayList<ArrayList<Jewel>> board, String type1, String type2) {
        // In this array, I will keep the indexes of possible triples.
        ArrayList<Integer[]> deletable = new ArrayList<>();

        // I looked upwards at first because of the Triangle's feature.
        // So I first checked to see if there was any Jewel above that I could check. Row must be more than 0 to look over.
        if (row != 0) {
            if (((board.get(row - 1)).get(column).jewelType).equals(type1)) {
                deletable.add(new Integer[]{row - 1, column});
                // row - 1 must be more than 0 to look over.
                if (row - 1 != 0) {
                    if (((board.get(row - 2)).get(column).jewelType).equals(type2)) {
                        deletable.add(new Integer[]{row - 2, column});
                    }
                }
            }
        }


        // I looked over the selected jewel object. If I haven't created a triple from the top,
        // I'm looking at the bottom as a last resort. The row must be smaller than the size of the board in order to look underneath.
        if (deletable.size() != 2 && row < board.size() - 1) {
            if (((board.get(row + 1)).get(column).jewelType).equals(type1)) {
                deletable = new ArrayList<>();
                deletable.add(new Integer[]{row + 1, column});

                // In the last case, I need to check if I have created a triple again.
                // If I haven't and row+1 is still smaller than board's size, I try here.
                if (row + 1 < board.size() - 1) {
                    if (((board.get(row + 2)).get(column).jewelType).equals(type2)) {
                        deletable.add(new Integer[]{row + 2, column});
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
