package com.appreservas.reservas.reserva.generated;

import com.appreservas.reservas.cliente.Cliente;
import com.appreservas.reservas.reserva.Reserva;
import com.appreservas.reservas.sessao.Sessao;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.core.util.OptionalUtil;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * The generated base implementation of the {@link
 * com.appreservas.reservas.reserva.Reserva}-interface.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedReservaImpl implements Reserva {
    
    private int reservaid;
    private int sessaoid;
    private int clienteid;
    private BigDecimal custo;
    
    protected GeneratedReservaImpl() {}
    
    @Override
    public int getReservaid() {
        return reservaid;
    }
    
    @Override
    public int getSessaoid() {
        return sessaoid;
    }
    
    @Override
    public int getClienteid() {
        return clienteid;
    }
    
    @Override
    public Optional<BigDecimal> getCusto() {
        return Optional.ofNullable(custo);
    }
    
    @Override
    public Reserva setReservaid(int reservaid) {
        this.reservaid = reservaid;
        return this;
    }
    
    @Override
    public Reserva setSessaoid(int sessaoid) {
        this.sessaoid = sessaoid;
        return this;
    }
    
    @Override
    public Reserva setClienteid(int clienteid) {
        this.clienteid = clienteid;
        return this;
    }
    
    @Override
    public Reserva setCusto(BigDecimal custo) {
        this.custo = custo;
        return this;
    }
    
    @Override
    public Sessao findSessaoid(Manager<Sessao> foreignManager) {
        return foreignManager.stream().filter(Sessao.SESSAOID.equal(getSessaoid())).findAny().orElse(null);
    }
    
    @Override
    public Cliente findClienteid(Manager<Cliente> foreignManager) {
        return foreignManager.stream().filter(Cliente.CLIENTEID.equal(getClienteid())).findAny().orElse(null);
    }
    
    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner(", ", "{ ", " }");
        sj.add("reservaid = " + Objects.toString(getReservaid()));
        sj.add("sessaoid = "  + Objects.toString(getSessaoid()));
        sj.add("clienteid = " + Objects.toString(getClienteid()));
        sj.add("custo = "     + Objects.toString(OptionalUtil.unwrap(getCusto())));
        return "ReservaImpl " + sj.toString();
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (!(that instanceof Reserva)) { return false; }
        final Reserva thatReserva = (Reserva)that;
        if (this.getReservaid() != thatReserva.getReservaid()) { return false; }
        if (this.getSessaoid() != thatReserva.getSessaoid()) { return false; }
        if (this.getClienteid() != thatReserva.getClienteid()) { return false; }
        if (!Objects.equals(this.getCusto(), thatReserva.getCusto())) { return false; }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Integer.hashCode(getReservaid());
        hash = 31 * hash + Integer.hashCode(getSessaoid());
        hash = 31 * hash + Integer.hashCode(getClienteid());
        hash = 31 * hash + Objects.hashCode(OptionalUtil.unwrap(getCusto()));
        return hash;
    }
}