import java.util.ArrayList;

public class MathematicalSymbol extends Jewel{
    private final String mathsType;

    MathematicalSymbol(String t) {
        this.mathsType = t;
        this.point = 20;
        this.jewelType = "MathematicalSymbol";
    }

    public String getmathsType() {
        return mathsType;
    }

    @Override
    public ArrayList<Integer[]> tripleMatch(int row, int column, ArrayList<ArrayList<Jewel>> board) {
        MathematicalSymbol m = (MathematicalSymbol) (board.get(row)).get(column);
        ArrayList<Integer[]> deletable = new ArrayList<>();

        // I will equate this variable to different motion types.

        switch (m.getmathsType()) {
            case "/":
                motion = new RightDiagonal();
                deletable = motion.match(row, column, board, "MathematicalSymbol", "MathematicalSymbol");
                break;
            case "-":
                motion = new Horizontal();
                deletable = motion.match(row, column, board, "MathematicalSymbol", "MathematicalSymbol");
                break;
            case "\\":
                motion = new LeftDiagonal();
                deletable = motion.match(row, column, board, "MathematicalSymbol", "MathematicalSymbol");
                break;
            case "|":
                motion = new Vertical();
                deletable = motion.match(row, column, board, "MathematicalSymbol", "MathematicalSymbol");
                break;
            case "+":
                motion = new Horizontal();
                deletable = motion.match(row, column, board, "MathematicalSymbol", "MathematicalSymbol");
                if (deletable == null) {
                    motion = new Vertical();
                    deletable = motion.match(row, column, board, "MathematicalSymbol", "MathematicalSymbol");
                }
                break;
        }
        return deletable;
    }
}