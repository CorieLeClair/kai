package kai.kaibrain.pai

import py4j.GatewayServer
import java.util.HashMap

internal data class PaiInformation(var fileResult : String, var dict : HashMap<String, String>, var isFinished : Boolean, var trainType : Int, var url : String)
private val paiInformation = PaiInformation("default.json", HashMap(), false, 0, "default")

internal data class KaiSpeechInformation(var words : String)
private val kaiSpeechInformation = KaiSpeechInformation("hello")

/** Pai Will More Than Likely not Work On Mobile Systems. For use on Windows, Linux, BSD, and Mac.
 * Pai Server will create connections with Python to execute tasks.**/
internal class PaiServer(){
    internal val speechServerGateway = GatewayServer.GatewayServerBuilder().javaPort(1001).build()
    internal val trainServerGateway = GatewayServer.GatewayServerBuilder().javaPort(1000).build()

    internal val winPython = "src/main/kotlin/kai/kaibrain/pai/python/interp/win/python.exe"

    class TrainChatServer(){
        fun startTrainServer(){
            PaiServer().trainServerGateway.start()
        }

        fun killTrainServer(){
            PaiServer().trainServerGateway.shutdown()
        }

        fun startChatTraining(dict : HashMap<String, String>, fileResult : String){
            paiInformation.trainType = 1
            paiInformation.fileResult = fileResult
            paiInformation.dict = dict
            val p = Runtime.getRuntime().exec("${PaiServer().winPython} C:\\Users\\corie\\OneDrive\\Documents\\kai\\api\\kai\\src\\main\\kotlin\\kai\\kaibrain\\pai\\python\\trainer\\chat_creater.py")
        }

        fun startArticleChatTraining(url : String, fileResult : String){
            paiInformation.trainType = 2
            paiInformation.fileResult = fileResult
            paiInformation.url = url
            val p = Runtime.getRuntime().exec("${PaiServer().winPython} C:\\Users\\corie\\OneDrive\\Documents\\kai\\api\\kai\\src\\main\\kotlin\\kai\\kaibrain\\pai\\python\\trainer\\chat_creater.py")
        }

        fun getDict() : HashMap<String, String>{
            return paiInformation.dict
        }

        fun getFileResult() : String{
            return paiInformation.fileResult
        }

        fun getURL() : String{
            return paiInformation.url
        }

        fun getTrainType() : Int{
            return paiInformation.trainType
        }
    }

    class SpeechServer(){
        fun startSpeechServer(){
            PaiServer().speechServerGateway.start()
        }

        fun startSpeech(text : String){
            kaiSpeechInformation.words = text
            val p = Runtime.getRuntime().exec("${PaiServer().winPython} C:\\Users\\corie\\OneDrive\\Documents\\kai\\api\\kai\\src\\main\\kotlin\\kai\\kaibrain\\pai\\python\\speech\\speech.py")

        }

        fun getWords() : String{
            return kaiSpeechInformation.words
        }

        fun killSpeechServer(){
            PaiServer().speechServerGateway.shutdown()
        }
    }
}
