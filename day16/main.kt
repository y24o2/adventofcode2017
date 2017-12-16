import java.io.File

fun main(args: Array<String>) {
    var data = (0..15).map { (it+'a'.toInt()).toChar() }.toMutableList()
    var cnt = 0
    while (cnt++ < 1)
        File("""C:\Users\Daniel\Downloads\Neuer Ordner\adventofcode\input.txt""")
            .readText()
            .trim()
            .split(',')
            .map {
                when (it[0]) {
                    's' -> {
                        for (c in 1..it.subSequence(1, it.length).toString().toInt()) {
                            var tmp = data.toList()
                            data[0] = tmp.last()
                            for (i in 1..data.count() - 1) data[i] = tmp[i - 1]
                        }
                    }
                    'x' -> {
                        val swap = it.subSequence(1, it.length).toString().split('/').map { it.toString().toInt() }
                        data[swap[0]] = data[swap[1]].also { data[swap[1]] = data[swap[0]] }
                    }
                    'p' -> {
                        val swap = it.subSequence(1, it.length).toString().split('/').map { data.indexOf(it[0]) }
                        data[swap[0]] = data[swap[1]].also { data[swap[1]] = data[swap[0]] }
                    }
                }
            }
    var res = ""
    data.map { res += it }
    println(res)
}
