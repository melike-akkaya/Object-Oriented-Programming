import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    // to calculate the salary and if the current square is out of map
    public static String gainSalary (UPlayer player, UBanker banker, String processing) {
        if (player.getCurrentSquare() > 40 || player.getCurrentSquare() == 1) {
            NonProperty x = new Go(player.getCurrentSquare());
            processing = x.setProcessing(player, banker, processing);
        }
        return processing;
    }

    // to check if the player or opponent owns the property. to calculate the rent if the opponent owns the property. if none owns it, to buy the property
    public static String propertyWorks(Property p, UPlayer player, UPlayer opponent, UBanker banker, int dice, int railroad, ArrayList<Property> propertiesList) {
        String processing;
        if (p.owner == null) {
            if (player.money >= p.cost) {
                p.owner = player;
                player.money -= p.cost;
                banker.money += p.cost;
                player.deeds.append(p.name).append(", ");
                processing = player.getName() + " bought " + p.name;
		if (player.money == 0) {
                    player.setBankrupt(true);
		}
            }
            else {
                player.setBankrupt(true);
                processing = player.getName() + " goes bankrupt";
            }
        } else {
            if (p.owner == player)
                processing = player.getName() + " has " + p.name;
            else {
                if (p instanceof Land) {
                    p.calculateRent(1);
                } else if (p instanceof Company) {
                    p.calculateRent(dice);
                } else if (p instanceof RailRoad) {
                    for (Property i : propertiesList) {
                        if (i instanceof RailRoad) {
                            if (i.owner != player && i.owner != null) {
                                railroad++;
                            }
                        }
                    }
                    p.calculateRent(railroad);
                }
                if (p.rent > player.money) {
                    player.setBankrupt(true);
                    processing = player.getName() + " goes bankrupt";
                }
                else {
                    player.money -= p.rent;
                    opponent.money += p.rent;
                    processing = player.getName() + " paid rent for " + p.name;
		    if (player.money == p.rent) {
			player.setBankrupt(true);
		    }
                }
            }
        }
        return processing;
    }

    // to find the type of the square and perform their functions
    public static String findSquare(UPlayer player, ArrayList<Property> propertiesList, UBanker banker, int dice, ArrayList<Chance> chanceObjectList,
                                    ArrayList<CommunityChest> communityChestObjectList, UPlayer opponent) {
        int railroad = 0; // to keep the number of railroad deeds

        boolean control = false; // to learn if the square is a property or not

        String processing = "";

        processing = gainSalary(player, banker, processing);

        // to control if the player has to wait for a turn
        if (player.getTurnsInPrison() != 0 && player.getTurnsInPrison() != 4) {
            player.setTurnsInPrison(player.getTurnsInPrison() + 1);
            processing = player.getName() + " in jail (count = " + (player.getTurnsInPrison() - 1) + ")";
            player.setCurrentSquare(player.getCurrentSquare() - dice);
        }
        else if (player.getTurnsInParking() == 1) {
            processing = player.getName() + " in park (count = " + (player.getTurnsInParking()) + ")";
            player.setTurnsInParking(0);
            player.setCurrentSquare(player.getCurrentSquare() - dice);
        }
        else {
            if (player.getTurnsInPrison() == 4)
                player.setTurnsInPrison(0);
            for (Property p : propertiesList) {
                if (p.getId() == player.getCurrentSquare()) {
                    control = true;
                    processing = propertyWorks(p, player, opponent, banker, dice, railroad, propertiesList);
                }
                else if (p.getId() > player.getCurrentSquare()) {
                    break;
                }
            }
            if (!control) {
                if (player.getCurrentSquare() == 3 || player.getCurrentSquare() == 18 || player.getCurrentSquare() == 34) {
                    // get a community chest card
                    Action x = communityChestObjectList.get(CommunityChest.getNumber());
                    processing = x.actionFunction(x, player, opponent, banker, propertiesList, processing, dice, railroad, chanceObjectList, communityChestObjectList);
                }
                else if (player.getCurrentSquare() == 5 || player.getCurrentSquare() == 39) {
                    // income tax and super tax
                    NonProperty x = new TaxSquares(player.getCurrentSquare());
                    processing = x.setProcessing(player, banker, processing);
                }
                else if (player.getCurrentSquare() == 8 | player.getCurrentSquare() == 23 || player.getCurrentSquare() == 37) {
                    // get a chance card
                    if (!player.isBankrupt() && !opponent.isBankrupt()) {
                        Action x = chanceObjectList.get(Chance.getNumber());
                        processing = x.actionFunction(x, player, opponent, banker, propertiesList,
                                processing, dice, railroad, chanceObjectList, communityChestObjectList);
                        Chance.setNumber(Chance.getNumber() + 1);
                        // since there is more variety in the Chance class; it would be complicated to change the number attribute,
                        // which represents which card was last used in the Chance class. Therefore, unlike what I did in the community chest class,
                        // I changed the number variable for the Chance class in the Main class.
                        if (Chance.getNumber() > 5)
                            Chance.setNumber(Chance.getNumber() - 6);
                    }
                    else if (player.isBankrupt())
                        processing = player.getName() + " goes bankrupt";
                    else if (opponent.isBankrupt())
                        processing = opponent.getName() + " goes bankrupt";
                }
                else if (player.getCurrentSquare() == 11) {
                    // jail
                    NonProperty x = new Jail(player.getCurrentSquare());
                    processing = x.setProcessing(player, banker, processing);
                }
                else if (player.getCurrentSquare() == 21) {
                    // free parking
                    NonProperty x = new FreeParking(player.getCurrentSquare());
                    processing = x.setProcessing(player, banker, processing);
                }
                else if (player.getCurrentSquare() == 31) {
                    // go to jail
                    NonProperty x = new GotoJail(player.getCurrentSquare());
                    processing = x.setProcessing(player, banker, processing);
                }
            }
        }
        return processing;
    }

    public static void main(String[] args) throws IOException {

        // to create action cards' objects
        ArrayList<Chance> chanceObjectList = new ArrayList<>();
        ArrayList<CommunityChest> communityChestObjectList = new ArrayList<>();
        new ListJsonReader(chanceObjectList, communityChestObjectList);

        // to create a list which contains property objects
        ArrayList<Property> propertiesList = new ArrayList<>();
        new PropertyJsonReader(propertiesList);
        propertiesList.sort(Comparator.comparing(Property::getId)); //to obtain the map

        // to open the input file
        ArrayList<String> commandList = new ArrayList<>();
        File file = new File(args[0]);
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            commandList.add(scan.nextLine());
        }

        // to create the users
        UPlayer player1 = new UPlayer("Player 1");
        UPlayer player2 = new UPlayer("Player 2");
        UBanker banker = new UBanker();

        StringBuilder output = new StringBuilder();
        String processing;

        for (String i:commandList) {
            String[] miniList = i.split(";");
            if (player1.isBankrupt() || player2.isBankrupt()) {
                break;
            }
            else {
                if (!miniList[0].equals("show()")){
                    int plusSquare = Integer.parseInt(miniList[1]);
                    if (Objects.equals(miniList[0], "Player 1")) {
                        player1.setCurrentSquare(player1.getCurrentSquare() + plusSquare);
                        processing = findSquare(player1, propertiesList, banker, Integer.parseInt(miniList[1]),
                                chanceObjectList, communityChestObjectList, player2);
                        OutputWriter.playerStatus(output, "Player 1" , plusSquare, player1.getCurrentSquare(), (int) player1.money, (int) player2.money, processing);
                    }
                    else {
                        player2.setCurrentSquare(player2.getCurrentSquare() + plusSquare);
                        processing = findSquare(player2, propertiesList, banker, Integer.parseInt(miniList[1]),
                                chanceObjectList, communityChestObjectList, player1);
                        OutputWriter.playerStatus(output, "Player 2", plusSquare, player2.getCurrentSquare(), (int) player1.money, (int) player2.money, processing);
                    }
                }
                else {
                    OutputWriter.showStatus (output, player1, player2, (int) banker.money);
                }
            }
        }
        OutputWriter.showStatus (output, player1, player2, (int) banker.money);
        new OutputWriter(output);
    }
}