package kai.kaibrain

import kai.kaibrain.pai.PaiServer

class KaiSpeech {
    fun speak(words : String){
        val a = PaiServer.SpeechServer().startSpeech(words)
    }

    fun startSpeechServer(){
        PaiServer.SpeechServer().startSpeechServer()
    }

    fun killSpeechServer(){
        PaiServer.SpeechServer().killSpeechServer()
    }
}