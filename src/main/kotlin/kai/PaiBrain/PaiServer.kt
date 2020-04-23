package kai.paibrain

import kai.configuration.kaiInformation
import kai.kaibrain.KaiBrainGeneral
import py4j.GatewayServer
import py4j.Py4JNetworkException
import java.io.File
import java.net.InetSocketAddress
import java.util.HashMap

internal data class PaiInformation(var fileResult : String, var dict : HashMap<String, String>, var isFinished : Boolean, var url : String)
private val paiInformation = PaiInformation("default.json", HashMap(), false,  "default")

internal data class KaiSpeechInformation(var words : String)
private val kaiSpeechInformation = KaiSpeechInformation("hello")

/** Pai Will More Than Likely not Work On Mobile Systems. For use on Windows, Linux, BSD, and Mac.
 * Pai Server will create connections with Python to execute tasks.**/
internal class PaiServer(){
    internal val trainServerGateway = GatewayServer.GatewayServerBuilder().javaPort(1000).build()
    internal val speechServerGateway = GatewayServer.GatewayServerBuilder().javaPort(1001).build()
    internal val autoThinkServerGateway = GatewayServer.GatewayServerBuilder().javaPort(1002).build()

    internal val winPython = "${kaiInformation.pythonDirectory}\\interp\\win\\python.exe"
    internal val scriptTrainChat = "${kaiInformation.pythonDirectory}/chat_file/train_chat_files.py"
    internal val scriptAutoThink = "${kaiInformation.pythonDirectory}/autothink/auto_think.py"
    internal val scriptSpeechSpeak = "${kaiInformation.pythonDirectory}/speech/speech.py"

    class TrainChatServer(){
        private fun startTrainServer(){
            try{
                PaiServer().trainServerGateway.start()
            } catch (ex : Py4JNetworkException){
                killTrainServer()
                startTrainServer()
            }
        }

        private fun killTrainServer(){
            try{
                PaiServer().trainServerGateway.shutdown()
            } catch (ex : Py4JNetworkException){
                // do nothing
            }
        }

        fun startChatTraining(dict : HashMap<String, String>, fileResult : String){
            startTrainServer()
            paiInformation.fileResult = fileResult
            paiInformation.dict = dict
            val p = Runtime.getRuntime().exec("${PaiServer().winPython} ${PaiServer().scriptTrainChat}")
            killTrainServer()
        }

        fun getDict() : HashMap<String, String>{
            return paiInformation.dict
        }

        fun getFileResult() : String{
            return paiInformation.fileResult
        }

        fun finished(text: String){
            println("FINSIHED WHORE")
            println(text)
        }
    }

    class SpeechServer(){
        fun startSpeechServer(){
            PaiServer().speechServerGateway.start()
        }

        fun startSpeech(text : String){
            kaiSpeechInformation.words = text
            val p = Runtime.getRuntime().exec("${PaiServer().winPython} ${PaiServer().scriptSpeechSpeak}")

        }

        fun getWords() : String{
            return kaiSpeechInformation.words
        }

        fun killSpeechServer(){
            PaiServer().speechServerGateway.shutdown()
        }
    }

    class AutoThinkServer(){
        fun startAutoThinkServer(){
            PaiServer().autoThinkServerGateway.start()
        }

        fun killAutoThinkServer(){
            PaiServer().autoThinkServerGateway.start()
        }

        fun startThinking(){
            val p = Runtime.getRuntime().exec("${PaiServer().winPython} ${PaiServer().scriptAutoThink}")
        }

        fun getAllMessages() : ArrayList<String>{
            return KaiBrainGeneral().kaiGetAllMessages()
        }
    }
}