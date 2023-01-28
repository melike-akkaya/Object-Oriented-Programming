import java.util.ArrayList;

public class Square extends Jewel{
    Square () {
        this.point = 15;
        this.jewelType = "S";
        this.motion = new Horizontal();
    }

    // to control if there is a triple match
    @Override
    public ArrayList<Integer[]> tripleMatch(int row, int column, ArrayList<ArrayList<Jewel>> board) {
        // The lines with starting an if segment which contains fromWildcard are called from the Wildcard class and
        // they are doing the process if i have to create a triple match from a Wildcard object.

        // In this array, I will keep the indexes of possible triples.
        ArrayList<Integer[]> deletable = new ArrayList<>();

        deletable = motion.match(row, column, board, "S", "S");

        return deletable;
    }
}
