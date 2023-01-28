public class TaxSquares extends NonProperty{

    @Override
    public String setProcessing(UPlayer player, UBanker banker, String processing) {
        if (player.money >= 100) {
            player.money -= 100;
            banker.money += 100;
            processing = player.getName() + " paid Tax";
        } else {
            player.setBankrupt(true);
            processing = player.getName() + " goes bankrupt";
        }
        return processing;
    }

    TaxSquares(int i){
        super(i);
    }
}
