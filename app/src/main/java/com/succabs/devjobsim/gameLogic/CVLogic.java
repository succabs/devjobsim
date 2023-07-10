package com.succabs.devjobsim.gameLogic;

import com.succabs.devjobsim.ui.GameUI;
import com.succabs.devjobsim.player.PlayerStats;

import java.util.ArrayList;
import java.util.List;

public class CVLogic {
    private static List<CVEntry> entries = new ArrayList<>();

    // Add a CVEntry to the list
    public static void addEntry(CVEntry entry) {
        entries.add(entry);
    }

    // Get the list of CVEntries
    public static List<CVEntry> getEntries() {
        return entries;
    }

    public static void handleCVButton(GameUI gameUI, PlayerStats playerStats) {
        StringBuilder cvContent = new StringBuilder();
        cvContent.append(playerStats.getName() + "\n");

        for (CVEntry entry : entries) {
            for (String fieldName : entry.getFieldNames()) {
                cvContent.append(fieldName).append(": ").append(entry.getField(fieldName)).append("\n");
            }
            cvContent.append("----------------------\n");
        }

        gameUI.updateGameScreen(cvContent.toString());
    }
}

class CVEntry {
    private final String type;
    private final List<Field> fields;

    public CVEntry(String type) {
        this.type = type;
        this.fields = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void addField(String name, String value) {
        fields.add(new Field(name, value));
    }

    public List<String> getFieldNames() {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    public String getField(String name) {
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                return field.getValue();
            }
        }
        return null;
    }
}

class Field {
    private final String name;
    private final String value;

    public Field(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
