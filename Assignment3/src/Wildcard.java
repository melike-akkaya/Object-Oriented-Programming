import java.util.ArrayList;

public class Wildcard extends Jewel{
    Wildcard() {
        this.point = 10;
        this.jewelType = "W";
    }

    public static ArrayList<Integer[]> wildcardMatch (int row, int column, ArrayList<ArrayList<Jewel>> board, Motion motion) {
        // In this array, I will keep the indexes of possible triples.
        ArrayList<Integer[]> deletable = new ArrayList<>();

        deletable = motion.match (row, column, board, "T", "T");
        if (deletable == null)
            deletable = motion.match (row, column, board, "W", "W");
        if (deletable == null)
            deletable = motion.match(row, column, board, "D", "D");
        if (deletable == null)
            deletable = motion.match (row, column, board, "S", "S");
        if (deletable == null)
            deletable = motion.match(row, column, board, "W", "T");
        if (deletable == null)
            deletable = motion.match (row, column, board, "W", "D");
        if (deletable == null)
            deletable = motion.match (row, column, board, "W", "S");
        if (deletable == null)
            deletable = motion.match(row, column, board, "T", "W");
        if (deletable == null)
            deletable = motion.match (row, column, board, "D", "W");
        if (deletable == null)
            deletable = motion.match (row, column, board, "S", "W");

        return deletable;
    }

    @Override
    public ArrayList<Integer[]> tripleMatch(int row, int column, ArrayList<ArrayList<Jewel>> board) {
        // If the coordinate contains a W, the program starts searching from vertical, followed by horizontal and finally diagonals left and right.
        // If two Wildcard objects are matched, a triple match will occur regardless of the third.

        // In this array, I will keep the indexes of possible triples.
        ArrayList<Integer[]> deletable = new ArrayList<>();

        // Firstly, I control if there is any vertical match
        motion = new Vertical();
        deletable = wildcardMatch(row, column, board, motion);

        // Secondly, I control if there is any horizontal match
        if (deletable == null) {
            motion = new Horizontal();
            deletable = wildcardMatch(row, column, board, motion);
        }

        // Thirdly, I control if there is any left diagonal match
        if (deletable == null){
            motion = new LeftDiagonal();
            deletable = wildcardMatch(row, column, board, motion);
        }

        // Finally, I control if there is any right diagonal match
        if (deletable == null) {
            motion = new RightDiagonal();
            deletable = wildcardMatch(row, column, board, motion);
        }

        return deletable;
    }
}