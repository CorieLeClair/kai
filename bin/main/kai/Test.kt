package kai

import kai.configuration.Kai
import java.io.File
import kai.speech.speak
import kai.kaibrain.BrainMessageInformation

fun main(){
    // files from the KaiPy GUI // brain files
    Kai().defineConversationDirectory(File("C:/Users/corie/OneDrive/Documents/kai/trainer/kaipy/demo"))
    Kai().enableAutoThink(true)

    while(true){
        print("> ")
        // defining the input for Kai to read
        Kai().defineInput(readLine().toString())
        // running conversation systems
        Kai().runConversation(true)
        //  printing system results!
        println("Kai:${Kai().getKaiResponse()}")

        // How easy?
    }
}
