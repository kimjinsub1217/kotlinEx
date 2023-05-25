import ProgramState.*
import java.io.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main() {
    val mainClass = MainClass()
    mainClass.running()
}

// 메인 클래스
class MainClass {

    // 프로그램 상태를 담는 변수에 초기 상태를 설정한다.
    var programState = PROGRAM_STATE_MENU_PRINT

    // 운동 기록을 저장하기 위한 변수
    private val exerciseRecords: MutableMap<String, String> = mutableMapOf()

    init {
        loadExerciseRecordsFromFile() // 프로그램 실행 시 파일에서 운동 기록을 불러옴
    }


    // 프로그램 상태 전체를 관리하며 운영하는 메서드
    fun running() {
        while (true) {
            // 프로그램 상태에 따른 분기

            when (programState) {
                PROGRAM_STATE_MENU_PRINT -> { // 메뉴 출력
                    printMenu()
                }

                PROGRAM_STATE_RECORD -> { // 오늘의 운동 기록
                    recordToday()
                }

                PROGRAM_STATE_RECORD_PRINT -> {// 날짜별 운동 기록 보기
                    println("날짜별 운동 기록")
                    recordPrint()
                }

                PROGRAM_STATE_END -> {// 종료
                    println("종료")
                    break
                }
            }
        }
    }

    // 메뉴를 출력하는 함수
    private fun printMenu() {
        println("메뉴를 선택해주세요.")
        println("1. 오늘의 운동 기록")
        println("2. 날짜별 운동 기록 보기")
        println("3. 종료")

        print("번호 입력 : ")
        val input = readlnOrNull()?.toInt() ?: 0
        when (input) {
            1 -> programState = PROGRAM_STATE_RECORD
            2 -> programState = PROGRAM_STATE_RECORD_PRINT
            3 -> programState = PROGRAM_STATE_END

        }

        exerciseRecords["2023-05-10"] = "운동 종류 : 달리기\n횟수 : 30\n세트 : 5"
        exerciseRecords["2023-05-11"] = "운동 종류 : 달리기\n횟수 : 30\n세트 : 5"
        exerciseRecords["2023-05-12"] = "운동 종류 : 달리기\n횟수 : 30\n세트 : 5"
        exerciseRecords["2023-05-09"] = "운동 종류 : 달리기\n횟수 : 30\n세트 : 5"
    }

    // 운동을 기록하는 함수
    fun recordToday() {
        println()
        print("운동 종류 : ")
        val exerciseType = readlnOrNull() ?: ""
        print("횟수 : ")
        val number = readlnOrNull()?.toIntOrNull() ?: 0
        print("세트 : ")
        val set = readlnOrNull()?.toIntOrNull() ?: 0

        val todayWorkRecord = "운동 종류 : $exerciseType\n횟수 : $number\n세트 : $set"
        val currentDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE)

        if (exerciseRecords.containsKey(currentDate)) {
            // 이미 해당 날짜에 기록이 있는 경우 기존 기록과 신규 기록을 합침
            val existingRecord = exerciseRecords[currentDate]
            exerciseRecords[currentDate] = "$existingRecord\n\n$todayWorkRecord"
        } else {
            // 해당 날짜에 기록이 없는 경우 새로운 기록을 추가함
            exerciseRecords[currentDate] = todayWorkRecord
        }

        saveExerciseRecordsToFile() // 운동 기록을 파일에 저장

        programState = PROGRAM_STATE_MENU_PRINT
    }

    // 운동 기록을 파일에 저장하는 함수
    private fun saveExerciseRecordsToFile() {
        try {
            val fos = FileOutputStream("data1.dat")
            val oos = ObjectOutputStream(fos)

            oos.writeObject(exerciseRecords)

            oos.close()
            fos.close()
        } catch (e: IOException) {
            println("운동 기록을 저장하는 동안 오류가 발생했습니다.")
            e.printStackTrace()
        }

    }


    fun recordPrint() {
        println()
        val keysList = exerciseRecords.keys.toList()
        val sortedKeys = keysList.sorted().distinct()
        for ((index, key) in sortedKeys.withIndex()) {
            println("${index + 1}. $key")
        }
        while (true) {
            print("날짜를 선택해 주세요 (0. 이전)")
            val date = readlnOrNull()?.toIntOrNull() ?: 0

            if (date == 0) {
                break
            }


            val selectedDate = sortedKeys.getOrNull(date - 1)
            if (selectedDate != null) {
                val exerciseRecord = exerciseRecords[selectedDate]
                if (exerciseRecord != null) {
                    println("${selectedDate}의 운동 기록입니다.\n$exerciseRecord")
                } else {
                    println("해당 날짜의 운동 기록이 없습니다.")
                }
            } else {
                println("유효하지 않은 날짜입니다.")
            }
        }
        programState = PROGRAM_STATE_MENU_PRINT
        println()
    }

    // 운동 기록을 파일에서 불러오는 함수
    private fun loadExerciseRecordsFromFile() {
        try {
            val fis = FileInputStream("data1.dat")
            val ois = ObjectInputStream(fis)

            exerciseRecords.clear()
            exerciseRecords.putAll(ois.readObject() as MutableMap<String, String>)

            ois.close()
            fis.close()
        } catch (e: FileNotFoundException) {
            // 파일이 존재하지 않는 경우 무시
        } catch (e: IOException) {
            println("운동 기록을 불러오는 동안 오류가 발생했습니다.")
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            println("운동 기록 파일의 형식이 잘못되었습니다.")
            e.printStackTrace()
        }
    }
}


// 프로그램 상태를 나타내는 enum
enum class ProgramState {
    // 상태를 나타내는 값들을 정의한다.
    PROGRAM_STATE_MENU_PRINT, // 메뉴 출력
    PROGRAM_STATE_RECORD, // 오늘의 운동 기록
    PROGRAM_STATE_RECORD_PRINT,// 날짜별 운동 기록 보기
    PROGRAM_STATE_END// 종료
}

