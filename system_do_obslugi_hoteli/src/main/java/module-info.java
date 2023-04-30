module com.example.siechoteli {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.siechoteli to javafx.fxml;
    exports com.example.siechoteli.server;
    opens com.example.siechoteli.server to javafx.fxml;
    exports com.example.siechoteli.klient.hotels;
    opens com.example.siechoteli.klient.hotels to javafx.fxml;
    exports com.example.siechoteli.klient.guests;
    opens com.example.siechoteli.klient.guests to javafx.fxml;
    exports com.example.siechoteli.klient.helpscene;
    opens com.example.siechoteli.klient.helpscene to javafx.fxml;
    exports com.example.siechoteli.klient.rooms;
    opens com.example.siechoteli.klient.rooms to javafx.fxml;
    exports com.example.siechoteli.klient.login;
    opens com.example.siechoteli.klient.login to javafx.fxml;
    exports com.example.siechoteli.klient.mainscene;
    opens com.example.siechoteli.klient.mainscene to javafx.fxml;
    exports com.example.siechoteli.klient.mainapp;
    opens com.example.siechoteli.klient.mainapp to javafx.fxml;
    exports com.example.siechoteli.chatserver;
    opens com.example.siechoteli.chatserver to javafx.fxml;
    exports com.example.siechoteli.klient.klientlivechat;
    opens com.example.siechoteli.klient.klientlivechat to javafx.fxml;
}