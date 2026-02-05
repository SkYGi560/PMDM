package com.pmdm.ejemploroom

import android.app.Application
import com.pmdm.ejemploroom.data.room.cliente.ClienteDao
import com.pmdm.ejemploroom.data.room.cliente.ClienteEntity
import com.pmdm.ejemploroom.data.room.pedido.PedidoDao
import com.pmdm.ejemploroom.data.room.pedido.PedidoEntity
import dagger.hilt.android.HiltAndroidApp
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import java.time.LocalDate


@HiltAndroidApp
    class TiendaAplication: Application() {
        @Inject
        lateinit var daoClientes: ClienteDao
        @Inject
        lateinit var daoPedido: PedidoDao
        override fun onCreate() {
            super.onCreate()
            runBlocking {
               if (daoClientes.count() == 0) {
                    daoClientes.insert(
                        ClienteEntity("12345678A", "Juan", "Pérez")
                    )
                    daoClientes.insert(
                        ClienteEntity("87654321B", "María", "García"))
               }
               if(daoPedido.count()==0)
               {
                    daoPedido.insert( PedidoEntity(0,"12345678A", LocalDate.now()))
                    daoPedido.insert( PedidoEntity(0,"12345678A", LocalDate.now()))
                    daoPedido.insert( PedidoEntity(0,"12345678A", LocalDate.now()))
                    daoPedido.insert( PedidoEntity(0,"87654321B", LocalDate.now()))
               }
            }}

    }