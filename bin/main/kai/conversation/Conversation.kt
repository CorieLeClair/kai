package kai.conversation

// imports
import kai.configuration.KaiInformation
import kai.utils.getSpecificFiles
import kai.utils.stringToList
import kai.utils.getStringOfLine
import kai.utils.getLineInFile
import kai.configuration.kaiInformation
import kai.kaibrain.BrainMessageInformation
import kai.kaibrain.enums.BrainReturnMessageInfo
import java.io.File

// loop counters
internal var counterFile = 0
internal var counterFileLine = 0
internal var counterInputIndex = 0
internal var counterRun = 0


internal fun conversation(){

    val input = kaiInformation.input
    val files = getSpecificFiles(kaiInformation.conversationDirectory, ".json")

    // check if the file exsits at the index
    fun collectFile() : Boolean {
        if(files.count() > counterFile){
            return true
        } else{
            return false
        }
    }

    // get line in file -- get message information
    fun collectFileLine(counter: Int) : ArrayList<String>{
      try{
        return stringToList(getStringOfLine(files[counterFile], counter), ":", -1)
      } catch(exception : Exception){
        return arrayListOf<String>("null")
      }
    }

    fun collectLinesInFile() : Int{
        return getLineInFile(files[counterFile])
    }

    // setting up counters for new indexing
    fun resetCounter(){
        counterInputIndex = 0
        counterRun = 0
        counterFileLine = 0
        counterFile = 0
    }
    

    fun collectResultChatBroad() : String{
        var response = "I'm not sure what you mean. I am sorry."

        while(collectFile() && BrainMessageInformation().getListOfMessages(files[counterFile]).count() > counterInputIndex){
            if(input.contains(BrainMessageInformation().getListOfMessages(files[counterFile]).get(counterInputIndex).toString())){
                response = BrainMessageInformation().getMessageInformation(BrainMessageInformation().getListOfMessages(files[counterFile]).get(counterInputIndex).toString(), files[counterFile], BrainReturnMessageInfo.MessageResult)
                break
            } else{
                if(counterInputIndex >= BrainMessageInformation().getListOfMessages(files[counterFile]).count() - 1){
                    counterInputIndex = 0
                    counterFile++
                } else{
                    counterInputIndex++
                }
            }
        }
        return response
    }

    resetCounter()
    kaiInformation.kaiResponse = collectResultChatBroad()
}
