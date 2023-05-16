package dto;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class DeviceDirectory extends Identifier {
    private String deviceType;
    private EnRu name;
}
