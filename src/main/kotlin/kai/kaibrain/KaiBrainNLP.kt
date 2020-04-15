package kai.kaibrain

import kai.utils.GeneralUtils
import opennlp.tools.postag.POSModel
import opennlp.tools.postag.POSTaggerME
import opennlp.tools.tokenize.TokenizerME
import opennlp.tools.tokenize.TokenizerModel
import java.io.FileInputStream


internal class KaiSpeechTagging {

    private fun getPartOfSpeech(word : String) : Array<String> {
        val string = word.replace(".", "")
        val tokenModelIn = FileInputStream("src/main/kotlin/kai/kaibrain/autothink/opennlp/en-token.bin")
        val tokenModel = TokenizerModel(tokenModelIn)
        val tokenizer = TokenizerME(tokenModel)
        val tokens = tokenizer.tokenize(string)
        val posModeIn = FileInputStream("src/main/kotlin/kai/kaibrain/autothink/opennlp/en-pos-maxent.bin")
        val posModel = POSModel(posModeIn)
        val posTagger = POSTaggerME(posModel)

        return posTagger.tag(tokens)
    }

    fun isNoun(words : String) : Boolean{
        val tags = getPartOfSpeech(words)
        for(item in tags){
            when(item){
                //"NN" -> return true
                "NNP" -> return true
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

    fun isConj(word : String) : Boolean{
        val tags = getPartOfSpeech(word)
        for(item in tags){
            when(item){
                "CC" -> return true
                "IN" -> return true
            }
        }

        return false
    }

    fun isDetermin(word : String) : Boolean{
        val tags = GeneralUtils().stringToList(word, " ", -1)
        for(item in tags){
            when(item){
                "is" -> return true
                "and" -> return true
            }
        }
        return false
    }
}