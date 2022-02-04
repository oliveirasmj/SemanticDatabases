package program;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdfconnection.RDFConnection ;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

public class JenaTDB1 {

    public static void opcao1(String word){

        try (RDFConnection conn = RDFConnection.connect("http://localhost:3030/projeto")) {

            /*String query =  "PREFIX db: <http://www.ua.pt/estga/MIA/bds/projeto#>" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                    + "SELECT ?Nome"
                    + " WHERE {"
                    + "?entity db:Nome ?Nome."
                    + "?entity rdf:type db:Medico."
                    + "}";*/

            String query =  "PREFIX db: <http://www.ua.pt/estga/MIA/bds/projeto#>" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                    + "SELECT ?Nome"
                    + " WHERE {"
                    + "?entity db:Nome ?Nome."
                    + "?entity rdf:type db:"+ word +"."
                    + "}";

            QueryExecution qExec = conn.query(query);
            ResultSet rs = qExec.execSelect();

            System.out.println("Lista de " + word + " :");
            while (rs.hasNext()) {
                QuerySolution qs = rs.next();
                Literal nome = qs.getLiteral("Nome");
                //Literal n = qs.getLiteral("DataNasc");

                System.out.println("Nome: " + nome.getString());
            }

        }
    }

    public static void opcao2(){

        try (RDFConnection conn = RDFConnection.connect("http://localhost:3030/projeto")) {

            String query =  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                            "PREFIX db: <http://www.ua.pt/estga/MIA/bds/projeto#>\n" +
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "SELECT ?nome\n" +
                    "WHERE {\n" +
                    "  ?entity rdf:type/rdfs:subClassOf* db:Funcionario .\n" +
                    "  ?entity db:Nome ?nome\n" +
                    "}";

            QueryExecution qExec = conn.query(query);
            ResultSet rs = qExec.execSelect();

            System.out.println("Lista de funcionarios: ");
            while (rs.hasNext()) {
                QuerySolution qs = rs.next();
                Literal nome = qs.getLiteral("nome");
                //Literal n = qs.getLiteral("DataNasc");

                System.out.println("Nome: " + nome.getString());
            }

        }
    }

    public static void opcao3(String word){

        try (RDFConnection conn = RDFConnection.connect("http://localhost:3030/projeto")) {

            /*String query =  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX db: <http://www.ua.pt/estga/MIA/bds/projeto#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "ASK { ?x db:Nome \"Miguel Oliveira\"}";*/

            String query =  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX db: <http://www.ua.pt/estga/MIA/bds/projeto#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "ASK { ?x db:Nome \""+ word +"\"}";

            QueryExecution qExec = conn.query(query);
            boolean rs = qExec.execAsk();

            if(rs) {
                System.out.println("Nome existente");
            } else {
                System.out.println("Nome não existe");
            }

        }
    }

    public static void opcao4(String word){

        try (RDFConnection conn = RDFConnection.connect("http://localhost:3030/projeto")) {

            String query =  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX db: <http://www.ua.pt/estga/MIA/bds/projeto#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX dbp: <http://dbpedia.org/property/> \n" +
                    "\n" +
                    "SELECT ?Nome\n" +
                    "WHERE {\n" +
                    "  ?entity rdf:type/rdfs:subClassOf* db:Funcionario .\n" +
                    "  ?entity db:Nome ?Nome\n" +
                    "  FILTER (\n" +
                    "       regex(?Nome, \""+ word +"\") \n" +
                    "   )\n" +
                    "}\n" +
                    "ORDER BY ?Nome";


            QueryExecution qExec = conn.query(query);
            ResultSet rs = qExec.execSelect();

            System.out.println("Lista de funcionarios com a string '" + word + " ' : ");
            while (rs.hasNext()) {
                QuerySolution qs = rs.next();
                Literal nome = qs.getLiteral("Nome");
                //Literal n = qs.getLiteral("DataNasc");

                System.out.println("Nome: " + nome.getString());
            }

        }
    }

    public static void opcao5(String name, String word, String date){

        try (RDFConnection conn = RDFConnection.connect("http://localhost:3030/projeto")) {

            String query =  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX db: <http://www.ua.pt/estga/MIA/bds/projeto#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX dbp: <http://dbpedia.org/property/> \n" +
                    "\n" +
                    "#Criar um funcionario\n" +
                    "INSERT DATA\n" +
                    "{\n" +
                    "\tdb:"+name+"\n" +
                    "\trdf:type db:"+word+";\n" +
                    "\tdb:Nome \""+name+"\";\n" +
                    "  \tdb:DataNasc \""+date+"\"^^xsd:dateTime;\n" +
                    "  \t#db:trabalhaEm\n" +
                    "  \t#db:Hospitalzeco\n" +
                    "}";

            UpdateRequest request = UpdateFactory.create(query);
            UpdateProcessor qe = UpdateExecutionFactory.createRemote(request,
                    "http://localhost:3030/projeto/update");
            qe.execute();

        }
    }

    public static void opcao6(String name){

        try (RDFConnection conn = RDFConnection.connect("http://localhost:3030/projeto")) {

            String query =  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX db: <http://www.ua.pt/estga/MIA/bds/projeto#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX dbp: <http://dbpedia.org/property/> \n" +
                    "\n" +
                    "INSERT DATA\n" +
                    "{\n" +
                    "\tdb:"+ name +"\n" +
                    "\trdf:type db:Hospital;\n" +
                    "\tdb:Nome \""+ name +"\"\n" +
                    "}";

            UpdateRequest request = UpdateFactory.create(query);
            UpdateProcessor qe = UpdateExecutionFactory.createRemote(request,
                    "http://localhost:3030/projeto/update");
            qe.execute();

        }
    }

    public static void opcao7(){

        try (RDFConnection conn = RDFConnection.connect("http://localhost:3030/projeto")) {

            String query =  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX db: <http://www.ua.pt/estga/MIA/bds/projeto#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "SELECT ?Nome\n" +
                    "WHERE {\n" +
                    "  ?entity db:Nome ?Nome. #os nomes\n" +
                    "  ?entity rdf:type db:Hospital.\n" +
                    "}";

            QueryExecution qExec = conn.query(query);
            ResultSet rs = qExec.execSelect();

            System.out.println("Lista de hospitais: ");
            while (rs.hasNext()) {
                QuerySolution qs = rs.next();
                Literal nome = qs.getLiteral("Nome");
                //Literal n = qs.getLiteral("DataNasc");

                System.out.println("Nome: " + nome.getString());
            }

        }
    }

    public static void opcao8(String name, String hospital){

        try (RDFConnection conn = RDFConnection.connect("http://localhost:3030/projeto")) {

            String query =  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX db: <http://www.ua.pt/estga/MIA/bds/projeto#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX dbp: <http://dbpedia.org/property/> \n" +
                    "\n" +
                    "INSERT DATA {\n" +
                    "\tdb:"+ name +" db:trabalhaEm db:"+ hospital +".\n" +
                    "}";

            UpdateRequest request = UpdateFactory.create(query);
            UpdateProcessor qe = UpdateExecutionFactory.createRemote(request,
                    "http://localhost:3030/projeto/update");
            qe.execute();

        }
    }

    public static void opcao9(String date, String hospital){

        try (RDFConnection conn = RDFConnection.connect("http://localhost:3030/projeto")) {

            String query =  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX db: <http://www.ua.pt/estga/MIA/bds/projeto#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX dbp: <http://dbpedia.org/property/> \n" +
                    "\n" +
                    "SELECT ?Nome ?DataNasc ?Hospital ?NomeHospital\n" +
                    "WHERE {\n" +
                    "  ?entity rdf:type ?type.\n" +
                    "  ?type rdfs:subClassOf* db:Funcionario. #todos os funcionarios\n" +
                    "  #?entity rdf:type db:Medico. #de todos os que sao do tipo medico\n" +
                    "  ?entity db:Nome ?Nome. #os nomes\n" +
                    "  ?entity db:DataNasc ?DataNasc\n" +
                    "  FILTER( ?DataNasc >= \""+ date +"\"^^xsd:dateTime) #maior ou igual ao ano 1995\n" +
                    "  \n" +
                    "  ?entity db:trabalhaEm ?Hospital. #trabalha em\n" +
                    "  ?Hospital db:Nome ?NomeHospital #nome do hospital\n" +
                    "  FILTER( ?NomeHospital = \""+ hospital +"\"^^xsd:string) #Hospital Infante D. Pedro\n" +
                    "}";


            QueryExecution qExec = conn.query(query);
            ResultSet rs = qExec.execSelect();

            System.out.println("Listar medicos a partir de uma data de nascimento '" + date + " e no Hospital " + hospital + " : ");
            while (rs.hasNext()) {
                QuerySolution qs = rs.next();
                Literal nome = qs.getLiteral("Nome");
                //Literal n = qs.getLiteral("DataNasc");

                System.out.println("Nome: " + nome.getString());
            }

        }
    }

    public static void opcao10(String word, String date, String hospital){

        try (RDFConnection conn = RDFConnection.connect("http://localhost:3030/projeto")) {

            String query =  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX db: <http://www.ua.pt/estga/MIA/bds/projeto#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX dbp: <http://dbpedia.org/property/> \n" +
                    "\n" +
                    "SELECT ?Nome ?DataNasc ?Hospital ?NomeHospital\n" +
                    "WHERE {\n" +
                    "  ?entity rdf:type ?type.\n" +
                    "  ?type rdfs:subClassOf* db:Funcionario. #todos os funcionarios\n" +
                    "  ?entity rdf:type db:"+word+". #de todos os que sao do tipo medico\n" +
                    "  ?entity db:Nome ?Nome. #os nomes\n" +
                    "  ?entity db:DataNasc ?DataNasc\n" +
                    "  FILTER( ?DataNasc >= \""+date+"\"^^xsd:dateTime) #maior ou igual ao ano 1995\n" +
                    "  \n" +
                    "  ?entity db:trabalhaEm ?Hospital. #trabalha em\n" +
                    "  ?Hospital db:Nome ?NomeHospital #nome do hospital\n" +
                    "  FILTER( ?NomeHospital = \""+hospital+"\"^^xsd:string) #Hospital Infante D. Pedro\n" +
                    "}";


            QueryExecution qExec = conn.query(query);
            ResultSet rs = qExec.execSelect();

            System.out.println("Listar "+ word + " a partir de uma data de nascimento '" + date + " e no Hospital " + hospital + " : ");
            while (rs.hasNext()) {
                QuerySolution qs = rs.next();
                Literal nome = qs.getLiteral("Nome");
                //Literal n = qs.getLiteral("DataNasc");

                System.out.println("Nome: " + nome.getString());
            }

        }
    }

    public static void opcao11(){

        try (RDFConnection conn = RDFConnection.connect("http://localhost:3030/projeto")) {

            String query =  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX db: <http://www.ua.pt/estga/MIA/bds/projeto#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX dbp: <http://dbpedia.org/property/> \n" +
                    "\n" +
                    "SELECT (COUNT(?entity) AS ?triples) \n" +
                    "WHERE {\n" +
                    "\t?entity rdf:type ?type.\n" +
                    "  \t?type rdfs:subClassOf* db:Funcionario. #todos os funcionarios\n" +
                    "  \t?entity db:Nome ?Nome. #os nomes\n" +
                    "  \t?entity rdf:type db:Medico. #de todos os que sao do tipo medico\n" +
                    "  \n" +
                    "    ?entity db:trabalhaEm ?Hospital. #trabalha em\n" +
                    "    ?Hospital db:Nome ?NomeHospital #nome do hospital\n" +
                    "  \tFILTER( ?NomeHospital = \"Hospital Infante D. Pedro\"^^xsd:string) #Hospital Infante D. Pedro\n" +
                    "  \n" +
                    "  \t?entity db:especialistaEm ?Cardiologia. #especialista em\n" +
                    "  \t?Cardiologia db:Nome ?especialidade #nome da especialidade\n" +
                    "  \tFILTER( ?especialidade = \"Cardiologia\"^^xsd:string) #Cardiologia\n" +
                    "}\n" +
                    "LIMIT 5000";


            QueryExecution qExec = conn.query(query);
            ResultSet rs = qExec.execSelect();

            while (rs.hasNext()) {
                QuerySolution qs = rs.next();
                Literal nome = qs.getLiteral("triples");
                //Literal n = qs.getLiteral("DataNasc");

                System.out.println("Número de médicos: " + nome.getString());
            }

        }
    }
}
