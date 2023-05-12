import java.util.Scanner
import kotlin.math.abs

/*
자판기를 구현한다.

음료수를 고르세요
1. 콜라(1000원), 2. 사이다(1000원), 3.커피(1500원), 4.복숭아아이스트(2000원) : 1

만약 그외의 번호를 입력하면.... 다시 입력해주세요 라고 출력하고 위의 메뉴가 다시 나오게 한다.

현재 0원/부족 1000원
동전을 넣어주세요 : 500
현재 500원/부족 500원
동전을 넣어주세요 : 1000
현재 1500원/부족 0원

콜라가 나왔습니다.
잔액은 500원 입니다.

콜라는 1000원이고
양은 300ml 입니다
회사는 코카콜라 입니다.

사이다는 1000원이고
양은 300ml 입니다
회사는 칠성입니다.

커피는 1500원이고
양은 500ml 입니다
회사는 별다방입니다.

복숭아아스티는 2000원이고
양은 700ml 입니다
회사는 빽다방입니다
 */
val sc = Scanner(System.`in`)
fun main() {
    vendingMachine()
}

fun vendingMachine() {
    while (true) {
        println("음료수를 고르세요")
        print("1. 콜라(1000원), 2. 사이다(1000원), 3.커피(1500원), 4.복숭아아이스트(2000원) : ")
        val num = sc.nextInt()

        when (num) {
            1 -> {
                val cola = Cola("콜라",1000, 300, "코카콜라")
                Calculate(cola.price, cola.item)
                cola.print()
                println()
            }

            2 -> {
                val cider = Cider("사이다",1000, 300, "칠성")
                Calculate(cider.price, cider.item)
                cider.print()
                println()
            }

            3 -> {
                val coffle = Coffle("커피",1500, 500, "별다방")
                Calculate(coffle.price, coffle.item)
                coffle.print()
                println()
            }

            4 -> {
                val iceTea = IceTea("아이스티",2000, 700, "빽다방")
                Calculate(iceTea.price, iceTea.item)
                iceTea.print()
                println()
            }

            else -> println("다시 입력해주세요")
        }
    }
}

fun Calculate(price: Int, item: String) {

    var money = 0
    //1000 <0
    while (price > money) {
        print("동전을 넣어주세요 : ")
        val num = sc.nextInt() //300원 넣는다 가정
        money += num

        // 거스름돈
        val change = price - money //(콜라) 1000 - 300 = 700

        //700 > 0
        if (change > 0) {
            println("현재 $money 원/부족 $change 원")
            println()
        } else {
            println("$item 가 나왔습니다.")
            println("현재 $money 원/거스름돈 ${abs(change)} 원")
            println()
            break
        }
    }
}

class Cola(var item: String, var price: Int, var ml: Int, var company: String) {

    fun print() {
        println("$company 는 $price")
        println("양은 $ml ml 입니다")
        println("회사는 $company 입니다.")
    }
}

class Cider(var item: String, var price: Int, var ml: Int, var company: String) {

    fun print() {
        println("$company 는 $price")
        println("양은 $ml ml 입니다")
        println("회사는 $company 입니다.")
    }
}

class Coffle(var item: String, var price: Int, var ml: Int, var company: String) {

    fun print() {
        println("$company 는 $price")
        println("양은 $ml ml 입니다")
        println("회사는 $company 입니다.")
    }
}

class IceTea(var item: String, var price: Int, var ml: Int, var company: String) {

    fun print() {
        println("$company 는 $price")
        println("양은 $ml ml 입니다")
        println("회사는 $company 입니다.")
    }
}

