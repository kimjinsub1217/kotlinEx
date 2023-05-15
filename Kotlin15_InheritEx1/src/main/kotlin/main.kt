fun main() {
    val jajangmyeonList = mutableListOf<Jajangmyeon>()
    val jjambbongList = mutableListOf<Jjambbong>()
    val friedRiceList = mutableListOf<FriedRice>()
    Restaurant(jajangmyeonList, jjambbongList, friedRiceList)
}

private fun Restaurant(
    jajangmyeonList: MutableList<Jajangmyeon>,
    jjambbongList: MutableList<Jjambbong>,
    friedRiceList: MutableList<FriedRice>
) {
    var selectNumber = 0
    var totalPrice = 0
    var jajangmyeon: Jajangmyeon?
    var jjambbong: Jjambbong?
    var friedRice: FriedRice?

    while (true) {
        println("메뉴를 선택해주세요 ")
        print("1. 짜장면, 2. 짬뽕, 3. 볶음밥, 4. 종료 : ")
        selectNumber = readln().toInt()

        when (selectNumber) {
            1 -> {
//                println("짜장")
                jajangmyeon = Jajangmyeon()
                jajangmyeonList.add(jajangmyeon)
                totalPrice += jajangmyeon.price
            }

            2 -> {
//                println("짬뽕")
                jjambbong = Jjambbong()
                jjambbongList.add(jjambbong)
                totalPrice += jjambbong.price
            }

            3 -> {
//                println("볶음")
                friedRice = FriedRice()
                friedRiceList.add(friedRice)
                totalPrice += friedRice.price

            }

            4 -> {
                //주문 총 금액 : 30000원
                //짜장면 : 0개
                //짬뽕 : 0개
                //볶음밥 : 0개
                //
                //음식 : 짜장면
                //가격 : 6000원
                //곱배기여부 : 곱배기 입니다 or 곱배기 아닙니다
                //
                //음식 : 짬뽕
                //가격 : 8000원
                //홍합여부 : 홍합이 있습니다 or 홍합이 없습니다
                //
                //음식 : 볶음밥
                //가격 : 10000원
                //국물종류 : 짬뽕국물 or 계란국물
                orderForm(totalPrice, jajangmyeonList, jjambbongList, friedRiceList)

                println("")
                foodInfo(jajangmyeonList, jjambbongList, friedRiceList)
                break
            }

            else -> println("1 ~ 4 번만 선택이가능해요")
        }


    }
}

private fun foodInfo(
    jajangmyeonList: MutableList<Jajangmyeon>,
    jjambbongList: MutableList<Jjambbong>,
    friedRiceList: MutableList<FriedRice>
) {
    println("=====음식정보======")
    if (jajangmyeonList.isNotEmpty()) {
        for (i in 0 until jajangmyeonList.size) {
            println("음식 : ${jajangmyeonList[i].name}")
            println("가격 : ${jajangmyeonList[i].price}")
            println("곱빼기 여부 : ${jajangmyeonList[i].check}")
            println("")
        }
    }

    if (jjambbongList.isNotEmpty()) {
        for (i in 0 until jjambbongList.size) {
            println("음식 : ${jjambbongList[i].name}")
            println("가격 : ${jjambbongList[i].price}")
            println("곱빼기 여부 : ${jjambbongList[i].check}")
            println("")
        }

    }

    if (friedRiceList.isNotEmpty()) {
        for (i in 0 until friedRiceList.size) {
            println("음식 : ${friedRiceList[i].name}")
            println("가격 : ${friedRiceList[i].price}")
            println("곱빼기 여부 : ${friedRiceList[i].check}")
            println("")
        }

    }
}

private fun orderForm(
    totalPrice: Int,
    jajangmyeonList: MutableList<Jajangmyeon>,
    jjambbongList: MutableList<Jjambbong>,
    friedRiceList: MutableList<FriedRice>
) {
    println("")
    println("=====주문서======")
    println("주문 총 금액 : $totalPrice")
    println("짜장면 : ${jajangmyeonList.size} 개")
    println("짬뽕 : ${jjambbongList.size}개")
    println("볶음밥 : ${friedRiceList.size}개")
}


class Jajangmyeon(val name: String = "짜장면", var price: Int = 6000, var check: String = "") {
    init {

        while (true) {
            print("곱빼기 여부 (곱빼기( +1000 ) : 1 / 보통 : 2) : ")
            when (readln().toInt()) {
                1 -> {
                    check = "곱빼기 입니다.(+1000)"
                    price = 7000
                    break
                }

                2 -> {
                    check = "곱배기 아닙니다"
                    break
                }

                3 -> {
                    println()
                    println("★☆1,2 번만 선택이 가능합니다.☆★")
                }
            }
        }
    }
}

class Jjambbong(val name: String = "짬뽕", val price: Int = 8000, var check: String = "") {
    init {
        while (true) {
            print("홍합 여부 (넣는다 : 1 / 뺀다 : 2) : ")
            when (readln().toInt()) {
                1 -> {
                    check = "홉합이 있습니다."
                    break
                }

                2 -> {
                    check = "홍합이 없습니다."
                    break
                }

                3 -> {
                    println()
                    println("★☆1,2 번만 선택이 가능합니다.☆★")
                }
            }
        }
    }
}

class FriedRice(val name: String = "볶음밥", val price: Int = 10000, var check: String = "") {
    init {
        while (true) {
            print("국물을 선택하세요. (짬뽕국물 : 1 / 계란국물 : 2) : ")
            when (readln().toInt()) {
                1 -> {
                    check = "짬뽕국물"
                    break
                }

                2 -> {
                    check = "계란국물"
                    break
                }

                3 -> {
                    println()
                    println("★☆1,2 번만 선택이 가능합니다.☆★")
                }
            }
        }

    }
}