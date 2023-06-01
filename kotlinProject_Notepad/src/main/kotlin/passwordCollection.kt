import java.io.*

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
                val password = readln().toIntOrNull() ?: continue

                print("한번 더 입력해주세요 : ")
                val againPassword = readln().toIntOrNull() ?: continue

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

    // 파일 존재 여부 확인
    private fun fileExists(filePath: String): Boolean {
        val file = File(filePath)
        return file.exists()
    }

}