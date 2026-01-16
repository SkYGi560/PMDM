package com.pmdm.tienda.data.mocks


import com.pmdm.tienda.data.mocks.articulo.ArticuloDaoMock
import com.pmdm.tienda.data.toArticulo
import com.pmdm.tienda.data.toArticulos

import com.pmdm.tienda.models.Articulo
import javax.inject.Inject

class ArticuloRepository @Inject constructor(private val proveedorArticulo: ArticuloDaoMock) {


    fun get(): List<Articulo> = proveedorArticulo.get().toArticulos()

    fun get(id: Int): Articulo? = proveedorArticulo.get(id)?.toArticulo()

    fun get(filtro:String):List<Articulo>? = proveedorArticulo.get(filtro)?.toArticulos()

    fun get(ids:MutableList<Int>):List<Articulo>? = proveedorArticulo.get(ids)?.toArticulos()


}
