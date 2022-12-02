package com.appreservas.reservas.organizador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.appreservas.Select.ISelect;
import com.appreservas.Select.Select;
import com.appreservas.reservas.organizador.generated.GeneratedOrganizadorController;

/**
 * REST controller logic
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
@RestController
public class OrganizadorController extends GeneratedOrganizadorController {
    private Select query = Select.getInstance();
    private ISelect user = new SelectOrganizador();
    
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/organizador", produces = "application/json")
    public String list(
            @RequestParam(name = "filter", defaultValue = "[]") String filters,
            @RequestParam(name = "sort", defaultValue = "[]") String sorters,
            @RequestParam(value = "start", defaultValue = "0") long start,
            @RequestParam(value = "limit", defaultValue = "25") long limit) {

        return query.select(p -> user.attachRows(p), user.buildQuery(-1));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/organizador/{organizadorid}", produces = "application/json")
    public String get(
        @PathVariable(name = "organizadorid") int organizadorid) {

        return query.select(p -> user.attachRows(p), user.buildQuery(organizadorid));
    }}