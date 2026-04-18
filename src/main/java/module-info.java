module dev.marekvoe.prakticka_02 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens dev.marekvoe.prakticka_02 to javafx.fxml;
    exports dev.marekvoe.prakticka_02;
}