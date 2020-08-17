package kai.kaibrain.trainer

import kai.configuration.Kai
import kai.kaibrain.messageinformation.KaiBrainMessages

class ConversationAnalyze {
    data class ConversationAnaBuilder(val name : String) {
        var messageDict : HashMap<String, String> = hashMapOf()
    }
}