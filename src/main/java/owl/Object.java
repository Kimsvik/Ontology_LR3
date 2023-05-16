package owl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class Object {
    private String name;
    private List<String> dataProperties;
    private List<String> objectProperties;

}
