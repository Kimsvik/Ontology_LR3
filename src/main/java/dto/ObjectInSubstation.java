package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;


public class ObjectInSubstation {

//    @Getter
//    @Setter
//    /* Список напряжений на ПС */
//    private  List<BaseVoltage> baseVoltages = new LinkedList<>();
//
//    @Getter
//    @Setter
//    /* Список оборудования на ПС */
//    private  List<ConductingEquipment> conductingEquipments = new LinkedList<>();

    @Getter
    @Setter
    /* Список терминалов на ПС */
    private  List<Terminal> terminals = new LinkedList<>();

//    @Getter
//    @Setter
//    /* Список connectivityNodes на ПС*/
//    private  List<ConnectivityNode> connectivityNodes = new LinkedList<>();

}