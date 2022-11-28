package com.appreservas.reservas.organizador;

import java.nio.ByteBuffer;
import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;

import com.appreservas.reservas.organizador.generated.GeneratedOrganizadorEntityStoreSerializerImpl;

/**
 * An {@link
 * com.speedment.enterprise.datastore.runtime.entitystore.EntityStoreSerializer}
 * class for table {@link com.speedment.runtime.config.Table} named organizador.
 * <p>
 * This file is safe to edit. It will not be overwritten by the code generator.
 * 
 * @author company
 */
public final class OrganizadorEntityStoreSerializerImpl extends GeneratedOrganizadorEntityStoreSerializerImpl {
    
    public OrganizadorEntityStoreSerializerImpl(LongFunction<ByteBuffer> bufferFinder, LongToIntFunction offsetFinder) {
        super(bufferFinder, offsetFinder);
    }
}