package com.appreservas.reservas.sala;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.appreservas.Select.ISelect;
import com.appreservas.Select.Select;
import com.appreservas.reservas.sala.generated.GeneratedSalaController;

/**
 * REST controller logic
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
@RestController
public class SalaController extends GeneratedSalaController {
    Select query = Select.getInstance();
    ISelect sala = new SelectSala();
    String className = this.getClass().getSimpleName().replace("Controller", "").toLowerCase();
    
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/sala", produces = "application/json")
    public String list(
            @RequestParam(name = "filter", defaultValue = "[]") String filters,
            @RequestParam(name = "sort", defaultValue = "[]") String sorters,
            @RequestParam(value = "start", defaultValue = "0") long start,
            @RequestParam(value = "limit", defaultValue = "25") long limit) {

        return query.select(p -> sala.attachRows(p), sala.buildQuery(-1));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/sala/{salaid}", produces = "application/json")
    public String get(
        @PathVariable(name = "salaid") int salaid) {

        return query.select(p -> sala.attachRows(p), sala.buildQuery(salaid));
    }
}