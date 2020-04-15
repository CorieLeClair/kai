package kai.utils

// imports
import kai.configuration.KaiConfig
import kai.configuration.kaiInformation
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Scanner

internal class GeneralUtils() {

    internal fun getSpecificFiles(directory: File, ext: String): ArrayList<File> {
        val files = arrayListOf<File>()

        for (f in directory.listFiles { _, name -> name.toLowerCase().endsWith(ext) }) {
            files.add(File("${kaiInformation.conversationDirectory}/${f.name}"))
        }

        return files
    }

    internal fun stringToList(string: String, split: String, exception: Int): ArrayList<String> {
        val list = ArrayList(listOf(*string.split(split.toRegex()).toTypedArray()))
        if (exception > -1) {
            list.removeAt(exception)
        }
        return list
    }

    internal fun getStringOfLine(file: File, lineNumber: Int): String {
        return Files.readAllLines(Paths.get(file.toURI()))[lineNumber]
    }

    internal fun getLineInFile(file: File): Int {
        val reader = BufferedReader(FileReader(file))
        var linesInFile = 0
        while (reader.readLine() != null) linesInFile++
        reader.close()

        return linesInFile
    }

    internal fun fileToList(file: File): ArrayList<String> {
        val scanner = Scanner(file)
        val list = ArrayList<String>()
        while (scanner.hasNext()) {
            list.add(scanner.next())
        }
        scanner.close()
        return list
    }
}
