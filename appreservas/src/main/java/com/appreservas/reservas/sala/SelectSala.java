package com.appreservas.reservas.sala;

import java.sql.ResultSet;

import com.appreservas.Select.ISelect;

public class SelectSala implements ISelect{
    private static SelectSala _instance = null;

    public static SelectSala getInstance(){
        return _instance == null ? new SelectSala() : _instance;
    }

    String className = this.getClass().getSimpleName().replace("Select", "").toLowerCase();
    String idName = this.getClass().getSimpleName().replace("Select", "").toLowerCase() + "id";
    
    public String attachRows(ResultSet rs){
        String result = "";
        try {
            var id = rs.getInt("salaid");
            var nome = rs.getString("nome");
            var localizacao = rs.getString("localizacao");
            var lugaresmarcados = rs.getBoolean("lugaresmarcados");
            var lotacao = rs.getInt("lotacao");
            result += String.format("{");
            result += String.format("\"salaid\":\"%s\",", id);
            result += String.format("\"nome\":\"%s\",", nome);
            result += String.format("\"localizacao\":\"%s\",", localizacao);
            result += String.format("\"lugaresmarcados\":\"%s\",", lugaresmarcados);
            result += String.format("\"lotacao\":\"%s\",", lotacao);
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
