package dto;

import lombok.Setter;
import lombok.Getter;

import java.util.List;

@Getter @Setter
public class SingleLineDiagram {
    private List<Link> links;
    private List<Element> elements;

}
