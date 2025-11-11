package com.pmdm.mosaico.data.mock.imagen

class ImagenDaoMock {
    private var imagenesMosaico = mutableListOf<ImagenMock>()

    init {
        for (i in 1..8) {
            for (j in 1..5) {
                imagenesMosaico.add(ImagenMock("http://10.0.2.2/mosaico/fila_${i}_columna_${j}.jpg"))
            }
        }
    }

    fun getImagenes(): List<ImagenMock> = imagenesMosaico.toList()
}