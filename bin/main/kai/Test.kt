package kai

import kai.configuration.KaiConfig
import java.io.File
import java.text.SimpleDateFormat
import java.text.DateFormat
import java.util.*

private class MyTimeTask : TimerTask() {
    override fun run() {
        println("here")
    }
}

fun main(){
    // files from the KaiPy GUI // brain files // json files
    KaiConfig.Kai().defineConversationDirectory(File("C:\\Users\\corie\\OneDrive\\Documents\\kai\\KaiCompanion\\documents"))
    KaiConfig.Kai().enableAutoThink(true)

    val dateFormatter: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date: Date = dateFormatter.parse("2020-04-15 13:50:01")
    val timer = Timer()
    timer.schedule(MyTimeTask(), date)


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
