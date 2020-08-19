package kai.task.math

import kai.configuration.Kai
import java.util.function.BinaryOperator
import kotlin.math.abs

internal class CompleteMath {
    class BasicMath(private val list : ArrayList<String>){

        private fun add(intOne : Int, intTwo : Int) : Int{
            return abs(intOne + intTwo)
        }

        private fun subtract(intOne: Int, intTwo: Int) : Int{
            return abs(intOne - intTwo)
        }

        private fun divide(intOne : Int, intTwo : Int) : Int{
            return abs(intOne / intTwo)
        }

        private fun multiply(intOne : Int, intTwo : Int) : Int{
            return abs(intOne * intTwo)
        }

        fun doMath(builder: Kai.KaiBuilder){
            var counter = 0
            var int = list[0].toInt()

            while(counter < list.count()){
                when(list[counter]){
                    "-" -> {int = subtract(int, list[counter + 1].toInt())}
                    "+" -> {int = add(int, list[counter + 1].toInt())}
                    "*" -> {int = multiply(int, list[counter + 1].toInt())}
                    "/" -> {int = divide(int, list[counter + 1].toInt())}
                }

                counter++
            }

            builder.response = int.toString()
            println(builder.response)
            int = 0
        }
    }
}