package kai.kaibrain.nlp

import edu.stanford.nlp.simple.Document
import kai.utils.GeneralUtils
import edu.stanford.nlp.simple.Sentence

class NaturalLanguageSpeechHelper {
    class POS() {

        private fun getPartOfSpeech(words: String): ArrayList<String> {
            val list = GeneralUtils().stringToList(words, " ", -1)
            val returnList = ArrayList<String>()

            for (word in list) {
                returnList.add(Sentence(word).posTag(0))
            }

            return returnList
        }

        fun getPartsOfSpeech(words: String): ArrayList<String> {
            return getPartOfSpeech(words)
        }

        fun isCommonNoun(word: String): Boolean {
            val tags = getPartOfSpeech(word)
            for (item in tags) {
                when (item) {
                    "NN" -> return true
                    "NNS" -> return true
                }
            }
            return false
        }

        fun isNoun(word: String): Boolean {
            val tags = getPartOfSpeech(word)
            for (item in tags) {
                when (item) {
                    "NNP" -> return true
                    "NNPS" -> return true
                }
            }
            return false
        }

        fun isAdj(word: String): Boolean {
            val tags = getPartOfSpeech(word)
            for (item in tags) {
                when (item) {
                    "JJ" -> return true
                }
            }
            return false
        }

        fun isConj(word: String): Boolean {
            val tags = getPartOfSpeech(word)
            for (item in tags) {
                when (item) {
                    "CC" -> return true
                    "IN" -> return true
                }
            }

            return false
        }

        fun isDetermin(word: String): Boolean {
            val tags = getPartOfSpeech(word)
            for (item in tags) {
                when (item) {
                    "DT" -> return true
                }
            }
            return false
        }

        fun isPlural(word : String) : Boolean{
            val lem = Sentence(word).lemma(0)
            return lem != word
        }
    }

    class SentenceHelper(){
        fun getTopic(sentence: String) : String{

            val doc = Document(sentence)


            println(sentence)

            for (sent in doc.sentences()) {
                for (triple in sent.openieTriples()) {
                    return triple.subjectLemmaGloss()
                }
            }

            return "null"
        }

        fun collectTopicFromSentence(string : String) : String {
            val words = GeneralUtils().stringToList(string, " ", -1)
            val tags = Sentence(string).posTags()
            println(tags)

            var counter = 0

            while(counter < tags.count()) {
                if(tags[counter] != "VBP" && tags[counter] != "PRP" && tags[counter] != "IN") {
                    if(tags[counter] == "NNP" || tags[counter] == "NN") {
                        return words[counter]
                    } else if(tags[counter] == "NNPS" || tags[counter] == "NNS"){
                        return words[counter]
                    } else if(tags[counter] == "JJ") {
                        return words[counter]
                    } else{
                        counter++
                    }
                } else{
                    counter++
                }
            }

            return "null"
        }

        private fun isTopicAccompanied(topic : String, sentence : String) : Boolean{
            try {
                val sentenceList = GeneralUtils().stringToList(sentence, " ", -1)
                val listDetermine = arrayListOf<String>("is", "are", "was")

                if (sentence.contains(topic)) {
                    for (determination in listDetermine) {
                        if (sentenceList[sentenceList.indexOf(topic) + 1] == determination) {
                            return true
                        }
                    }
                } else {
                    return false
                }
            } catch (exception : IndexOutOfBoundsException){
                return false
            }

            return false
        }

        fun generateSentenceFromAttributes(topic : String, attributes : ArrayList<String>) : String{
            var sentence = topic

            for(attribute in attributes) {
                sentence += if (isTopicAccompanied(topic, sentence)) {
                    if(attributes.indexOf(attribute) != attributes.count() - 1){
                        " , $attribute"
                    } else{
                        " and $attribute"
                    }
                } else{
                    if(POS().isPlural(topic)){
                        " are $attribute"
                    } else{
                        " is $attribute"
                    }
                }
            }

            return sentence
        }
    }
}