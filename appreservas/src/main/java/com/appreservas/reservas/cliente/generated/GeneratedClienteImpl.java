package com.appreservas.reservas.cliente.generated;

import com.appreservas.reservas.cliente.Cliente;
import com.appreservas.reservas.utilizador.Utilizador;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.core.util.OptionalUtil;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.StringJoiner;

/**
 * The generated base implementation of the {@link
 * com.appreservas.reservas.cliente.Cliente}-interface.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedClienteImpl implements Cliente {
    
    private int clienteid;
    private int userid;
    private String nome;
    private Integer telemovel;
    private String email;
    private Integer nif;
    
    protected GeneratedClienteImpl() {}
    
    @Override
    public int getClienteid() {
        return clienteid;
    }
    
    @Override
    public int getUserid() {
        return userid;
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public OptionalInt getTelemovel() {
        return OptionalUtil.ofNullable(telemovel);
    }
    
    @Override
    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }
    
    @Override
    public OptionalInt getNif() {
        return OptionalUtil.ofNullable(nif);
    }
    
    @Override
    public Cliente setClienteid(int clienteid) {
        this.clienteid = clienteid;
        return this;
    }
    
    @Override
    public Cliente setUserid(int userid) {
        this.userid = userid;
        return this;
    }
    
    @Override
    public Cliente setNome(String nome) {
        this.nome = nome;
        return this;
    }
    
    @Override
    public Cliente setTelemovel(Integer telemovel) {
        this.telemovel = telemovel;
        return this;
    }
    
    @Override
    public Cliente setEmail(String email) {
        this.email = email;
        return this;
    }
    
    @Override
    public Cliente setNif(Integer nif) {
        this.nif = nif;
        return this;
    }
    
    @Override
    public Utilizador findUserid(Manager<Utilizador> foreignManager) {
        return foreignManager.stream().filter(Utilizador.UTILIZADORID.equal(getUserid())).findAny().orElse(null);
    }
    
    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner(", ", "{ ", " }");
        sj.add("clienteid = " + Objects.toString(getClienteid()));
        sj.add("userid = "    + Objects.toString(getUserid()));
        sj.add("nome = "      + Objects.toString(getNome()));
        sj.add("telemovel = " + Objects.toString(OptionalUtil.unwrap(getTelemovel())));
        sj.add("email = "     + Objects.toString(OptionalUtil.unwrap(getEmail())));
        sj.add("nif = "       + Objects.toString(OptionalUtil.unwrap(getNif())));
        return "ClienteImpl " + sj.toString();
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (!(that instanceof Cliente)) { return false; }
        final Cliente thatCliente = (Cliente)that;
        if (this.getClienteid() != thatCliente.getClienteid()) { return false; }
        if (this.getUserid() != thatCliente.getUserid()) { return false; }
        if (!Objects.equals(this.getNome(), thatCliente.getNome())) { return false; }
        if (!Objects.equals(this.getTelemovel(), thatCliente.getTelemovel())) { return false; }
        if (!Objects.equals(this.getEmail(), thatCliente.getEmail())) { return false; }
        if (!Objects.equals(this.getNif(), thatCliente.getNif())) { return false; }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Integer.hashCode(getClienteid());
        hash = 31 * hash + Integer.hashCode(getUserid());
        hash = 31 * hash + Objects.hashCode(getNome());
        hash = 31 * hash + Objects.hashCode(OptionalUtil.unwrap(getTelemovel()));
        hash = 31 * hash + Objects.hashCode(OptionalUtil.unwrap(getEmail()));
        hash = 31 * hash + Objects.hashCode(OptionalUtil.unwrap(getNif()));
        return hash;
    }
}