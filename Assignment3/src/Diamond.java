import java.util.ArrayList;

public class Diamond extends Jewel{
    Diamond() {
        this.point = 30;
        this.jewelType = "D";
        this.motion = new LeftDiagonal();
    }

    // to control if there is a triple match
    @Override
    public ArrayList<Integer[]> tripleMatch(int row, int column, ArrayList<ArrayList<Jewel>> board) {
        // The lines with starting an if segment which contains fromWildcard are called from the Wildcard class and
        // they are doing the process if i have to create a triple match from a Wildcard object.

        // In this array, I will keep the indexes of possible triples.
        ArrayList<Integer[]> deletable = new ArrayList<>();

        // I have to look for left diagonal direction
        deletable = motion.match(row, column, board, "D", "D");


        // I have to look for right diagonal direction, if I couldn't find any match when I search for left diagonal
        if (deletable == null)
            motion = new RightDiagonal();
            deletable = motion.match(row, column, board, "D", "D");

        return deletable;
    }
}