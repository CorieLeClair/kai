package kai

import edu.stanford.nlp.simple.Sentence
import kai.configuration.Kai
import kai.kaibrain.emotion.ToneRecognition
import kai.kaibrain.nlp.NaturalLanguageSpeechHelper
import java.io.File
import kai.kaibrain.trainer.BaseTrainer
import java.util.*


fun main(){
    //KaiReminders().initReminder( { a() }, "2020-05-04 00:021:00")
    val builder = Kai.KaiBuilder("first")

    builder.projectPath = File("C:/Users/corie/OneDrive/Desktop/kaiProject")
    builder.conversationStatus = true

    Sentence("a").nerTags()
    Sentence("a").posTag(0)
    Sentence("a").openie()
    Sentence("a").sentiment()
    Sentence("a").lemmas()


    while(true){
        print("> ")
        builder.input = readLine().toString()

        println(NaturalLanguageSpeechHelper.SentenceHelper().getTopic(builder.input))
        println(NaturalLanguageSpeechHelper.SentenceHelper().collectTopicFromSentence(builder.input))

        Kai().runConversation(builder)

        println("Kai: ${builder.response}")
    }
}
