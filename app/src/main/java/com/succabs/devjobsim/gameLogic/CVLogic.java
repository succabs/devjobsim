package com.succabs.devjobsim.gameLogic;

import com.succabs.devjobsim.ui.GameUI;
import com.succabs.devjobsim.player.PlayerStats;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, List<Field>> fieldsByType = new LinkedHashMap<>(); // Use LinkedHashMap to preserve insertion order

        for (CVEntry entry : entries) {
            String type = entry.getType();
            fieldsByType.putIfAbsent(type, new ArrayList<>());
            fieldsByType.get(type).addAll(entry.getFields());
        }

        for (Map.Entry<String, List<Field>> entry : fieldsByType.entrySet()) {
            cvContent.append(entry.getKey()).append("\n");
            for (Field field : entry.getValue()) {
                cvContent.append(field.getName()).append(": ").append(field.getValue()).append("\n");
            }
            cvContent.append("----------------------\n");
        }

        gameUI.updateCVScreen(cvContent.toString());
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
