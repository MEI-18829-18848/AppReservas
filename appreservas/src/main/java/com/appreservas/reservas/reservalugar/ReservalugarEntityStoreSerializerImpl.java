package com.appreservas.reservas.reservalugar;

import java.nio.ByteBuffer;
import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;

import com.appreservas.reservas.reservalugar.generated.GeneratedReservalugarEntityStoreSerializerImpl;

/**
 * An {@link
 * com.speedment.enterprise.datastore.runtime.entitystore.EntityStoreSerializer}
 * class for table {@link com.speedment.runtime.config.Table} named
 * reservalugar.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
public final class ReservalugarEntityStoreSerializerImpl extends GeneratedReservalugarEntityStoreSerializerImpl {
    
    public ReservalugarEntityStoreSerializerImpl(LongFunction<ByteBuffer> bufferFinder, LongToIntFunction offsetFinder) {
        super(bufferFinder, offsetFinder);
    }
}