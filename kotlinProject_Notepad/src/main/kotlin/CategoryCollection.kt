import java.io.*

// 카테고리를 읽는 함수
fun loadCategoryList(categoryList: MutableList<String>) {
    val categoryFile = File("categoryName.dat")

    //만약 파일이 존재한다면
    if (categoryFile.exists()) {
        try {
            val fis = FileInputStream(categoryFile) //파일 열기
            val ois = ObjectInputStream(fis) // 파일에서 객체 읽기

            val loadedCategoryList = ois.readObject() as MutableList<String>

            categoryList.clear() // 기존의 categoryList를 비운다
            categoryList.addAll(loadedCategoryList) // 카테고리 목록을 categoryList에 추가합니다.

            ois.close()
            fis.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

// 카테고리 리스트를 파일에 저장하는 함수
fun saveCategoryList(categoryList: MutableList<String>) {
    val categoryFile = File("categoryName.dat")

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

    val categoryFile = File("categoryName.dat")

    try {
        val fos = FileOutputStream(categoryFile)  // 파일쓰기
        val oos = ObjectOutputStream(fos) // 객체 직렬화하여 스트림으로 쓰다.

        oos.writeObject(categoryName) // 파일쓰기
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

// 수정하기
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

