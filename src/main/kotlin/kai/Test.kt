package kai

import kai.configuration.KaiConfig
import kai.kaibrain.KaiTrainer
import java.io.File

fun main(){
    // files from the KaiPy GUI // brain files // json files
    KaiConfig.Kai().defineProjectFolder(File("C:/Users/corie/OneDrive/Documents/kai/KaiCompanion/documents"))
    KaiConfig.Kai().definePythonInterpreter(File("C:/Users/corie/OneDrive/Documents/kai/kai/pai/"))
    KaiConfig.Kai().enableAutoThink(true)


    while(true){
        print("> ")

        // defining the input for Kai to read
        KaiConfig.Kai().defineInput(readLine().toString())

        // running conversation systems
        KaiConfig.Kai().runConversation(true)

        //  printing system results!
        println("Kai:${KaiConfig.Kai().getKaiResponse()}")

        // How easy?
    }
}
