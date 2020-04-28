package kai.kaibrain

import kai.utils.GeneralUtils
import org.apache.commons.io.FileUtils
import org.json.JSONObject
import org.json.JSONArray
import java.io.File

 class KaiBrainGeneral {

    class BrainMessageInformation() {
        fun getMessageInformation(input: String, jsonFile: File, returnType: BrainClasses.BrainReturnMessageInfo) = KaiBrainGeneral().kaiGetMessageInformation(input, jsonFile, returnType)
        fun getListOfMessages(jsonFile: File) = KaiBrainGeneral().kaiGetMessageList(jsonFile)
        fun getAllStatements() = KaiBrainGeneral().kaiGetAllStatements()
        fun getAllMessages() = KaiBrainGeneral().kaiGetAllMessages()
        fun getAllResultMessagesSpec(files : ArrayList<File>) = KaiBrainGeneral().kaiGetAllResultMessagesSpecFiles(files)
        fun getAllContainMessagesSpec(files : ArrayList<File>) = KaiBrainGeneral().kaiGetAllContainMessagesSpecFiles(files)
        fun getMessgesDict(jsonFile : File) = KaiBrainGeneral().kaiGetMessagesDict(jsonFile)
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
                    "null"
                }
            }

            BrainClasses.BrainReturnMessageInfo.All ->{
                return try {
                    jsonMessages.getJSONArray(input).getJSONObject(0).get("message_result_string").toString()
                } catch (e: Exception) {
                    "null"
                }
            }

            BrainClasses.BrainReturnMessageInfo.Desc ->{
                return try {
                    jsonMessages.getJSONArray(input).getJSONObject(0).get("desc").toString()
                } catch (e: Exception) {
                    "null"
                }
            }
        }
    }

    internal fun kaiGetMessagesDict(jsonFile : File) : HashMap<String, String> {
        val dict = HashMap<String, String>()
        val messageList =  kaiGetMessageList(jsonFile)

        for(item in messageList){
            dict[item.toString()] = kaiGetMessageInformation(item.toString(), jsonFile, BrainClasses.BrainReturnMessageInfo.MessageResult)
        }

        return dict
    }

    internal fun kaiGetAllStatements(): ArrayList<String> {
        val fileList = GeneralUtils().getManualFiles(".json")
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
        val fileList = GeneralUtils().getManualFiles(".json")
        val list = arrayListOf<String>()

        for (file in fileList) {

            for (item in kaiGetMessageList(file)) {
                list.add(kaiGetMessageInformation(item.toString(), file, BrainClasses.BrainReturnMessageInfo.MessageResult))
                list.add(kaiGetMessageInformation(item.toString(), file, BrainClasses.BrainReturnMessageInfo.MessageInput))
            }
        }

        return GeneralUtils().stringToList(list.toString().replace("\n", ""), ",", -1)
    }

    internal fun kaiGetAllResultMessagesSpecFiles(files : ArrayList<File>) : ArrayList<String>{
        val list = arrayListOf<String>()

        for (file in files) {

            for (item in kaiGetMessageList(file)) {
                list.add(kaiGetMessageInformation(item.toString(), file, BrainClasses.BrainReturnMessageInfo.MessageResult))
            }
        }

        return GeneralUtils().stringToList(list.toString().replace("\n", ""), ",", -1)
    }

    internal fun kaiGetAllContainMessagesSpecFiles(files : ArrayList<File>) : ArrayList<String>{
        val list = arrayListOf<String>()

        for (file in files) {

            for (item in kaiGetMessageList(file)) {
                list.add(kaiGetMessageInformation(item.toString(), file, BrainClasses.BrainReturnMessageInfo.MessageInput))
            }
        }

        return GeneralUtils().stringToList(list.toString().replace("\n", ""), ",", -1)
    }
}
