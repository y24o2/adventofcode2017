import java.io.File

typealias Layer = Pair<Int, Int>

fun main(args: Array<String>) {
    val list = File("""C:\Users\Daniel\Downloads\Neuer Ordner\adventofcode\input.txt""").readLines().filter { !it.isEmpty() }
            .map { Pair(it.split(':')[0].trim().toInt(), it.split(':')[1].trim().toInt()) }

    println("Part1: ${part1(list)}")
    println("Part2 ${part2(list)}")
}

fun part1(list:List<Layer>):Int{
    var res = 0
    list.map{ if(it.first % (2 * (it.second - 1)) == 0) res +=it.first * it.second }
    return res
}

fun part2(list:List<Layer>):Int{
    var delay = 0
    while (list.filter { (it.first + delay) % (2 * (it.second - 1)) == 0 }.count() != 0) delay++
    return delay
}
