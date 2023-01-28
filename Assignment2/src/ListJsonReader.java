import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;


public class ListJsonReader{
     
    
    public ListJsonReader(ArrayList<Chance> chances, ArrayList<CommunityChest> chests){
        JSONParser processor = new JSONParser();
        try (Reader file = new FileReader("list.json")){
            JSONObject jsonfile = (JSONObject) processor.parse(file);
            JSONArray chanceList = (JSONArray) jsonfile.get("chanceList");
            for(Object i:chanceList){
				

				 //You can reach items by using:
				 String chance = ((String)((JSONObject)i).get("item"));
                 chances.add(new Chance(chance));
				 //And you can add these items to any datastructure (e.g. array, linkedlist, etc.)
				 
            }
            JSONArray communityChestList = (JSONArray) jsonfile.get("communityChestList");
            for(Object i:communityChestList){
				
				//You can reach items by using:
				String communityChest = ((String)((JSONObject)i).get("item"));
                chests.add(new CommunityChest(communityChest));
				//And you can add these items to any datastructure (e.g. array, linkedlist, etc.) 
				
            }
        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
}

