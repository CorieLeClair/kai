package kai.configuration

// imports
import java.io.File
import kai.conversation.conversationDirectory
import kai.kaibrain.autothink.autoThink

internal class KaiDefaultInformation {
    internal var conversationDirectory = File("/cf")
    internal var input = String()
    internal var run = true
    internal var kaiResponse = String()
}

internal data class KaiInformation(var conversationDirectory : File, var input : String, var run : Boolean, var kaiResponse : String)

internal val kaiInformation = KaiInformation(KaiDefaultInformation().conversationDirectory, KaiDefaultInformation().input, KaiDefaultInformation().run, KaiDefaultInformation().kaiResponse)

public class Kai {
    /** Defines Directory For All Aspects Of Conversations */
    public fun defineConversationDirectory(directory : File) = kaiDefineConversationDirectory(directory)
    /**  Defines Input For Kai To Respond To */
    public fun defineInput(input : String) = kaiDefineInput(input)
    /** Starts Or Kills Conversation Systems */
    public fun runConversation(boolean : Boolean) = kaiRunConversationSystem(boolean)
    /** Gets Kai Response */
    public fun getKaiResponse() = kaiGetKaiResponse()
    /** allows backend thinking */
    public fun enableAutoThink(boolean : Boolean) = kaiEnableAutoThink(boolean)
}

// internal functions, backend handling systems

internal fun kaiEnableAutoThink(boolean : Boolean){
    if(boolean){
        Thread().start().run { autoThink() }
    }
}

internal fun kaiDefineConversationDirectory(directory : File){
    kaiInformation.conversationDirectory = directory
}

internal fun kaiDefineInput(input : String){
    kaiInformation.input = input
}

internal fun kaiRunConversationSystem(boolean : Boolean){
    kaiInformation.run = boolean 
    conversationDirectory()
}

internal fun kaiSetKaiResponse(response : String){
    kaiInformation.kaiResponse = response
}

internal fun kaiGetKaiResponse() : String{
    return kaiInformation.kaiResponse
}
