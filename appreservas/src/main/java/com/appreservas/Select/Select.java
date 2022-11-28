package com.appreservas.Select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.function.*;;

public class Select
{
    private static Select _instance = null;
    public static Select getInstance()
    {
        if (_instance == null)
            _instance = new Select();
  
        return _instance;
    }

    public Connection connection;

    private Select(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appreservas", "postgres", "postgres");
            //     c.setAutoCommit(false);
            System.out.println("Successfully Connected.");
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    public void setConnection( String args[] ) 
    {
        
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/appreservas", "postgres", "postgres");
            //     c.setAutoCommit(false);
            System.out.println("Successfully Connected.");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }

        System.out.println(" Data Retrieved Successfully ..");
    }

    public String select(
        Function<ResultSet, String> resultsCollector, 
        String query)
    {
        String result = "[";
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            result += rs.next() ? resultsCollector.apply(rs) : "";

            while ( rs.next() )
                result += ",\n" + resultsCollector.apply(rs);

            rs.close();
            stmt.close();
        }
        catch(Exception exc){
            System.out.println(exc.getMessage());
            return "Error ocurred";
        }
        return result + "]";
    }

    protected void finalize() throws Throwable  
    {  
        connection.close();
    }  
}