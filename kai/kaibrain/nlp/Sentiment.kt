package kai.kaibrain.nlp

import edu.stanford.nlp.simple.Sentence

class Sentiment {
    enum class SentimentValues {
        Positive, Negative
    }

    fun sentimentValue(sentence : String) : SentimentValues{
        return if(Sentence(sentence).sentiment().isNegative){
            SentimentValues.Negative
        } else{
            SentimentValues.Positive
        }
    }
}