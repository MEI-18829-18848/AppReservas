package com.appreservas.reservas.evento.generated;

import com.appreservas.reservas.evento.Evento;
import com.appreservas.reservas.evento.EventoManager;
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
 * The default REST controller logic for Evento entities.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
@CrossOrigin(origins = "*", maxAge = 3600)
public abstract class GeneratedEventoController {
    
    protected @Autowired JsonComponent jsonComponent;
    protected @Autowired EventoManager manager;
    protected JsonEncoder<Evento> encoder;
    
    @PostConstruct
    void createEventoEncoder() {
        encoder = jsonComponent.<Evento>emptyEncoder()
            .put("eventoid", Evento.EVENTOID)
            .put("salaid", Evento.SALAID)
            .put("organizadorid", Evento.ORGANIZADORID)
            .put("nome", Evento.NOME)
            .put("descricao", Evento.DESCRICAO)
            .put("categoria", Evento.CATEGORIA)
            .put("imdbid", Evento.IMDBID)
            .build();
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/evento", produces = "application/json")
    public String list(
            @RequestParam(name = "filter", defaultValue = "[]") String filters,
            @RequestParam(name = "sort", defaultValue = "[]") String sorters,
            @RequestParam(value = "start", defaultValue = "0") long start,
            @RequestParam(value = "limit", defaultValue = "25") long limit) {
        
        return listHelper(
            ControllerUtil.parseFilters(filters, EventoFilter::new).collect(toList()),
            ControllerUtil.parseSorts(sorters, EventoSort::new).collect(toList()),
            start,
            limit
        );
    }
    
    protected String listHelper(
            List<Predicate<Evento>> predicates,
            List<Comparator<Evento>> sorters,
            long start,
            long limit) {
        Stream<Evento> stream = manager.stream();
        
        for (final Predicate<Evento> predicate : predicates) {
            stream = stream.filter(predicate);
        }
        
        if (!sorters.isEmpty()) {
            final Optional<Comparator<Evento>> comparator = sorters.stream()
                .reduce(Comparator::thenComparing);
            
            stream = stream.sorted(comparator.get());
        }
        
        return stream
            .skip(start)
            .limit(limit)
            .collect(JsonCollectors.toList(encoder));
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/evento/{eventoid}", produces = "application/json")
    public String get(
            @PathVariable(name = "eventoid") int eventoid) {
        return encoder.apply(
            manager.stream()
                .filter(Evento.EVENTOID.equal(eventoid))
                .findFirst()
                .orElseThrow(() -> new EventoNotFoundException(eventoid))
        );
    }
    
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(path = "/evento", consumes = "application/json")
    public void create(
            @RequestBody @Validated EventoCreateBody createBody) {
        final FieldSet<Evento> excluded = FieldSet.allExcept(
            Evento.EVENTOID
        );
        
        final Persister<Evento> persister = manager.persister(excluded);
        final Evento evento = manager.create()
            .setSalaid(createBody.getSalaid())
            .setOrganizadorid(createBody.getOrganizadorid())
            .setNome(createBody.getNome())
            .setDescricao(createBody.getDescricao())
            .setCategoria(createBody.getCategoria())
            .setImdbid(createBody.getImdbid())
        ;
        
        persister.accept(evento);
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping(path = "/evento/{eventoid}", consumes = "application/json")
    public void update(
            @PathVariable(name = "eventoid") int eventoid,
            @RequestBody @Validated EventoUpdateBody updateBody) {
        final FieldSet<Evento> excluded = FieldSet.allExcept(
            Evento.EVENTOID
        );
        
        final Updater<Evento> updater = manager.updater(excluded);
        final Evento evento = manager.stream()
            .filter(Evento.EVENTOID.equal(eventoid))
            .findFirst()
            .orElseThrow(() -> new EventoNotFoundException(eventoid));
        
        evento.setSalaid(updateBody.getSalaid());
        evento.setOrganizadorid(updateBody.getOrganizadorid());
        evento.setNome(updateBody.getNome());
        evento.setDescricao(updateBody.getDescricao());
        evento.setCategoria(updateBody.getCategoria());
        evento.setImdbid(updateBody.getImdbid());
        
        updater.accept(evento);
    }
    
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/evento/{eventoid}")
    public void delete(
            @PathVariable(name = "eventoid") int eventoid) {
        manager.stream()
            .filter(Evento.EVENTOID.equal(eventoid))
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
    public static final class EventoFilter extends AbstractFilter<Evento> {
        
        public EventoFilter(
                String operator,
                String property,
                String value) {
            super(operator, property, value);
        }
        
        @Override
        public Predicate<Evento> toPredicate() {
            switch (property()) {
                case "eventoid" : {
                    final int v = Integer.parseInt(value());
                    switch (operator()) {
                        case "eq"   : return Evento.EVENTOID.equal(v);
                        case "ne"   : return Evento.EVENTOID.notEqual(v);
                        case "lt"   : return Evento.EVENTOID.lessThan(v);
                        case "le"   : return Evento.EVENTOID.lessOrEqual(v);
                        case "gt"   : return Evento.EVENTOID.greaterThan(v);
                        case "ge"   : return Evento.EVENTOID.greaterOrEqual(v);
                        case "like" : // Fallthrough
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Evento.eventoid."
                        );
                    }
                }
                case "salaid" : {
                    final int v = Integer.parseInt(value());
                    switch (operator()) {
                        case "eq"   : return Evento.SALAID.equal(v);
                        case "ne"   : return Evento.SALAID.notEqual(v);
                        case "lt"   : return Evento.SALAID.lessThan(v);
                        case "le"   : return Evento.SALAID.lessOrEqual(v);
                        case "gt"   : return Evento.SALAID.greaterThan(v);
                        case "ge"   : return Evento.SALAID.greaterOrEqual(v);
                        case "like" : // Fallthrough
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Evento.salaid."
                        );
                    }
                }
                case "organizadorid" : {
                    final int v = Integer.parseInt(value());
                    switch (operator()) {
                        case "eq"   : return Evento.ORGANIZADORID.equal(v);
                        case "ne"   : return Evento.ORGANIZADORID.notEqual(v);
                        case "lt"   : return Evento.ORGANIZADORID.lessThan(v);
                        case "le"   : return Evento.ORGANIZADORID.lessOrEqual(v);
                        case "gt"   : return Evento.ORGANIZADORID.greaterThan(v);
                        case "ge"   : return Evento.ORGANIZADORID.greaterOrEqual(v);
                        case "like" : // Fallthrough
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Evento.organizadorid."
                        );
                    }
                }
                case "nome" : {
                    final String v = value();
                    switch (operator()) {
                        case "eq"   : return Evento.NOME.equal(v);
                        case "ne"   : return Evento.NOME.notEqual(v);
                        case "lt"   : return Evento.NOME.lessThan(v);
                        case "le"   : return Evento.NOME.lessOrEqual(v);
                        case "gt"   : return Evento.NOME.greaterThan(v);
                        case "ge"   : return Evento.NOME.greaterOrEqual(v);
                        case "like" : return Evento.NOME.contains(v);
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Evento.nome."
                        );
                    }
                }
                case "descricao" : {
                    final String v = value();
                    switch (operator()) {
                        case "eq"   : return Evento.DESCRICAO.equal(v);
                        case "ne"   : return Evento.DESCRICAO.notEqual(v);
                        case "lt"   : return Evento.DESCRICAO.lessThan(v);
                        case "le"   : return Evento.DESCRICAO.lessOrEqual(v);
                        case "gt"   : return Evento.DESCRICAO.greaterThan(v);
                        case "ge"   : return Evento.DESCRICAO.greaterOrEqual(v);
                        case "like" : return Evento.DESCRICAO.contains(v);
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Evento.descricao."
                        );
                    }
                }
                case "categoria" : {
                    final String v = value();
                    switch (operator()) {
                        case "eq"   : return Evento.CATEGORIA.equal(v);
                        case "ne"   : return Evento.CATEGORIA.notEqual(v);
                        case "lt"   : return Evento.CATEGORIA.lessThan(v);
                        case "le"   : return Evento.CATEGORIA.lessOrEqual(v);
                        case "gt"   : return Evento.CATEGORIA.greaterThan(v);
                        case "ge"   : return Evento.CATEGORIA.greaterOrEqual(v);
                        case "like" : return Evento.CATEGORIA.contains(v);
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Evento.categoria."
                        );
                    }
                }
                case "imdbid" : {
                    final String v = value();
                    switch (operator()) {
                        case "eq"   : return Evento.IMDBID.equal(v);
                        case "ne"   : return Evento.IMDBID.notEqual(v);
                        case "lt"   : return Evento.IMDBID.lessThan(v);
                        case "le"   : return Evento.IMDBID.lessOrEqual(v);
                        case "gt"   : return Evento.IMDBID.greaterThan(v);
                        case "ge"   : return Evento.IMDBID.greaterOrEqual(v);
                        case "like" : return Evento.IMDBID.contains(v);
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Evento.imdbid."
                        );
                    }
                }
                default : throw new IllegalArgumentException(
                    "'" + property() + "' is not a valid Evento property."
                );
            }
        }
    }
    
    /**
     * How to sort the results from the controller. This class is designed to be
     * deserialized automatically from JSON.
     */
    public static final class EventoSort extends AbstractSort<Evento> {
        
        public EventoSort(String property, String direction) {
            super(property, direction);
        }
        
        @Override
        public Comparator<Evento> toComparator() {
            final Comparator<Evento> comparator;
            switch (property()) {
                case "eventoid"      : comparator = Evento.EVENTOID.comparator();      break;
                case "salaid"        : comparator = Evento.SALAID.comparator();        break;
                case "organizadorid" : comparator = Evento.ORGANIZADORID.comparator(); break;
                case "nome"          : comparator = Evento.NOME.comparator();          break;
                case "descricao"     : comparator = Evento.DESCRICAO.comparator();     break;
                case "categoria"     : comparator = Evento.CATEGORIA.comparator();     break;
                case "imdbid"        : comparator = Evento.IMDBID.comparator();        break;
                default : throw new IllegalArgumentException(
                    "'" + property() + "' is not a valid Evento property."
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
    private static class EventoNotFoundException extends RuntimeException {
        
        public EventoNotFoundException(int eventoid) {
            super("Evento with eventoid '" + eventoid + "' not found");
        }
    }
    
    @JsonInclude(Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class EventoCreateBody {
        
        private final int salaid;
        private final int organizadorid;
        private final String nome;
        private final String descricao;
        private final String categoria;
        private final String imdbid;
        
        @JsonCreator
        public EventoCreateBody(
                @JsonProperty("salaid") Integer salaid,
                @JsonProperty("organizadorid") Integer organizadorid,
                @JsonProperty("nome") String nome,
                @JsonProperty("descricao") String descricao,
                @JsonProperty("categoria") String categoria,
                @JsonProperty("imdbid") String imdbid) {
            this.salaid = Objects.requireNonNull(salaid, "`salaid` is required");
            this.organizadorid = Objects.requireNonNull(organizadorid, "`organizadorid` is required");
            this.nome = Objects.requireNonNull(nome, "`nome` is required");
            this.descricao = Objects.requireNonNull(descricao, "`descricao` is required");
            this.categoria = Objects.requireNonNull(categoria, "`categoria` is required");
            this.imdbid = Objects.requireNonNull(imdbid, "`imdbid` is required");
        }
        
        public int getSalaid() {
            return this.salaid;
        }
        
        public int getOrganizadorid() {
            return this.organizadorid;
        }
        
        public String getNome() {
            return this.nome;
        }
        
        public String getDescricao() {
            return this.descricao;
        }
        
        public String getCategoria() {
            return this.categoria;
        }
        
        public String getImdbid() {
            return this.imdbid;
        }
    }
    
    @JsonInclude(Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class EventoUpdateBody {
        
        private final int salaid;
        private final int organizadorid;
        private final String nome;
        private final String descricao;
        private final String categoria;
        private final String imdbid;
        
        @JsonCreator
        public EventoUpdateBody(
                @JsonProperty("salaid") Integer salaid,
                @JsonProperty("organizadorid") Integer organizadorid,
                @JsonProperty("nome") String nome,
                @JsonProperty("descricao") String descricao,
                @JsonProperty("categoria") String categoria,
                @JsonProperty("imdbid") String imdbid) {
            this.salaid = Objects.requireNonNull(salaid, "`salaid` is required");
            this.organizadorid = Objects.requireNonNull(organizadorid, "`organizadorid` is required");
            this.nome = Objects.requireNonNull(nome, "`nome` is required");
            this.descricao = Objects.requireNonNull(descricao, "`descricao` is required");
            this.categoria = Objects.requireNonNull(categoria, "`categoria` is required");
            this.imdbid = Objects.requireNonNull(imdbid, "`imdbid` is required");
        }
        
        public int getSalaid() {
            return this.salaid;
        }
        
        public int getOrganizadorid() {
            return this.organizadorid;
        }
        
        public String getNome() {
            return this.nome;
        }
        
        public String getDescricao() {
            return this.descricao;
        }
        
        public String getCategoria() {
            return this.categoria;
        }
        
        public String getImdbid() {
            return this.imdbid;
        }
    }
}