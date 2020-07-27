package comp3350.Group2.areyouhungry.ui.preferred;

import android.provider.BaseColumns;

public final class SearchContract {

    private SearchContract(){}


    /* Base columns provide id for table. */
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "search_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NUMBER = "answerNumber";
    }


}
