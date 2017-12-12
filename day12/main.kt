import java.io.File

fun main(args: Array<String>) {

    val list = File("""C:\Users\Daniel\Downloads\Neuer Ordner\adventofcode\input.txt""").readLines().filter {!it.isEmpty()}.map {
        Program(it.split("<->")[0].trim().toInt(), it.split("<->")[1].split(",").map { it.trim().toInt() })
    }
    val result = mutableListOf<Int>()
    val groups = mutableListOf<List<Int>>()

    fun tree(startId:Int){
        for (c in list.filter { it.id == startId }[0].connections){
            if (!result.contains(c)){
                result.add(c)
                tree(c)
            }
        }
    }

    print("Part1: ")
    tree(0)
    println(result.count())

    print("Part2: ")
    for (program in list){
        if (groups.filter { it.contains(program.id) }.count() == 0){
            result.clear()
            tree(program.id)
            groups.add(result.toList())
        }
    }

    println(groups.count())
}

data class Program(val id:Int, val connections:List<Int>)
