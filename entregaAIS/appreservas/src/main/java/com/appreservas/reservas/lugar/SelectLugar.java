package com.appreservas.reservas.lugar;

import java.sql.ResultSet;

import com.appreservas.Select.ISelect;

public class SelectLugar implements ISelect{
    private String className = this.getClass().getSimpleName().replace("Select", "").toLowerCase();
    private String idName = this.getClass().getSimpleName().replace("Select", "").toLowerCase() + "id";
    
    private static SelectLugar _instance = null;
    public static SelectLugar getInstance(){
        return _instance == null ? new SelectLugar() : _instance;
    }
    
    public String attachRows(ResultSet rs){
        String result = "";
        try {
            var id = rs.getInt("lugarid");
            var salaid = rs.getInt("salaid");
            var nome = rs.getString("nome");
            var quantidade = rs.getInt("quantidade");
            var valor = rs.getDouble("valor");
            result += String.format("{");
            result += String.format("\"lugarid\":\"%s\",", id);
            result += String.format("\"salaid\":\"%s\",", salaid);
            result += String.format("\"nome\":\"%s\",", nome);
            result += String.format("\"quantidade\":\"%s\",", quantidade);
            result += String.format("\"valor\":\"%s\"", valor);
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
