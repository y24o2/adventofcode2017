import java.io.File

fun part1():Int{
    val lengths = File("""C:\Users\Daniel\Downloads\Neuer Ordner\adventofcode\input.txt""").readLines()[0].split(',').map { it.toInt() }
    val list = (0..255).toMutableList()
    var position = 0
    for ((skipSize, length) in lengths.withIndex()){
            if (length > list.count()) throw IllegalArgumentException("Invalid Length: $length")
            val tmp = mutableListOf<Int>()
            for (i in position..position + length - 1) tmp.add(list[i % list.count()])
            var cnt = 0
            tmp.reverse()
            for (i in position..position + length - 1) list[i % list.count()] = tmp[cnt++]
        position += length + skipSize
    }
    return list[0] * list[1]
}

fun part2():String {

    val lengths = File("""C:\Users\Daniel\Downloads\Neuer Ordner\adventofcode\input.txt""").readLines()[0].trim().map { it.toInt() } + listOf(17, 31, 73, 47, 23)
    val list = (0..255).toMutableList()
    var position = 0
    var skipSize = 0
    for(i__ in 1..64) {
        for (length in lengths){
            if (length > list.count()) throw IllegalArgumentException("Invalid Length: $length")
            val tmp = mutableListOf<Int>()
            for (i in position..position + length - 1)tmp.add(list[i % list.count()])
            var cnt = 0
            tmp.reverse()
            for (i in position..position + length - 1) list[i % list.count()] = tmp[cnt++]
            position += length + skipSize++
        }
    }
    var res = ""
    var index = 0
    while(index < list.count()){
        var h = 0
        for (i in 0..15){
            h = h xor list[index++]
        }
        res += if(Integer.toHexString(h).length == 2) Integer.toHexString(h) else "0" + Integer.toHexString(h)
    }
    return res
}

fun main(args: Array<String>) {
    println("Part1: ${part1()}")
    println("Part2: ${part2()}")
}
