package kai.configuration

// imports
import java.io.File
import kai.conversation.ConversationLoader
import kai.kaibrain.autothink.AutoThinkSystem
import kai.kaibrain.pai.PaiServer

internal data class KaiInformation(var conversationDirectory: File, var input: String, var run: Boolean, var kaiResponse: String)
internal val kaiInformation = KaiInformation(KaiConfig.KaiDefaultInformation().conversationDirectory, KaiConfig.KaiDefaultInformation().input, KaiConfig.KaiDefaultInformation().run, KaiConfig.KaiDefaultInformation().kaiResponse)

class KaiConfig() {

    internal class KaiDefaultInformation {
        internal var conversationDirectory = File("/cf")
        internal var input = String()
        internal var run = true
        internal var kaiResponse = String()
    }

    class Kai {
        /** Defines Directory For All Aspects Of Conversations */
        public fun defineConversationDirectory(directory: File) {
            kaiInformation.conversationDirectory = directory
        }

        /**  Defines Input For Kai To Respond To */
        public fun defineInput(input: String) {
            kaiInformation.input = input
        }

        /** Starts Or Kills Conversation Systems */
        public fun runConversation(boolean: Boolean) {
            kaiInformation.run = boolean
            ConversationLoader().conversationDirectory()
        }

        /** Gets Kai Response */
        public fun getKaiResponse(): String {
            return kaiInformation.kaiResponse
        }

        /** allows backend thinking */
        public fun enableAutoThink(boolean : Boolean) {
            if (boolean) {
                Thread("AutoThink").start().run { PaiServer.AutoThinkServer().startAutoThinkServer(); PaiServer.AutoThinkServer().startAutoThinkSystems()}
            }
        }
    }
}
