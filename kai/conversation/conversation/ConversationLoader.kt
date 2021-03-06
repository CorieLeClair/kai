package kai.conversation.conversation

import kai.configuration.Kai
import kai.kaibrain.trainer.AutoLearning
import kai.kaibrain.trainer.ConversationAnalyze
import kai.kaibrain.trainer.Mock
import kai.task.math.MathDetection
import kai.task.wikiresponse.WikiResponse

private val analyze = ConversationAnalyze.ConversationAnaBuilder("builder")

internal class ConversationLoader() {
     fun conversationSystem(builder: Kai.KaiBuilder){
         val functions = createFunctionList(builder.fallbackOrder, builder)
         analyze.messageDict[builder.response] = builder.input

         if (builder.conversationCounter % 5 == 0) {
             Mock.Trainer().startMockTrainingManualMessages(builder, analyze)
             Mock.Trainer().updateResponsesMock(builder, analyze)
         }

         if(builder.conversationHolder == 1) {
             if(builder.input != "no") {
                 AutoLearning().createMessage(builder)
             } else {
                // builder.response = "Okay."
                 builder.conversationMethodSuccessFull = true
                 builder.conversationHolder = 0
             }
         }

         for (function in functions) {
             if (builder.conversationMethodSuccessFull) {
                 break
             } else {
                 function()
             }
         }

         builder.conversationCounter++
         builder.conversationMethodSuccessFull = false
    }

    private fun createFunctionList(list : ArrayList<Kai.KaiFallbackFeatures>, builder: Kai.KaiBuilder): ArrayList<() -> Unit> {
        val listFunctions = ArrayList<() -> Unit>()

        for(item in list){
            when(item){
                Kai.KaiFallbackFeatures.BaseConversation -> listFunctions.add {Conversation().conversation(builder)}
                Kai.KaiFallbackFeatures.AutogeneratedConversation -> listFunctions.add {AutoLearnedConversation().conversation(builder)}
                Kai.KaiFallbackFeatures.Math -> listFunctions.add { MathDetection(builder).collectOperators()}
                Kai.KaiFallbackFeatures.WikiSearch -> listFunctions.add { WikiResponse(builder).getWikiResponse()}
                Kai.KaiFallbackFeatures.ConversationRevive -> listFunctions.add {Mock.ReviveConversation().revievWithStarter(builder)}
                //Kai.KaiFallbackFeatures.Spotify -> TODO()
                Kai.KaiFallbackFeatures.AskAbout -> listFunctions.add { AutoLearning().askAbout(builder) }
            }
        }

        return listFunctions
    }
}