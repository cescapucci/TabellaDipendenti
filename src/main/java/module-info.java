module com.example.tabdipendentilibro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tabdipendentilibro to javafx.fxml;
    exports com.example.tabdipendentilibro;
}