package kai.conversation.autogen

import kai.configuration.kaiInformation
import kai.kaibrain.KaiBrainGeneral
import kai.utils.GeneralUtils
import kai.utils.MessageType
import kai.kaibrain.BrainClasses

internal class AutoGenAsk {
    fun autoGenConversation(){
        val input = kaiInformation.input

        val files = GeneralUtils().getAutoGeneratedFiles(".json")

        for(file in files){
            for(message in KaiBrainGeneral.BrainMessageInformation().getAllContainMessagesSpec(files)){
                if(input.contains(message.replace("]", "").replace("[", ""))){
                    if(KaiBrainGeneral.BrainMessageInformation().getMessageInformation(message.replace("]", "").replace("[", ""), file, BrainClasses.BrainReturnMessageInfo.Desc) == "desc" && MessageType().returnQuestionType(input) == BrainClasses.MessageType.Question.Ask001){
                        kaiInformation.kaiResponse = KaiBrainGeneral.BrainMessageInformation().getMessageInformation(message.replace("]", "").replace("[", ""), file, BrainClasses.BrainReturnMessageInfo.MessageResult)
                    }
                }
            }
        }

    }
}