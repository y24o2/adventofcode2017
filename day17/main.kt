
fun main(args: Array<String>) {
    var jump = 304
    println("Part1: ${part1(jump)}")
    println("Part2: ${part2(jump)}")
}

fun part1(jump:Int):Int{
    var l = mutableListOf(0)
    var pos = 0
    for (i in 1..2017){
        pos = 1 + (pos + jump) % i
        l.add(pos, i)
    }
    return l[l.indexOf(2017)+1]
}

fun part2(jump:Int):Int{
    var pos = 0
    var res = 0
    for ( i in 1..50000000){
        pos = 1 + (pos + jump) % i
        if (pos == 1) res = i
    }
    return res
}
