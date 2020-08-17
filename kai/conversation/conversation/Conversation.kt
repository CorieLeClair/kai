package kai.conversation.conversation

import edu.stanford.nlp.simple.Sentence
import kai.configuration.Kai
import kai.kaibrain.messageinformation.KaiBrainMessages
import kai.utils.GeneralUtils

internal class Conversation() {

    var counterFile = 0
    var counterFileLine = 0
    var counterInputIndex = 0
    var counterRun = 0


    internal fun conversation(builder: Kai.KaiBuilder){
        fun resetCounter() {
            counterInputIndex = 0
            counterRun = 0
            counterFileLine = 0
            counterFile = 0
        }


        fun collectResultChatBroad(): String {
            var response = "I'm not sure what you mean. I am sorry."

            val messages = GeneralUtils().sortListBy(KaiBrainMessages.ResponseInformation().collectAllManualMessages(builder))

            //println(GeneralUtils().sortListBy(messages))


            for(message in messages){
                if(builder.input == message) {
                    response = KaiBrainMessages.ResponseInformation().collectRandomResponse(message.replace(" ", "_"), builder)
                } else{
                    for(word in GeneralUtils().sortListBy(GeneralUtils().stringToList(builder.input, " ", -1))){
                        if(word == message) {
                            response = KaiBrainMessages.ResponseInformation().collectRandomResponse(message.replace(" ", "_"), builder)
                            break
                        }
                    }
                }
            }

            return response
        }

        resetCounter()
        builder.response = collectResultChatBroad()

        builder.conversationMethodSuccessFull = builder.response != "I'm not sure what you mean. I am sorry."
    }
}
