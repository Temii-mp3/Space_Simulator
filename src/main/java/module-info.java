module com.example.space_simulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.space_simulator to javafx.fxml;
    exports com.example.space_simulator;
}