package kai.conversation

import kai.configuration.KaiConfig
import kai.configuration.KaiInformation


internal class ConversationLoader() {
    internal fun conversationDirectory() {
        if (KaiInformation(KaiConfig.KaiDefaultInformation().conversationDirectory, KaiConfig.KaiDefaultInformation().input, KaiConfig.KaiDefaultInformation().run, KaiConfig.KaiDefaultInformation().kaiResponse).run) {
            Conversation().conversation()
        }
    }
}