fun main() {
    val students = mutableListOf<Student>() // <Student> 타입의 학생을 담을 List
    val school = School() // School 객체생성

    var generalStudents = 0 // 일반 학생 수
    var soccerStudents = 0 // 축구부 학생 수
    var volleyballClubStudents = 0 // 배구부 학생 수
    var swimmingClubStudents = 0 // 수영부 학생 수

    var numberStudentsPlayingFootball = 0 // 축구를 하는 학생 수
    var numberStudentsPlayingVolleyball = 0 // 배구를 하는 학생 수
    var numberStudentsSwimming = 0 // 수영을 하는 학생 수

    //소속, 이름, 학년, 국어, 영어, 수학은 키보드로 입력을 받는다.
    //그리고 students에 추가한다.
    while (true) {
        println("☆★학생 정보를 입력해 주세요☆★")
        println("값을 넣지 않거나  0을 누르면 끝나요!")

        print("소속  (일반 , 축구, 배구, 수영 중에 적어주세요.): ")
        val belong = readln()

        // 소속이 일반, 축구, 배구, 수영이 아니라면 continue문이 실행되어 다시 입력받게 한다.
        if (belong != "일반" && belong != "축구" && belong != "배구" && belong != "수영") {
            println("소속이 불분명하네요. 다시 입력하세요")
            println()
            continue
        }

        print("이름: ")
        val name = readln()

        // 빈 문자열이면 continue문이 실행되어 다시 처음부터 입력받게 한다.
        if (name.isEmpty()) {
            println("이름이 뭔가요?")
            println()
            continue
        }

        print("학년 ( 1 ~ 6 사이 숫자만 입력해주세요!!!): ")
        val grade = readln().toInt()

        // 만약 1 ~ 6 숫자가 이 안에 있다는걸 부정한다.
        // 즉 1 ~ 6 은 이 if문이 실행이 안된다.
        if (grade !in 1..6) {
            println("1학년부터 6학년까지만 있어요!!!")
            println()
            continue
        }

        print("국어 점수 ( 0 ~ 100 사이 숫자만 입력해주세요!!!): ")
        val korean = readln().toInt()

        // 만약 0 ~ 100 숫자가 이 안에 있다는걸 부정한다.
        // 즉 0 ~ 100 은 이 if문이 실행이 안된다.
        if (korean !in 0..100) {
            println("0 ~ 100 사이의 값을 입력해주세요!!!")
            println()
            continue
        }

        print("영어 점수 ( 0 ~ 100 사이 숫자만 입력해주세요!!!): ")
        val english = readln().toInt()
        if (english !in 0..100) {
            println("0 ~ 100 사이의 값을 입력해주세요!!!")
            println()
            continue
        }

        print("수학 점수 ( 0 ~ 100 사이 숫자만 입력해주세요!!!): ")
        val math = readln().toInt()
        if (math !in 0..101) {
            println("0 ~ 100 사이의 값을 입력해주세요!!!")
            println()
            continue
        }

        // 열거형에서 정의한 이름을 분기한다.
        when (belong) {
            "일반" -> {
                val student = Student.GeneralStudent(
                    BelongEnum.일반, name, grade, korean, english, math
                )
                students.add(student)
            }

            "축구" -> {
                val student = Student.GeneralStudent(
                    BelongEnum.축구, name, grade, korean, english, math
                )
                students.add(student)
            }

            "배구" -> {
                val student = Student.GeneralStudent(
                    BelongEnum.배구, name, grade, korean, english, math
                )
                students.add(student)
            }

            "수영" -> {
                val student = Student.GeneralStudent(
                    BelongEnum.수영, name, grade, korean, english, math
                )
                students.add(student)
            }

        }

        println("☆★계속 입력받고 싶으면 엔터 / 그만 입력받고 싶으면 숫자 0 입력☆★")

        val num = readlnOrNull() // 0을 누르면 반복문을 나가는 변수 readIn() 함수로 사용자 입력값을 넣는다.
        if (num == "0") {
            break
        }
    }

    println()
    // 학생 정보를 출력한다.
    println("☆★학생 정보 출력☆★")
    for (student in students) {
        println("소속: ${student.belong}부")
        println("이름: ${student.name}")
        println("학년: ${student.grade}")
        println("국어: ${student.korean}점")
        println("영어: ${student.english}점")
        println("수학: ${student.math}점")
        val total = student.korean + student.english + student.math
        val average = total / 3.0
        println("개인 총점: $total 점")
        println("개인 평균: %.2f 점\n".format(average))
    }

    println("☆★학생들은 무엇은 할까?☆★")

    // 교실 출력
    // 전체 출력
    for (student in students) {
        school.classroomPrintMethod(student.name)
    }

    println()
    // 운동장 출력
    // 운동장에서는 축구부와 배구부 학생들이 축구를 한다.
    for (student in students) {
        if (student.belong == BelongEnum.축구 || student.belong == BelongEnum.배구) {
            school.playground.soccerAndVolleyball(student.name)
            numberStudentsPlayingFootball += 1 // 축구를 하는 사람 수를 1씩 증가
        }
    }

    println()
    // 체육관 출력
    // 배구부와 수영부 학생들이 배구를 한다.
    for (student in students) {
        if (student.belong == BelongEnum.배구 || student.belong == BelongEnum.수영) {
            school.gym.volleyballAndSwimming(student.name)
            numberStudentsPlayingVolleyball += 1 // 배구를 하는 사람 수를 1씩 증가
        }
    }

    println()
    // 수영장 출력
    // 수영부와 축구부 학생들이 수영을 한다.
    for (student in students) {
        if (student.belong == BelongEnum.수영 || student.belong == BelongEnum.축구) {
            school.swimmingPool.swimmingAndSoccer(student.name)
            numberStudentsSwimming += 1 // 수영을 하는 사람 수를 1씩 증가
        }
    }

    // 일반 학생 수 담기
    for (student in students) {
        if (student.belong == BelongEnum.일반) {
            generalStudents += 1
        }
    }

    // 축구부 학생 수 담기
    for (student in students) {
        if (student.belong == BelongEnum.축구) {
            soccerStudents += 1
        }
    }

    // 배구부 학생 수 담기
    for (student in students) {
        if (student.belong == BelongEnum.배구) {
            volleyballClubStudents += 1
        }
    }

    // 수영부 학생 수 담기
    for (student in students) {
        if (student.belong == BelongEnum.수영) {
            swimmingClubStudents += 1
        }
    }
    println("")
    println("☆★학생들 인원☆★")
    println("전체 학생 수 : ${students.size} 명")
    println("전체 학생 수 : ${students.size} 명")
    println("일반 학생 수  : $generalStudents 명")
    println("축구부 학생 수 : $soccerStudents 명")
    println("배구부 학생 수 : $volleyballClubStudents 명")
    println("수영부 학생 수 :$swimmingClubStudents 명")
    println("축구를 하는 학생 수 : $numberStudentsPlayingFootball 명")
    println("배구를 하는 학생 수 : $numberStudentsPlayingVolleyball 명")
    println("수영을 하는 학생 수 : $numberStudentsSwimming 명")


}

//소속을 열거함
enum class BelongEnum {
    일반, 축구, 배구, 수영
}


sealed class Student(
    val belong: BelongEnum,
    val name: String,
    val grade: Int,
    val korean: Int,
    val english: Int,
    val math: Int
) {
    // 일반부
    data class GeneralStudent(
        val generalBelong: BelongEnum,
        val generalName: String,
        val generalGrade: Int,
        val generalKorean: Int,
        val generalEnglish: Int,
        val generalMath: Int
    ) : Student(generalBelong, generalName, generalGrade, generalKorean, generalEnglish, generalMath)

    // 축구부
    data class SoccerStudent(
        val soccerBelong: BelongEnum,
        val soccerName: String,
        val soccerGrade: Int,
        val soccerKorean: Int,
        val soccerEnglish: Int,
        val soccerMath: Int
    ) : Student(soccerBelong, soccerName, soccerGrade, soccerKorean, soccerEnglish, soccerMath)

    // 배구부
    data class VolleyballStudent(
        val volleyballBelong: BelongEnum,
        val volleyballName: String,
        val volleyballGrade: Int,
        val volleyballKorean: Int,
        val volleyballEnglish: Int,
        val volleyballMath: Int
    ) : Student(volleyballBelong, volleyballName, volleyballGrade, volleyballKorean, volleyballEnglish, volleyballMath)

    // 수영부
    data class SwimmingStudent(
        val swimmingBelong: BelongEnum,
        val swimmingName: String,
        val swimmingGrade: Int,
        val swimmingKorean: Int,
        val swimmingEnglish: Int,
        val swimmingMath: Int
    ) : Student(swimmingBelong, swimmingName, swimmingGrade, swimmingKorean, swimmingEnglish, swimmingMath)
}

class School() {

    // 일반 출력문
    fun classroomPrintMethod(name: String) {
        println("$name 학생이 교실에서 공부하고 있습니다.★")
    }


    // 운동장
    val playground: Playground = object : Playground {
        override fun soccerAndVolleyball(name: String) {
            println("$name 학생이 운동장에서 축구를 하고 있습니다")
        }

    }

    // 체육관
    val gym: Gym = object : Gym {
        override fun volleyballAndSwimming(name: String) {
            println("$name 학생이 운동장에서 배구를 하고 있습니다")
        }
    }

    // 수영장
    val swimmingPool: SwimmingPool = object : SwimmingPool {
        override fun swimmingAndSoccer(name: String) {
            println("$name 학생이 수영장에서 수영을 하고 있습니다")
        }
    }

    // 운동장 인터페이스
    interface Playground {
        fun soccerAndVolleyball(name: String)
    }

    // 체육관 인터페이스
    interface Gym {
        fun volleyballAndSwimming(name: String)
    }

    // 수영장 인터페이스
    interface SwimmingPool {
        fun swimmingAndSoccer(name: String)
    }

}

