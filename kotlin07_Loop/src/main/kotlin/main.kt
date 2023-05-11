fun main() {

    // 1 ~ 10 까지 총 10번 반복을 하고
    // 반복할 때 마다 반복 회차에 해당하는 값을 in 좌측 변수에 담아둔다.
    // 이때, 변수는 타입을 정하지 않아도 된다.

    for (item1 in 1..10) {
        print("$item1 ")
    }
    println()
    // 2씩 증가 시키는 경우
    for (item2 in 1..10 step 2) {
        print("$item2 ")
    }
    println()
    for (item3 in 10 downTo 1) {
        print("$item3 ")
    }
    println()
    // while
    // 자바랑 동일하다
    var a5 = 0

    while (a5 < 10) {
        print("$a5 ")
        a5++
    }
    println()

    // do while
    // 자바랑 동일하다
    var a6 = 0

    do{
        print("$a6 ")
        a6++
    } while(a6 < 10)

}