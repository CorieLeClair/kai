package kai.conversation

import kai.configuration.KaiInformation
import kai.configuration.KaiDefaultInformation
import kai.conversation.conversation
import kai.configuration.kaiInformation

internal fun conversationDirectory(){
    if(KaiInformation(KaiDefaultInformation().conversationDirectory, KaiDefaultInformation().input, KaiDefaultInformation().run, KaiDefaultInformation().kaiResponse).run == true){
        conversation()
    }
}