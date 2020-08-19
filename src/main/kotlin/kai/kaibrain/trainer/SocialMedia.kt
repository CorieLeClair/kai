package kai.kaibrain.trainer

import org.apache.commons.io.FileUtils
import org.json.JSONException
import org.json.JSONObject
import java.io.File

internal class SocialMedia {
    class FacebookMessages{
        fun createDict(jsonFile : File) : HashMap<String, String>{
            val jsonContent = FileUtils.readFileToString(jsonFile, "utf-8")
            val messages = JSONObject(jsonContent).getJSONArray("messages")
            val dict = HashMap<String, String>()

            var counter = 1

            while(counter < messages.count() - 1){
                if(messages.getJSONObject(counter).get("type") == "Generic" && messages.getJSONObject(counter + 1).get("type") == "Generic") {
                    try {
                        dict[messages.getJSONObject(counter).get("content").toString()] = messages.getJSONObject(counter + 1).get("content").toString()
                        counter += 2
                    } catch (ex : JSONException) {
                        counter += 2
                    }
                } else{
                    counter++
                }
            }

            return dict

        }
    }
}