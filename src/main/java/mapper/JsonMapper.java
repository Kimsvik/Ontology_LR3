package mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.SneakyThrows;
//import package4.DeviceDirectory;
import dto.DeviceDirectory;
import dto.SingleLineDiagram;
import dto.VoltageLevelDirectory;
//import package4.DisplayName;


import java.io.File;
import java.util.List;


public class JsonMapper {
    ObjectMapper objectMapper = new ObjectMapper().
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @SneakyThrows
    public SingleLineDiagram mapJsonToSld(String filePath) {
        return objectMapper.readValue(
                new File(filePath), SingleLineDiagram.class
        );
    }

    @SneakyThrows
    public static List<DeviceDirectory> mapJsonToDev(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper().
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        TypeFactory typeFactory = objectMapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(
                List.class, DeviceDirectory.class);
        return objectMapper.readValue(new File(filePath), collectionType);

    }

    @SneakyThrows
    public static List<VoltageLevelDirectory> mapJsonToVol(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper().
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        TypeFactory typeFactory = objectMapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(
                List.class, VoltageLevelDirectory.class);
        return objectMapper.readValue(new File(filePath), collectionType);

    }

}
