import java.io.File

fun main(args: Array<String>) {
    val data = File("""C:\Users\Daniel\Downloads\Neuer Ordner\adventofcode\input.txt""").readText()
    var level = 0
    var score = 0
    var cntGarbage = 0
    var ignore = false
    var garbage = false
    for (c in data){
        if (ignore){
            ignore = false
        }
        else{
            if(c == '!') {
                ignore = true
            }
            else{
                if (!garbage) {
                    when (c) {
                        '{' -> {
                            level++
                            score += level
                        }
                        '}' -> level--
                        '<' -> garbage = true
                    }
                } else {
                    if (c == '>') garbage = false
                    else cntGarbage++
                }
            }
        }
    }
    println("Part1: $score")
    println("Part3: $cntGarbage")
}
