package dto;


import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter @Setter
public class Element extends Identifier {

    private String type;
    private String directoryEntryId;
    private String voltageLevel;
    private String operationName;
    private String projectName;
    private List<Ports> ports = new LinkedList<>();
    private List<Fields> fields;
    private String ConNod;

}

