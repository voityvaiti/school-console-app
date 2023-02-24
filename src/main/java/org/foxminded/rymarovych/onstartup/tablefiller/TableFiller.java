package org.foxminded.rymarovych.onstartup.tablefiller;

import java.util.Random;

public abstract class TableFiller {
    protected final Random random = new Random();

    protected static final String STATEMENT_ELEM_START = "\n (";
    protected static final String STATEMENT_ELEM_END = "),";
    protected static final String STATEMENT_ENDING = "; END IF; END $$;";
    protected static final String STATEMENT_GAP = ", ";
    public static final int STUDENTS_AMOUNT = 200;
    public static final int COURSES_AMOUNT = 10;
    public static final int GROUPS_AMOUNT = 10;

    protected abstract String generateStatement();
}
