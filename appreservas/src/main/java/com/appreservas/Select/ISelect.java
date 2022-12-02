package com.appreservas.Select;

import java.sql.ResultSet;

public interface ISelect {
    String attachRows(ResultSet rs);
    String buildQuery(int id);
}
