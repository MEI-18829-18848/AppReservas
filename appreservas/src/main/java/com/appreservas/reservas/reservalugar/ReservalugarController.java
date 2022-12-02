package com.appreservas.reservas.reservalugar;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.appreservas.Select.ISelect;
import com.appreservas.Select.Select;
import com.appreservas.reservas.reservalugar.generated.GeneratedReservalugarController;

/**
 * REST controller logic
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
@RestController
public class ReservalugarController extends GeneratedReservalugarController {
    Select query = Select.getInstance();
    ISelect user = new SelectReservalugar();
    String className = this.getClass().getSimpleName().replace("Controller", "").toLowerCase();
    
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/reservalugar", produces = "application/json")
    public String list(
            @RequestParam(name = "filter", defaultValue = "[]") String filters,
            @RequestParam(name = "sort", defaultValue = "[]") String sorters,
            @RequestParam(value = "start", defaultValue = "0") long start,
            @RequestParam(value = "limit", defaultValue = "25") long limit) {

        return query.select(p -> user.attachRows(p), className);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/reservalugar/{reservaid}", produces = "application/json")
    public String get(
        @PathVariable(name = "reservaid") int reservaid) {

        return query.select(p -> user.attachRows(p), user.buildQuery(reservaid));
    }
}