package com.appreservas.reservas.sessao;

import java.nio.ByteBuffer;
import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;

import com.appreservas.reservas.sessao.generated.GeneratedSessaoEntityStoreSerializerImpl;

/**
 * An {@link
 * com.speedment.enterprise.datastore.runtime.entitystore.EntityStoreSerializer}
 * class for table {@link com.speedment.runtime.config.Table} named sessao.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
public final class SessaoEntityStoreSerializerImpl extends GeneratedSessaoEntityStoreSerializerImpl {
    
    public SessaoEntityStoreSerializerImpl(LongFunction<ByteBuffer> bufferFinder, LongToIntFunction offsetFinder) {
        super(bufferFinder, offsetFinder);
    }
}