package com.appreservas.reservas.reservalugar.generated;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Stream;

import com.appreservas.reservas.reservalugar.Reservalugar;
import com.appreservas.reservas.reservalugar.ReservalugarEntityStoreSerializerImpl;
import com.appreservas.reservas.reservalugar.ReservalugarManager;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.common.tuple.Tuple2;
import com.speedment.common.tuple.Tuples;
import com.speedment.enterprise.datastore.runtime.entitystore.EntityStore;
import com.speedment.enterprise.datastore.runtime.entitystore.EntityStoreHolder;
import com.speedment.enterprise.datastore.runtime.fieldcache.FieldCache;
import com.speedment.enterprise.datastore.runtime.fieldcache.FieldCache.OfInt;
import com.speedment.enterprise.datastore.runtime.fieldcache.MultiFieldCache;
import com.speedment.enterprise.datastore.runtime.statistic.Statistics;
import com.speedment.enterprise.datastore.runtime.util.DataStoreHolderUtil;
import com.speedment.enterprise.datastore.runtime.util.StatisticsUtil;
import com.speedment.runtime.bulk.PersistOperation;
import com.speedment.runtime.bulk.RemoveOperation;
import com.speedment.runtime.bulk.UpdateOperation;
import com.speedment.runtime.config.identifier.ColumnIdentifier;
import com.speedment.runtime.config.identifier.ColumnLabel;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.component.StreamSupplierComponent;
import com.speedment.runtime.field.trait.HasIdentifier;

/**
 * A holder class for the various caches that are used to speed up the {@link
 * ReservalugarManager}.
 * 
 * Generated by
 * com.speedment.enterprise.datastore.generator.internal.code.GeneratedEntityCacheHolderTranslator
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public final class GeneratedReservalugarCacheHolder implements EntityStoreHolder<Reservalugar> {
    
    private final EntityStore<Reservalugar> entityStore;
    private final OfInt fieldReservaidCache;
    private final OfInt fieldLugaridCache;
    
    public GeneratedReservalugarCacheHolder(
            EntityStore<Reservalugar> entityStore,
            OfInt fieldReservaidCache,
            OfInt fieldLugaridCache) {
        
        this.entityStore         = requireNonNull(entityStore);
        this.fieldReservaidCache = requireNonNull(fieldReservaidCache);
        this.fieldLugaridCache   = requireNonNull(fieldLugaridCache);
    }
    
    @Override
    public EntityStore<Reservalugar> getEntityStore() {
        return entityStore;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <CACHE extends FieldCache<CACHE>> CACHE getFieldCache(ColumnIdentifier<Reservalugar> columnId) {
        if (columnId instanceof Reservalugar.Identifier) {
            final Reservalugar.Identifier _id = (Reservalugar.Identifier) columnId;
            switch (_id) {
                case RESERVAID : return (CACHE) fieldReservaidCache;
                case LUGARID   : return (CACHE) fieldLugaridCache;
                default : {
                    throw new UnsupportedOperationException(
                        String.format("Unknown enum constant '%s'.", _id)
                    );
                }
            }
        } else {
            final String _colName = columnId.getColumnId();
            switch (_colName) {
                case "reservaid" : return (CACHE) fieldReservaidCache;
                case "lugarid"   : return (CACHE) fieldLugaridCache;
                default : {
                    throw new UnsupportedOperationException(
                        String.format("Unknown column name '%s'.", _colName)
                    );
                }
            }
        }
    }
    
    @Override
    public boolean isHavingMultiFieldCache(ColumnIdentifier<Reservalugar> columnId) {
        return false;
    }
    
    public static CompletableFuture<GeneratedReservalugarCacheHolder> reload(StreamSupplierComponent streamSupplier, ExecutorService executor) {
        return reload(DataStoreHolderUtil.buildEntityStore(
            streamSupplier,
            executor,
            ReservalugarEntityStoreSerializerImpl::new,
            TableIdentifier.of("appreservas", "reservas", "reservalugar")
        ), executor);
    }
    
    @Override
    public EntityStoreHolder<Reservalugar> recycleAndPersist(PersistOperation<Reservalugar> persistOperation) {
        return wrapped().recycleAndPersist(persistOperation);
    }
    
    @Override
    public EntityStoreHolder<Reservalugar> recycleAndRemove(RemoveOperation<Reservalugar> removeOperation) {
        return wrapped().recycleAndRemove(removeOperation);
    }
    
    @Override
    public EntityStoreHolder<Reservalugar> recycleAndUpdate(UpdateOperation<Reservalugar> updateOperation) {
        return wrapped().recycleAndUpdate(updateOperation);
    }
    
    private EntityStoreHolder<Reservalugar> wrapped() {
        // Use explicit type for Stream to improve compilation time.
        final Map<ColumnLabel, FieldCache<?>> fieldCaches = Stream.<Tuple2<HasIdentifier<Reservalugar>, FieldCache<?>>>of(
            Tuples.of(Reservalugar.RESERVAID,fieldReservaidCache),
            Tuples.of(Reservalugar.LUGARID,  fieldLugaridCache)
        )
            .collect(toMap(t2 -> t2.get0().identifier().label(), Tuple2::get1));
        final Map<ColumnLabel,  Map<ColumnLabel, MultiFieldCache<?, ?, ?>>>  multiFieldCaches = createMultiCacheMap();
        final Set<ColumnIdentifier<Reservalugar>> columnIdentifiers = Stream.<HasIdentifier<Reservalugar>>of(
            Reservalugar.RESERVAID,
            Reservalugar.LUGARID
        )
            .map(HasIdentifier::identifier)
            .collect(toSet());
        return EntityStoreHolder.of(
            entityStore,
            fieldCaches,
            multiFieldCaches,
            columnIdentifiers
        );
    }
    
    public static CompletableFuture<GeneratedReservalugarCacheHolder> reload(CompletableFuture<EntityStore<Reservalugar>> entityStoreFuture, ExecutorService executor) {
        final CompletableFuture<FieldCache.OfInt> fieldReservaidCacheFuture =
            DataStoreHolderUtil.buildIntCache(entityStoreFuture, executor, Reservalugar.RESERVAID, 0);
        
        final CompletableFuture<FieldCache.OfInt> fieldLugaridCacheFuture =
            DataStoreHolderUtil.buildIntCache(entityStoreFuture, executor, Reservalugar.LUGARID, 0);
        
        return entityStoreFuture.thenApplyAsync(entityStore -> {
            try {
                return new GeneratedReservalugarCacheHolder(
                    entityStore,
                    fieldReservaidCacheFuture.get(),
                    fieldLugaridCacheFuture.get()
                );
            } catch (final ExecutionException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    
    @Override
    public void close() {
        entityStore.close();
        fieldReservaidCache.close();
        fieldLugaridCache.close();
    }
    
    @Override
    public Statistics getStatistics() {
        return StatisticsUtil.getStatistics(    
            this,
            entityStore.identifier(),
            Arrays.asList(
                Reservalugar.Identifier.RESERVAID,
                Reservalugar.Identifier.LUGARID
            )
        
        );
    }
    
    private Map<ColumnLabel, Map<ColumnLabel, MultiFieldCache<?, ?, ?>>> createMultiCacheMap() {
        return Collections.emptyMap();
    }
}