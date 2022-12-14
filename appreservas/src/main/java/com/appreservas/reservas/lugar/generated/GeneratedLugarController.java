package com.appreservas.reservas.lugar.generated;

import com.appreservas.reservas.lugar.Lugar;
import com.appreservas.reservas.lugar.LugarManager;
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
import com.speedment.runtime.core.manager.FieldSet;
import com.speedment.runtime.core.manager.Persister;
import com.speedment.runtime.core.manager.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
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
 * The default REST controller logic for Lugar entities.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
@CrossOrigin(origins = "*", maxAge = 3600)
public abstract class GeneratedLugarController {
    
    protected @Autowired JsonComponent jsonComponent;
    protected @Autowired LugarManager manager;
    protected JsonEncoder<Lugar> encoder;
    
    @PostConstruct
    void createLugarEncoder() {
        encoder = jsonComponent.<Lugar>emptyEncoder()
            .put("lugarid", Lugar.LUGARID)
            .put("salaid", Lugar.SALAID)
            .put("nome", Lugar.NOME)
            .put("quantidade", Lugar.QUANTIDADE)
            .put("valor", Lugar.VALOR)
            .build();
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/lugar", produces = "application/json")
    public String list(
            @RequestParam(name = "filter", defaultValue = "[]") String filters,
            @RequestParam(name = "sort", defaultValue = "[]") String sorters,
            @RequestParam(value = "start", defaultValue = "0") long start,
            @RequestParam(value = "limit", defaultValue = "25") long limit) {
        
        return listHelper(
            ControllerUtil.parseFilters(filters, LugarFilter::new).collect(toList()),
            ControllerUtil.parseSorts(sorters, LugarSort::new).collect(toList()),
            start,
            limit
        );
    }
    
    protected String listHelper(
            List<Predicate<Lugar>> predicates,
            List<Comparator<Lugar>> sorters,
            long start,
            long limit) {
        Stream<Lugar> stream = manager.stream();
        
        for (final Predicate<Lugar> predicate : predicates) {
            stream = stream.filter(predicate);
        }
        
        if (!sorters.isEmpty()) {
            final Optional<Comparator<Lugar>> comparator = sorters.stream()
                .reduce(Comparator::thenComparing);
            
            stream = stream.sorted(comparator.get());
        }
        
        return stream
            .skip(start)
            .limit(limit)
            .collect(JsonCollectors.toList(encoder));
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/lugar/{lugarid}", produces = "application/json")
    public String get(
            @PathVariable(name = "lugarid") int lugarid) {
        return encoder.apply(
            manager.stream()
                .filter(Lugar.LUGARID.equal(lugarid))
                .findFirst()
                .orElseThrow(() -> new LugarNotFoundException(lugarid))
        );
    }
    
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(path = "/lugar", consumes = "application/json")
    public void create(
            @RequestBody @Validated LugarCreateBody createBody) {
        final FieldSet<Lugar> excluded = FieldSet.allExcept(
            Lugar.LUGARID
        );
        
        final Persister<Lugar> persister = manager.persister(excluded);
        final Lugar lugar = manager.create()
            .setSalaid(createBody.getSalaid())
            .setNome(createBody.getNome())
            .setQuantidade(createBody.getQuantidade())
            .setValor(createBody.getValor())
        ;
        
        persister.accept(lugar);
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping(path = "/lugar/{lugarid}", consumes = "application/json")
    public void update(
            @PathVariable(name = "lugarid") int lugarid,
            @RequestBody @Validated LugarUpdateBody updateBody) {
        final FieldSet<Lugar> excluded = FieldSet.allExcept(
            Lugar.LUGARID
        );
        
        final Updater<Lugar> updater = manager.updater(excluded);
        final Lugar lugar = manager.stream()
            .filter(Lugar.LUGARID.equal(lugarid))
            .findFirst()
            .orElseThrow(() -> new LugarNotFoundException(lugarid));
        
        lugar.setSalaid(updateBody.getSalaid());
        lugar.setNome(updateBody.getNome());
        lugar.setQuantidade(updateBody.getQuantidade());
        lugar.setValor(updateBody.getValor());
        
        updater.accept(lugar);
    }
    
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/lugar/{lugarid}")
    public void delete(
            @PathVariable(name = "lugarid") int lugarid) {
        manager.stream()
            .filter(Lugar.LUGARID.equal(lugarid))
            .forEach(manager.remover());
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
    public static final class LugarFilter extends AbstractFilter<Lugar> {
        
        public LugarFilter(
                String operator,
                String property,
                String value) {
            super(operator, property, value);
        }
        
        @Override
        public Predicate<Lugar> toPredicate() {
            switch (property()) {
                case "lugarid" : {
                    final int v = Integer.parseInt(value());
                    switch (operator()) {
                        case "eq"   : return Lugar.LUGARID.equal(v);
                        case "ne"   : return Lugar.LUGARID.notEqual(v);
                        case "lt"   : return Lugar.LUGARID.lessThan(v);
                        case "le"   : return Lugar.LUGARID.lessOrEqual(v);
                        case "gt"   : return Lugar.LUGARID.greaterThan(v);
                        case "ge"   : return Lugar.LUGARID.greaterOrEqual(v);
                        case "like" : // Fallthrough
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Lugar.lugarid."
                        );
                    }
                }
                case "salaid" : {
                    final int v = Integer.parseInt(value());
                    switch (operator()) {
                        case "eq"   : return Lugar.SALAID.equal(v);
                        case "ne"   : return Lugar.SALAID.notEqual(v);
                        case "lt"   : return Lugar.SALAID.lessThan(v);
                        case "le"   : return Lugar.SALAID.lessOrEqual(v);
                        case "gt"   : return Lugar.SALAID.greaterThan(v);
                        case "ge"   : return Lugar.SALAID.greaterOrEqual(v);
                        case "like" : // Fallthrough
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Lugar.salaid."
                        );
                    }
                }
                case "nome" : {
                    final String v = value();
                    switch (operator()) {
                        case "eq"   : return Lugar.NOME.equal(v);
                        case "ne"   : return Lugar.NOME.notEqual(v);
                        case "lt"   : return Lugar.NOME.lessThan(v);
                        case "le"   : return Lugar.NOME.lessOrEqual(v);
                        case "gt"   : return Lugar.NOME.greaterThan(v);
                        case "ge"   : return Lugar.NOME.greaterOrEqual(v);
                        case "like" : return Lugar.NOME.contains(v);
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Lugar.nome."
                        );
                    }
                }
                case "quantidade" : {
                    final Integer v = Integer.parseInt(value());
                    switch (operator()) {
                        case "eq"   : return Lugar.QUANTIDADE.equal(v);
                        case "ne"   : return Lugar.QUANTIDADE.notEqual(v);
                        case "lt"   : return Lugar.QUANTIDADE.lessThan(v);
                        case "le"   : return Lugar.QUANTIDADE.lessOrEqual(v);
                        case "gt"   : return Lugar.QUANTIDADE.greaterThan(v);
                        case "ge"   : return Lugar.QUANTIDADE.greaterOrEqual(v);
                        case "like" : // Fallthrough
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Lugar.quantidade."
                        );
                    }
                }
                case "valor" : {
                    final BigDecimal v = new BigDecimal(value());
                    switch (operator()) {
                        case "eq"   : return Lugar.VALOR.equal(v);
                        case "ne"   : return Lugar.VALOR.notEqual(v);
                        case "lt"   : return Lugar.VALOR.lessThan(v);
                        case "le"   : return Lugar.VALOR.lessOrEqual(v);
                        case "gt"   : return Lugar.VALOR.greaterThan(v);
                        case "ge"   : return Lugar.VALOR.greaterOrEqual(v);
                        case "like" : // Fallthrough
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Lugar.valor."
                        );
                    }
                }
                default : throw new IllegalArgumentException(
                    "'" + property() + "' is not a valid Lugar property."
                );
            }
        }
    }
    
    /**
     * How to sort the results from the controller. This class is designed to be
     * deserialized automatically from JSON.
     */
    public static final class LugarSort extends AbstractSort<Lugar> {
        
        public LugarSort(String property, String direction) {
            super(property, direction);
        }
        
        @Override
        public Comparator<Lugar> toComparator() {
            final Comparator<Lugar> comparator;
            switch (property()) {
                case "lugarid"    : comparator = Lugar.LUGARID.comparator();    break;
                case "salaid"     : comparator = Lugar.SALAID.comparator();     break;
                case "nome"       : comparator = Lugar.NOME.comparator();       break;
                case "quantidade" : comparator = Lugar.QUANTIDADE.comparator(); break;
                case "valor"      : comparator = Lugar.VALOR.comparator();      break;
                default : throw new IllegalArgumentException(
                    "'" + property() + "' is not a valid Lugar property."
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
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private static class LugarNotFoundException extends RuntimeException {
        
        public LugarNotFoundException(int lugarid) {
            super("Lugar with lugarid '" + lugarid + "' not found");
        }
    }
    
    @JsonInclude(Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class LugarCreateBody {
        
        private final int salaid;
        private final String nome;
        private final Integer quantidade;
        private final BigDecimal valor;
        
        @JsonCreator
        public LugarCreateBody(
                @JsonProperty("salaid") Integer salaid,
                @JsonProperty("nome") String nome,
                @JsonProperty("quantidade") Integer quantidade,
                @JsonProperty("valor") BigDecimal valor) {
            this.salaid = Objects.requireNonNull(salaid, "`salaid` is required");
            this.nome = Objects.requireNonNull(nome, "`nome` is required");
            this.quantidade = Objects.requireNonNull(quantidade, "`quantidade` is required");
            this.valor = Objects.requireNonNull(valor, "`valor` is required");
        }
        
        public int getSalaid() {
            return this.salaid;
        }
        
        public String getNome() {
            return this.nome;
        }
        
        public Integer getQuantidade() {
            return this.quantidade;
        }
        
        public BigDecimal getValor() {
            return this.valor;
        }
    }
    
    @JsonInclude(Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class LugarUpdateBody {
        
        private final int salaid;
        private final String nome;
        private final Integer quantidade;
        private final BigDecimal valor;
        
        @JsonCreator
        public LugarUpdateBody(
                @JsonProperty("salaid") Integer salaid,
                @JsonProperty("nome") String nome,
                @JsonProperty("quantidade") Integer quantidade,
                @JsonProperty("valor") BigDecimal valor) {
            this.salaid = Objects.requireNonNull(salaid, "`salaid` is required");
            this.nome = Objects.requireNonNull(nome, "`nome` is required");
            this.quantidade = Objects.requireNonNull(quantidade, "`quantidade` is required");
            this.valor = Objects.requireNonNull(valor, "`valor` is required");
        }
        
        public int getSalaid() {
            return this.salaid;
        }
        
        public String getNome() {
            return this.nome;
        }
        
        public Integer getQuantidade() {
            return this.quantidade;
        }
        
        public BigDecimal getValor() {
            return this.valor;
        }
    }
}