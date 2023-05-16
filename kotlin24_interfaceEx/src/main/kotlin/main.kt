fun main() {

    val animals = mutableListOf<Animal>()
    val Zoo = mutableListOf<Zoo>()

    val dolphin = Dolphin()
    val shark = Shark()
    val horse = Horse()
    val giraffe = Giraffe()
    val tiger = Tiger()
    val lion = Lion()
    var num = 0


    while (true) {
        println("어떤 동물을 동물원에 넣어줄까요?")
        print("1. 돌고래, 2. 상어, 3. 말, 4. 기린, 5. 호랑이, 6. 사자, 0. 그만넣어 :")
        num = readln().toInt()
        when (num) {
            1 -> {
                animals.add(dolphin)
            }

            2 -> {
                animals.add(shark)
            }

            3 -> {
                animals.add(horse)
            }

            4 -> {
                animals.add(giraffe)
            }

            5 -> {
                animals.add(tiger)
            }

            6 -> {
                animals.add(lion)
            }

            0 -> break
            else -> {
                println("잘못 입력하였습니다.")
                continue
            }
        }
    }
    println()
    println("---------------출력---------------")
    // 정보
    for (animal in animals) {
        animal.info()
    }
    // 먹기
    println()
    println("★동물 사육장에 넣어주면 모든 동물들이 밥을 먹는다.★")
    for (animal in animals) {
        animal.eat()
    }
    println()

    // 헤엄
    println("★해양생물 놀이터에 넣어주면 돌고래와 상어가 헤엄을 친다.★")
    for (animal in animals) {
        if (animal is Dolphin || animal is Shark ) {
            animal.swim()
        }
    }
    // 뛰기
    println("★방목장에 넣어주면 말과 기린과 호랑이만 뛰어 다닌다.★")
    for (animal in animals) {

        if (animal is Horse || animal is Giraffe || animal is Tiger) {
            animal.run()
        }
    }


    // 사냥
    println("★밀림에 넣어주면 상어와 기린과 사자가 사냥을 한다.★")
    for (animal in animals) {
        if (animal is Giraffe || animal is Shark || animal is Lion) {
            animal.hunt()
        }
    }
}

abstract class Zoo {
    abstract fun action()
}



// 동물 인터페이스
abstract class Animal : Swimmable, Runnable, Hunter, type {
    open fun eat() {}
    override fun info() {}
}

// 수영 가능한 동물 인터페이스
interface Swimmable {
    fun swim()
}

// 뛸 수 있는 동물 인터페이스
interface Runnable {
    fun run()
}

// 사냥하는 동물 인터페이스
interface Hunter {
    fun hunt()
}

// 타입
interface type {
    fun info()
}

// 돌고래 클래스
class Dolphin : Animal(), Swimmable, type {
    override fun eat() {
        println("돌고래는 밥을 먹을 때 \"냠냠냠\"하고 먹는다.")
    }

    override fun swim() {
        println("돌고래는 헤엄을 칠 때 \"돌~돌~\"하면서 헤엄을 친다.")
    }

    override fun run() {

    }

    override fun hunt() {

    }

    override fun info() {
        println("타입 : 돌고래")
        println("크기 : 돌고래 만큼 크다")
        println("헤엄 속도 : 300노트")
    }
}

// 상어 클래스
class Shark : Animal(), Swimmable, Hunter, type {
    override fun eat() {
        println("상어는 밥을 먹을 때 \"얌얌얌\"하고 먹는다.")
    }

    override fun swim() {
        println("상어가 헤엄을 칠 때 \"슈웅 슈융\" 하면서 헤엄을 친다.")
    }

    override fun run() {

    }

    override fun hunt() {
        println("상어가 사냥을 하면 \"으아아아아아!!!\" 하면서 사냥을 한다.")
    }

    override fun info() {
        println("타입 : 상어")
        println("크기 : 상어 만큼 크다")
        println("헤엄 속도 : 500노트")
    }
}

// 말 클래스
class Horse : Animal(), Runnable, type {
    override fun eat() {
        println("말은 밥을 먹을 때 \"당근 당근\"하고 먹는다.")
    }

    override fun run() {
        println("말이 뛰어 다닐 때닌 \"이히히히힝~\" 하면서 뛰어 다니고")
    }

    override fun hunt() {

    }

    override fun info() {
        println("  타입 : 말")
        println(" 크기 : 말 만큼 크다")
        println(" 달리기 속도 : 300km/h")
    }

    override fun swim() {

    }
}

// 기린 클래스
class Giraffe : Animal(), Runnable, type {
    override fun eat() {
        println("기린은 밥을 먹을 때 \"풀풀풀\" 하고 먹는다.")
    }

    override fun run() {
        println("기린이 뛰어 다닐 때는 \"영차~ 영차~\" 하면서 뛰어 다닌다.")
    }

    override fun hunt() {

    }

    override fun info() {
        println("타입 : 기린")
        println("크기 : 기린 만큼 크다")
        println("달리기 속도 : 500km/h")
    }

    override fun swim() {
    }
}

// 호랑이 클래스
class Tiger : Animal(), Runnable, Hunter, type {
    override fun eat() {
        println("호랑이가 밥을 먹을 때 \"아작 아작\"하고 먹는다.")
    }

    override fun run() {
        println("호랑이가 뛰어 다닐 때는 \"헥~ 헥~\" 하면서 뛰어 다닌다.")
    }

    override fun hunt() {
        println("호랑이가 사냥을 하면 \"가즈아~\" 하고 사냥을 한다.")
    }

    override fun info() {
        println("타입 : 호랑이")
        println("크기 : 호랑이 만큼 크다")
        println("먹이량 : 500마리")
    }

    override fun swim() {

    }
}

// 사자 클래스
class Lion : Animal(), Hunter, type {
    override fun eat() {
        println("사자가 법을 먹을 때 \"꿀꺽 꿀꺽\" 하고 는다.")
    }

    override fun hunt() {
        println("사자가 사냥을 하면 \"암컷아 사냥해와라~\"하고 사냥을 한다.")
    }

    override fun info() {
        println("타입 : 사자")
        println("크기 : 사자만큼 크다")
        println("먹이량 : 600마리")
    }

    override fun swim() {

    }

    override fun run() {

    }

}


