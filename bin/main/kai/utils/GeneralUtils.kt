package kai.utils

// imports
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Scanner
import kai.configuration.kaiInformation
import opennlp.tools.postag.POSModel
import opennlp.tools.postag.POSTaggerME
import opennlp.tools.tokenize.Tokenizer
import opennlp.tools.tokenize.TokenizerME
import opennlp.tools.tokenize.TokenizerModel
import java.io.FileInputStream
import java.io.IOException

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

internal class KaiSpeechTagging(){

    fun getPartOfSpeech(word : String) : Array<String> {
        val string = word.replace(".", "")
        val tokenModelIn = FileInputStream("src/main/kotlin/kai/kaibrain/autothink/opennlp/en-token.bin")
        val tokenModel = TokenizerModel(tokenModelIn)
        val tokenizer = TokenizerME(tokenModel)
        var tokens = tokenizer.tokenize(string)

        val posModeIn = FileInputStream("src/main/kotlin/kai/kaibrain/autothink/opennlp/en-pos-maxent.bin")
        val posModel = POSModel(posModeIn)
        val posTagger = POSTaggerME(posModel)
        val tags = posTagger.tag(tokens)

        return tags
    }

    fun isNoun(words : String) : Boolean{
        val tags = getPartOfSpeech(words)

        for(item in tags){
            when(item){
                "NN" -> return true
            }
        }

        return false
    }

    fun isAdj(words : String)  : Boolean{
        val tags = getPartOfSpeech(words)

        for(item in tags){
            when(item){
                "JJ" -> return true 
            }
        }

        return false
    }

}
