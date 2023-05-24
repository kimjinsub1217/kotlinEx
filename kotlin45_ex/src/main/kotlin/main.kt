fun main() {
    var money: Long = 100
//    println(Long.MAX_VALUE)

    println("1일차 : $money 원")
    for (i in 2..30) {
        var balance = ""
        val length = money.toString().length
        val halfIndex = length / 2
        var firstHalf = money.toString().substring(0, halfIndex)
        var secondHalf = money.toString().substring(halfIndex)
        firstHalf = (firstHalf.toLong()*4).toString()
        secondHalf =(secondHalf.toLong()*4).toString()
        money *= 4
        balance = firstHalf +secondHalf

        println("${i}일차 : ${balance}0 원")
    }
}
