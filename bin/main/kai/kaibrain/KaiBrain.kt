package kai.kaibrain

import kai.utils.getSpecificFiles
import kai.configuration.kaiInformation
import kai.utils.stringToList
import kai.utils.getStringOfLine
import kai.utils.getLineInFile
import kai.configuration.kaiInformation
import kai.kaibrain.enums.BrainFileType
import kai.kaibrain.enums.BrainReturnMessageInfo
import org.apache.commons.io.FileUtils
import org.json.JSONObject
import org.json.JSONArray
import java.io.File
import java.util.Random

internal class BrainMessageInformation(){
    public fun getMessageInformation(input : String, jsonFile: File, returnType : BrainReturnMessageInfo) = kaiGetMessageInformation(input, jsonFile, returnType)
    public fun getListOfMessages(jsonFile : File) = kaiGetMessageList(jsonFile)
    public fun getAllStatements() = kaiGetAllStatements()

    public fun getRandomStatement() : String {
        return kaiGetAllStatements().random().toString().replace("[", "").replace("]", "")
    }
}

// brain systems

internal fun kaiGetMessageList(jsonFile: File) : JSONArray{
    val jsonContent = FileUtils.readFileToString(jsonFile, "utf-8")
    val jsonMain = JSONObject(jsonContent)
    val jsonMessages = jsonMain.getJSONObject("messages")

    return jsonMessages.names()
}

internal fun kaiGetMessageInformation(input : String, jsonFile: File, returnType : BrainReturnMessageInfo) : String{
    val jsonContent = FileUtils.readFileToString(jsonFile, "utf-8")
    val jsonMain = JSONObject(jsonContent)
    val jsonMessages = jsonMain.getJSONObject("messages")

    when(returnType){
        BrainReturnMessageInfo.MessageInputType -> {
            try{
                return jsonMessages.getJSONArray(input).getJSONObject(0).get("message_contain_type").toString()
            } catch(e : Exception){
                return "null"
            }
        }

        BrainReturnMessageInfo.MessageResultType ->{
            try{
                return jsonMessages.getJSONArray(input).getJSONObject(0).get("message_result_type").toString()
            } catch(e : Exception){
                return "null"
            }
        }

        BrainReturnMessageInfo.MessageInput ->{
            try{
                return jsonMessages.getJSONArray(input).getJSONObject(0).get("message_contain_string").toString()
            } catch(e : Exception){
                return "null"
            }
        }

        BrainReturnMessageInfo.MessageResult ->{
            try{
                return jsonMessages.getJSONArray(input).getJSONObject(0).get("message_result_string").toString()
            } catch(e : Exception){
                println(e.printStackTrace())
                return "null"
            }
        }
    }
}

internal fun kaiGetAllStatements() : ArrayList<String>{
    val fileList = getSpecificFiles(kaiInformation.conversationDirectory, ".json")
    val list = arrayListOf<String>()

    for(file in fileList){

        for(item in kaiGetMessageList(file)){
            if(kaiGetMessageInformation(item.toString(), file, BrainReturnMessageInfo.MessageResultType) == "Statement"){
                list.add(kaiGetMessageInformation(item.toString(), file, BrainReturnMessageInfo.MessageResult))
            }
        }
    }

    return stringToList(list.toString().replace("\n", ""), ",", -1)
}