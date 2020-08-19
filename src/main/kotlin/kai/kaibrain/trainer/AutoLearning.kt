package kai.kaibrain.trainer

import kai.configuration.Kai
import kai.kaibrain.messageinformation.KaiBrainMessages
import kai.kaibrain.nlp.NameEntityRec
import kai.kaibrain.nlp.Sentiment
import kai.kaibrain.nlp.NaturalLanguageSpeechHelper
import kai.utils.GeneralUtils
import java.io.File

internal class AutoLearning {
    private fun collectDefinition(words : String, topic : String) : NameEntityRec.NER.NERTypes {
        val topicIndex = GeneralUtils().stringToList(words," ", -1).indexOf(topic)
        return NameEntityRec.NER().getNerType(words, topicIndex)
    }

    private fun collectSentiment(sentence: String) : Sentiment.SentimentValues{
        return Sentiment().sentimentValue(sentence)
    }

    private fun collectAdj(sentence : String) : ArrayList<String>{
        val list = ArrayList<String>()

        for(word in GeneralUtils().stringToList(sentence, " ", -1)){
            if(NaturalLanguageSpeechHelper.POS().isAdj(word)){
                list.add(word)
            }
        }

        return list
    }

    fun  askAbout(builder: Kai.KaiBuilder) {
        println("ask")
        val topic = NaturalLanguageSpeechHelper.SentenceHelper().collectTopicFromSentence(builder.input)
        println(topic)
        if (topic != "null" && NaturalLanguageSpeechHelper.POS().isNoun(topic)) {
            builder.response = "Please tell me more about $topic"
            builder.conversationMethodSuccessFull = true
            builder.conversationHolder = 1
        } else {
            builder.response = "Okay."
            builder.conversationMethodSuccessFull = true
        }
    }

    fun createMessage(builder: Kai.KaiBuilder){
        // response
        val responseAsk001 : String
        val responseAsk002 : String
        val responseAsk003 :  String
        val responseAsk004 : String
        val responseAsk005 : String

        val sentiment = collectSentiment(builder.input)
        val topic = NaturalLanguageSpeechHelper.SentenceHelper().collectTopicFromSentence(builder.input)

        responseAsk001 = when(sentiment){
            Sentiment.SentimentValues.Positive -> "I like $topic."
            Sentiment.SentimentValues.Negative -> "I don't like $topic."
        }

        responseAsk002 = when(collectDefinition(builder.input, topic)){
            NameEntityRec.NER.NERTypes.PERSON -> "$topic is a person."
            NameEntityRec.NER.NERTypes.ORG -> "$topic is an organization."
            NameEntityRec.NER.NERTypes.LOCATION -> "$topic is a location"
            NameEntityRec.NER.NERTypes.TIME -> "$topic is a time."
            NameEntityRec.NER.NERTypes.UNKNOWN -> "I am unsure of what $topic is."
        }

        responseAsk003 = NaturalLanguageSpeechHelper.SentenceHelper().generateSentenceFromAttributes(topic, collectAdj(builder.input))
        responseAsk004 = "I'm not sure where $topic is."
        responseAsk005 = "I'm not sure how $topic is done."

        val messages = arrayListOf("$responseAsk001 : Ask001", "$responseAsk002 : Ask002", "$responseAsk003 : Ask003", "$responseAsk004 : Ask004", "$responseAsk005 : Ask005")
        val dict = HashMap<String, ArrayList<Any>>()

        dict[topic] = BaseTrainer().ResultBuilder(messages, true, false)
        builder.response = "Thank you for telling me about $topic"
        builder.conversationMethodSuccessFull = true
        builder.conversationHolder = 0
        BaseTrainer().writeToJson(File("${builder.projectPath}/autogen/$topic.json"), BaseTrainer().trainDictionary(dict))
    }

    // https://www.wikem.org/w/api.php?action=query&list=search&srsearch=blood&format=json
    // http://clinfowiki.org/wiki/api.php?action=query&list=search&srsearch=blood&format=json
}