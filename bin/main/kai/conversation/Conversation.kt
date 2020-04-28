package kai.conversation

// imports
import kai.utils.GeneralUtils
import kai.kaibrain.KaiBrainGeneral
import kai.configuration.kaiInformation
import kai.kaibrain.BrainClasses
import kai.utils.MessageType

internal class Conversation() {

    var counterFile = 0
    var counterFileLine = 0
    var counterInputIndex = 0
    var counterRun = 0


    internal fun conversation() : Boolean{

        val input = kaiInformation.input
        val files = GeneralUtils().getManualFiles( ".json")

        fun collectFile(): Boolean {
            return files.count() > counterFile
        }

        fun resetCounter() {
            counterInputIndex = 0
            counterRun = 0
            counterFileLine = 0
            counterFile = 0
        }


        fun collectResultChatBroad(): String {
            var response = "I'm not sure what you mean. I am sorry."

            while (collectFile() && KaiBrainGeneral.BrainMessageInformation().getListOfMessages(files[counterFile]).count() > counterInputIndex) {
                if (input.contains(KaiBrainGeneral.BrainMessageInformation().getListOfMessages(files[counterFile]).get(counterInputIndex).toString())) {
                    response = KaiBrainGeneral.BrainMessageInformation().getMessageInformation(KaiBrainGeneral.BrainMessageInformation().getListOfMessages(files[counterFile]).get(counterInputIndex).toString(), files[counterFile], BrainClasses.BrainReturnMessageInfo.MessageResult)
                    break
                } else {
                    if (counterInputIndex >= KaiBrainGeneral.BrainMessageInformation().getListOfMessages(files[counterFile]).count() - 1) {
                        println(MessageType().returnQuestionType(input))
                        counterInputIndex = 0
                        counterFile++

                    } else {
                        counterInputIndex++
                    }
                }
            }
            return response
        }

        resetCounter()
        kaiInformation.kaiResponse = collectResultChatBroad()

        return kaiInformation.kaiResponse != "I'm not sure what you mean. I am sorry."
    }
}
