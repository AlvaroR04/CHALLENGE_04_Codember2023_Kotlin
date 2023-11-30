import java.io.File
import java.util.Collections
import java.util.Scanner

fun main() {
    val numReal = 33 //Numero real que se desea conocer
    val archivo = Scanner(File("files_quarantine.txt"))

    var realesActuales = 0 //Numero de reales que se han obtenido
    var checksumReal = "" //Checksum del numero real que se desea conocer

    while(archivo.hasNext() && realesActuales < numReal) {
        //Se divide para mayor facilidad
        val nombreArchivo = archivo.nextLine().split("-")
        val numApariciones = Array(nombreArchivo[1].length){0}
        val indiceApariciones = Array(nombreArchivo[1].length){0}

        //Comprobacion del orden de aparicion de los caracteres del checksum
        for(i in nombreArchivo[1].indices) {
            indiceApariciones[i] = nombreArchivo[0].indexOf(nombreArchivo[1][i])
        }
        val indiceAparicionesOrdenado = indiceApariciones.sorted()

        var esCorrecto = (indiceAparicionesOrdenado == indiceApariciones.toList())

        //Comprueba que no haya mas de uno
        var i = 0
        while(i < nombreArchivo[0].length && esCorrecto) {
            var j = 0
            while(j < nombreArchivo[1].length && esCorrecto) {
                if(nombreArchivo[0][i] == nombreArchivo[1][j]) {
                    numApariciones[j]++
                }

                esCorrecto = (numApariciones[j] < 2)

                j++
            }

            i++
        }

        if(esCorrecto) {
            checksumReal = nombreArchivo[0]
            realesActuales++
        }
    }

    println("Real nº $numReal: $checksumReal")
}