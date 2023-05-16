package dto;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Terminal  {

    public Terminal(String name, String mrId) {    //, String conductingEquipmentId, String connectivityNodeId){
        this.mrId = mrId;
        this.name = name;
        this.setConnectivityNodeId(connectivityNodeId);
        this.setConductingEquipmentId(conductingEquipmentId);
    }
    private String name;
    private String mrId;
    private String conductingEquipmentId;
    private String connectivityNodeId;

//    /* Связь с другими объектами */
//    private ConnectivityNode connectivityNode;
//    private ConductingEquipment conductingEquipment;
}
