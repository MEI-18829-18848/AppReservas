package com.appreservas.reservas.reservalugar;

import java.sql.ResultSet;

import com.appreservas.Select.ISelect;

public class SelectReservalugar implements ISelect{
    private String className = this.getClass().getSimpleName().replace("Select", "").toLowerCase();
    private String idName = "reservaid";
    
    private static SelectReservalugar _instance = null;
    public static SelectReservalugar getInstance(){
        return _instance == null ? new SelectReservalugar() : _instance;
    }
    
    public String attachRows(ResultSet rs){
        String result = "";
        try {
            var id = rs.getInt("reservaid");
            var lugarid = rs.getInt("lugarid");
            result += String.format("{");
            result += String.format("\"reservaid\":\"%s\",", id);
            result += String.format("\"lugarid\":\"%s\",", lugarid);
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
