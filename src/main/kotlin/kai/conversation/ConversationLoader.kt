package kai.conversation

import kai.configuration.KaiConfig
import kai.configuration.KaiInformation
import kai.configuration.kaiInformation
import kai.conversation.autogen.AutoGenAsk
import java.io.File


internal class ConversationLoader() {
    internal fun conversationDirectory() {
        if (KaiInformation(File("${KaiConfig.KaiDefaultInformation().conversationDirectory}"), KaiConfig.KaiDefaultInformation().input, KaiConfig.KaiDefaultInformation().run, KaiConfig.KaiDefaultInformation().kaiResponse, kaiInformation.pythonDirectory, kaiInformation.platform).run) {
            if(!Conversation().conversation()){
                AutoGenAsk().autoGenConversation()
            }
        }
    }
}