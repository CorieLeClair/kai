package kai.kaibrain.autothink

import kai.kaibrain.BrainMessageInformation
import kai.utils.stringToList
import kai.utils.KaiSpeechTagging

internal class AutoThink:Runnable{
    public override fun run(){
        while(true){
            createConnections()
        }
    }
}

internal fun createConnections(){
    var noun = getNoun()
    var list = getConnectionDesc(getConnections(noun), noun)

    if(list.count() < 1){
        while(list.count() < 1){ 
            noun = getNoun()
            list = getConnectionDesc(getConnections(noun), noun)
        }
    } else{
        println(list)
    }
}

internal fun getConnectionDesc(statement: String, noun : String) : ArrayList<String>{
    val statementList = stringToList(statement, " ", -1)
    var list = ArrayList<String>()
    var nounArea = ArrayList<String>()

    if(statementList.indexOf(noun) == 1 && statementList.count() > 3){
        nounArea.add(statementList[statementList.indexOf(noun) + 2])
    } else if(statementList.indexOf(noun) > 1){
        try{
            println(statementList[statementList.indexOf(noun) + 2])
            nounArea.add(statementList[statementList.indexOf(noun) + 2])
            nounArea.add("a")
        } catch(e : IndexOutOfBoundsException){
            println("failed")
        }
    }

    println(nounArea)
    println(statementList)

    
    for(item in nounArea){
        if(KaiSpeechTagging().isAdj(item) == true){
            list.add(item)
        }
    }
    

    return list
    
}



internal fun getConnections(noun : String) : String{
    val statements = BrainMessageInformation().getAllStatements()

    for(item in statements){
        if(item.contains(noun)){
            return item
        } 
    }

    return "null"
}

internal fun getNoun() : String{
    var nouns = arrayListOf<String>()
    var counter = 0

    while(nouns.count() < 1){
        var list = stringToList(BrainMessageInformation().getRandomStatement(), " ", 0)
        counter++

        for(item in list){
            if(KaiSpeechTagging().isNoun(item)){
                    nouns.add(item)
            }
        }

        if(counter > BrainMessageInformation().getAllStatements().count()){
            break
        }
    }

    if(nouns.count() == 0){
        return ""
    } else{
        return nouns.random()
    }
}

internal fun autoThink() {
    val autoThinkThread = Thread(AutoThink())
    autoThinkThread.start()
  }   
