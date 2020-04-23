package kai.configuration

// imports
import java.io.File
import kai.conversation.ConversationLoader
import kai.paibrain.PaiServer

internal data class KaiInformation(var conversationDirectory: File, var input: String, var run: Boolean, var kaiResponse: String, var pythonDirectory : File, var platform : KaiConfig.Platform)
internal val kaiInformation = KaiInformation(KaiConfig.KaiDefaultInformation().conversationDirectory, KaiConfig.KaiDefaultInformation().input, KaiConfig.KaiDefaultInformation().run, KaiConfig.KaiDefaultInformation().kaiResponse, KaiConfig.KaiDefaultInformation().pythonDirectory, KaiConfig.Platform.Windows)

class KaiConfig() {
    enum class Platform{
        Windows, Mac, Linux, Android
    }

    internal class KaiDefaultInformation {
        internal var conversationDirectory = File("/default")
        internal var input = String()
        internal var run = true
        internal var kaiResponse = String()
        internal var pythonDirectory = File("/default")
    }

    class Kai {
        /** Defines Directory For All Aspects Of Conversations */
        fun defineProjectFolder(directory: File) {
            kaiInformation.conversationDirectory = directory
        }

        /** To define a python interpreter -- download Pai(Working Python Interpreter) from Github **/
        fun definePythonInterpreter(directory: File){
            kaiInformation.pythonDirectory = directory
        }

        /**  Defines Input For Kai To Respond To */
        fun defineInput(input: String) {
            kaiInformation.input = input
        }

        /** Starts Or Kills Conversation Systems */
        fun runConversation(boolean: Boolean) {
            kaiInformation.run = boolean
            ConversationLoader().conversationDirectory()
        }

        /** Gets Kai Response */
        fun getKaiResponse(): String {
            return kaiInformation.kaiResponse
        }

        /** allows backend thinking */
        fun enableAutoThink(boolean : Boolean) {
            if (boolean) {
                Thread("AutoThink").start().run { PaiServer.AutoThinkServer().startAutoThinkServer(); PaiServer.AutoThinkServer().startThinking(); PaiServer.AutoThinkServer().getAllMessages()}
            }
        }

        fun definePlatform(platform : Platform){
            kaiInformation.platform = platform
        }
    }
}
