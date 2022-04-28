module com.example.demoj1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demoj1 to javafx.fxml;
    exports com.example.demoj1;
}