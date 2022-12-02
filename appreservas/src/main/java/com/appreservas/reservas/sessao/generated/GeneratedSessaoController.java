package com.appreservas.reservas.sessao.generated;

import com.appreservas.reservas.sessao.Sessao;
import com.appreservas.reservas.sessao.SessaoManager;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;
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
 * The default REST controller logic for Sessao entities.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/reservas")
public abstract class GeneratedSessaoController {
    
    protected @Autowired JsonComponent jsonComponent;
    protected @Autowired SessaoManager manager;
    protected JsonEncoder<Sessao> encoder;
    
    @PostConstruct
    void createSessaoEncoder() {
        encoder = jsonComponent.<Sessao>emptyEncoder()
            .put("sessaoid", Sessao.SESSAOID)
            .put("eventoid", Sessao.EVENTOID)
            .put("salaid", Sessao.SALAID)
            .put("duracao", Sessao.DURACAO)
            .put("data", Sessao.DATA)
            .build();
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/sessao", produces = "application/json")
    public String list(
            @RequestParam(name = "filter", defaultValue = "[]") String filters,
            @RequestParam(name = "sort", defaultValue = "[]") String sorters,
            @RequestParam(value = "start", defaultValue = "0") long start,
            @RequestParam(value = "limit", defaultValue = "25") long limit) {
        
        return listHelper(
            ControllerUtil.parseFilters(filters, SessaoFilter::new).collect(toList()),
            ControllerUtil.parseSorts(sorters, SessaoSort::new).collect(toList()),
            start,
            limit
        );
    }
    
    protected String listHelper(
            List<Predicate<Sessao>> predicates,
            List<Comparator<Sessao>> sorters,
            long start,
            long limit) {
        Stream<Sessao> stream = manager.stream();
        
        for (final Predicate<Sessao> predicate : predicates) {
            stream = stream.filter(predicate);
        }
        
        if (!sorters.isEmpty()) {
            final Optional<Comparator<Sessao>> comparator = sorters.stream()
                .reduce(Comparator::thenComparing);
            
            stream = stream.sorted(comparator.get());
        }
        
        return stream
            .skip(start)
            .limit(limit)
            .collect(JsonCollectors.toList(encoder));
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(path = "/sessao/{sessaoid}", produces = "application/json")
    public String get(
            @PathVariable(name = "sessaoid") int sessaoid) {
        return encoder.apply(
            manager.stream()
                .filter(Sessao.SESSAOID.equal(sessaoid))
                .findFirst()
                .orElseThrow(() -> new SessaoNotFoundException(sessaoid))
        );
    }
    
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(path = "/sessao", consumes = "application/json")
    public void create(
            @RequestBody @Validated SessaoCreateBody createBody) {
        final FieldSet<Sessao> excluded = FieldSet.allExcept(
            Sessao.SESSAOID
        );
        
        final Persister<Sessao> persister = manager.persister(excluded);
        final Sessao sessao = manager.create()
            .setEventoid(createBody.getEventoid())
            .setSalaid(createBody.getSalaid())
            .setDuracao(createBody.getDuracao())
            .setData(createBody.getData())
        ;
        
        persister.accept(sessao);
    }
    
    @ResponseStatus(code = HttpStatus.OK)
    @PatchMapping(path = "/sessao/{sessaoid}", consumes = "application/json")
    public void update(
            @PathVariable(name = "sessaoid") int sessaoid,
            @RequestBody @Validated SessaoUpdateBody updateBody) {
        final Updater<Sessao> updater = manager.updater();
        final Sessao sessao = manager.stream()
            .filter(Sessao.SESSAOID.equal(sessaoid))
            .findFirst()
            .orElseThrow(() -> new SessaoNotFoundException(sessaoid));
        
        sessao.setSessaoid(updateBody.getSessaoid());
        sessao.setEventoid(updateBody.getEventoid());
        sessao.setSalaid(updateBody.getSalaid());
        sessao.setDuracao(updateBody.getDuracao());
        sessao.setData(updateBody.getData());
        
        updater.accept(sessao);
    }
    
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/sessao/{sessaoid}")
    public void delete(
            @PathVariable(name = "sessaoid") int sessaoid) {
        manager.stream()
            .filter(Sessao.SESSAOID.equal(sessaoid))
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
    public static final class SessaoFilter extends AbstractFilter<Sessao> {
        
        public SessaoFilter(
                String operator,
                String property,
                String value) {
            super(operator, property, value);
        }
        
        @Override
        public Predicate<Sessao> toPredicate() {
            switch (property()) {
                case "sessaoid" : {
                    final int v = Integer.parseInt(value());
                    switch (operator()) {
                        case "eq"   : return Sessao.SESSAOID.equal(v);
                        case "ne"   : return Sessao.SESSAOID.notEqual(v);
                        case "lt"   : return Sessao.SESSAOID.lessThan(v);
                        case "le"   : return Sessao.SESSAOID.lessOrEqual(v);
                        case "gt"   : return Sessao.SESSAOID.greaterThan(v);
                        case "ge"   : return Sessao.SESSAOID.greaterOrEqual(v);
                        case "like" : // Fallthrough
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Sessao.sessaoid."
                        );
                    }
                }
                case "eventoid" : {
                    final int v = Integer.parseInt(value());
                    switch (operator()) {
                        case "eq"   : return Sessao.EVENTOID.equal(v);
                        case "ne"   : return Sessao.EVENTOID.notEqual(v);
                        case "lt"   : return Sessao.EVENTOID.lessThan(v);
                        case "le"   : return Sessao.EVENTOID.lessOrEqual(v);
                        case "gt"   : return Sessao.EVENTOID.greaterThan(v);
                        case "ge"   : return Sessao.EVENTOID.greaterOrEqual(v);
                        case "like" : // Fallthrough
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Sessao.eventoid."
                        );
                    }
                }
                case "salaid" : {
                    final int v = Integer.parseInt(value());
                    switch (operator()) {
                        case "eq"   : return Sessao.SALAID.equal(v);
                        case "ne"   : return Sessao.SALAID.notEqual(v);
                        case "lt"   : return Sessao.SALAID.lessThan(v);
                        case "le"   : return Sessao.SALAID.lessOrEqual(v);
                        case "gt"   : return Sessao.SALAID.greaterThan(v);
                        case "ge"   : return Sessao.SALAID.greaterOrEqual(v);
                        case "like" : // Fallthrough
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Sessao.salaid."
                        );
                    }
                }
                case "duracao" : {
                    final Integer v = Integer.parseInt(value());
                    switch (operator()) {
                        case "eq"   : return Sessao.DURACAO.equal(v);
                        case "ne"   : return Sessao.DURACAO.notEqual(v);
                        case "lt"   : return Sessao.DURACAO.lessThan(v);
                        case "le"   : return Sessao.DURACAO.lessOrEqual(v);
                        case "gt"   : return Sessao.DURACAO.greaterThan(v);
                        case "ge"   : return Sessao.DURACAO.greaterOrEqual(v);
                        case "like" : // Fallthrough
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Sessao.duracao."
                        );
                    }
                }
                case "data" : {
                    final Timestamp v = Timestamp.valueOf(value());
                    switch (operator()) {
                        case "eq"   : return Sessao.DATA.equal(v);
                        case "ne"   : return Sessao.DATA.notEqual(v);
                        case "lt"   : return Sessao.DATA.lessThan(v);
                        case "le"   : return Sessao.DATA.lessOrEqual(v);
                        case "gt"   : return Sessao.DATA.greaterThan(v);
                        case "ge"   : return Sessao.DATA.greaterOrEqual(v);
                        case "like" : // Fallthrough
                        default : throw new IllegalArgumentException(
                            "'" + operator() + "' is not a valid operator for " +
                            "Sessao.data."
                        );
                    }
                }
                default : throw new IllegalArgumentException(
                    "'" + property() + "' is not a valid Sessao property."
                );
            }
        }
    }
    
    /**
     * How to sort the results from the controller. This class is designed to be
     * deserialized automatically from JSON.
     */
    public static final class SessaoSort extends AbstractSort<Sessao> {
        
        public SessaoSort(String property, String direction) {
            super(property, direction);
        }
        
        @Override
        public Comparator<Sessao> toComparator() {
            final Comparator<Sessao> comparator;
            switch (property()) {
                case "sessaoid" : comparator = Sessao.SESSAOID.comparator(); break;
                case "eventoid" : comparator = Sessao.EVENTOID.comparator(); break;
                case "salaid"   : comparator = Sessao.SALAID.comparator();   break;
                case "duracao"  : comparator = Sessao.DURACAO.comparator();  break;
                case "data"     : comparator = Sessao.DATA.comparator();     break;
                default : throw new IllegalArgumentException(
                    "'" + property() + "' is not a valid Sessao property."
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
    private static class SessaoNotFoundException extends RuntimeException {
        
        public SessaoNotFoundException(int sessaoid) {
            super("Sessao with sessaoid '" + sessaoid + "' not found");
        }
    }
    
    @JsonInclude(Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class SessaoCreateBody {
        
        private final int eventoid;
        private final int salaid;
        private final Integer duracao;
        private final Timestamp data;
        
        @JsonCreator
        public SessaoCreateBody(
                @JsonProperty("eventoid") Integer eventoid,
                @JsonProperty("salaid") Integer salaid,
                @JsonProperty("duracao") Integer duracao,
                @JsonProperty("data") Timestamp data) {
            this.eventoid = Objects.requireNonNull(eventoid, "`eventoid` is required");
            this.salaid = Objects.requireNonNull(salaid, "`salaid` is required");
            this.duracao = Objects.requireNonNull(duracao, "`duracao` is required");
            this.data = Objects.requireNonNull(data, "`data` is required");
        }
        
        public int getEventoid() {
            return this.eventoid;
        }
        
        public int getSalaid() {
            return this.salaid;
        }
        
        public Integer getDuracao() {
            return this.duracao;
        }
        
        public Timestamp getData() {
            return this.data;
        }
    }
    
    @JsonInclude(Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class SessaoUpdateBody {
        
        private final int sessaoid;
        private final int eventoid;
        private final int salaid;
        private final Integer duracao;
        private final Timestamp data;
        
        @JsonCreator
        public SessaoUpdateBody(
                @JsonProperty("sessaoid") Integer sessaoid,
                @JsonProperty("eventoid") Integer eventoid,
                @JsonProperty("salaid") Integer salaid,
                @JsonProperty("duracao") Integer duracao,
                @JsonProperty("data") Timestamp data) {
            this.sessaoid = Objects.requireNonNull(sessaoid, "`sessaoid` is required");
            this.eventoid = Objects.requireNonNull(eventoid, "`eventoid` is required");
            this.salaid = Objects.requireNonNull(salaid, "`salaid` is required");
            this.duracao = Objects.requireNonNull(duracao, "`duracao` is required");
            this.data = Objects.requireNonNull(data, "`data` is required");
        }
        
        public int getSessaoid() {
            return this.sessaoid;
        }
        
        public int getEventoid() {
            return this.eventoid;
        }
        
        public int getSalaid() {
            return this.salaid;
        }
        
        public Integer getDuracao() {
            return this.duracao;
        }
        
        public Timestamp getData() {
            return this.data;
        }
    }
}