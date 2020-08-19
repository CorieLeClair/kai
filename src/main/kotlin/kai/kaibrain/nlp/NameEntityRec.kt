package kai.kaibrain.nlp

import edu.stanford.nlp.simple.Sentence
import java.lang.IndexOutOfBoundsException

class NameEntityRec {
     class NER() {
         enum class NERTypes{
             PERSON, ORG, TIME, LOCATION, UNKNOWN
         }

         fun isPerson(word : String, index: Int) : Boolean{
             return Sentence(word).nerTag(index) == "PERSON"

         }

         fun isOrg(word : String, index: Int) : Boolean{
             return Sentence(word).nerTag(index) == "ORGANIZATION "

         }

         fun isLocation(word : String, index : Int) : Boolean{
             return Sentence(word).nerTag(index) == "LOCATION "

         }

         fun isTime(words : String, index : Int) : Boolean{
             return Sentence(words).nerTag(index) == "TIME "

         }

         fun getNerType(words : String, index : Int) : NERTypes{
             return try {
                 when (Sentence(words.toUpperCase()).nerTag(index)) {
                     "LOCATION" -> NERTypes.LOCATION
                     "TIME" -> NERTypes.TIME
                     "ORGANIZATION" -> NERTypes.ORG
                     "PERSON" -> NERTypes.PERSON
                     else -> NERTypes.UNKNOWN
                 }
             } catch (ex : IndexOutOfBoundsException) {
                 NERTypes.UNKNOWN
             }
         }
    }
}