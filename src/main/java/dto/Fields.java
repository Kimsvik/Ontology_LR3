package dto;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class Fields extends Identifier {

    private String name;
    private String value;
    private String directoryId;

}