import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static String process(ArrayList<String> commands, ArrayList<ArrayList<Jewel>> board, Player player) {
        // to prepare the output that I will print to the monitoring.txt file, I created a StringBuilder to convert it to String later.

        StringBuilder output = new StringBuilder("Game grid:\n\n" + PreparatoryWork.createBoardOutput(board, player) + "\n");


        for (String i : commands) {

            // In this way, I understood what was entered as command.
            // If my split command is able to perform the split operation, the row and column values must be entered,
            // if it cannot, it must be either the end game command or the name of the player.
            String[] miniList = i.split(" ");

            // if player gave row and column indexes as command,
            if (miniList.length == 2) {
                output.append("Select coordinate or enter E to end the game: ");

                int row = Integer.parseInt(miniList[0]);
                int column = Integer.parseInt(miniList[1]);
                output.append(row).append(" ").append(column).append("\n\n");

                // Inside this list I keep the indexes of the three matching Jewels that the user selects.
                ArrayList<Integer[]> explosiveJewelery = new ArrayList<>();

                if (row > board.size() || column > board.get(row).size() || (board.get(row)).get(column) instanceof ExplosiveJewel) {
                    output.append("Please enter a valid coordinate.\n\n");
                }
                else {
                    // I fill the list I created to keep the indexes of the matching Jewels, using the tripleMatch method
                    // (which I write in the Jewel class and override in its subclasses.)
                    explosiveJewelery = (board.get(row)).get(column).tripleMatch(row, column, board);

                    // Each Jewel has point value inside. In this method, I calculate the user's score using the point values that I set in Jewel classes.
                    if (explosiveJewelery != null) {
                        player.gainPoint(explosiveJewelery, board);

                        // to explosive the chosen jewels
                        for (Integer[] oldJewel : explosiveJewelery) {
                            ExplosiveJewel e = new ExplosiveJewel();
                            board.get(oldJewel[0]).set(oldJewel[1], e);
                        }
                    }

                    // If the explosiveJewelery list remains null, the gainPoint method for the player cannot be entered.
                    // This causes problems in the output when calculating the points earned by the user for that round.
                    // I added this if statement to fix this situation.
                    if (explosiveJewelery == null)
                        player.setPreviousScore(player.getScore());

                    scroll(board);
                    output.append(PreparatoryWork.createBoardOutput(board, player));
                }
            }

            // if the player want to end the game as command,
            else if (miniList[0].equals("E")) {
                output.append("Select coordinate or enter E to end the game: ");
                output.append("E\n\nTotal Score: ").append(player.getScore()).append(" Points\n");
            }

            // if the player gave his/her name as command,
            else {
                player.setName(miniList[0]);
                output.append("\nEnter name: ").append(player.getName()).append("\n");
            }
        }
        return String.valueOf(output);
    }

    // with this method, I remove the jewels that the player exploded from the game board.
    public static void scroll (ArrayList<ArrayList<Jewel>> tempBoard) {
        for (int i = 0 ; i < tempBoard.get(0).size(); i++) {
            ArrayList<Jewel> persistentJewels = new ArrayList<>();
            for (ArrayList<Jewel> jewels : tempBoard) {
                if (!(jewels.get(i) instanceof ExplosiveJewel)) {
                    persistentJewels.add(jewels.get(i));
                }
            }
            Collections.reverse(persistentJewels);
            int k = 0;
            for (int j = tempBoard.size() - 1; j > -1; j--) {
                if (k < persistentJewels.size()){
                    tempBoard.get(j).set(i, persistentJewels.get(k));
                    k ++;
                }
                else {
                    tempBoard.get(j).set(i, new ExplosiveJewel());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // to create the board on which the game will be played.
        ArrayList<ArrayList<Jewel>> board = new ArrayList<>();
        board = PreparatoryWork.createBoard(args[0]); //gameGrid.txt


        // to get and execute the user's commands
        ArrayList<String> commands = PreparatoryWork.openFile(args[1]); //command.txt

        Player player = new Player();

        String output = process(commands, board, player);
        String sortOutput = PreparatoryWork.writeLeaderboard ("leaderboard.txt", player);
        output += sortOutput;
        PreparatoryWork.createOutputFile(output);
    }
}