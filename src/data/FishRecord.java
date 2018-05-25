package data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="FishRecord")
public class FishRecord{
   private static List<FishInRecord> fishRecord = new ArrayList<FishInRecord>();
    
    public  void add(FishInRecord fishRecord) {
        FishRecord.fishRecord.add(fishRecord);
    }
 
    @XmlElements(@XmlElement(name="fish"))
    public List<FishInRecord> getFishRecord() {
        return fishRecord;
    }
 
    public void setFishRecord(List<FishInRecord> fishRecord) {
        FishRecord.fishRecord = fishRecord;
    }
}
