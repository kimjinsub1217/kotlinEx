fun main() {
    // 배열 생성
    // 배열이 관리할 값들을 넣어준다.
    val array = arrayOf(10, 20, 30, 40, 50)

    // 배열이 관리하는 값들을 출력할 수 있다.
    println("array")
    println(array.contentToString())

    // 배열은 다양한 타입의 값을 담을 수 있다.
    val array2 = arrayOf(100, 11.11, "문자열,", true)
    println()
    println("array2")
    println(array2.contentToString())

    // 특정 타입의 값만 담는 배열을 생성한다.
    val array3 = intArrayOf(10, 20, 30, 40, 50)
    val array4 = doubleArrayOf(11.11, 22.22, 33.33, 44.44, 55.55)
    val array5 = arrayOf<String>("문자열1", "문자열2", "문자열3")

    println("array3 : ${array3.contentToString()}")
    println("array4 : ${array4.contentToString()}")
    println("array5 : ${array5.contentToString()}")

    // 람다식을 통한 배열 생성
    // 람다식 코드를 수행하여 제일 마지막에 작성되어 있는 값을 배열의 요소로 담아준다.
    // 특정 식을 통해 계산된 결과나 패턴을 가지는 배열을 만들 때 사용한다
    // 0 이 5개 들어 있는 배열 생성
    val array6 = Array(5) {
        0
    }
    println("array6 : ${array6.contentToString()}")

    // it 사용
    // it 에는 0부터 1씩 증가되는 값이 들어온다.
    val array7 = Array(5) {
        it
    }
    println("array7 : :${array7.contentToString()}")

    // 특정 패턴을 가진 값을 가지는 배열
    // 3의 배수
    val array8 = Array(10) {
        (it + 1) * 3
    }
    println("array8 : ${array8.contentToString()}")

//    val array9 = Array<Int?>(10) {
//        null
//    }
//
//    println("array9 : ${array9.contentToString()}")

    println("---------------------------------------")

    // 배열의 값을 처음 부터 끝까지 순회한다.
    for (item in array) {
        println("item : $item")
    }
    println("---------------------------------------")

    // 다 차원 배열 만들기
    val array9 = arrayOf(
        arrayOf(10, 20, 30),
        arrayOf(10, 20, 30),
        arrayOf(10, 20, 30),
    )

    println("array9 : $array9")
    println("array9 : ${array9.contentToString()}")
    println("array9 : ${array9.contentDeepToString()}")

    for (item in array9) {
        for (item2 in item) {
            println("item2 : $item2")
        }
    }

    println("---------------------------------------")
    // 배열 요소에 접근
    // 0 부터 1씩 증가되는 값을 사용
    println("array 1 0 : ${array[0]}")
    println("array 1 1 : ${array[1]}")

    println(array.last())

    println("----------------------------------------------------")

    // 새로운 값을 저장한다.
    println("array1 : ${array.contentToString()}")

    array[0] = 100
    println("array1 : ${array.contentToString()}")

    array[1] = 200
    println("array1 : ${array.contentToString()}")

    println("----------------------------------------------------")

    // 배열이 관리하는 값의 개수
    println("array1 size : ${array.size}")

    println("----------------------------------------------------")

    // 배열에 요소 추가
    // 새로운 배열이 생성

    println("array 1 : ${array.contentToString()}")

    val array10 = array.plus(60)
    println("array : ${array.contentToString()}")
    println("array10 : ${array10.contentToString()}")

    // 일부를 가져온다.
    // 배열에서 지정된 부분의 값을 추출해 새로운 배열로 만들어 반환한다.
    // 순서값 1 ~ 3 까지( 두 번째 ~ 네 번째 )
    val array11 = array.sliceArray(1..3)
    println("array11 : ${array11.contentToString()}")

    println("----------------------------------------------------")
    // 배열이 제공하는 다양한 메서드들
    println("첫 번째 값 : ${array.first()}")
    println("마지막 값 : ${array.last()}")
    println("30의 위치 : ${array.indexOf(30)}")
    println("평균 : ${array.average()}")
    println("합 : ${array.sum()}")
    println("개수 : ${array.count()}")
    println("개수 : ${array.size}")
    println("30을 포함하는 가 : ${array.contains(30)}")
    println("1000을 포함하는 가 : ${array.contains(1000)}")
    println("30을 포함하는가 : ${30 in array}")
    println("1000을 포함하는 가 : ${1000 in array}")
    println("최대 : ${array.max()}")
    println("최소 : ${array.min()}")

    println("----------------------------------------------------")

    // 정렬
    // 값이 정렬된 새로운 배열을 생성한다.
    val array12 = arrayOf(6, 1, 3, 6, 7, 10)

    // 오름 차순
    val array13 = array12.sortedArray()

    // 내림 차순 정렬
    val array14 = array12.sortedArrayDescending()

    println("array12 : ${array12.contentToString()}")
    println("array13 : ${array13.contentToString()}")
    println("array14 : ${array14.contentToString()}")

}