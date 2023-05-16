package sparql;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;


public class SparqlSelector {

    private final String cimUri = "http://iec.ch/TC57/2013/CIM-schema-cim16#";

    public void modelToJavaObjects(Model model) {
        Repository repository = new SailRepository(new MemoryStore());
        RepositoryConnection connection = repository.getConnection();
        connection.add(model);

        String queryString = "PREFIX cim: <" + cimUri + "> " +
                "SELECT ?tId ?cnId " +
                "WHERE { " +
                " ?t a cim:ConnectivityNode ; " +
                " cim:IdentifiedObject.mRID ?tId ; " +
                " cim:ConnectivityNode.VoltageLevel ?cnId" +
                "}";
        TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL,
                queryString);
        try (TupleQueryResult result = query.evaluate()) {
            for (BindingSet solution : result) {
                String tId = solution.getValue("tId").stringValue();
                String cnId = solution.getValue("cnId").stringValue();
                System.out.println(tId + cnId);
            }
        }
    }

}