package com.appreservas.reservas.evento.generated;

import static com.speedment.runtime.core.util.ResultSetUtil.getInt;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.appreservas.reservas.evento.Evento;
import com.appreservas.reservas.evento.EventoImpl;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.component.SqlAdapter;
import com.speedment.runtime.core.db.SqlFunction;

/**
 * The generated Sql Adapter for a {@link
 * com.appreservas.reservas.evento.Evento} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedEventoSqlAdapter implements SqlAdapter<Evento> {
    
    private final TableIdentifier<Evento> tableIdentifier;
    
    protected GeneratedEventoSqlAdapter() {
        this.tableIdentifier = TableIdentifier.of("appreservas", "reservas", "evento");
    }
    
    protected Evento apply(ResultSet resultSet, int offset) throws SQLException {
        return createEntity()
            .setEventoid(      getInt(resultSet, 1 + offset))
            .setSalaid(        getInt(resultSet, 2 + offset))
            .setOrganizadorid( getInt(resultSet, 3 + offset))
            .setNome(          resultSet.getString(4 + offset))
            .setDescricao(     resultSet.getString(5 + offset))
            .setCategoria(     resultSet.getString(6 + offset))
            .setImdbid(        resultSet.getString(7 + offset))
            ;
    }
    
    protected EventoImpl createEntity() {
        return new EventoImpl();
    }
    
    @Override
    public TableIdentifier<Evento> identifier() {
        return tableIdentifier;
    }
    
    @Override
    public SqlFunction<ResultSet, Evento> entityMapper() {
        return entityMapper(0);
    }
    
    @Override
    public SqlFunction<ResultSet, Evento> entityMapper(int offset) {
        return rs -> apply(rs, offset);
    }
}