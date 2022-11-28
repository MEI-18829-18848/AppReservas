package com.appreservas.generated;

import com.appreservas.ReservasApplication;
import com.appreservas.ReservasApplicationBuilder;
import com.appreservas.ReservasApplicationImpl;
import com.appreservas.ReservasCacheReloader;
import com.appreservas.ReservasInjectorProxy;
import com.appreservas.reservas.cliente.ClienteManagerImpl;
import com.appreservas.reservas.cliente.ClienteSqlAdapter;
import com.appreservas.reservas.evento.EventoManagerImpl;
import com.appreservas.reservas.evento.EventoSqlAdapter;
import com.appreservas.reservas.lugar.LugarManagerImpl;
import com.appreservas.reservas.lugar.LugarSqlAdapter;
import com.appreservas.reservas.organizador.OrganizadorManagerImpl;
import com.appreservas.reservas.organizador.OrganizadorSqlAdapter;
import com.appreservas.reservas.reserva.ReservaManagerImpl;
import com.appreservas.reservas.reserva.ReservaSqlAdapter;
import com.appreservas.reservas.reservalugar.ReservalugarManagerImpl;
import com.appreservas.reservas.reservalugar.ReservalugarSqlAdapter;
import com.appreservas.reservas.sala.SalaManagerImpl;
import com.appreservas.reservas.sala.SalaSqlAdapter;
import com.appreservas.reservas.sessao.SessaoManagerImpl;
import com.appreservas.reservas.sessao.SessaoSqlAdapter;
import com.appreservas.reservas.utilizador.UtilizadorManagerImpl;
import com.appreservas.reservas.utilizador.UtilizadorSqlAdapter;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.common.injector.Injector;
import com.speedment.runtime.application.AbstractApplicationBuilder;
import com.speedment.runtime.connector.postgres.PostgresBundle;

/**
 * A generated base {@link
 * com.speedment.runtime.application.AbstractApplicationBuilder} class for the
 * {@link com.speedment.runtime.config.Project} named appreservas.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedReservasApplicationBuilder extends AbstractApplicationBuilder<ReservasApplication, ReservasApplicationBuilder> {
    
    protected GeneratedReservasApplicationBuilder() {
        super(ReservasApplicationImpl.class, GeneratedReservasMetadata.class);
        withManager(ClienteManagerImpl.class);
        withManager(EventoManagerImpl.class);
        withManager(LugarManagerImpl.class);
        withManager(OrganizadorManagerImpl.class);
        withManager(ReservaManagerImpl.class);
        withManager(ReservalugarManagerImpl.class);
        withManager(SalaManagerImpl.class);
        withManager(SessaoManagerImpl.class);
        withManager(UtilizadorManagerImpl.class);
        withComponent(ClienteSqlAdapter.class);
        withComponent(EventoSqlAdapter.class);
        withComponent(LugarSqlAdapter.class);
        withComponent(OrganizadorSqlAdapter.class);
        withComponent(ReservaSqlAdapter.class);
        withComponent(ReservalugarSqlAdapter.class);
        withComponent(SalaSqlAdapter.class);
        withComponent(SessaoSqlAdapter.class);
        withComponent(UtilizadorSqlAdapter.class);
        withBundle(PostgresBundle.class);
        withInjectorProxy(new ReservasInjectorProxy());
        withComponent(ReservasCacheReloader.class);
    }
    
    @Override
    public ReservasApplication build(Injector injector) {
        return injector.getOrThrow(ReservasApplication.class);
    }
}