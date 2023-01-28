import java.util.ArrayList;

public class Player implements Comparable<Player> {
    private int score;
    private String name;
    private int previousScore;

    public int getScore() {
        return this.score;
    }

    public  int getPreviousScore() {
        return this.previousScore;
    }

    public void setPreviousScore(int previousScore) {
        this.previousScore = previousScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore (int x) {
        this.score = x;
    }

    public void gainPoint(ArrayList<Integer[]> indexes, ArrayList<ArrayList<Jewel>>board ) {
        this.previousScore = this.score;
        for (Integer[] i : indexes) {
            this.score += (board.get(i[0])).get(i[1]).point;
        }
    }

    @Override
    public int compareTo(Player o) {
        return Integer.compare(o.getScore(), this.score);
    }
}
