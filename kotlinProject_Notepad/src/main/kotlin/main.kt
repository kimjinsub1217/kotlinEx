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

    // 각 상태별 객체 생성
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
                                            val memoFile = File("$selectedCategory.txt")
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
                                            }
                                        }

                                    }
                                }

                                ProgramMainState.PROGRAM_STATE_SEE_ALL_NOTES.itemNumber -> {
                                    val selectedCategory = categoryList[1 - 1]
                                    val memoFile = File("$selectedCategory.txt")
                                    val memoList = loadMemoList(memoFile)

                                    var num = 0
                                    if (categoryList.isEmpty()) {
                                        println("☆등록된 카테고리가 없습니다.☆")
                                    } else {
                                        for (i in categoryList) {

                                            println("------------------------------------------")
                                            println(i)
                                            println("------------------------------------------")

                                            val selectedCategory = categoryList[num]
                                            val memoFile = File("$selectedCategory.txt")
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

// 패스워드 -> 메뉴 패스워드가 일치하면 상태 이동을 위한 클래스
class PasswordMenuClass(var programState: ProgramPassWordMainState) {

    fun login() {
        if (fileExists("password.dat")) {
            // 있으면 로그인하고 상태를 변경한다.
            password()

        } else {
            // 파일이 없으면 비밀번호 파일을 만든다.
            passwordSetting()
        }

    }

    // 비밀번호 비교
    private fun password() {
        while (true) {
            print("로그인을 하시려면 비밀 번호를 입력하세요 : ")
            val inputPassword = readln().toInt()

            if (inputPassword == readObjectStream()) {
                // 비밀 번호가 일치하면 상태 변경
                programState = ProgramPassWordMainState.PROGRAM_STATE_MAIN
                break
            } else {
                println("비밀번호가 일치 하지 않아요")
            }
        }
    }

    // 비밀번호 셋팅
    fun passwordSetting() {
        while (true) {
            try {
                println()
                print("설정할 비밀번호를 입력해주세요 : ")
                val password = readln().toIntOrNull() ?: 0

                print("한번 더 입력해주세요 : ")
                val againPassword = readln().toIntOrNull() ?: 0

                if (password == againPassword) {
                    val fos = FileOutputStream("password.dat")
                    val oos = DataOutputStream(fos)

                    oos.writeInt(password)
                    oos.flush()
                    oos.close()
                    fos.close()
                    password()
                    break
                } else {
                    println("다시 입력해주세요")
                }

            } catch (e: Exception) {
                println("잘못 입력하였습니다")
            }
        }
    }

    // 입력한 비밀번호 리턴
    fun readObjectStream(): Int {
        val fis = FileInputStream("password.dat")
        val dis = DataInputStream(fis)


        val readPassword = dis.readInt()
        dis.close()
        fis.close()

        return readPassword
    }

    private fun fileExists(filePath: String): Boolean {
        val file = File(filePath)
        return file.exists() // exists : 파일 존재 여부 확인 메서드
    }

}

// 카테고리를 읽는 함수
fun loadCategoryList(categoryList: MutableList<String>) {
    val categoryFile = File("categoryList.txt")

    if (categoryFile.exists()) { //만약 파일이 존재한다면
        try {
            val fis = FileInputStream(categoryFile)
            val ois = ObjectInputStream(fis)

            val loadedCategoryList = ois.readObject() as MutableList<String>

            categoryList.clear() // 기존의 categoryList를 비운다
            categoryList.addAll(loadedCategoryList) // 역직렬화된 카테고리 목록을 categoryList에 추가합니다.

            ois.close()
            fis.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

// 카테고리 리스트를 파일에 저장하는 함수
fun saveCategoryList(categoryList: MutableList<String>) {
    val categoryFile = File("categoryList.txt")

    try {
        val fos = FileOutputStream(categoryFile)
        val oos = ObjectOutputStream(fos)

        oos.writeObject(categoryList)

        oos.close()
        fos.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

// 카테고리 추가 함수
fun addCategory(categoryList: MutableList<String>) {
    print("등록할 카테고리 이름을 입력해주세요 : ")
    val categoryName = readln()

    val categoryFile = File("categoryName.txt")

    try {
        val fos = FileOutputStream(categoryFile)
        val oos = ObjectOutputStream(fos)

        oos.writeObject(categoryName)
        oos.close()
        fos.close()
        categoryList.add(categoryName) // 등록된 카테고리를 리스트에 추가
        saveCategoryList(categoryList)
        println("카테고리가 등록되었습니다.")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

// 카테고리 삭제
fun DeleteCategory(categoryList: MutableList<String>) {
    if (categoryList.isEmpty()) {
        println("삭제할 카테고리가 없어요.")
    } else {
        print("삭제할 카테고리 번호를 입력해주세요 : ")
        var categoryNum = readln().toInt()
        if (categoryNum in 0..categoryList.size) {
            categoryList.removeAt(categoryNum - 1)
            saveCategoryList(categoryList)
        } else {
            println("잘못 입력하였습니다. ")
        }
    }
}

fun EditCategory(categoryList: MutableList<String>) {
    if (categoryList.isEmpty()) {
        println("수정할 카테고리가 없어요.")
    } else {
        print("수정할 카테고리 번호를 입력해주세요 : ")
        var categoryNum = readln().toInt()
        if (categoryNum in 0..categoryList.size) {
            print("${categoryList[categoryNum - 1]} -> ")
            var newName = readln()
            categoryList[categoryNum - 1] = newName
            saveCategoryList(categoryList)
        } else {
            println("잘못 입력하였습니다. ")
        }
    }
}

// 메모 보기
fun viewMemo(categoryNum: Int, categoryList: MutableList<String>) {
    val categoryName = categoryList[categoryNum - 1]
    val memoFile = File("$categoryName.txt")

    val memoList = loadMemoList(memoFile)  // 선택된 카테고리의 메모 리스트를 불러옴

    if (memoList.isEmpty()) {
        println("메모가 없습니다.")
    } else {
        print("확인할 메모의 번호를 입력해주세요 (0. 이전) : ")
        val num = readln().toInt()

        if (num == 0) {
            return
        }

        if (num in 1..memoList.size) {
            println("+++++++++++++++++++++++++++++++++++++")
            println("제목 : ${memoList[num - 1].title}  ")
            println("내용 : ${memoList[num - 1].content}")
            println("+++++++++++++++++++++++++++++++++++++")
        } else {
            println("잘못된 번호입니다..")
        }
    }
}


// 메모 등록
fun addMemo(categoryNum: Int, categoryList: MutableList<String>) {
    val categoryName = categoryList[categoryNum - 1]
    val memoFile = File("$categoryName.txt")

    val existingMemos = loadMemoList(memoFile)

    print("메모 제목: ")
    val memoTitle = readln()
    print("메모 내용: ")
    val memoContent = readln()

    val memo = Memo(memoTitle, memoContent)
    existingMemos.add(memo)

    saveMemoList(memoFile, existingMemos)

    println("메모가 등록되었습니다.")

}

// 메모 저장용 data class 제목, 내용
data class Memo(var title: String, var content: String) : Serializable

// 메모 리스트 읽기
fun loadMemoList(file: File): MutableList<Memo> {
    val memoList = mutableListOf<Memo>()

    if (file.exists()) {
        try {
            val fis = FileInputStream(file)
            val ois = ObjectInputStream(fis)

            while (fis.available() > 0) {
                val memo = ois.readObject() as Memo
                memoList.add(memo)
            }

            ois.close()
            fis.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return memoList
}

// 파일에 메모 목록을 저장
fun saveMemoList(file: File, memos: MutableList<Memo>) {
    val fos = FileOutputStream(file)
    val oos = ObjectOutputStream(fos)

    for (memo in memos) {
        oos.writeObject(memo)
    }

    oos.close()
    fos.close()
}

// 메모 수정하기
fun editMemo(categoryNum: Int, categoryList: MutableList<String>) {
    val categoryName = categoryList[categoryNum - 1]
    val memoFile = File("$categoryName.txt")

    val memoList = loadMemoList(memoFile)

    if (memoList.isEmpty()) {
        println("수정할 메모가 없습니다.")
    } else {
        print("수정할 메모의 번호를 입력하세요 (0. 이전): ")
        val memoNum = readln().toInt()

        if (memoNum == 0) {
            return
        }

        if (memoNum in 1..memoList.size) {
            val selectedMemo = memoList[memoNum - 1]

            println("제목 : ${selectedMemo.title}")
            print("메모의 새로운 제목을 입력해주세요(0 입력시 무시) : ")
            val newTitle = readln()

            println("내용 : ${selectedMemo.content}")
            print("메모의 새로운 내용을 입력해주세요(0 입력시 무시) : ")
            val newContent = readln()

            // 0 누르면 기존꺼 아니면 새로운 거
            if (newTitle == "0") {
                selectedMemo.title = selectedMemo.title
            } else {
                selectedMemo.title = newTitle
            }

            if (newContent == "0") {
                selectedMemo.content = selectedMemo.content
            } else {
                selectedMemo.content = newContent
            }
            saveMemoList(memoFile, memoList)

        } else {
            println("메모 번호를 확인해 주세요.")
        }
    }
}

// 메모 삭제 함수
fun deleteMemo(categoryNum: Int, categoryList: MutableList<String>) {
    val categoryName = categoryList[categoryNum - 1]
    val memoFile = File("$categoryName.txt")

    val memoList = loadMemoList(memoFile)  // 선택된 카테고리의 메모 리스트를 불러옴

    if (memoList.isEmpty()) {
        println("삭제할 메모가 없습니다.")
    } else {
        print("삭제할 메모의 번호를 입력해주세요 (0. 이전) : ")
        val num = readln().toInt()

        if (num == 0) {
            return
        }

        if (num in 1..memoList.size) {
            memoList.removeAt(num - 1)
            saveMemoList(memoFile, memoList)
        } else {
            println("잘못된 번호입니다..")
        }
    }
}


// 프로그램 상태를 나타내는 enum
enum class ProgramPassWordMainState {
    // 상태를 나타내는 값들을 정의한다.
    PROGRAM_STATE_PASSWORD,
    PROGRAM_STATE_MAIN
}

//  메인 화면 상태를 나타내는 enum
enum class ProgramMainState(val itemNumber: Int) {
    // 상태를 나타내는 값들을 정의한다.
    PROGRAM_STATE_List(1),
    PROGRAM_STATE_SELECT_CATEGORY(2),
    PROGRAM_STATE_SEE_ALL_NOTES(3),
    PROGRAM_STATE_END(4)
}

// 카테고리 관리를 나타내는 enum
enum class ProgramSelectCategoryState(val itemNumber: Int) {
    // 상태를 나타내는 값들을 정의한다.
    PROGRAM_STATE_ADD_Category(1),
    PROGRAM_STATE_DELETE__Category(2),
    PROGRAM_STATE_EDIT__Category(3),
    PROGRAM_STATE_END(4)
}

// 카테고리 관리를 나타내는 enum
enum class ProgramChoiceCategoryState(val itemNumber: Int) {

    // 상태를 나타내는 값들을 정의한다.
    PROGRAM_STATE_VIEW_MEMO(1),
    PROGRAM_STATE_ADD_MEMO(2),
    PROGRAM_STATE_EDIT_MEMO(3),
    PROGRAM_STATE_DELETE_MEMO(4),
    PROGRAM_STATE_BEFORE(5),
}