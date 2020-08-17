package kai.kaibrain.trainer

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kai.configuration.Kai
import kai.kaibrain.messageinformation.KaiBrainMessages
import org.apache.commons.io.FileUtils
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class BaseTrainer {

    fun ResultBuilder(result: ArrayList<String>, ignoreAutoLearn: Boolean, ignoreMock : Boolean): ArrayList<Any> {
        return arrayListOf(result, ignoreAutoLearn, ignoreMock)
    }

    fun trainDictionary(dict : HashMap<String, ArrayList<Any>>): JsonObject {

        val messagesJson = JsonObject()
        val messageContainer = JsonObject()


        for(id in dict.keys){
            messagesJson.addProperty(id.replace(" ", "_"), dict[id].toString())
        }

        messageContainer.add("messages", messagesJson)

        return messageContainer
    }

    fun writeToJson(file: File, json: JsonObject){
        val gson = GsonBuilder().setPrettyPrinting().create()
        val writer = BufferedWriter(FileWriter(file))
        writer.write(gson.toJson(json))
        writer.close()

    }

    fun writeToJson(file: File, json: JSONObject){
        val gson = GsonBuilder().setPrettyPrinting().create()
        val writer = BufferedWriter(FileWriter(file))
        writer.write(gson.toJson(json))
        writer.close()

    }

    fun  appendToMessages(jsonFile : File, dict : HashMap<String, ArrayList<Any>>, builder : Kai.KaiBuilder) {
        val jsonContent = FileUtils.readFileToString(jsonFile, "utf-8")
        val jsonMain = JSONObject(jsonContent)
        val jsonMessages = jsonMain.getJSONObject("messages")

        for(name in jsonMessages.names()){
            var counter = 0
            while(counter < dict.count()) {
                if(!dict.containsKey(name)) {
                    val messageList = arrayListOf<String>()

                    for(thing in KaiBrainMessages.ResponseInformation().collectAllResponses(name.toString(), builder)) {
                        messageList.add(thing)
                    }

                    dict[name.toString()] = ResultBuilder(messageList, KaiBrainMessages.ResponseInformation().collectAutoLearnBoolean(name.toString(), builder), KaiBrainMessages.ResponseInformation().collectMockBoolean(name.toString(), builder))

                }

                counter++
            }
        }

        if(dict.count() > 0) {
            writeToJson(jsonFile, trainDictionary(dict))
        }
    }

    fun trainFaceboook(jsonFile: File, writeFile : File) {
       //  writeToJson(writeFile, trainDictionary(SocialMedia.FacebookMessages().createDict(jsonFile)))
    }
}