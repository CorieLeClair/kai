package kai.kaibrain

import kai.paibrain.PaiServer

class KaiSpeech {
    fun speak(words : String){
        val a = PaiServer.SpeechServer().startSpeech(words)
    }

    fun startSpeechServer(){
        PaiServer.SpeechServer().startSpeechServer()
    }

    fun startListenServer(){
        PaiServer.ListenServer().startServer()
    }

    fun listen() : String{
        return PaiServer.ListenServer().listen()
    }
}