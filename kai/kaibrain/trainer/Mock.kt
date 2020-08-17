package kai.kaibrain.trainer

import edu.stanford.nlp.simple.Sentence
import kai.configuration.Kai
import kai.kaibrain.messageinformation.KaiBrainMessages
import kai.kaibrain.nlp.NaturalLanguageSpeechHelper
import java.io.File

class Mock {
    class Trainer {
        fun startMockTrainingManualMessages(builder: Kai.KaiBuilder, map : ConversationAnalyze.ConversationAnaBuilder) {
            val allMessages = KaiBrainMessages.ResponseInformation().collectAllManualMessages(builder)
            val dict = HashMap<String, ArrayList<Any>>()


            for (message in allMessages) {
                if (!isSpecificQuestion(message) && !KaiBrainMessages.ResponseInformation().collectMockBoolean(message, builder)) {
                    dict[message] = BaseTrainer().ResultBuilder(arrayListOf(""), ignoreAutoLearn = true, ignoreMock = true)
                }
            }

            for(item in map.messageDict) {
                if(!isSpecificQuestion(item.key)) {
                    dict[item.key] = BaseTrainer().ResultBuilder(arrayListOf(""), ignoreAutoLearn = true, ignoreMock = true)
                }
            }

            BaseTrainer().writeToJson(File("${builder.projectPath}/autogen/rev/1.json"), BaseTrainer().trainDictionary(dict))
        }

        private fun isSpecificQuestion(question: String): Boolean {
            if(NaturalLanguageSpeechHelper.SentenceHelper().getTopic(question).length < 2){
                return true
            }

            if(NaturalLanguageSpeechHelper.SentenceHelper().getTopic(question) == "null") {
                return false
            }

            return true
        }

        fun updateResponsesMock(builder: Kai.KaiBuilder, dict : ConversationAnalyze.ConversationAnaBuilder){
            val newDict = HashMap<String, ArrayList<Any>>()

            for(file in KaiBrainMessages.ResponseInformation().collectAllManualFiles(builder)){
                for(item in KaiBrainMessages.ResponseInformation().collectAllMessagesFromFile(file)){
                    for(message in dict.messageDict){
                        if(message.key == item){
                            val listOfMessages = KaiBrainMessages.ResponseInformation().collectAllResponses(message.key, file)
                            listOfMessages.add(message.value)
                            newDict[message.key] = BaseTrainer().ResultBuilder(listOfMessages, true, ignoreMock = true)
                        }
                    }
                }


                BaseTrainer().appendToMessages(file, newDict, builder)
            }

            dict.messageDict.clear()
            newDict.clear()
        }
    }

    class ReviveConversation {
        private fun isConversationDead(builder: Kai.KaiBuilder) : Boolean {
            val listOfDeadConversation = arrayListOf<String>("ok", "okay", "alright", "cool", "yeah", "yep", "kool", "oh okay")
            for(tag in Sentence(builder.input).posTags()) {
                if(tag == "UH") {
                    return true
                }
            }

            return false
        }

        fun revievWithStarter(builder: Kai.KaiBuilder) {
            if(isConversationDead(builder)) {
                val conversationStarters = KaiBrainMessages.ResponseInformation().collectAllMessagesFromFile(File("${builder.projectPath}/autogen/rev/1.json")).toList()
                if(conversationStarters.count() > 0){
                    builder.response = conversationStarters.random().toString()
                    builder.conversationMethodSuccessFull = true
                }
            }
        }

        fun reviveWithClarifiction(builder: Kai.KaiBuilder) {

        }
    }
}