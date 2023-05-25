fun main() {
    val ticTacToc = TicTacToe()
    ticTacToc.play()

}


// 구현할 목록
// 1. 이긴 경우 확인하기 가로,세로, 대각선  : o
// 2. 게임판이 가득차면 무승부 : o
// 3. O 두고 X 번갈아 가기 : o
// 4. 유효검 검사 : o

class TicTacToe {
    private val board = Array(3) { CharArray(3) { ' ' } }
    private var turn = 1
    private var player = 'O'

    fun play() {
        println("☆ Tic-Tac-Toe 게임을 시작합니다 ☆")
        while (true) {
            printBoard()

            try {
                if (player == 'O') {
                    // 플레이어 O 입력받기
                    print("Player1 입력(줄,칸) : ")
                    val input = readlnOrNull()?.replace(" ", "")?.trim() // (" ", "") 형식으로 입력받고 공백 제거
                    val row = input?.get(0)?.toString()?.toIntOrNull()// 입력된 문자열에서 첫 번째 문자를 추출하고, 추출된 문자를 문자열로 변환한 뒤 정수로 변환
                    val col = input?.get(2)?.toString()?.toIntOrNull()
                    // 유효성 검사
                    if (row == null || col == null || row !in 0..2 || col !in 0..2) {
                        println("유효하지 않은 입력입니다. 다시 입력하세요.")
                        continue
                    }
                    makeMove(row, col)
                    if (checkWin(row, col)) {
                        println("★☆Player1이 이겼습니다! ☆★")
                        break
                    }

                    if(checkDraw()){
                        println("무승부")
                        break
                    }
                    print("$turn 번째 턴")
                } else if (player == 'X') {
                    // 플레이어 X 입력받기
                    print("Player2 입력(줄,칸) : ")
                    val input = readlnOrNull()?.replace(" ", "")?.trim() // (" ", "") 형식으로 입력받고 공백 제거
                    val row = input?.get(0)?.toString()?.toIntOrNull()// 입력된 문자열에서 첫 번째 문자를 추출하고, 추출된 문자를 문자열로 변환한 뒤 정수로 변환
                    val col = input?.get(2)?.toString()?.toIntOrNull()
                    // 유효성 검사
                    if (row == null || col == null || row !in 0..2 || col !in 0..2) {
                        println("유효하지 않은 입력입니다. 다시 입력하세요.")
                        continue
                    }
                    makeMove(row, col)
                    if (checkWin(row, col)) {
                        println("★☆Player2가 이겼습니다! ☆★")
                        break
                    }

                    if(checkDraw()){
                        println("무승부")
                        break
                    }
                    print("$turn 번째 턴")
                }
            } catch (e: Exception) {
                println("$e ")
                continue
            }

            println()
            turn += 1
            player = if (player == 'O') 'X' else 'O' // O, X 체인지
        }
    }

    private fun printBoard() {
        println("  0 1 2 ")
        for (i in 0 until 3) {
            print("$i ")

            for (j in 0 until 3) {
                print("${board[i][j]}")
                if (j == 2) {
                    continue
                }
                print("|")
            }
            println()
            if (i == 2) {
                continue
            }
            println("  -+-+-")
        }
    }

    private fun makeMove(row: Int, col: Int) {
        try {
            if (board[row][col] != ' ') {
                throw IllegalArgumentException("해당 위치는 이미 선택되었습니다. 다른 위치를 선택하세요.")
            } else {
                board[row][col] = player // 보드판에 플레이어 O 또는 X 표시
            }
        } catch (e: IllegalArgumentException) { //적합하지 않거나(illegal) 적절하지 못한(inappropriate) 인자를 메소드에 넘겨주었을 때 발생
            println(e)
            if (player == 'O') {
                print("Player1 입력(줄,칸) : ")
            } else {
                print("Player2 입력(줄,칸) : ")
            }
            val input = readlnOrNull()?.replace(" ", "")?.trim()
            val newRow = input?.get(0)?.toString()?.toIntOrNull()
            val newCol = input?.get(2)?.toString()?.toIntOrNull()

            // 유효성 검사
            if (newRow == null || newCol == null || newRow !in 0..2 || newCol !in 0..2) {
                println("유효하지 않은 입력입니다. 다시 입력하세요 : ")
                makeMove(row, col) // 재귀 호출로 다시 입력받기
            } else {
                makeMove(newRow, newCol)
            }
        }
    }

    // 승리 조건
    // 가로, 세로 , 대각선 플레이어서 3칸을 먹으면 승리
    private fun checkWin(row: Int, col: Int): Boolean {
        // 가로 확인


        if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
            return true
        }

        // 세로 확인
        if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
            return true
        }

        // 대각선 확인
        if (row == col && board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true
        }
        return row + col == 2 && board[0][2] == player && board[1][1] == player && board[2][0] == player
    }

    // 무승부 조건
    // 모든 칸이 채워져있으면 무승부
    private fun checkDraw(): Boolean {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (board[i][j] == ' ') {
                    return false
                }
            }
        }
        return true
    }

}


