import java.io.*;
import java.util.*;

public class PreparatoryWork {

    // Since there are more than one txt file that I need to open as input in this assignment,
    // I created such a method in order not to repeat the code all the time.
    public static ArrayList<String> openFile (String name) throws FileNotFoundException {
        File file = new File (name);
        Scanner scan = new Scanner(file);
        ArrayList<String> temp = new ArrayList<>();
        while (scan.hasNextLine()) {
            temp.add(scan.nextLine());
        }
        return temp;
    }

    // In this method, I created the board where the game will be played without making my Main class crowded and confusing.
    public static ArrayList<ArrayList<Jewel>> createBoard (String name) throws FileNotFoundException {
        ArrayList<String> temp = new ArrayList<>();
        temp = openFile(name);
        ArrayList<ArrayList<Jewel>> board = new ArrayList<>();

        for (String i : temp) {
            String [] processList = i.split(" ");
            ArrayList<Jewel> jewels = new ArrayList<>();
            for (String processing : processList) {
                if (processing.equals("D")) {
                    Diamond x = new Diamond();
                    jewels.add(x);
                }
                else if (processing.equals("S")) {
                    Square x = new Square();
                    jewels.add(x);
                }
                else if (processing.equals("T")) {
                    Triangle x = new Triangle();
                    jewels.add(x);
                }
                else if (processing.equals("W")) {
                    Wildcard x = new Wildcard();
                    jewels.add(x);
                }
                else {
                    MathematicalSymbol x = new MathematicalSymbol(processing);
                    jewels.add(x);
                }
            }
            board.add(jewels);
        }
        return board;
    }

    // In this method I added the player to the leaderboard.txt file
    // Also, I prepared an output that describes the status of the player compared to other players with this method.
    public static String writeLeaderboard(String fileName, Player player) throws IOException {
        // to add the player to the leaderboard
        File file = new File (fileName);
        FileWriter fw = new FileWriter(file, true);
        fw.write(   "\n" + player.getName() + " " + player.getScore());
        fw.close();

        // to create an ArrayList which contains all the players
        ArrayList<String> temp = openFile (fileName);
        ArrayList<Player> players = new ArrayList<>();
        for (String i : temp) {
            String[] miniList = i.split(" ");
            Player x = new Player();
            if (miniList.length > 1) {
                x.setName(miniList[0]);
                x.setScore(Integer.parseInt(miniList[1]));
                players.add(x);
            }
        }

        // to sort all the Player objects in the ArrayList according to the method I had overridden in the Player class.
        Collections.sort(players);

        // to find the last Player who played the game from the list of all Player objects
        int i = Arrays.binarySearch(players.toArray(), player);

        StringBuilder sortOutput = new StringBuilder("\nYour rank is " + (i + 1) + "/" + players.size() + ", ");

        // to control if i should add the "and" keyword to the output or not
        boolean writeAnd = false;

        if (i != 0) {
            writeAnd = true;
            sortOutput.append("your score is ").append(players.get(i - 1).getScore() - player.getScore()).append(" points lower than ").append(players.get(i-1).getName());
        }
        if (i != players.size() - 1) {
            if (writeAnd)
                sortOutput.append(" and ");
            else
                sortOutput.append("your score is ");
            sortOutput.append(player.getScore() - players.get(i + 1).getScore()).append(" points higher than ").append(players.get(i + 1).getName());
        }
        sortOutput.append("\n\nGood bye!");

        return String.valueOf(sortOutput);
    }

    // In this method I create the board and score lines' output to write the monitoring.txt file
    public static String createBoardOutput(ArrayList<ArrayList<Jewel>> board, Player player) {
        StringBuilder boardLine = new StringBuilder();

        for (ArrayList<Jewel> line : board) {
            for (Jewel j : line) {
                if (Objects.equals(j.jewelType, "MathematicalSymbol")) {
                    boardLine.append(((MathematicalSymbol) j).getmathsType()).append(" ");
                }
                else {
                    boardLine.append(j.jewelType).append(" ");
                }
            }
            boardLine.append("\n");
        }
        if (player.getScore() != 0) {
            boardLine.append("\n").append("Score: ").append((player.getScore()-player.getPreviousScore())).append(" Points\n\n");
        }
        return String.valueOf(boardLine);
    }

    // In this method I create the monitoring.txt
    public static void createOutputFile(String output) throws IOException {
        FileWriter f = new FileWriter("monitoring.txt");
        f.write(output);
        f.close();
    }
}
