package com.appreservas.reservas.reservalugar.generated;

import com.appreservas.reservas.reservalugar.Reservalugar;
import com.appreservas.reservas.reservalugar.ReservalugarManager;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.common.json.Json;
import com.speedment.enterprise.plugins.json.JsonCollectors;
import com.speedment.enterprise.plugins.json.JsonComponent;
import com.speedment.enterprise.plugins.json.JsonEncoder;
import com.speedment.enterprise.plugins.spring.runtime.AbstractFilter;
import com.speedment.enterprise.plugins.spring.runtime.AbstractSort;
import com.speedment.enterprise.plugins.spring.runtime.ControllerUtil;
import com.speedment.runtime.core.manager.Persister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;

import static java.util.stream.Collectors.toList;

/**
 * The default REST controller logic for Reservalugar entities.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/reservas")
public abstract class GeneratedReservalugarController {
    
    protected @Autowired JsonComponent jsonComponent;
    protected @Autowired ReservalugarManager manager;
    protected JsonEncoder<Reservalugar> encoder;
    
    @PostConstruct
    void createReservalugarEncoder() {
        encoder = jsonComponent.<Reservalugar>emptyEncoder()
            .put("reservaid", Reservalugar.RESERVAID)
            .put("lugarid", Reservalugar.LUGARID)
            .build();
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/reservalugar", produces = "application/json")
    public String list(
            @RequestParam(name = "filter", defaultValue = "[]") String filters,
            @RequestParam(name = "sort", defaultValue = "[]") String sorters,
            @RequestParam(value = "start", defaultValue = "0") long start,
            @RequestParam(value = "limit", defaultValue = "25") long limit) {
        
        return listHelper(
            ControllerUtil.parseFilters(filters, ReservalugarFilter::new).collect(toList()),
            ControllerUtil.parseSorts(sorters, ReservalugarSort::new).collect(toList()),
            start,
            limit
        );
    }
    
    protected String listHelper(
            List<Predicate<Reservalugar>> predicates,
            List<Comparator<Reservalugar>> sorters,
            long start,
            long limit) {
        Stream<Reservalugar> stream = manager.stream();
        
        for (final Predicate<Reservalugar> predicate : predicates) {
            stream = stream.filter(predicate);
        }
        
        if (!sorters.isEmpty()) {
            final Optional<Comparator<Reservalugar>> comparator = sorters.stream()
                .reduce(Comparator::thenComparing);
            
            stream = stream.sorted(comparator.get());
        }
        
        return stream
            .skip(start)
            .limit(limit)
            .collect(JsonCollectors.toList(encoder));
    }
    
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(path = "/reservalugar", consumes = "application/json")
    public void create(
            @RequestBody @Validated ReservalugarCreateBody createBody) {
        final Persister<Reservalugar> persister = manager.persister();
        final Reservalugar reservalugar = manager.create()
            .setReservaid(createBody.getReservaid())
            .setLugarid(createBody.getLugarid())
        ;
        
        persister.accept(reservalugar);
    }
    
    @ExceptionHandler(JsonMappingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMissingValueError() {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Bad Request");
        error.put("status", 400);
        error.put("message", "Invalid request body: missing required fields");
        
        return Json.toJson(error, true);
    }
    
    @ExceptionHandler(JsonParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidJsonError() {
        Map<String, Object> error = new HashMap<>();
        error.put("error", "Bad Request");
        error.put("status", 400);
        error.put("message", "Invalid request body: invalid JSON syntax");
        
        return Json.toJson(error, true);
    }
    
    /**
     * How to filter the results from the controller. This class is designed to
     * be deserialized automatically from JSON.
     */
    public static final class ReservalugarFilter extends AbstractFilter<Reservalugar> {
        
        public ReservalugarFilter(
                String operator,
                String property,
                String value) {
            super(operator, property, value);
        }
        
        @Override
        public Predicate<Reservalugar> toPredicate() {
            switch (property()) {
                case "reservaid" : {
                    final int v = Integer.parseInt(value());
                    switch (operator()) {
                        case "eq"   : return Reservalugar.RESERVAID.equal(v);
                        case "ne"   : return Reservalugar.RESERVAID.notEqual(v);
                        case "lt"   : return Reservalugar.RESERVAID.lessThan(v);
                        case "le"   : return Reservalugar.RESERVAID.lessOrEqual(v);
                        case "gt"   : return Reservalugar.RESERVAID.greaterThan(v);
                        case "ge"   : return Reservalugar.RESERVAID.greaterOrEqual(v);
                        case "like" : // Fallthrough
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Reservalugar.reservaid."
                        );
                    }
                }
                case "lugarid" : {
                    final int v = Integer.parseInt(value());
                    switch (operator()) {
                        case "eq"   : return Reservalugar.LUGARID.equal(v);
                        case "ne"   : return Reservalugar.LUGARID.notEqual(v);
                        case "lt"   : return Reservalugar.LUGARID.lessThan(v);
                        case "le"   : return Reservalugar.LUGARID.lessOrEqual(v);
                        case "gt"   : return Reservalugar.LUGARID.greaterThan(v);
                        case "ge"   : return Reservalugar.LUGARID.greaterOrEqual(v);
                        case "like" : // Fallthrough
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Reservalugar.lugarid."
                        );
                    }
                }
                default : throw new IllegalArgumentException(
                    "'" + property() + "' is not a valid Reservalugar property."
                );
            }
        }
    }
    
    /**
     * How to sort the results from the controller. This class is designed to be
     * deserialized automatically from JSON.
     */
    public static final class ReservalugarSort extends AbstractSort<Reservalugar> {
        
        public ReservalugarSort(String property, String direction) {
            super(property, direction);
        }
        
        @Override
        public Comparator<Reservalugar> toComparator() {
            final Comparator<Reservalugar> comparator;
            switch (property()) {
                case "reservaid" : comparator = Reservalugar.RESERVAID.comparator(); break;
                case "lugarid"   : comparator = Reservalugar.LUGARID.comparator();   break;
                default : throw new IllegalArgumentException(
                    "'" + property() + "' is not a valid Reservalugar property."
                );
            }
            
            switch (direction()) {
                case "ASC"  : return comparator;
                case "DESC" : return comparator.reversed();
                default : throw new IllegalArgumentException(
                    "'" + direction() + "' is not a valid sort direction. " +
                    "Use either 'ASC' or 'DESC', or leave out."
                );
            }
        }
    }
    
    @JsonInclude(Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class ReservalugarCreateBody {
        
        private final int reservaid;
        private final int lugarid;
        
        @JsonCreator
        public ReservalugarCreateBody(
                @JsonProperty("reservaid") Integer reservaid,
                @JsonProperty("lugarid") Integer lugarid) {
            this.reservaid = Objects.requireNonNull(reservaid, "`reservaid` is required");
            this.lugarid = Objects.requireNonNull(lugarid, "`lugarid` is required");
        }
        
        public int getReservaid() {
            return this.reservaid;
        }
        
        public int getLugarid() {
            return this.lugarid;
        }
    }
}