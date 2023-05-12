import java.util.Scanner

fun main() {

    val sc = Scanner(System.`in`)
    print("학생의 수 :")
    val studentNum = sc.nextInt()

    //학생의 점수와 평균을 담을 array 생성
    // Any : 안에 String과 Int가 있기 때문에
    val studentList: ArrayList<ArrayList<Any>> = ArrayList()

    //학생의 수 만큼 반복문을 돌린다.
    repeatNumberStudents(studentNum, sc, studentList)

    //반복문을 이용해 학생들의 정보를 출력한다.
    printStudentsInformation(studentList)


    //국,영,수의 총점을 담을 Array 생성
    val totalScore = totalScoreCalculation(studentList)

    // 출력
    studentInformationOutput(totalScore, studentNum)
}


private fun repeatNumberStudents(
    studentNum: Int,
    sc: Scanner,
    studentList: ArrayList<ArrayList<Any>>
) {
    for (i in 0 until studentNum) {
        //Array안에 들어갈 ArrayList 생성
        val stdntInfo = ArrayList<Any>()
        print("이름 : ")
        stdntInfo.add(sc.next())
        print("학년 : ")
        stdntInfo.add(sc.nextInt())
        print("국어 점수를 입력하세요: ")
        stdntInfo.add(sc.nextInt())
        print("영어 점수를 입력하세요: ")
        stdntInfo.add(sc.nextInt())
        print("수학 점수를 입력하세요: ")
        stdntInfo.add(sc.nextInt())

        //학생의 정보를 학생 리스트 arrayList에 담는다.
        //ex) 0 - 삐삐   1 100 100 100
        //ex) 1 - 픽시   2 50  50  50
        //ex) 그럼  0번지에 이름을 출력하게 된다, 1번지는 학년 2번지는 국어 3번지는 영어 4번지는 수학
        studentList.add(stdntInfo)
    }
}

private fun printStudentsInformation(studentList: ArrayList<ArrayList<Any>>) {
    println()
    println("각 학생들의 정보를 출력★")
    for (s in studentList) {
        println("이름: ${s[0]}, 학년: ${s[1]}, 국어: ${s[2]}, 영어: ${s[3]}, 수학: ${s[4]}")
    }
}

private fun totalScoreCalculation(studentList: ArrayList<ArrayList<Any>>): IntArray {
    println()
    val totalScore = IntArray(3)
    for (studentInfo in studentList) {
        totalScore[0] += studentInfo[2] as Int // 국어 점수 총점 계산
        totalScore[1] += studentInfo[3] as Int // 영어 점수 총점 계산
        totalScore[2] += studentInfo[4] as Int // 수학 점수 총점 계산
    }
    return totalScore
}

private fun studentInformationOutput(totalScore: IntArray, studentNum: Int) {
    println("각 학생들의 총점/평균 출력★")
    println("국어 총점: ${totalScore[0]}, 국어 평균: ${totalScore[0] / studentNum.toDouble()}")
    println("영어 총점: ${totalScore[1]}, 영어 평균: ${totalScore[1] / studentNum.toDouble()}")
    println("수학 총점: ${totalScore[2]}, 수학 평균: ${totalScore[2] / studentNum.toDouble()}")
}