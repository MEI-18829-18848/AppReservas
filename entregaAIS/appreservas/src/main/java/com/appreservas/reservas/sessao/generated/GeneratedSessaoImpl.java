package com.appreservas.reservas.sessao.generated;

import com.appreservas.reservas.evento.Evento;
import com.appreservas.reservas.sala.Sala;
import com.appreservas.reservas.sessao.Sessao;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.core.util.OptionalUtil;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.StringJoiner;

/**
 * The generated base implementation of the {@link
 * com.appreservas.reservas.sessao.Sessao}-interface.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedSessaoImpl implements Sessao {
    
    private int sessaoid;
    private int eventoid;
    private int salaid;
    private Integer duracao;
    private Timestamp data;
    
    protected GeneratedSessaoImpl() {}
    
    @Override
    public int getSessaoid() {
        return sessaoid;
    }
    
    @Override
    public int getEventoid() {
        return eventoid;
    }
    
    @Override
    public int getSalaid() {
        return salaid;
    }
    
    @Override
    public OptionalInt getDuracao() {
        return OptionalUtil.ofNullable(duracao);
    }
    
    @Override
    public Optional<Timestamp> getData() {
        return Optional.ofNullable(data);
    }
    
    @Override
    public Sessao setSessaoid(int sessaoid) {
        this.sessaoid = sessaoid;
        return this;
    }
    
    @Override
    public Sessao setEventoid(int eventoid) {
        this.eventoid = eventoid;
        return this;
    }
    
    @Override
    public Sessao setSalaid(int salaid) {
        this.salaid = salaid;
        return this;
    }
    
    @Override
    public Sessao setDuracao(Integer duracao) {
        this.duracao = duracao;
        return this;
    }
    
    @Override
    public Sessao setData(Timestamp data) {
        this.data = data;
        return this;
    }
    
    @Override
    public Evento findEventoid(Manager<Evento> foreignManager) {
        return foreignManager.stream().filter(Evento.EVENTOID.equal(getEventoid())).findAny().orElse(null);
    }
    
    @Override
    public Sala findSalaid(Manager<Sala> foreignManager) {
        return foreignManager.stream().filter(Sala.SALAID.equal(getSalaid())).findAny().orElse(null);
    }
    
    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner(", ", "{ ", " }");
        sj.add("sessaoid = " + Objects.toString(getSessaoid()));
        sj.add("eventoid = " + Objects.toString(getEventoid()));
        sj.add("salaid = "   + Objects.toString(getSalaid()));
        sj.add("duracao = "  + Objects.toString(OptionalUtil.unwrap(getDuracao())));
        sj.add("data = "     + Objects.toString(OptionalUtil.unwrap(getData())));
        return "SessaoImpl " + sj.toString();
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (!(that instanceof Sessao)) { return false; }
        final Sessao thatSessao = (Sessao)that;
        if (this.getSessaoid() != thatSessao.getSessaoid()) { return false; }
        if (this.getEventoid() != thatSessao.getEventoid()) { return false; }
        if (this.getSalaid() != thatSessao.getSalaid()) { return false; }
        if (!Objects.equals(this.getDuracao(), thatSessao.getDuracao())) { return false; }
        if (!Objects.equals(this.getData(), thatSessao.getData())) { return false; }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Integer.hashCode(getSessaoid());
        hash = 31 * hash + Integer.hashCode(getEventoid());
        hash = 31 * hash + Integer.hashCode(getSalaid());
        hash = 31 * hash + Objects.hashCode(OptionalUtil.unwrap(getDuracao()));
        hash = 31 * hash + Objects.hashCode(OptionalUtil.unwrap(getData()));
        return hash;
    }
}