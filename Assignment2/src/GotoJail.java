public class GotoJail extends NonProperty{

    GotoJail(int i) {
        super(i);
    }

    @Override
    public String setProcessing(UPlayer player, UBanker banker, String processing) {
        processing = player.getName() + " went to jail";
        player.setCurrentSquare(11);
        player.setTurnsInPrison(1);
        return processing;
    }
}
