/*
학교 클래스

학생은 이름, 국어, 영어, 수학, 총점, 평균

프로그램이 시작하면 다음과 같이 입력을 받는다.
학생 정보를 입력해주세요
이름 : 홍길동
국어 : 100
영어 : 100
수학 : 100
계속 입력하시겠습니까?(1. 예, 2. 아니오) :

각 학생들의 정보를 모두 출력한다.
이름 : 홍길동
국어 : 100
영어 : 100
수학 : 100
총점 : 300
평균 : 100

....

국어 전체 총점 : 000점
영어 전체 총점 : 000점
수학 전체 총점 : 000점

국어 전체 평균 : 000점
영어 전체 평균 : 000점
수학 전체 평균 : 000점

조건 : let, run, apply, with, also 중에 사용하고 싶은 것을
적극적으로 활용해주세요.
그 밖에 오늘 배운 것을 써먹을 곳이 있다면 써먹어 주세요

 */
fun main() {
    val students = mutableListOf<Student>()

    //이름 : 홍길동
//국어 : 100
//영어 : 100
//수학 : 100
//계속 입력하시겠습니까?(1. 예, 2. 아니오) :

    while (true) {

        /*
          val t8 = TestClass1(100, 200).apply {
        a3 = 300
        a4 = 400
    }
         */


//        println("학생의 정보를 입력하세요")
//        print("이름 : ")
//        var name = readln()
//
//        print("국어 : ")
//        var ko = readln().toInt()
//
//        print("영어 : ")
//        var en = readln().toInt()
//
//        print("수학 : ")
//        var math = readln().toInt()

        val student2 = Student().apply {
            print("이름 : ")
            name = readln()

            print("국어 : ")
            kor = readln().toInt()

            print("영어 : ")
            eng = readln().toInt()

            print("수학 : ")
            math = readln().toInt()
        }
        students.add(student2)
        // 학생 객체를 생성

        println("계속 입력하시겠습니까?(1. 예, 2. 아니오) : ")
        var inputNumber = readln().toInt()
        if (inputNumber == 2) {
            break
        } else {
            continue
        }
    }

    // 각 학생들의 정보를 모두 출력한다.
    for (std in students) {
        std.printInfo()
    }

    var korTotal = 0
    var engTotal = 0
    var mathTotal = 0

    println()
    for (std in students) {
        korTotal.plus(std.kor)
        engTotal.plus(std.eng)
        mathTotal.plus(std.math)
    }

    println("국어 전체 총점 : $korTotal")
    println("영어 전체 총점 : $engTotal")
    println("수학 전체 총점 : $mathTotal")

    println("국어 전체 평균 : ${korTotal / students.size}")
    println("영어 전체 평균 : ${engTotal / students.size}")
    println("수학 전체 평균 : ${mathTotal / students.size}")


}

class Student(
    var name: String = "",
    var kor: Int = 0,
    var eng: Int = 0,
    var math: Int = 0
) {
    fun printInfo() {
        println("이름 : $name")
        println("국어 : $kor")
        println("영어 : $eng")
        println("수학 : $math")
        println("총점 : ${kor + eng + math}")
        println("평균 : ${(kor + eng + math) / 3}")
    }

    operator fun plus(target: Student): Student {
        var r1 = this.kor + this.eng + this.math

        val result = Student(r1)
        return result
    }

    constructor(name: Int) : this()


}

/*
fun main() {
    val num1 = 100
    val num2 = 200

    // kotlion 에서 연산자를 사용하면 그 연산자에 해당하는
    // 메서드가 호출되고 그 메서드가 반환하는 값이 최종 결과가 된다.

    val a1 = num1 + num2
    println("a1 : $a1")

    val a2 = num1.plus(num2)
    println("a2 : $a2")

    val obj1 = TestClass(100, 200)
    val obj2 = TestClass(10, 20)

    val obj3 = obj1 + obj2
    println("obj3.a1 : ${obj3.a1}")
    println("obj3.a2 : ${obj3.a2}")

    val obj4 = obj1 - obj2
    println("obj4.a1 : ${obj4.a1}")
    println("obj4.a2 : ${obj4.a2}")
}

class TestClass(var a1: Int, var a2: Int) {
    // 더하기 연산자를 사용하면 호출되는 메서드를 구현
    // 연산자를 기준으로 좌측의 객체를 통해 메서드가 호출되고
    // 우측의 객체가 매개변수로 돌아온다.

    operator fun plus(target: TestClass): TestClass {
        var r1 = this.a1 + target.a1
        var r2 = this.a2 + target.a2

        val result = TestClass(r1, r2)
        return result
    }

    operator fun minus(target: TestClass) : TestClass{
        var r1 = this.a1 - target.a1
        var r2 = this.a2 - target.a2

        val result = TestClass(r1, r2)
        return result
    }
}
 */

