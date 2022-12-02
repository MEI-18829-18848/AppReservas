package com.appreservas.reservas.utilizador;

import java.sql.ResultSet;

import com.appreservas.Select.ISelect;

public class SelectUtilizador implements ISelect{
    private String className = this.getClass().getSimpleName().replace("Select", "").toLowerCase();
    private String idName = this.getClass().getSimpleName().replace("Select", "").toLowerCase() + "id";
    
    private static SelectUtilizador _instance = null;
    public static SelectUtilizador getInstance(){
        return _instance == null ? new SelectUtilizador() : _instance;
    }
    
    public String attachRows(ResultSet rs){
        String result = "";
        try {
            var id = rs.getInt("utilizadorid");
            var role = rs.getString("role");
            result += String.format("{");
            result += String.format("\"sessaoid\":\"%s\",", id);
            result += String.format("\"role\":\"%s\",", role);
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
