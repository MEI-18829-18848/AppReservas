package com.appreservas.reservas.evento;

import java.sql.ResultSet;

import com.appreservas.Select.ISelect;

public class SelectEvento implements ISelect{
    private String className = this.getClass().getSimpleName().replace("Select", "").toLowerCase();
    private String idName = this.getClass().getSimpleName().replace("Select", "").toLowerCase() + "id";
    
    private static SelectEvento _instance = null;
    public static SelectEvento getInstance(){
        return _instance == null ? new SelectEvento() : _instance;
    }
    
    public String attachRows(ResultSet rs){
        String result = "";
        try {
            var id = rs.getInt("eventoid");
            var salaid = rs.getInt("salaid");
            var organizadorid = rs.getInt("organizadorid");
            var nome = rs.getString("nome");
            var descricao = rs.getString("descricao");
            var categoria = rs.getString("categoria");
            var imdbid = rs.getString("imdbid");
            result += String.format("{");
            result += String.format("\"eventoid\":\"%s\",", id);
            result += String.format("\"salaid\":\"%s\",", salaid);
            result += String.format("\"organizadorid\":\"%s\",", organizadorid);
            result += String.format("\"nome\":\"%s\",", nome);
            result += String.format("\"descricao\":\"%s\",", descricao);
            result += String.format("\"categoria\":\"%s\"", categoria);
            result += String.format("\"imdbid\":\"%s\"", imdbid);
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
