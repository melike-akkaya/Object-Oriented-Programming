import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {
    // to get the output as desired
    public static void playerStatus(StringBuilder text, String player,int dice, int position, int pmoney, int omoney, String processing){
        text.append(player).append("\t").append(dice).append("\t").append(position).append("\t").append(pmoney)
                .append("\t").append(omoney).append("\t").append(processing).append("\n");
    }

    public static void showStatus (StringBuilder text, UPlayer player1, UPlayer player2,  int bmoney) {
        if (player1.deeds.length() != 0)
            player1.deeds.replace(player1.deeds.length() - 2,player1.deeds.length(), "");
        if (player2.deeds.length() != 0)
            player2.deeds.replace(player2.deeds.length() - 2,player2.deeds.length(), "");
        text.append("-------------------------------------------------------------------------------------------------------------------------\n").
                append("Player 1 \t").append((int) player1.money).append("\thave:\t").append(player1.deeds).append("\nPlayer 2\t").append((int) player2.money).
                append("\thave:\t").append(player2.deeds).append("\nBanker\t").append(bmoney);
        if (player1.money > player2.money)
            text.append("\nWinner\t").append("Player 1");
        else if (player2.money > player1.money)
            text.append("\nWinner\t").append("Player 2");
        else
            text.append("Draw");
        text.append("\n-------------------------------------------------------------------------------------------------------------------------\n");
        if (player1.deeds.length() != 0)
            player1.deeds.append(", ");
        if (player2.deeds.length() != 0)
            player2.deeds.append(", ");
    }

    OutputWriter(StringBuilder output) throws IOException {
        output.replace(output.length() - 1,output.length(), "");
        FileWriter writer = new FileWriter("output.txt");
        writer.write(String.valueOf(output));
        writer.close();
    }
}
