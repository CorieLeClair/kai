package kai.kaibrain.emotion

import edu.stanford.nlp.simple.Sentence
import kai.configuration.Kai

class ToneRecognition {

    enum class Emotion {
        Angry, Sad, Happy, Normal
    }

    fun getEmotionTone(builder: Kai.KaiBuilder) {
        emotionToneRecognizer(builder)
    }

    private fun emotionToneRecognizer(builder : Kai.KaiBuilder) : Emotion{         
        return Emotion.Normal
    }
}