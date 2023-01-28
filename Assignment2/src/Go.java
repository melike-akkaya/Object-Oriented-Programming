public class Go extends NonProperty{
    Go(int i) {
        super(i);
    }

    @Override
    public String setProcessing(UPlayer player, UBanker banker, String processing) {
        player.money += 200;
        banker.money -= 200;
        if (player.getCurrentSquare() > 40) {
            player.setCurrentSquare(player.getCurrentSquare() - 40);
        }
        if (player.getCurrentSquare() == 1)
            processing = player.getName() + " is in GO square";
        return processing;
    }
}
