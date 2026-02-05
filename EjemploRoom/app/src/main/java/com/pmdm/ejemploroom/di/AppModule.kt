package com.pmdm.tienda.di

import android.content.Context
import com.pmdm.ejemploroom.data.room.PedidoRepository
import com.pmdm.ejemploroom.data.room.TiendaDB
import com.pmdm.ejemploroom.data.room.cliente.ClienteDao
import com.pmdm.ejemploroom.data.room.ClienteRepository
import com.pmdm.ejemploroom.data.room.pedido.PedidoDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideTiendaDatabase(
        @ApplicationContext context: Context
    ): TiendaDB = TiendaDB.getDatabase(context)


    @Provides
    @Singleton
    fun provideClienteDao(
        db: TiendaDB
    ): ClienteDao = db.clienteDao()

    @Provides
    @Singleton
    fun providePedidoDao(
        db: TiendaDB
    ): PedidoDao = db.pedidoDao()

    @Provides
    @Singleton
    fun provideClienteRepository(
        clienteDao: ClienteDao
    ): ClienteRepository =
        ClienteRepository(clienteDao)

    @Provides
    @Singleton
    fun providePedidoRepository(
        pedidoDao: PedidoDao
    ): PedidoRepository =
        PedidoRepository(pedidoDao)
}

