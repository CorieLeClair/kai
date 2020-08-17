package kai.conversation.conversation

import kai.configuration.Kai
import kai.kaibrain.messageinformation.KaiBrainMessages
import kai.utils.MessageType

internal class AutoLearnedConversation() {

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
            val messages = KaiBrainMessages.ResponseInformation().collectAllAutoLearnedMessages(builder)

            for(message in messages){
                if(builder.input.contains(message)){
                    val messageType = MessageType().returnQuestionType(builder.input)

                    response = KaiBrainMessages.ResponseInformation().getResponseFromDesc(message, builder, messageType).replace("[", "").replace("]", "")
                }
            }

            return response
        }

        resetCounter()
        builder.response = collectResultChatBroad()

        builder.conversationMethodSuccessFull = builder.response != "I'm not sure what you mean. I am sorry."
    }
}