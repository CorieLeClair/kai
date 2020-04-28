package kai

import kai.configuration.KaiConfig
import kai.kaibrain.KaiSpeech
import kai.paibrain.PaiServer
import java.io.File


fun main(){
    // files from the KaiPy GUI // brain files // json files
    KaiConfig.Kai().defineProjectFolder(File("C:/Users/corie/OneDrive/Desktop/kaiProject"))
    KaiConfig.Kai().definePythonInterpreter(File("C:/Users/corie/OneDrive/Documents/Programming/projects/python/pai"))
    KaiConfig.Kai().enableAutoThink(true)

    while(true){
        print("> ")
        KaiConfig.Kai().defineInput(readLine().toString())
        KaiConfig.Kai().runConversation(true)

        println("Kai: ${KaiConfig.Kai().getKaiResponse()}")

        /*
        // defining the input for Kai to read
        val init = KaiSpeech().listen()
        println(init)

        if(init == "hey KY" || init == "Hayti" || init == "haggai" || init == "haggai") {
            PaiServer.ListenServer().finished()
            KaiSpeech().speak("Yes Corie?")
            KaiConfig.Kai().defineInput(KaiSpeech().listen())
            PaiServer.ListenServer().finished()

            // running conversation systems
            KaiConfig.Kai().runConversation(true)

            //  printing system results!
            println("Kai:${KaiConfig.Kai().getKaiResponse()}")
            KaiSpeech().speak(KaiConfig.Kai().getKaiResponse())
            // How easy?
        }
        */
    }
}
