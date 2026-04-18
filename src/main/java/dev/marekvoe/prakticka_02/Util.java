package dev.marekvoe.prakticka_02;

import javafx.scene.control.Alert;

public class Util {

    public static void zobrazitAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
