import java.io.File

//part 2

val f = File("""C:\Users\Daniel\Downloads\Neuer Ordner\adventofcode\input.txt""").readLines().filter { !it.isEmpty() }

fun main(args: Array<String>) {
    val p = createProcess("eugwuhl")
    var unbalanceChild = p.unbalancedChild
    while (unbalanceChild != null){
        if (unbalanceChild.first.unbalancedChild == null) println("${unbalanceChild.first.name} (${unbalanceChild.first.weight}) ${unbalanceChild.second} = ${unbalanceChild.first.weight + unbalanceChild.second}")
        unbalanceChild = unbalanceChild.first.unbalancedChild
    }
}

data class Process(val name:String, var weight:Int, val childrens:MutableList<Process>, var sum:Int, var unbalancedChild:Pair<Process, Int>? = null)
fun Process.getWeight(){ for (row in f) if (row.split(" ")[0] == this.name) this.weight = row.split("(")[1].split(")")[0].toInt() }
fun Process.getChildrens(){ for (row in f) if (row.split(" ")[0] == this.name && row.split("->").count() == 2) for (child in row.split("->")[1].split(",")) this.childrens.add(createProcess(child.trim())) }
fun Process.getSum(){
    this.sum = this.weight
    for (c in childrens) this.sum += c.sum
}
fun Process.unbalancedChild(){ for (c in this.childrens) if (this.childrens.filter { it.sum == c.sum }.count() == 1) this.unbalancedChild = Pair(c, this.childrens.filter({ it.sum != c.sum })[0].sum - c.sum) }

fun createProcess(name:String):Process{
    val res = Process(name, 0, mutableListOf(), 0)
    res.getWeight()
    res.getChildrens()
    res.getSum()
    res.unbalancedChild()
    return res
}
