fun main() {

    // 외부 클래스의 객체를 생성한다.
    val obj1 = Outer1()

    // 이를 통해 내부 클래스의 객체를 생성한다.
    val obj2 = obj1.Inner1()

    val t1 = TestClass1()
    t1.interMethod1()
    t1.interMethod2()

    // 만약 인터페이스를 구현하거나 클래스를 상속받은 클래스를 통해 객체를 하나만 생성한다면
    // 익명 중첩 클래스를 사용해도 된다.
    val t2 = object: Inter1{
        override fun interMethod1() {
            println("익명 중첩 클래스의 interMethod1")
        }

        override fun interMethod2() {
            println("익명 중첩 클래스의 interMethod2")
        }


    }
    t2.interMethod1()
    t2.interMethod2()
}

// 일반 중첩 클래스
// 내부에 있는 클래스의 객체 생성은 외부 클래스로 부터 생성한 객체를 통해 생성할 수 있다.
// 내부의 클래스를 가지고 만든 객체는 외부 클래스를 통해 만든 객체가 무조건 있다느 것을
// 보장받을 수 있기 때문에 외부 클래스에 정의한 멤버의 접근이 자유롭다.
class Outer1 {
    var outerV1 = 100

    fun outerMethod() {
        println("Outer1의 outerMethod 입니다")
    }

    inner class Inner1 {

        fun innerMethod(){
            println("outerV1 : $outerV1")
            outerMethod()
        }

    }
}

interface Inter1 {
    fun interMethod1()
    fun interMethod2()
}

// 익명 중첩클래스를 사용하지 않는다면
// 인터페이스를 구현하거나 클래스를 상속 받은 다음에
// 메서드를 오버라이딩한 클래스를 만들고 위로 올라가서 객체 생성해 사용해야 한다.
// 만약 클래스를 통해 생성하는 객체가 두 개 이상이면 클래스를 정의하고 객체 생성해서 사용한다.

class TestClass1 :Inter1{
    override fun interMethod1() {
        println("TestClass1의 interMethod1 ")
    }

    override fun interMethod2() {
        println("TestClass1의 interMethod2 ")
    }

}