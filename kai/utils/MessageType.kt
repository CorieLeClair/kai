package kai.utils

import kai.kaibrain.messageinformation.BrainClasses

class MessageType{

    fun isSpecificQuestion(question : String) : Boolean {
        return when {
            returnQuestionType(question) == BrainClasses.MessageType.Question.Ask001Specific -> { true }
            returnQuestionType(question) == BrainClasses.MessageType.Question.Ask002Specific -> { true }
            returnQuestionType(question) == BrainClasses.MessageType.Question.Ask003Specific -> { true }
            returnQuestionType(question) == BrainClasses.MessageType.Question.Ask004Specific -> { true }
            else -> return false
        }
    }

    fun returnQuestionType(message : String) : BrainClasses.MessageType.Question {
        val listQuestionKeySymbols001 = arrayListOf<String>("how do you feel", "do you like", "do you enjoy", "are you enjoy", "how is", "hows", "howd you", "how did")
        val listQuestionKeySymbols002 = arrayListOf<String>("what is", "what are")
        val listQuestionKeySymbols003 = arrayListOf<String>("who is", "who are")
        val listQuestionKeySymbols004 = arrayListOf<String>("where is", "located where")
        val listQuestionKeySymbols005 = arrayListOf<String>("how do you do", "how is ${message.toLowerCase()} done", "how can you do")

        val question001SpecificList = arrayListOf("do you like", "do you enjoy")
        val question002SpecificList = arrayListOf("what is")
        val question003SpecificList = arrayListOf("what is")
        val question004SpecificList = arrayListOf("where is", "located where")

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

        for(item in question001SpecificList){
            if(message.toLowerCase().contains(item)){
                return BrainClasses.MessageType.Question.Ask001Specific
            }
        }

        for(item in question002SpecificList){
            if(message.toLowerCase().contains(item)){
                return BrainClasses.MessageType.Question.Ask002Specific
            }
        }

        for(item in question003SpecificList){
            if(message.toLowerCase().contains(item)){
                return BrainClasses.MessageType.Question.Ask003Specific
            }
        }

        for(item in question004SpecificList){
            if(message.toLowerCase().contains(item)){
                return BrainClasses.MessageType.Question.Ask004Specific
            }
        }

        return BrainClasses.MessageType.Question.Unknown
    }
}