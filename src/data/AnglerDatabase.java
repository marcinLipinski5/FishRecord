package data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name="AnglerDatabase")
public class AnglerDatabase {
   private static List<Angler> angler = new ArrayList<Angler>();
    
    public  void add(Angler angler) {
        AnglerDatabase.angler.add(angler);
    }
 
    @XmlElements(@XmlElement(name="angler"))
    public List<Angler> getAnglerDatabase() {
        return angler;
    }
 
    public void setAnglerDatabase(List<Angler> angler) {
        AnglerDatabase.angler = angler;
    }   
}