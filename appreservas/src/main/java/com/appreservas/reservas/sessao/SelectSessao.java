package com.appreservas.reservas.sessao;

import java.sql.ResultSet;

import com.appreservas.Select.ISelect;

public class SelectSessao implements ISelect{
    private static SelectSessao _instance = null;

    public static SelectSessao getInstance(){
        return _instance == null ? new SelectSessao() : _instance;
    }

    String className = this.getClass().getSimpleName().replace("Select", "").toLowerCase();
    String idName = this.getClass().getSimpleName().replace("Select", "").toLowerCase() + "id";
    
    public String attachRows(ResultSet rs){
        String result = "";
        try {
            var id = rs.getInt("sessaoid");
            var eventoid = rs.getInt("eventoid");
            var salaid = rs.getInt("salaid");
            var duracao = rs.getInt("duracao");
            var data = rs.getString("data");
            result += String.format("{");
            result += String.format("\"sessaoid\":\"%s\",", id);
            result += String.format("\"eventoid\":\"%s\",", eventoid);
            result += String.format("\"salaid\":\"%s\",", salaid);
            result += String.format("\"duracao\":\"%s\",", duracao);
            result += String.format("\"data\":\"%s\",", data);
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
