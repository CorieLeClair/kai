package kai.kaibrain.autothink

import kai.kaibrain.KaiBrainGeneral
import kai.kaibrain.KaiSpeechTagging
import kai.utils.GeneralUtils

internal data class AutoThinkInformation(var possibleNouns : ArrayList<String>)
internal val autoThinkInformation = AutoThinkInformation(KaiBrainGeneral().kaiGetAllMessages() )

internal class AutoThinkSystem() {

    internal class AutoThink : Runnable {
         override fun run() {
            while (true) {
                if(AutoThinkSystem().getNoun() == "null"){
                    break
                } else{
                    println(AutoThinkSystem().getNoun())
                }
            }
        }
    }

    internal fun getNoun() : String{
        println(KaiSpeechTagging().isNoun("Larissa"))
        for(item in autoThinkInformation.possibleNouns){
            val itemList = GeneralUtils().stringToList(item, " ", -1)

            for(word in itemList){
                if(KaiSpeechTagging().isNoun(word)){
                    autoThinkInformation.possibleNouns.removeAt(autoThinkInformation.possibleNouns.indexOf(item))
                    return word
                }
            }
        }
        return "null"
    }



    internal fun autoThink() {
        val autoThinkThread = Thread(AutoThink())
        autoThinkThread.start()
    }

}
