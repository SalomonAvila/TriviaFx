module com.example.triviafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.triviafx to javafx.fxml;
    exports com.example.triviafx;
}
