package kai.utils

// imports
import kai.configuration.Kai
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import kotlin.collections.ArrayList

internal class GeneralUtils() {

    internal fun getManualFiles(ext: String, builder : Kai.KaiBuilder): ArrayList<File> {
        val directoryTwo = File("${builder.projectPath}/mangen")

        val files = arrayListOf<File>()

        for (f in directoryTwo.listFiles { _, name -> name.toLowerCase().endsWith(ext) }) {
            files.add(File("${directoryTwo}/${f.name}"))
        }

        return files
    }

    internal fun getAutoGeneratedFiles(ext : String, builder : Kai.KaiBuilder) : ArrayList<File>{
        val directoryTwo = File("${builder.projectPath}/autogen")

        val files = arrayListOf<File>()

        for (f in directoryTwo.listFiles { _, name -> name.toLowerCase().endsWith(ext) }) {
            files.add(File("${directoryTwo}/${f.name}"))
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



    internal fun sortListBy(list : ArrayList<String>) : ArrayList<String> {
        val new = ArrayList<String>()
        new.add(list[0])
        list.removeAt(0)

        if(new.count() > 0) {
            for(item in list) {
                if(item.length >= new[0].length) {
                    new.add(0, item)
                } else {
                    var counter = 0
                    while(counter <= new.count() - 1) {
                        if(item.length > new[counter].length) {
                            new.add(counter, item)
                            break
                        } else if(counter == new.count() - 1){
                            new.add(item)
                            break
                        } else{
                            counter++
                        }
                    }
                }
            }
        }

        return new

    }
}