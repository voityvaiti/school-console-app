package org.foxminded.rymarovych.onstartup.tablefiller;

import java.util.Random;

abstract class TableFiller {
    protected final Random random = new Random();

    protected final String STATEMENT_ELEM_START = "\n (";
    protected final String STATEMENT_ELEM_END = "),";
    protected final String STATEMENT_ENDING = ";";
    protected final String STATEMENT_GAP = ", ";
    protected final int STUDENTS_AMOUNT = 200;
    protected final int COURSES_AMOUNT = 10;
    protected final int GROUPS_AMOUNT = 10;

    abstract protected String generateStatement();
}
