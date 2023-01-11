package com.appreservas.reservas.sessao.generated;

import com.appreservas.reservas.sessao.Sessao;
import com.appreservas.reservas.sessao.SessaoImpl;
import com.appreservas.reservas.sessao.SessaoManager;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.manager.AbstractManager;
import com.speedment.runtime.field.Field;

import java.util.stream.Stream;

/**
 * The generated base implementation for the manager of every {@link
 * com.appreservas.reservas.sessao.Sessao} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedSessaoManagerImpl 
extends AbstractManager<Sessao> 
implements GeneratedSessaoManager {
    
    private final TableIdentifier<Sessao> tableIdentifier;
    
    protected GeneratedSessaoManagerImpl() {
        this.tableIdentifier = TableIdentifier.of("appreservas", "reservas", "sessao");
    }
    
    @Override
    public Sessao create() {
        return new SessaoImpl();
    }
    
    @Override
    public TableIdentifier<Sessao> getTableIdentifier() {
        return tableIdentifier;
    }
    
    @Override
    public Stream<Field<Sessao>> fields() {
        return SessaoManager.FIELDS.stream();
    }
    
    @Override
    public Stream<Field<Sessao>> primaryKeyFields() {
        return Stream.of(
            Sessao.SESSAOID
        );
    }
}