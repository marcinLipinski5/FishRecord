package data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name="FishDatabase")
public class FishDatabase {
   private static List<FishInDatabase> fishDatabase = new ArrayList<FishInDatabase>();
    
    public  void add(FishInDatabase fishDatabase) {
        FishDatabase.fishDatabase.add(fishDatabase);
    }
 
    @XmlElements(@XmlElement(name="fish"))
    public List<FishInDatabase> getFishDatabase() {
        return fishDatabase;
    }
 
    public void setFishDatabase(List<FishInDatabase> fishDatabase) {
        FishDatabase.fishDatabase = fishDatabase;
    }  
}