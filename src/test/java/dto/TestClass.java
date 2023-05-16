package dto;

import bin.ElementsBinder;
import lombok.SneakyThrows;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.junit.jupiter.api.Test;
import converter.CimToJavaConverter;
import converter.SldToCimConverter;
import mapper.JsonMapper;
import org.eclipse.rdf4j.rio.Rio;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import sparql.SparqlSelector;
import static org.eclipse.rdf4j.rio.Rio.createParser;


public class TestClass {
    @Test @SneakyThrows
    public void test()  {

        /** Лабораторная работа №1
         * Отображение модели подстанции в CIM-модель */

        // Создание объектов Java
        JsonMapper jsonMapper = new JsonMapper();
        SingleLineDiagram sld = jsonMapper.mapJsonToSld("src/test/resources/Viezdnoe.json");
        List<DeviceDirectory> dev =  jsonMapper.mapJsonToDev("src/test/resources/DeviceDirectory.json");
        List<VoltageLevelDirectory> vol =  jsonMapper.mapJsonToVol("src/test/resources/VoltageLevelDirectory.json");
        ElementsBinder.bind(sld);

        // Конвертирование. Внутри converter есть сам modelBuilder
        SldToCimConverter converter = new SldToCimConverter();
        converter.convert(sld, dev, vol);

        // Создание файла CIM-модели в формате xml
        String cimModelXml = converter.getResult(".xml", RDFFormat.RDFXML);


        /** Лабораторная работа №2
         * Чтение информации о подстанции из CIM-модели */

        // Инициализация файла в формате xml
        File initialFile = new File("src/test/resources/LR1_cimModel2.xml");
        InputStream targetStream = new FileInputStream(initialFile);

        // Конвертация из xml в CIM-модель
        Model model = Rio.parse(targetStream,"http://iec.ch/TC57/2013/CIM-schema-cim16#", RDFFormat.RDFXML);

        // Конвертирование из CIM-модели в Java-объект
        CimToJavaConverter cimToJavaConverter = new CimToJavaConverter();
        cimToJavaConverter.converterCimToJava(model);

        // Выборка SparQL
        SparqlSelector spar = new SparqlSelector();
        spar.modelToJavaObjects(model);

        // Конвертирование из Java-объекта в JSON
        cimToJavaConverter.WriteClassesToJson();
        System.out.println(cimModelXml);
    }
}

