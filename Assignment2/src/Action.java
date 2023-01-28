import java.util.ArrayList;

public abstract class Action extends Square{
    protected String item;

    public abstract String actionFunction (Action x, UPlayer player, UPlayer opponent, UBanker banker, ArrayList<Property> propertiesList,
                                String processing, int dice, int railroad, ArrayList<Chance> chanceObjectList, ArrayList<CommunityChest> communityChestObjectList);

    Action(String i) {
        item = i;
    }
}
