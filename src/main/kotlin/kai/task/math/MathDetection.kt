package kai.task.math

import kai.configuration.Kai
import kai.utils.GeneralUtils
import java.util.function.BinaryOperator
import java.util.regex.Pattern
import kotlin.math.abs

class MathDetection(public val builder : Kai.KaiBuilder) {

    fun collectOperators(){
        val listOfMath = ArrayList<String>()
        val subList = arrayListOf<String>("minus", "-", "subtract")
        val addList = arrayListOf<String>("plus", "+", "add")
        val divList = arrayListOf<String>("divided", "/", "divide")
        val multiplyList = arrayListOf<String>("multiply", "x", "*", "times", "multiplied")
        val listInput = GeneralUtils().stringToList(builder.input, " ", -1)
        var counter = 0

        while(counter < listInput.count()){
            for(operator in subList) {
                if(operator == listInput[counter] && listInput[counter - 1].toInt() == listInput[counter - 1].toInt() && listInput[counter + 1].toInt() == listInput[counter + 1].toInt()){
                    if(listOfMath.count() == 0) {
                        listOfMath.add(listInput[counter - 1])
                        listOfMath.add("-")
                        listOfMath.add(listInput[counter + 1])

                    } else{
                        listOfMath.add("-")
                        listOfMath.add(listInput[counter + 1])
                    }
                }
            }

            for(operator in addList) {
                if(operator == listInput[counter] && listInput[counter - 1].toInt() == listInput[counter - 1].toInt() && listInput[counter + 1].toInt() == listInput[counter + 1].toInt()){
                    if(listOfMath.count() == 0) {
                        listOfMath.add(listInput[counter - 1])
                        listOfMath.add("+")
                        listOfMath.add(listInput[counter + 1])

                    } else{
                        listOfMath.add("+")
                        listOfMath.add(listInput[counter + 1])
                    }
                }
            }

            for(operator in divList) {
                if(operator == listInput[counter] && listInput[counter - 1].toInt() == listInput[counter - 1].toInt() && listInput[counter + 1].toInt() == listInput[counter + 1].toInt()){
                    if(listOfMath.count() == 0) {
                        listOfMath.add(listInput[counter - 1])
                        listOfMath.add("/")
                        listOfMath.add(listInput[counter + 1])

                    } else{
                        listOfMath.add("/")
                        listOfMath.add(listInput[counter + 1])
                    }
                }
            }

            for(operator in multiplyList) {
                if(operator == listInput[counter] && listInput[counter - 1].toInt() == listInput[counter - 1].toInt() && listInput[counter + 1].toInt() == listInput[counter + 1].toInt()){
                    if(listOfMath.count() == 0) {
                        listOfMath.add(listInput[counter - 1])
                        listOfMath.add("*")
                        listOfMath.add(listInput[counter + 1])

                    } else{
                        listOfMath.add("*")
                        listOfMath.add(listInput[counter + 1])
                    }
                }
            }

            counter++
        }

        println(listOfMath)

        if(listOfMath.count() >= 2){
            CompleteMath.BasicMath(listOfMath).doMath(builder)
        }
    }
}