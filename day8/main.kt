import java.io.File


fun main(args: Array<String>) {
    val registers = mutableMapOf<String, Int>()
    var max = 0
    fun readRegister(register:String):Int = if (register in registers.keys) registers[register]!! else 0
    fun setRegister(register:String, value:Int) {
        if (value>max) max = value
        if (register in registers.keys) registers[register] = value
        else registers.put(register, value)
    }
    fun row(data:String):Row{
        val r = data.split(" if ")[0].split(" ")[0]
        val i = data.split(" if ")[0].split(" ")[1]
        val v = data.split(" if ")[0].split(" ")[2].toInt()

        val lV = data.split(" if ")[1].split(" ")[0]
        val rV = data.split(" if ")[1].split(" ")[2]
        val c = data.split(" if ")[1].split(" ")[1]
        return Row(r, i, v, Condition(lV, rV, c))
    }
    fun value2int(value:String):Int = if (value[0] in 'a'..'z') readRegister(value) else value.toInt()
    fun compute(row:Row){
        var b:Boolean = when(row.condition.condition) {
            ">" -> value2int(row.condition.leftValue) > value2int(row.condition.rightValue)
            ">=" -> value2int(row.condition.leftValue) >= value2int(row.condition.rightValue)
            "<" -> value2int(row.condition.leftValue) < value2int(row.condition.rightValue)
            "<=" -> value2int(row.condition.leftValue) <= value2int(row.condition.rightValue)
            "==" -> value2int(row.condition.leftValue) == value2int(row.condition.rightValue)
            "!=" -> value2int(row.condition.leftValue) != value2int(row.condition.rightValue)
            else -> false
        }
        if (b){
            setRegister(row.register, readRegister(row.register) + if(row.instruction == "inc") value2int(row.value.toString()) else -value2int(row.value.toString()))
        }
    }
    val data = File("""C:\Users\Daniel\Downloads\Neuer Ordner\adventofcode\input.txt""").readLines().filter { !it.isEmpty() }
    for (row in data){
        compute(row(row))
    }
    println("Part1: ${registers.maxBy { it.value }}")
    println("Part2: $max")
}

data class Row(val register:String, val instruction:String, val value:Int, val condition:Condition)
data class Condition(val leftValue:String, val rightValue:String, val condition:String)
