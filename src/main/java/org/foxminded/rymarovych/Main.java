package org.foxminded.rymarovych;

import org.foxminded.rymarovych.onstartup.TablesCreator;
import org.foxminded.rymarovych.onstartup.tablefiller.TablesFiller;

public class Main {
    public static void main(String[] args) {
        new TablesCreator().createTables();
        new TablesFiller().fillTables();

        
    }
}