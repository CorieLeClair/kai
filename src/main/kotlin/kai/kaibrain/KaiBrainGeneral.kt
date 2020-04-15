package kai.kaibrain

import kai.configuration.kaiInformation
import kai.utils.GeneralUtils
import org.apache.commons.io.FileUtils
import org.json.JSONObject
import org.json.JSONArray
import java.io.File

internal class KaiBrainGeneral {

    internal class BrainMessageInformation() {
        fun getMessageInformation(input: String, jsonFile: File, returnType: BrainClasses.BrainReturnMessageInfo) = KaiBrainGeneral().kaiGetMessageInformation(input, jsonFile, returnType)
        fun getListOfMessages(jsonFile: File) = KaiBrainGeneral().kaiGetMessageList(jsonFile)
        fun getAllStatements() = KaiBrainGeneral().kaiGetAllStatements()
        fun getRandomStatement(): String {
            return KaiBrainGeneral().kaiGetAllStatements().random().toString().replace("[", "").replace("]", "")
        }
    }

    internal fun kaiGetMessageList(jsonFile: File): JSONArray {
        val jsonContent = FileUtils.readFileToString(jsonFile, "utf-8")
        val jsonMain = JSONObject(jsonContent)
        val jsonMessages = jsonMain.getJSONObject("messages")

        return jsonMessages.names()
    }

    internal fun kaiGetMessageInformation(input: String, jsonFile: File, returnType: BrainClasses.BrainReturnMessageInfo): String {
        val jsonContent = FileUtils.readFileToString(jsonFile, "utf-8")
        val jsonMain = JSONObject(jsonContent)
        val jsonMessages = jsonMain.getJSONObject("messages")

        when (returnType) {
            BrainClasses.BrainReturnMessageInfo.MessageInputType -> {
                return try {
                    jsonMessages.getJSONArray(input).getJSONObject(0).get("message_contain_type").toString()
                } catch (e: Exception) {
                    "null"
                }
            }

            BrainClasses.BrainReturnMessageInfo.MessageResultType -> {
                return try {
                    jsonMessages.getJSONArray(input).getJSONObject(0).get("message_result_type").toString()
                } catch (e: Exception) {
                    "null"
                }
            }

            BrainClasses.BrainReturnMessageInfo.MessageInput -> {
                return try {
                    jsonMessages.getJSONArray(input).getJSONObject(0).get("message_contain_string").toString()
                } catch (e: Exception) {
                    "null"
                }
            }

            BrainClasses.BrainReturnMessageInfo.MessageResult -> {
                return try {
                    jsonMessages.getJSONArray(input).getJSONObject(0).get("message_result_string").toString()
                } catch (e: Exception) {
                    println(e.printStackTrace())
                    "null"
                }
            }

            BrainClasses.BrainReturnMessageInfo.All ->{
                return try {
                    jsonMessages.getJSONArray(input).getJSONObject(0).get("message_result_string").toString()
                } catch (e: Exception) {
                    println(e.printStackTrace())
                    "null"
                }
            }
        }
    }

    internal fun kaiGetAllStatements(): ArrayList<String> {
        val fileList = GeneralUtils().getSpecificFiles(kaiInformation.conversationDirectory, ".json")
        val list = arrayListOf<String>()

        for (file in fileList) {

            for (item in kaiGetMessageList(file)) {
                if (kaiGetMessageInformation(item.toString(), file, BrainClasses.BrainReturnMessageInfo.MessageResultType) == "Statement") {
                    list.add(kaiGetMessageInformation(item.toString(), file, BrainClasses.BrainReturnMessageInfo.MessageResult))
                }
            }
        }

        return GeneralUtils().stringToList(list.toString().replace("\n", ""), ",", -1)
    }

    internal fun kaiGetAllMessages(): ArrayList<String> {
        val fileList = GeneralUtils().getSpecificFiles(kaiInformation.conversationDirectory, ".json")
        val list = arrayListOf<String>()

        for (file in fileList) {

            for (item in kaiGetMessageList(file)) {
                list.add(kaiGetMessageInformation(item.toString(), file, BrainClasses.BrainReturnMessageInfo.MessageResult))
            }
        }

        return GeneralUtils().stringToList(list.toString().replace("\n", ""), ",", -1)
    }
}
