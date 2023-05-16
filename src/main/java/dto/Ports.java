package dto;

import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Setter;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter @Setter
public class Ports extends Identifier{

    private String name;
    private List<String> links = new LinkedList<>();
    private ArrayNode fields;

    private Link link;
    private Element element;
}
