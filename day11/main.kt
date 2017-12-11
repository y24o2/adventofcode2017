import java.io.File
import java.lang.Math.abs

fun main(args: Array<String>) {

    // https://www.redblobgames.com/grids/hexagons/

    val data = File("""C:\Users\Daniel\Downloads\Neuer Ordner\adventofcode\input.txt""").readText().trim().split(',')
    var x = 0; var y = 0; var z = 0;
    val steps = mutableListOf<Hex>()
    for (s in data) {
        when (s) {
            "nw" -> y++ + z + x--
            "n" -> y++ + z-- + x
            "ne" -> y + z-- + x++
            "se" -> y-- + z + x++
            "s" -> y-- + z++ + x
            "sw" -> y + z++ + x--
        }
        steps.add(Hex(x,y,z))
    }
    fun distance(hex:Hex):Int = (abs(hex.x) + abs(hex.y) + abs(hex.z))/2
    val part1 = distance(steps[steps.lastIndex])
    val part2 = steps.maxBy { distance(it) }

    println("part1: $part1\npart2: ${distance(part2!!)}")
}
data class Hex(val x:Int, val y:Int, val z:Int)
