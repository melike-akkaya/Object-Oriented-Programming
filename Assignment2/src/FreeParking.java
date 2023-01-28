public class FreeParking extends NonProperty{

    FreeParking(int i) {
        super(i);
    }

    @Override
    public String setProcessing(UPlayer player, UBanker banker, String processing) {
        player.setTurnsInParking(1);
        processing = player.getName() + " is in Free Parking";
        return processing;
    }
}
