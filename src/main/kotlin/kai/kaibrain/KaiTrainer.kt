package kai.kaibrain

import kai.kaibrain.pai.PaiServer
import org.json.JSONObject
import py4j.Py4JNetworkException
import java.lang.Exception

class KaiTrainer(){
    class KaiChatTrainer() {

        /** StartTrainingServer() will start the server for training **/
        fun startTrainingServer() {
            try {
                PaiServer.TrainChatServer().startTrainServer()
            } catch(exception: Py4JNetworkException){
                println("The server is already")
            }
        }

        /** Will train, StartTrainingServer() needs to be called first. **/
        fun kaiTrainChat(dict: HashMap<String, String>, fileResult: String) {
            PaiServer.TrainChatServer().startChatTraining(dict, fileResult)
        }

        fun killTrainingServer(){
            PaiServer.TrainChatServer().killTrainServer()
        }

    }

    class KaiSentimentTrainer(){

        fun startTrainer(noun : String, scale : Int, resultFile : String){

            if(!KaiSpeechTagging().isNoun(noun)){
                throw(Exception("The input is not a noun."))
            }

            if(scale > 10 || scale < 0){
                when{
                    scale > 10 -> throw(Exception("The number is too large; above 10"))
                    scale < 0 -> throw(Exception("The number is too small; under 0"))
                }
            }

            writeBrainFile(noun, scale, resultFile)
        }

        private fun writeBrainFile(noun : String, scale : Int, resultFile : String){
            println("here")
            val dict = HashMap<String, String>()

            when{
                scale < 5 ->{
                    dict["do you like $noun"] = "No. I do not like $noun"
                    //dict["do you hate $noun"] = "Yes I do."
                    //dict["Why do you hate Trump $noun"] = "I'm not sure"
                }

                scale == 5 ->{
                    dict["do you like $noun"] = "$noun are mutual to me."
                }

                scale > 5 ->{
                    dict["do you like $noun"] = "I do enjoy $noun"
                }
            }

            KaiChatTrainer().startTrainingServer()
            KaiChatTrainer().kaiTrainChat(dict, resultFile)
        }
    }
}