package kai.kaibrain

import kai.paibrain.PaiServer
import py4j.Py4JNetworkException
import java.lang.Exception

public class KaiTrainer(){
    /** Train off of chat messages **/
    class KaiChatTrainer() {
        /** Will train, StartTrainingServer() needs to be called first. **/
        fun kaiTrainChat(dict: HashMap<String, String>, fileResult: String) {
            PaiServer.TrainChatServer().startChatTraining(dict, fileResult)
        }

        fun startTrainingServer(){
            PaiServer.TrainChatServer().startTrainServer()
        }

        fun killTrainingServer(){
            PaiServer.TrainChatServer().killTrainServer()
        }

    }

    /** Train off of sentiment values 1 to 10 **/
    class KaiSentimentTrainer(){

        fun startTrainer(noun : String, scale : Int, resultFile : String){


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

            KaiChatTrainer().kaiTrainChat(dict, resultFile)
        }
    }
}