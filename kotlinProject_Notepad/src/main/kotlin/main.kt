import java.io.*
import kotlin.system.exitProcess
/*
O 1. 프로그램 실행시 설정된 비밀번호가 없다면 비밀번호를 설정하는 기능이 먼저 나와야 한다.
O 2. 설정된 비밀번호가 있다면 로그인 화면이 나온다.
O 3. 제목과 내용은 띄어쓰기 입력을 허용한다.
O 4. 모든 입력에 대해 잘못된 입력(허용하지 않는 숫자나 문자열 입력)에 대해 잘못된 입력이라는 문구를 보여줘야 한다.
O 5. 카테고리나 메모가 등록된게 없을 경우 카테고리나 메모 목록을 보여주는 화면에서는 등록된 것이 없다는 메시지를 보여줘야 한다.
O 6. 비밀번호, 카테고리 정보, 메모 내용 정보 등은 모두 파일에 저장을 하고 파일에서 불러와 보여주는 것으로 처리 한다.
 */
fun main() {
    val mainClass = MainClass()
    mainClass.running()
}

// 메인 클래스
class MainClass {

    // 프로그램 상태를 담는 변수에 초기 상태를 설정한다.( 비밀번호 화면 일치하면 메인 화면으로 상태 변경)
    var passwordProgramState = ProgramPassWordMainState.PROGRAM_STATE_PASSWORD

    // 프로그램 실행시 설정된 비밀번호가 없다면 설정하고 아니라면 로그인 화면을 보여준다.
    val passwordMenuClass = PasswordMenuClass(passwordProgramState)

    // 카테고리를 저장하기 위한 리스트
    val categoryList = mutableListOf<String>()


    // 프로그램 상태 전체를 관리하며 운영하는 메서드
    fun running() {
        loadCategoryList(categoryList) // 파일에서 카테고리 목록 로드
        while (true) {
            // 프로그램 상태에 따른 분기
            when (passwordProgramState) {
                ProgramPassWordMainState.PROGRAM_STATE_PASSWORD -> {
                    passwordMenuClass.login()
                    passwordProgramState = ProgramPassWordMainState.PROGRAM_STATE_MAIN
                }

                ProgramPassWordMainState.PROGRAM_STATE_MAIN -> {
                    println()
                    println("========메인화면=========")
                    println("|| 1. 메모 카테고리 관리 ||")
                    println("|| 2. 메모 카테고리 선택 ||")
                    println("|| 3. 메모 내용 전체 보기||")
                    println("|| 4. 종료             ||")
                    println("========================")
                    print("메뉴를 선택해주세요 : ")
                    try {
                        val menuNum = readln().toInt()
                        while (true) {
                            when (menuNum) {
                                ProgramMainState.PROGRAM_STATE_List.itemNumber -> {
                                    println()
                                    var num = 1
                                    if (categoryList.isEmpty()) {
                                        println("☆등록된 카테고리가 없습니다.☆")
                                    } else {
                                        for (i in categoryList) {
                                            println("$num : $i")
                                            num++
                                        }
                                    }

                                    println()

                                    println("=====메모 카테고리 관리====")
                                    println("|| 1. 카테고리 등록      ||")
                                    println("|| 2. 카테고리 삭제      ||")
                                    println("|| 3. 카테고리 수정      ||")
                                    println("|| 4. 이전             ||")
                                    println("========================")
                                    print("카테고리 관리 메뉴 선택 : ")
                                    val categoryNum = readln().toInt()

                                    when (categoryNum) {
                                        ProgramSelectCategoryState.PROGRAM_STATE_ADD_Category.itemNumber -> {
                                            addCategory(categoryList)
                                        }

                                        ProgramSelectCategoryState.PROGRAM_STATE_DELETE__Category.itemNumber -> {
                                            DeleteCategory(categoryList)
                                        }

                                        ProgramSelectCategoryState.PROGRAM_STATE_EDIT__Category.itemNumber -> {
                                            EditCategory(categoryList)
                                        }

                                        ProgramSelectCategoryState.PROGRAM_STATE_END.itemNumber -> {
                                            break
                                        }
                                        else ->{
                                            println("잘못 입력하였습니다.")
                                        }

                                    }
                                }

                                ProgramMainState.PROGRAM_STATE_SELECT_CATEGORY.itemNumber -> {

                                    println()
                                    var num = 1

                                    if (categoryList.isEmpty()) {
                                        println("☆등록된 카테고리가 없습니다.☆")
                                    } else {
                                        for (i in categoryList) {
                                            println("$num : $i")
                                            num++
                                        }
                                    }

                                    print("선택할 카테고리 번호를 입력해주세요(0. 이전) : ")
                                    var categoryNum = readln().toInt()

                                    if (categoryNum == 0) {
                                        break
                                    }
                                    while (true) {
                                        if (categoryNum in 1..categoryList.size) {

                                            val selectedCategory = categoryList[categoryNum - 1]
                                            val memoFile = File("$selectedCategory.dat")
                                            val memoList = loadMemoList(memoFile)
                                            println()
                                            if (memoList.isEmpty()) {
                                                println("등록된 메모가 없습니다.")
                                            } else {
                                                for ((index, memo) in memoList.withIndex()) {
                                                    println("${index + 1}. ${memo.title}")
                                                }
                                            }
                                            println()
                                            println("=====================================================")
                                            print("1. 메모 보기 2. 메모 등록 3. 메모 수정 4. 메모 삭제 5. 이전: ")
                                            val selectMemo = readln().toInt()

                                            when (selectMemo) {
                                                ProgramChoiceCategoryState.PROGRAM_STATE_VIEW_MEMO.itemNumber -> {
                                                    // 메모 보기
                                                    viewMemo(categoryNum, categoryList)
                                                }

                                                ProgramChoiceCategoryState.PROGRAM_STATE_ADD_MEMO.itemNumber -> {
                                                    // 메모 등록
                                                    addMemo(categoryNum, categoryList)
                                                }

                                                ProgramChoiceCategoryState.PROGRAM_STATE_EDIT_MEMO.itemNumber -> {
                                                    // 메모 수정
                                                    println()
                                                    editMemo(categoryNum, categoryList)
                                                }

                                                ProgramChoiceCategoryState.PROGRAM_STATE_DELETE_MEMO.itemNumber -> {
                                                    // 메모 삭제
                                                    deleteMemo(categoryNum, categoryList)
                                                }

                                                ProgramChoiceCategoryState.PROGRAM_STATE_BEFORE.itemNumber -> {
                                                    // 이전
                                                    break
                                                }
                                                else->{
                                                    println("잘못 입력하였습니다.")
                                                }
                                            }
                                        }

                                    }
                                }

                                ProgramMainState.PROGRAM_STATE_SEE_ALL_NOTES.itemNumber -> {
                                    var num = 0
                                    if (categoryList.isEmpty()) {
                                        println("☆등록된 카테고리가 없습니다.☆")
                                    } else {
                                        for (i in categoryList) {

                                            println("------------------------------------------")
                                            println(i)
                                            println("------------------------------------------")

                                            val selectedCategory = categoryList[num]
                                            val memoFile = File("$selectedCategory.dat")
                                            val memoList = loadMemoList(memoFile)

                                            if (memoList.isEmpty()) {
                                                println()
                                                println("등록된 메모가 없습니다.")
                                                println()
                                            } else {
                                                for (memo in memoList) {
                                                    println()
                                                    println("제목 : ${memo.title}")
                                                    println("내용 : ${memo.content}")
                                                    println()
                                                }
                                            }
                                            num++
                                        }
                                    }
                                    break
                                }

                                ProgramMainState.PROGRAM_STATE_END.itemNumber -> {
                                    println("프로그램을 종료합니다")
                                    exitProcess(0)
                                }
                                else ->{
                                    println("잘못 입력하였습니다.")
                                    break
                                }
                            }
                        }
                    }catch (e:Exception){
                        println("숫자만 입력해주세요.")
                    }
                }
            }
        }
    }


}


