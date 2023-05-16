package dto;

import lombok.Getter;
import lombok.Setter;



@Getter @Setter
public class Link extends Identifier {
    private String sourceId;
    private String targetId;
    private String sourcePortId;
    private String targetPortId;

    //для связи
    private Ports sourcePort;
    private Ports targetPort;
    private Element source;
    private Element target;
}
