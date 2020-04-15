package kai

import kai.configuration.KaiConfig
import java.io.File


fun main(){
    // files from the KaiPy GUI // brain files // json files
    KaiConfig.Kai().defineConversationDirectory(File("C:\\Users\\corie\\OneDrive\\Documents\\KaiCompanion\\documents"))
    KaiConfig.Kai().enableAutoThink(true)


    while(true){
        print("> ")

        // defining the input for Kai to read
        KaiConfig.Kai().defineInput(readLine().toString())
        // running conversation systems
        KaiConfig.Kai().runConversation(true)
        //  printing system results!
        println("Kai:${KaiConfig.Kai().getKaiResponse()}")
        //KaiSpeech().speak(KaiConfig.Kai().getKaiResponse())
        // How easy?
    }
}
