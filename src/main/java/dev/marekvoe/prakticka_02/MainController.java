package dev.marekvoe.prakticka_02;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Databaze databaze;

    @FXML
    private TableView<ObservableList<String>> mainTableView;

    @FXML
    private HBox filtryContainer;

    @FXML
    private ComboBox<Zobrazeni> zobrazeniComboBox;

    @FXML
    private HBox ctenariFiltryContainer;

    private ObservableList<Zobrazeni> zobrazeniList = FXCollections.observableArrayList();

    @FXML
    private ToggleGroup tgCtenarFiltry;

    @FXML
    private TextField filtrField;

    private Zobrazeni aktualniZobrazeni;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.databaze = new Databaze();
        this.zobrazeniList.addAll(Zobrazeni.DOSTUPNE_KNIHY, Zobrazeni.VYPUJCKY, Zobrazeni.CTENARI, Zobrazeni.NEVYPUJCENE);
        zobrazeniComboBox.setItems(zobrazeniList);
    }

    private void loadTable(String sql) {
        mainTableView.getColumns().clear();
        mainTableView.getItems().clear();

        try {
            ResultSet rs = databaze.getData(sql);
            ResultSetMetaData meta = rs.getMetaData();

            int pocetSloupcu = meta.getColumnCount();

            for (int i = 0; i < pocetSloupcu; i++) {
                TableColumn<ObservableList<String>, String> col = new TableColumn<>(meta.getColumnName(i + 1));
                final int index = i;

                col.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(index)));

                mainTableView.getColumns().add(col);
            }

            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();

                for (int i = 1; i <= pocetSloupcu; i++) {
                    String value = rs.getString(i);

                    if (value == null)
                        value = "";

                    row.add(value);
                }
                data.add(row);
            }

            mainTableView.setItems(data);

        } catch(SQLException e) {
            Util.zobrazitAlert("ERROR - Nepovedlo se načíst data do tabulky", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onZobrazeniChange(ActionEvent event) {
        this.aktualniZobrazeni = zobrazeniComboBox.getSelectionModel().getSelectedItem();

        if (aktualniZobrazeni != null) {
            filtryContainer.setVisible(true);
            switch (aktualniZobrazeni) {
                case DOSTUPNE_KNIHY:
                    mainTableView.setVisible(true);
                    loadTable("SELECT * FROM knihy");
                    ctenariFiltryContainer.setVisible(false);
                    break;

                case VYPUJCKY:
                    mainTableView.setVisible(true);
                    loadTable("SELECT c.jmeno, c.prijmeni, k.nazev, v.datum_vypujcky FROM vypujcky v JOIN ctenari c ON v.id_ctenar = c.id_ctenar JOIN knihy k ON v.id_kniha = k.id_kniha");
                    ctenariFiltryContainer.setVisible(false);
                    break;

                case CTENARI:
                    mainTableView.setVisible(true);
                    loadTable("SELECT * FROM ctenari");
                    ctenariFiltryContainer.setVisible(true);
                    break;

                case NEVYPUJCENE:
                    mainTableView.setVisible(true);
                    loadTable("SELECT * FROM knihy WHERE dostupna = 1");
                    ctenariFiltryContainer.setVisible(false);
            }
        }
    }

    @FXML
    void onTextFieldChange(KeyEvent event) {
        if (aktualniZobrazeni == Zobrazeni.CTENARI && tgCtenarFiltry.getSelectedToggle() != null) {
            RadioButton selectedToggle = (RadioButton) tgCtenarFiltry.getSelectedToggle();
            switch (selectedToggle.getText().trim().toLowerCase()) {
                case "podle jména":
                    loadTable("SELECT * FROM ctenari WHERE jmeno LIKE '%" + filtrField.getText().trim() + "%'");
                    break;

                case "podle příjmení":
                    loadTable("SELECT * FROM ctenari WHERE prijmeni LIKE '%" + filtrField.getText().trim() + "%'");
                    break;

                case "podle města":
                    loadTable("SELECT * FROM ctenari WHERE mesto LIKE '%" + filtrField.getText().trim() + "%'");
                    break;
            }
        }
    }
}