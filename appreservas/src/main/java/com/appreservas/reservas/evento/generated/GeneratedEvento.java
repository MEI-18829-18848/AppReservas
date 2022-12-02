package com.appreservas.reservas.evento.generated;

import com.appreservas.reservas.evento.Evento;
import com.appreservas.reservas.organizador.Organizador;
import com.appreservas.reservas.sala.Sala;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.enterprise.datastore.runtime.field.DatastoreFields;
import com.speedment.runtime.config.identifier.ColumnIdentifier;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.core.util.OptionalUtil;
import com.speedment.runtime.field.IntField;
import com.speedment.runtime.field.IntForeignKeyField;
import com.speedment.runtime.field.StringField;
import com.speedment.runtime.typemapper.TypeMapper;

import java.util.Optional;

/**
 * The generated base for the {@link
 * com.appreservas.reservas.evento.Evento}-interface representing
 * entities of the {@code evento}-table in the database.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public interface GeneratedEvento {
    
    /**
     * This Field corresponds to the {@link Evento} field that can be obtained
     * using the {@link Evento#getEventoid()} method.
     */
    IntField<Evento, Integer> EVENTOID = DatastoreFields.createIntField(
        Identifier.EVENTOID,
        Evento::getEventoid,
        Evento::setEventoid,
        TypeMapper.primitive(),
        true
    );
    /**
     * This Field corresponds to the {@link Evento} field that can be obtained
     * using the {@link Evento#getSalaid()} method.
     */
    IntForeignKeyField<Evento, Integer, Sala> SALAID = DatastoreFields.createIntForeignKeyField(
        Identifier.SALAID,
        Evento::getSalaid,
        Evento::setSalaid,
        Sala.SALAID,
        TypeMapper.primitive(),
        false
    );
    /**
     * This Field corresponds to the {@link Evento} field that can be obtained
     * using the {@link Evento#getOrganizadorid()} method.
     */
    IntForeignKeyField<Evento, Integer, Organizador> ORGANIZADORID = DatastoreFields.createIntForeignKeyField(
        Identifier.ORGANIZADORID,
        Evento::getOrganizadorid,
        Evento::setOrganizadorid,
        Organizador.ORGANIZADORID,
        TypeMapper.primitive(),
        false
    );
    /**
     * This Field corresponds to the {@link Evento} field that can be obtained
     * using the {@link Evento#getNome()} method.
     */
    StringField<Evento, String> NOME = DatastoreFields.createStringField(
        Identifier.NOME,
        o -> OptionalUtil.unwrap(o.getNome()),
        Evento::setNome,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link Evento} field that can be obtained
     * using the {@link Evento#getDescricao()} method.
     */
    StringField<Evento, String> DESCRICAO = DatastoreFields.createStringField(
        Identifier.DESCRICAO,
        o -> OptionalUtil.unwrap(o.getDescricao()),
        Evento::setDescricao,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link Evento} field that can be obtained
     * using the {@link Evento#getCategoria()} method.
     */
    StringField<Evento, String> CATEGORIA = DatastoreFields.createStringField(
        Identifier.CATEGORIA,
        o -> OptionalUtil.unwrap(o.getCategoria()),
        Evento::setCategoria,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link Evento} field that can be obtained
     * using the {@link Evento#getImdbid()} method.
     */
    StringField<Evento, String> IMDBID = DatastoreFields.createStringField(
        Identifier.IMDBID,
        o -> OptionalUtil.unwrap(o.getImdbid()),
        Evento::setImdbid,
        TypeMapper.identity(),
        false
    );
    
    /**
     * Returns the eventoid of this Evento. The eventoid field corresponds to
     * the database column appreservas.reservas.evento.eventoid.
     * 
     * @return the eventoid of this Evento
     */
    int getEventoid();
    
    /**
     * Returns the salaid of this Evento. The salaid field corresponds to the
     * database column appreservas.reservas.evento.salaid.
     * 
     * @return the salaid of this Evento
     */
    int getSalaid();
    
    /**
     * Returns the organizadorid of this Evento. The organizadorid field
     * corresponds to the database column
     * appreservas.reservas.evento.organizadorid.
     * 
     * @return the organizadorid of this Evento
     */
    int getOrganizadorid();
    
    /**
     * Returns the nome of this Evento. The nome field corresponds to the
     * database column appreservas.reservas.evento.nome.
     * 
     * @return the nome of this Evento
     */
    Optional<String> getNome();
    
    /**
     * Returns the descricao of this Evento. The descricao field corresponds to
     * the database column appreservas.reservas.evento.descricao.
     * 
     * @return the descricao of this Evento
     */
    Optional<String> getDescricao();
    
    /**
     * Returns the categoria of this Evento. The categoria field corresponds to
     * the database column appreservas.reservas.evento.categoria.
     * 
     * @return the categoria of this Evento
     */
    Optional<String> getCategoria();
    
    /**
     * Returns the imdbid of this Evento. The imdbid field corresponds to the
     * database column appreservas.reservas.evento.imdbid.
     * 
     * @return the imdbid of this Evento
     */
    Optional<String> getImdbid();
    
    /**
     * Sets the eventoid of this Evento. The eventoid field corresponds to the
     * database column appreservas.reservas.evento.eventoid.
     * 
     * @param eventoid to set of this Evento
     * @return         this Evento instance
     */
    Evento setEventoid(int eventoid);
    
    /**
     * Sets the salaid of this Evento. The salaid field corresponds to the
     * database column appreservas.reservas.evento.salaid.
     * 
     * @param salaid to set of this Evento
     * @return       this Evento instance
     */
    Evento setSalaid(int salaid);
    
    /**
     * Sets the organizadorid of this Evento. The organizadorid field
     * corresponds to the database column
     * appreservas.reservas.evento.organizadorid.
     * 
     * @param organizadorid to set of this Evento
     * @return              this Evento instance
     */
    Evento setOrganizadorid(int organizadorid);
    
    /**
     * Sets the nome of this Evento. The nome field corresponds to the database
     * column appreservas.reservas.evento.nome.
     * 
     * @param nome to set of this Evento
     * @return     this Evento instance
     */
    Evento setNome(String nome);
    
    /**
     * Sets the descricao of this Evento. The descricao field corresponds to the
     * database column appreservas.reservas.evento.descricao.
     * 
     * @param descricao to set of this Evento
     * @return          this Evento instance
     */
    Evento setDescricao(String descricao);
    
    /**
     * Sets the categoria of this Evento. The categoria field corresponds to the
     * database column appreservas.reservas.evento.categoria.
     * 
     * @param categoria to set of this Evento
     * @return          this Evento instance
     */
    Evento setCategoria(String categoria);
    
    /**
     * Sets the imdbid of this Evento. The imdbid field corresponds to the
     * database column appreservas.reservas.evento.imdbid.
     * 
     * @param imdbid to set of this Evento
     * @return       this Evento instance
     */
    Evento setImdbid(String imdbid);
    
    /**
     * Queries the specified manager for the referenced Sala. If no such Sala
     * exists, an {@code NullPointerException} will be thrown.
     * 
     * @param foreignManager the manager to query for the entity
     * @return               the foreign entity referenced
     */
    Sala findSalaid(Manager<Sala> foreignManager);
    
    /**
     * Queries the specified manager for the referenced Organizador. If no such
     * Organizador exists, an {@code NullPointerException} will be thrown.
     * 
     * @param foreignManager the manager to query for the entity
     * @return               the foreign entity referenced
     */
    Organizador findOrganizadorid(Manager<Organizador> foreignManager);
    
    enum Identifier implements ColumnIdentifier<Evento> {
        
        EVENTOID      ("eventoid"),
        SALAID        ("salaid"),
        ORGANIZADORID ("organizadorid"),
        NOME          ("nome"),
        DESCRICAO     ("descricao"),
        CATEGORIA     ("categoria"),
        IMDBID        ("imdbid");
        
        private final String columnId;
        private final TableIdentifier<Evento> tableIdentifier;
        
        Identifier(String columnId) {
            this.columnId        = columnId;
            this.tableIdentifier = TableIdentifier.of(    getDbmsId(), 
                getSchemaId(), 
                getTableId());
        }
        
        @Override
        public String getDbmsId() {
            return "appreservas";
        }
        
        @Override
        public String getSchemaId() {
            return "reservas";
        }
        
        @Override
        public String getTableId() {
            return "evento";
        }
        
        @Override
        public String getColumnId() {
            return this.columnId;
        }
        
        @Override
        public TableIdentifier<Evento> asTableIdentifier() {
            return this.tableIdentifier;
        }
    }
}