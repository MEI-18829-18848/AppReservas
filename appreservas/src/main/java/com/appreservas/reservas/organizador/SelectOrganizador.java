package com.appreservas.reservas.organizador;

import java.sql.ResultSet;

import com.appreservas.Select.ISelect;

public class SelectOrganizador implements ISelect{
    private String className = this.getClass().getSimpleName().replace("Select", "").toLowerCase();
    private String idName = this.getClass().getSimpleName().replace("Select", "").toLowerCase() + "id";
    
    private static SelectOrganizador _instance = null;
    public static SelectOrganizador getInstance(){
        return _instance == null ? new SelectOrganizador() : _instance;
    }
    
    public String attachRows(ResultSet rs){
        String result = "";
        try {
            var id = rs.getInt("organizadorid");
            var nome = rs.getString("nome");
            var contacto = rs.getString("contacto");
            var userid = rs.getInt("userid");
            result += String.format("{");
            result += String.format("\"organizadorid\":\"%s\",", id);
            result += String.format("\"nome\":\"%s\",", nome);
            result += String.format("\"contacto\":\"%s\",", contacto);
            result += String.format("\"userid\":\"%s\",", userid);
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
