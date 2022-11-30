package com.appreservas.reservas.organizador.generated;

import static com.speedment.runtime.core.util.ResultSetUtil.getInt;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.appreservas.reservas.organizador.Organizador;
import com.appreservas.reservas.organizador.OrganizadorImpl;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.component.SqlAdapter;
import com.speedment.runtime.core.db.SqlFunction;

/**
 * The generated Sql Adapter for a {@link
 * com.appreservas.reservas.organizador.Organizador} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedOrganizadorSqlAdapter implements SqlAdapter<Organizador> {
    
    private final TableIdentifier<Organizador> tableIdentifier;
    
    protected GeneratedOrganizadorSqlAdapter() {
        this.tableIdentifier = TableIdentifier.of("appreservas", "reservas", "organizador");
    }
    
    protected Organizador apply(ResultSet resultSet, int offset) throws SQLException {
        return createEntity()
            .setOrganizadorid( getInt(resultSet, 1 + offset))
            .setNome(          resultSet.getString(2 + offset))
            .setContacto(      resultSet.getString(3 + offset))
            .setUserid(        getInt(resultSet, 4 + offset))
            ;
    }
    
    protected OrganizadorImpl createEntity() {
        return new OrganizadorImpl();
    }
    
    @Override
    public TableIdentifier<Organizador> identifier() {
        return tableIdentifier;
    }
    
    @Override
    public SqlFunction<ResultSet, Organizador> entityMapper() {
        return entityMapper(0);
    }
    
    @Override
    public SqlFunction<ResultSet, Organizador> entityMapper(int offset) {
        return rs -> apply(rs, offset);
    }
}