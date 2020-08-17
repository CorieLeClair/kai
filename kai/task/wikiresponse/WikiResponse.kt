package kai.task.wikiresponse

import kai.configuration.Kai
import kai.kaibrain.messageinformation.BrainClasses
import kai.kaibrain.nlp.NaturalLanguageSpeechHelper
import kai.utils.MessageType
import org.fastily.jwiki.core.Wiki
import java.net.UnknownHostException
import java.text.BreakIterator
import java.util.*
import kotlin.collections.ArrayList


class WikiResponse(val builder: Kai.KaiBuilder) {

    enum class MediaWikis {
        WikiEM, Wikipedia
    }

    fun getSummaryWiki() : String {
        try {
            val kaiWiki = Wiki.Builder().build()
            val topic = NaturalLanguageSpeechHelper.SentenceHelper().getTopic(builder.input)

            val listOfSentences = ArrayList<String>()
            val returnSentences = ArrayList<String>()

            if (kaiWiki.exists(topic)) {
                val text = kaiWiki.getTextExtract(topic)

                val iterator = BreakIterator.getSentenceInstance(Locale.US)
                iterator.setText(text)

                var start = iterator.first()

                var end = iterator.next()

                while (end != BreakIterator.DONE) {
                    listOfSentences.add(text.substring(start, end))
                    start = end
                    end = iterator.next()
                }
            }
            /*
        var counter = 0
        while (counter <= 2){
            returnSentences.add(listOfSentences[counter])
            counter++
        }

         */

            return "$listOfSentences"
        } catch (e : Exception) {
            println("HERe")
            builder.conversationMethodSuccessFull = false
        }
        return ""
    }

    fun getWikiResponse() {
        if(MessageType().returnQuestionType(builder.input) != BrainClasses.MessageType.Question.Unknown){
            builder.response = getSummaryWiki()
        }
    }

    private fun isTopicNull (topic : String) : Boolean {
        return false
    }


    fun collectWikiResponse(wiki : MediaWikis) {
        if(wiki == MediaWikis.Wikipedia) {

        }
    }
 }