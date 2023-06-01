import java.io.*

// 메모 보기
fun viewMemo(categoryNum: Int, categoryList: MutableList<String>) {
    val categoryName = categoryList[categoryNum - 1]
    val memoFile = File("$categoryName.dat")

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
    val memoFile = File("$categoryName.dat")
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
    val memoFile = File("$categoryName.dat")

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
    val memoFile = File("$categoryName.dat")

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
