package item9

import java.io.BufferedReader
import java.io.FileReader

class Item9 {

    fun countCharactersInFile(path: String): Int {
        BufferedReader(FileReader(path)).use { reader -> return reader.lineSequence().sumOf { it.length } }
    }
}