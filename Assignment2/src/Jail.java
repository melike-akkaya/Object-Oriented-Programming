public class Jail extends NonProperty{
    Jail(int i) {
        super(i);
    }

    @Override
    public String setProcessing(UPlayer player, UBanker banker, String processing) {
        processing = player.getName() + " went to jail";
        player.setTurnsInPrison(1);
        return processing;
    }
}
