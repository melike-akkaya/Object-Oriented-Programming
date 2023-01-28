import java.util.ArrayList;

public class CommunityChest extends Action{
    private static int number = 0;

    public static int getNumber() {
        return number;
    }

    CommunityChest(String i) {
        super(i);
    }

    //in order for the community chest cards to perform their functions
    @Override
    public String actionFunction (Action x, UPlayer player, UPlayer opponent, UBanker banker, ArrayList<Property> propertiesList,
                                           String processing, int dice, int railroad, ArrayList<Chance> chanceObjectList, ArrayList<CommunityChest> communityChestObjectList) {
        switch (x.item) {
            case "Advance to Go (Collect $200)":
                player.money = player.money + 200;
                banker.money = banker.money - 200;
                player.setCurrentSquare(1);
                break;
            case "Bank error in your favor - collect $75":
                player.money = player.money + 75;
                banker.money = banker.money - 75;
                break;
            case "Doctor's fees - Pay $50":
            case "Pay School Fees of $50":
                if (player.money >= 50) {
                    player.money = player.money - 50;
                    banker.money = banker.money + 50;
                }
                else
                    player.setBankrupt(true);
                break;
            case "It is your birthday Collect $10 from each player":
                if (opponent.money >= 10) {
                    player.money = player.money + 10;
                    opponent.money = opponent.money - 10;
                }
                else
                    opponent.setBankrupt(true);
                break;
            case "Grand Opera Night - collect $50 from every player for opening night seats":
                if (opponent.money >= 50) {
                    player.money = player.money + 50;
                    opponent.money = opponent.money - 50;
                }
                else
                    opponent.setBankrupt(true);
                break;
            case "Income Tax refund - collect $20":
                player.money = player.money + 20;
                banker.money = banker.money - 20;
                break;
            case "Life Insurance Matures - collect $100":
            case "You inherit $100":
                player.money = player.money + 100;
                banker.money = banker.money - 100;
                break;
            case "Pay Hospital Fees of $100":
                if (player.money >= 100) {
                    player.money = player.money - 100;
                    banker.money = banker.money + 100;
                }
                else
                    player.setBankrupt(true);
                break;
            case "From sale of stock you get $50":
                player.money = player.money + 50;
                banker.money = banker.money - 50;
        }
        if (!player.isBankrupt() && !opponent.isBankrupt()) {
            processing = player.getName() + " draw Community Chest - " + communityChestObjectList.get(CommunityChest.getNumber()).item;
            number ++;
            if (number > 10)
                number -= 11;
        }
        else if (player.isBankrupt())
            processing = player.getName() + " goes bankrupt";
        else if (opponent.isBankrupt())
            processing = opponent.getName() + " goes bankrupt";

        return processing;
    }
}
