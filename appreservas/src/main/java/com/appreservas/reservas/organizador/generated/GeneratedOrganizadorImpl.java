package com.appreservas.reservas.organizador.generated;

import com.appreservas.reservas.organizador.Organizador;
import com.appreservas.reservas.utilizador.Utilizador;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.core.util.OptionalUtil;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * The generated base implementation of the {@link
 * com.appreservas.reservas.organizador.Organizador}-interface.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedOrganizadorImpl implements Organizador {
    
    private int organizadorid;
    private String nome;
    private String contacto;
    private int userid;
    
    protected GeneratedOrganizadorImpl() {}
    
    @Override
    public int getOrganizadorid() {
        return organizadorid;
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public Optional<String> getContacto() {
        return Optional.ofNullable(contacto);
    }
    
    @Override
    public int getUserid() {
        return userid;
    }
    
    @Override
    public Organizador setOrganizadorid(int organizadorid) {
        this.organizadorid = organizadorid;
        return this;
    }
    
    @Override
    public Organizador setNome(String nome) {
        this.nome = nome;
        return this;
    }
    
    @Override
    public Organizador setContacto(String contacto) {
        this.contacto = contacto;
        return this;
    }
    
    @Override
    public Organizador setUserid(int userid) {
        this.userid = userid;
        return this;
    }
    
    @Override
    public Utilizador findUserid(Manager<Utilizador> foreignManager) {
        return foreignManager.stream().filter(Utilizador.UTILIZADORID.equal(getUserid())).findAny().orElse(null);
    }
    
    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner(", ", "{ ", " }");
        sj.add("organizadorid = " + Objects.toString(getOrganizadorid()));
        sj.add("nome = "          + Objects.toString(getNome()));
        sj.add("contacto = "      + Objects.toString(OptionalUtil.unwrap(getContacto())));
        sj.add("userid = "        + Objects.toString(getUserid()));
        return "OrganizadorImpl " + sj.toString();
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (!(that instanceof Organizador)) { return false; }
        final Organizador thatOrganizador = (Organizador)that;
        if (this.getOrganizadorid() != thatOrganizador.getOrganizadorid()) { return false; }
        if (!Objects.equals(this.getNome(), thatOrganizador.getNome())) { return false; }
        if (!Objects.equals(this.getContacto(), thatOrganizador.getContacto())) { return false; }
        if (this.getUserid() != thatOrganizador.getUserid()) { return false; }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Integer.hashCode(getOrganizadorid());
        hash = 31 * hash + Objects.hashCode(getNome());
        hash = 31 * hash + Objects.hashCode(OptionalUtil.unwrap(getContacto()));
        hash = 31 * hash + Integer.hashCode(getUserid());
        return hash;
    }
}