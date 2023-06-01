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