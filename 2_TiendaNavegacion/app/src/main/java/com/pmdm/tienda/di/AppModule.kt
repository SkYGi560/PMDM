package com.pmdm.tienda.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.pmdm.tienda.data.ClienteRepository
import com.pmdm.tienda.data.PedidoRepository
import com.pmdm.tienda.data.UsuarioRepository
import com.pmdm.tienda.data.mocks.ArticuloRepository
import com.pmdm.tienda.data.mocks.articulo.ArticuloDaoMock
import com.pmdm.tienda.data.mocks.cliente.ClienteDaoMock
import com.pmdm.tienda.data.mocks.pedido.PedidoDaoMock
import com.pmdm.tienda.data.mocks.usuario.UsuarioDaoMock
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideArticuloDaoMock(): ArticuloDaoMock = ArticuloDaoMock()

    @Provides
    @Singleton
    fun provideArticuloRepository(
        articuloDaoMock: ArticuloDaoMock
    ): ArticuloRepository =
        ArticuloRepository(articuloDaoMock)

   @Provides
    @Singleton
    fun provideUsuarioDaoMock(): UsuarioDaoMock = UsuarioDaoMock()

    @Provides
    @Singleton
    fun provideUsuarioRepository(
        usuarioDaoMock: UsuarioDaoMock
    ): UsuarioRepository =
        UsuarioRepository(usuarioDaoMock)

    @Provides
    @Singleton
    fun provideClienteDaoMock(): ClienteDaoMock = ClienteDaoMock()

    @Provides
    @Singleton
    fun provideClienteRepository(clienteDaoMock: ClienteDaoMock): ClienteRepository =
        ClienteRepository(clienteDaoMock)


    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun providePedidoRepository(
        pedidoDaoMock: PedidoDaoMock
    ): PedidoRepository =
        PedidoRepository(pedidoDaoMock)

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun providePedidoDaoMock(): PedidoDaoMock = PedidoDaoMock()

}