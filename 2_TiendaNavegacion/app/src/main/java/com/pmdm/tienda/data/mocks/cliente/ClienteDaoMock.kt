package com.pmdm.tienda.data.mocks.cliente


class ClienteDaoMock {
    private var clientes = mutableListOf(
        ClienteMock(
            "11111111K", "juanjo@gmail.com", "Juanjo Perez", "654897584",
            Direccion("Los Molinos, 24Bajo", "Sevilla", "09882"), emptyList<Int>().toMutableList()
        ),
        ClienteMock(
            "22222222L", "pepe@gmail.com", "Pepe Lopez", "145678974",
            Direccion("Los Molinos, 22Bajo", "Sevilla", "09882"), emptyList<Int>().toMutableList()
        ),
        ClienteMock(
            "33333333M", "xusa@gmail.com", "Xusa Rico", "457845698",
            Direccion("Los Molinos, 22Bajo", "Sevilla", "09882"), emptyList<Int>().toMutableList()
        )
    )


    fun get(): MutableList<ClienteMock> = clientes
    fun get(correo: String): ClienteMock? = clientes.find { u -> u.correo == correo }
    fun insert(cliente: ClienteMock) = clientes.add(cliente)
    fun buscaCliente(correo:String)= clientes.find { c->c.correo==correo }
    fun updateCliente(cliente: ClienteMock){
        val posicion=clientes.indexOf(buscaCliente(cliente.correo))
        clientes.removeAt(posicion)
        clientes.add(cliente)
    }
    fun añadeAFavoritos(correo:String, idArticulo:Int)=buscaCliente(correo)?.favoritos?.add(idArticulo)

}
