package converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import dto.ObjectInSubstation;
import dto.Terminal;
//import ru.mpei.cimmaintainer.cimClasses.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Getter
@Setter
public class CimToJavaConverter {

    ObjectInSubstation objectInSubstation = new ObjectInSubstation();

    public  void converterCimToJava(Model model) {

        Repository repository = new SailRepository(new MemoryStore());
        RepositoryConnection connection = repository.getConnection();
        connection.add(model);
        getTerminals(connection);
//        getEquipment(connection);
//      getConnection();

    }

    public  void getTerminals(RepositoryConnection connection) {

        String queryString = "PREFIX cim: <" + "http://iec.ch/TC57/2013/CIM-schema-cim16#" + "> " +
                "SELECT ?tId ?name ?cnId ?ceId " +
                "WHERE { " +
                " ?t a cim:Terminal ; " +
                " cim:IdentifiedObject.mRID ?tId ; " +
                " cim:IdentifiedObject.name ?name; " +
//                " cim:Terminal.ConductingEquipment ?ce ; " +
                " cim:Terminal.ConnectivityNode ?cn . " +
//                " ?cn cim:IdentifiedObject.mRID ?cnId ." +
//                " ?ce cim:IdentifiedObject.mRID ?ceId ." +
                "}";
        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);


        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet solution : result) {

                String name = solution.getValue("name").stringValue();
                String tId = solution.getValue("tId").stringValue();
//                String cnId = solution.getValue("cnId").stringValue();
//                String ceId = solution.getValue("ceId").stringValue();

                Terminal terminal = new Terminal(name, tId);   //, ceId, cnId);
                objectInSubstation.getTerminals().add(terminal);
            }
            System.out.println();
        }
    }

    public void WriteClassesToJson() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(
                new File("src/test/resources/jsonModel.json")
                ,objectInSubstation);
        System.out.println();
    }
}
