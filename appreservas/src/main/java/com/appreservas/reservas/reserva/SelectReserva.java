package com.appreservas.reservas.reserva;

import java.sql.ResultSet;

import com.appreservas.Select.ISelect;

public class SelectReserva implements ISelect{
    private String className = this.getClass().getSimpleName().replace("Select", "").toLowerCase();
    private String idName = this.getClass().getSimpleName().replace("Select", "").toLowerCase() + "id";
    
    private static SelectReserva _instance = null;
    public static SelectReserva getInstance(){
        return _instance == null ? new SelectReserva() : _instance;
    }
    
    public String attachRows(ResultSet rs){
        String result = "";
        try {
            var id = rs.getInt("reservaid");
            var sessaoid = rs.getInt("sessaoid");
            var clienteid = rs.getInt("clienteid");
            var custo = rs.getDouble("custo");
            result += String.format("{");
            result += String.format("\"reservaid\":\"%s\",", id);
            result += String.format("\"sessaoid\":\"%s\",", sessaoid);
            result += String.format("\"clienteid\":\"%s\",", clienteid);
            result += String.format("\"custo\":\"%s\",", custo);
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
