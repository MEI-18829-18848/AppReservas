package com.appreservas.reservas.cliente;

import java.sql.ResultSet;

import com.appreservas.Select.ISelect;

public class SelectCliente implements ISelect{
    private String className = this.getClass().getSimpleName().replace("Select", "").toLowerCase();
    private String idName = this.getClass().getSimpleName().replace("Select", "").toLowerCase() + "id";
    
    private static SelectCliente _instance = null;
    public static SelectCliente getInstance(){
        return _instance == null ? new SelectCliente() : _instance;
    }
    
    public String attachRows(ResultSet rs){
        String result = "";
        try {
            var id = rs.getInt("clienteid");
            var userid = rs.getInt("userid");
            var nome = rs.getString("nome");
            var telemovel = rs.getInt("telemovel");
            var email = rs.getString("email");
            var nif = rs.getInt("nif");
            result += String.format("{");
            result += String.format("\"clienteid\":\"%s\",", id);
            result += String.format("\"userid\":\"%s\",", userid);
            result += String.format("\"nome\":\"%s\",", nome);
            result += String.format("\"telemovel\":\"%s\",", telemovel);
            result += String.format("\"email\":\"%s\",", email);
            result += String.format("\"nif\":\"%s\"", nif);
            result += String.format("}");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return result;
        }
        return result;
    }

    public String buildQuery(int id){
        String query = String.format("SELECT * FROM \"reservas\".\"%s\" ", className);
        query += id == -1 ? "" : String.format("WHERE %s=%s", idName, id);
        return query + ";";
    }
}
