import java.sql.*;
/**
 * Progetto d'esempio che mostra com'è possibile interfacciarsi con i database tramite Java.
 * In questo progetto è stata implementata una semplice rubrica telefonica sfruttando un database MySQL con il relativo connettore (il connettore serve, appunto, a connettere il database con l'applicazione).
 * Si lascia al lettore l'implementazione di tutti i controlli necessari (come ad esempio: stringa vuota, numero errato e così via), del main ed, eventualmente, un codice di migliore qualità.
 * Trovate il database qui: http://cdpjavaexamples.altervista.org/Materiali/Database.sql (tasto destro -> salva con nome)
 * Trovate il connettore qui: http://cdpjavaexamples.altervista.org/Materiali/Connettore_Database__MySQL_1.1.14_.jar
 *
 * @author Cosimo Damiano Prete
 */
public class RubricaProva
{
    private static final String driver="com.mysql.jdbc.Driver"; //driver del connettore
    private static final String urlDB="jdbc:mysql://127.0.0.1:3306/db"; //indirizzo del database (cambia in base al connettore)
    private static final String username="root"; //username per accedere al database
    private static final String password=""; //password per accedere al database
    private Connection con; //connessione col database
    private Statement st; //usato per operazioni senza parametri
    private PreparedStatement ps; //utilizzato per operazioni con parametri che, nelle stesse, vengono rappresentati con punti interrogativi
    private ResultSet rs; //contiene i risultati delle operazioni di selezione
    
    /**
     * Costruttore al cui interno si carica il driver del connettore, si apre una connessione col database e si istanzia un oggetto di classe Statement
     */
    public RubricaProva(){
        try{
            Class.forName(driver);
            con=DriverManager.getConnection(urlDB, username, password);
            st=con.createStatement();
        }catch(ClassNotFoundException ex){
            System.err.println("> Errore!\n> Driver non trovato!");
            System.exit(1);
        }catch(SQLException ex){
            System.err.println("> Errore nell'inizializzazione del programma!");
            System.exit(1);
        }
    }
    /**
     * Inserisce un contatto nella rubrica
     * @param codCont il codice del contatto
     * @param nome il nome del contatto
     * @param cognome il cognome del contatto
     * @param numero il numero del contatto
     */
    public void inserisciContatto(int codCont, String nome, String cognome, String numero){
        try{
            System.out.println("> Inserisco il seguente contatto nel database:");
            System.out.println("\tCodice Contatto: " + codCont + "\tNome: " + nome + "\tCognome: " + cognome + "\tNumero: " + numero);
            String insert="INSERT INTO Contatto(Nome, Cognome) VALUES(?, ?)";
            ps=con.prepareStatement(insert);
            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.executeUpdate();
            ps.close();
            insert="INSERT INTO Numero(Numero, CodCont) VALUES(?, ?)";
            ps=con.prepareStatement(insert);
            ps.setString(1, numero);
            ps.setInt(2, codCont);
            ps.executeUpdate();
            ps.close();
            System.out.println("> Inserimento riuscito!");
        }catch(SQLException ex){
            System.err.println("> Errore nell'inserimento!");
            ex.printStackTrace();
        }
    }
    /**
     * Elimina un contatto dalla rubrica
     * @param conCont il codice del contatto da eliminare
     */
    public void eliminaContatto(int codCont){
        try{
           System.out.println("> Elimino il seguente contatto dal database:");
           System.out.println("\tCodice Contatto: " + codCont);
           String delete="DELETE FROM Contatto WHERE CodCont = ?";
           ps=con.prepareStatement(delete);
           ps.setInt(1, codCont);
           if(ps.executeUpdate()!=0){
               System.out.println("> Contatto eliminato!");
            }else{
                System.out.println("> Contatto non trovato...");
            }
           ps.close();
        }catch(SQLException ex){
            System.err.println("> Errore nella cancellazione!");
            ex.printStackTrace();
        }
    }
    /**
     * Aggiorna le informazioni di un contatto
     * @param codCont il codice del contatto
     * @param nuovoNome il nuovo nome del contatto
     * @param nuovoCognome il nuovo cognome del contatto
     * @param nuovoNumero il nuovo numero del contatto
     */
    public void aggiornaContatto(int codCont, String nuovoNome, String nuovoCognome, String nuovoNumero){
        try{
            System.out.println("> Aggiorno il seguente contatto dal database:");
            System.out.println("\tCodice Contatto: " + codCont);
            System.out.println("> Con questi nuovi dati:");
            System.out.println("\tNome: " + nuovoNome + "\tCognome: " + nuovoCognome + "\tNumero: " + nuovoNumero);
            String update="UPDATE Contatto SET Nome = ?, Cognome = ? WHERE CodCont = ?";
            ps=con.prepareStatement(update);
            ps.setString(1, nuovoNome);
            ps.setString(2, nuovoCognome);
            ps.setInt(3, codCont);
            if(ps.executeUpdate()!=0){
                ps.close();
                update="UPDATE Numero SET Numero = ? WHERE CodCont = ?";
                ps=con.prepareStatement(update);
                ps.setString(1, nuovoNumero);
                ps.setInt(2, codCont);
                ps.executeUpdate();
                ps.close();
                System.out.println("> Aggiornamento riuscito!");
            }else{
                System.out.println("> Contatto non trovato...");
            }
        }catch(SQLException ex){
            System.err.println("> Errore nell'aggiornamento!");
            ex.printStackTrace();
        }
    }
    /**
     * Ricerca un contatto nella rubrica
     * @param codCont il codice del contatto da ricercare
     */
    public void ricercaContatto(int codCont){
        try{
            System.out.println("> Ricerco il seguente contatto dal database:");
            System.out.println("\tCodice Contatto: " + codCont);
            String select="SELECT Nome, Cognome, Numero FROM Contatto, Numero WHERE Contatto.CodCont = ? AND Contatto.CodCont = Numero.CodCont";
            ps=con.prepareStatement(select);
            ps.setInt(1, codCont);
            rs=ps.executeQuery();
            if(rs.next()){
                System.out.println("\n\tNome\t\tCognome\t\tNumero");
                do{
                    System.out.println("\t" + rs.getString("Nome") + "\t" + rs.getString("Cognome") + "\t" + rs.getString("Numero"));
                }while(rs.next());
            }else{
                System.out.println("> Contatto non trovato...");
            }
            rs.close();
            ps.close();
        }catch(SQLException ex){
            System.err.println("> Errore nella ricerca!");
            ex.printStackTrace();
        }
    }
    /**
     * Stampa la rubrica
     */
    public void stampaRubrica(){
        try{
            System.out.println("> Stampo la rubrica");
            String select="SELECT Nome, Cognome, Numero FROM Contatto, Numero WHERE Contatto.CodCont = Numero.CodCont";
            rs=st.executeQuery(select);
            if(rs.next()){
               System.out.println("\n\tNome\t\tCognome\t\tNumero");
                do{
                    System.out.println("\t" + rs.getString("Nome") + "\t" + rs.getString("Cognome") + "\t" + rs.getString("Numero"));
                }while(rs.next());
            }else{
                System.out.println("> Contatto non trovato...");
            }
            rs.close();
            st.close();
        }catch(SQLException ex){
            System.err.println("> Errore nella stampa!");
            ex.printStackTrace();
        }
    }
    /**
     * Chiude la connessione col database
     */
    public void chiudiConnessione(){
        try{
            if(con!=null)con.close();
        }catch(SQLException ex){
            System.err.println("> Errore nella chiusura!");
            ex.printStackTrace();
        }
    }
}
