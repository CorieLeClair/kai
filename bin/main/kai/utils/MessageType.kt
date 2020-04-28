package kai.utils

import kai.kaibrain.BrainClasses
import kai.kaibrain.KaiBrainGeneral
import java.io.File

internal class MessageType{

    fun isStatement(input: String, jsonFile: File) : Boolean{
        return KaiBrainGeneral.BrainMessageInformation().getMessageInformation(input, jsonFile, BrainClasses.BrainReturnMessageInfo.MessageInputType).toLowerCase().contains("statement")
    }

    fun returnQuestionType(message : String) : BrainClasses.MessageType.Question{
        println(message)
        val listQuestionKeySymbols001 = arrayListOf<String>("how do you feel", "do you like", "do you enjoy", "are you enjoy", "how is")
        val listQuestionKeySymbols002 = arrayListOf<String>("what is", "what are")
        val listQuestionKeySymbols003 = arrayListOf<String>("who is", "who are")
        val listQuestionKeySymbols004 = arrayListOf<String>("where is", "located where")
        val listQuestionKeySymbols005 = arrayListOf<String>("how do you do", "how is ${message.toLowerCase()} done", "how can you do")

        for(item in listQuestionKeySymbols001){
            if(message.toLowerCase().contains(item)){
                return BrainClasses.MessageType.Question.Ask001
            }
        }

        for(item in listQuestionKeySymbols002){
            if(message.toLowerCase().contains(item)){
                return BrainClasses.MessageType.Question.Ask002
            }
        }

        for(item in listQuestionKeySymbols003){
            if(message.toLowerCase().contains(item)){
                return BrainClasses.MessageType.Question.Ask003
            }
        }

        for(item in listQuestionKeySymbols004){
            if(message.toLowerCase().contains(item)){
                return BrainClasses.MessageType.Question.Ask004
            }
        }

        for(item in listQuestionKeySymbols005){
            if(message.toLowerCase().contains(item)){
                return BrainClasses.MessageType.Question.Ask005
            }
        }

        return BrainClasses.MessageType.Question.Unknown
    }
}