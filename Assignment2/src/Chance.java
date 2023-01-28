import java.util.ArrayList;

public class Chance extends Action{
    private static int number = 0;

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        Chance.number = number;
    }

    Chance(String i) {
        super(i);
    }

    //in order for the chance cards to perform their functions
    @Override
    public String actionFunction (Action x, UPlayer player, UPlayer opponent, UBanker banker, ArrayList<Property> propertiesList,
                                          String processing, int dice, int railroad, ArrayList<Chance> chanceObjectList, ArrayList<CommunityChest> communityChestObjectList) {
        switch (x.item) {
            case "Advance to Go (Collect $200)":
                player.money = player.money + 200;
                banker.money = banker.money - 200;
                player.setCurrentSquare(1);
                break;
            case "Go back 3 spaces":
                player.setCurrentSquare(player.getCurrentSquare() - 3);
                if (player.getCurrentSquare() == 34) {
                    // if the player goes backwards from this chance square, he must draw a new action card as he will come to the community chest square.
                    processing = communityChestObjectList.get(CommunityChest.getNumber()).actionFunction(x, player, opponent, banker, propertiesList, processing, dice,
                            railroad, chanceObjectList, communityChestObjectList);
                    processing = player.getName() + " draw Chance - " + chanceObjectList.get(Chance.getNumber()).item + "\t" + processing;
                }
                else if (player.getCurrentSquare() == 5) {
                    // if the player comes back 3 units from this square, he must do the work of Tax square as he will come to the that square.
                    TaxSquares t = new TaxSquares(5);
                    processing = t.setProcessing(player, banker, processing);
                    processing = player.getName() + " draw Chance - " + chanceObjectList.get(Chance.getNumber()).item + "\t" + processing;
                }
                else {
                    // the last case is when the user goes back 3 units and he comes to a property square.
                    // in this case, i called a method from my Main class so that the player can pay the rent for the property or buy its owner is null.
                    for (Property p : propertiesList) {
                        if (p.getId() == player.getCurrentSquare()) {
                            processing = Main.propertyWorks(p, player, opponent, banker, dice, railroad, propertiesList);
                            break;
                        }
                    }
                    processing = player.getName() + " draw Chance - " + chanceObjectList.get(Chance.getNumber()).item + "\t" + processing;
                }
                break;
            case "Pay poor tax of $15":
                if (player.money > 15) {
                    player.money = player.money - 15;
                    banker.money = banker.money + 15;
                }
                else
                    player.setBankrupt(true);
                break;
            case "Your building loan matures - collect $150":
                player.money = player.money + 150;
                banker.money = banker.money - 150;
                break;
            case "You have won a crossword competition - collect $100 ":
                player.money = player.money + 100;
                banker.money = banker.money - 100;
                break;
            case "Advance to Leicester Square":
                if (player.getCurrentSquare() > 27) {
                    player.money += 200;
                }
                player.setCurrentSquare(27);
                for (Property p : propertiesList) {
                    if (p.getId() == 27) {
                        processing = Main.propertyWorks(p, player, opponent, banker, dice, railroad, propertiesList);
                        break;
                    }
                }
                processing = player.getName() + " draw Chance - " + chanceObjectList.get(Chance.getNumber()).item + "\t" + processing;
                break;
        }
        if (!x.item.equals("Advance to Leicester Square") && !x.item.equals("Go back 3 spaces"))
            if (player.isBankrupt()) {
                processing = player.getName() + " goes bankrupt";
            }
            else
                processing = player.getName() + " draw Chance - " + chanceObjectList.get(Chance.getNumber()).item;
        return processing;
    }
}