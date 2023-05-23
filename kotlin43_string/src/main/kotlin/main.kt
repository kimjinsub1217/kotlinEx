fun main() {
    val str1 = "안녕하세요"
    println("str1 : $str1")

    // 원하는 번째의 글자를 가져올 수 있다.
    println("str1[0] : ${str1[0]}")
    println("str1[1] : ${str1[1]}")

    // 원하는 번째의 글자를 다른 근자로 변경한다.
    // 문자열은 글자를 바꾸는 것이 불가능하다.
    // str1[0]="a"

    println("-----------------------------------------------------")

    // 원하는 부분의 글자들을 추출하여 새로운 문자열로 받아온다.
    // 두 번째 글자 -> 네 번째 글자
    val str2 = str1.substring(1..3)
    println("str2 : $str2")

    println("-----------------------------------------------------")

    // 문자열 비교
    val str3 = "Hello World"
    val str4 = "hello world"
    val str5 = "Hello World"

    // 코틀린에서 == 를 가지고 비교를 하면 문자열 값 자체를 비교하는 작업을 수행한다.
    if (str3 == str4) {
        println("str3과 str4는 같습니다")
    }

    if (str3 == str5) {
        println("str3과 str5는 같습니다")
    }

    // compareTo : 같으면 0, 다르면 0이 아닌 값이 나온다.
    println(str3.compareTo(str4))
    println(str3.compareTo(str5))

    // 두 번째 매개 변수에 true를 넣어주면
    // 모두 소문자로 변환한 다음 코드값을 빼는 작업을 수행한다
    // 대소문자를 구분하고 같은가를 확인한다.
    println(str3.compareTo(str4, true))

    if (str3.equals(str4, true)) {
        println("대소문자 무시하고 같습니다.")
    }

    println("-----------------------------------------------------")

    // 구분자를 기준으로 문자열을 나눈다.
    val str6 = "ab cd ef gh"
    // 띄어쓰를 기준으로 나눈다.
    val r6 = str6.split(" ")
    println("r6 : $r6")

    for (temp6 in r6) {
        println(temp6)
    }

    println("-----------------------------------------")
    // 문자열 병합
    val str7 = "abc"
    val str8 = str7 + "def"
    val str9 = str8 + 10
    println("str7 : $str7")
    println("str8 : $str8")
    println("str9 : $str9")

    // String은 문자열 합치는 작업을 수행할 때 마다 새로운 문자열 객체가
    // 계속 생성된다(String은 관리하는 문자열 수정이 불가능)
    // 이럴 때 StringBuffer를 사용

    val buffer1 = StringBuffer()
    buffer1.append("abc")
    buffer1.append(100)
    buffer1.append(11.11)
    println("buffer1 : $buffer1")

}