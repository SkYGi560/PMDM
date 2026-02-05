package com.pmdm.tienda.data.mocks.articulo


import com.pmdm.tienda.data.mocks.pedido.ArticuloDePedidoMock
import javax.inject.Inject

import kotlin.random.Random

class ArticuloDaoMock @Inject constructor()  {


    private var articulos = mutableListOf<ArticuloMock>()

    //Tienda imagenes vestidos a partir de id

    init {
        //val urlBase="http://10.0.2.2//"
        val TOPE_PRECIO = 350
        val BASE_PRECIO = 100
        val urlBase = "@drawable/"

        for (i in 1..20) {
            articulos.add(
                ArticuloMock(
                    i,
                    // "${urlBase}imagen$i",
                    "imagen$i",
                    Random.nextFloat() * (TOPE_PRECIO - BASE_PRECIO) + BASE_PRECIO,
                    ""
                )
            )
        }

        //region descripcion articulos
        articulos[0] =
            articulos[0].copy(descripcion = "Elegante vestido con detalles dorados y complementos en negro-dorado")
        articulos[1] =
            articulos[1].copy(descripcion = "Vestido elegante color verde turquesa con altos tacones en turquesa")
        articulos[2] =
            articulos[2].copy(descripcion = "Vestido primaveral en color verde para una tarde de paseo")
        articulos[3] =
            articulos[3].copy(descripcion = "Vestido de noche con corpiño de pedrería color verde turquesa oscuro con pulsera a juego")
        articulos[4] =
            articulos[4].copy(descripcion = "Vestido azul rey para noche elegante, con zapatos y pendientes del mismo color ")
        articulos[5] =
            articulos[5].copy(descripcion = "Vestido corto estilo bohemio color morado índigo")
        articulos[6] =
            articulos[6].copy(descripcion = "Vestido  elegante morado purpura con cinturón y zapatos tacón muy alto")
        articulos[7] =
            articulos[7].copy(descripcion = "Vestido largo elegante negro con detalles morados purpura")
        articulos[8] =
            articulos[8].copy(descripcion = "Vestido de noche negro largo con detalles en negro y dorado")
        articulos[9] =
            articulos[9].copy(descripcion = "Vestido corto negro con estilo bohemio para tarde o noche")
        articulos[10] =
            articulos[10].copy(descripcion = "Vestido de coctel color crema con cinturón")
        articulos[11] =
            articulos[11].copy(descripcion = "Vestido de coctel color crema marfil con bolso y zapatos turquesa claro")
        articulos[12] =
            articulos[12].copy(descripcion = "Elegante vestido de dia en rosa crepé y falda plisada con preciosos zapatos a juego")
        articulos[13] =
            articulos[13].copy(descripcion = "Vestido de día para paseo, de color naranja con falda jaspeada en tonos crema")
        articulos[14] =
            articulos[14].copy(descripcion = "Vestido de encaje naranja para coctel con detares en dorado")
        articulos[15] =
            articulos[15].copy(descripcion = "Elegante vestido romántico para cena o fiesta de color escarlata y con preciosa sobre tela de encaje")
        articulos[16] =
            articulos[16].copy(descripcion = "Vestido primaveral blanco con estampado de flores en rojo y con vonitos zapatos en el mismo color")
        articulos[17] =
            articulos[17].copy(descripcion = "Vestido de encaje en rojo y con detalles en azul para coctel")
        articulos[18] =
            articulos[18].copy(descripcion = "Vestido primaveral palabra de honor con tela multicolor en rojos y verdes para paseos de día")
        articulos[19] =
            articulos[19].copy(descripcion = "Elegante vestido estilo años veinte con fondo en blanco y detalles en dorado y negro")
        //endregion
    }

    //Tienda postes de gatos descargados de Internet
    /*  init{
          val TOPE_PRECIO=35
          val BASE_PRECIO=10

          for( i in 1..50)
          {
              articulos.add(ArticuloMock(i, "https://loremflickr.com//400/400//?lock=$i",  Random.nextFloat()*(TOPE_PRECIO-BASE_PRECIO)+BASE_PRECIO,""))

          }
      }*/



    fun get(): MutableList<ArticuloMock> = articulos
    fun get(id: Int): ArticuloMock? = articulos.find { a -> a.id == id }

    fun getArticulosPedido(articulosPedido: List<ArticuloDePedidoMock>): List<ArticuloMock> {
        var listaArticulos = mutableListOf<ArticuloMock>()
        articulosPedido.forEach {
            listaArticulos.addAll(articulos.filter { a -> it.articuloId == a.id })
        }
        return listaArticulos.toList()
    }

    fun get(ids: MutableList<Int>): MutableList<ArticuloMock> {
        return articulos.filter { a -> ids.contains(a.id) }.toMutableList()
    }

    fun get(filtro: String): MutableList<ArticuloMock>? {
        val palabras = filtro.split("\\s+".toRegex())
        var lista = mutableListOf<ArticuloMock>()
        articulos.forEach { a ->
            run {
                for (p in palabras)
                    if (a.descripcion.contains(p)) {
                        lista.add(a)
                        break
                    }
            }
        }
        return  lista
    }
}