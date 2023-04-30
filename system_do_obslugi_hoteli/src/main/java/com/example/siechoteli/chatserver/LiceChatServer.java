

package com.example.siechoteli.chatserver;

        import com.example.siechoteli.klient.mainapp.RelaxHmsApp;
        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.image.Image;
        import javafx.stage.Stage;

        import java.io.IOException;


/**
  * This is a main class of our LiveChatServer app.
 *  Only thing it does is loading our fxml file into stage and then showing scene to get
 *  LIVE CHAT GUI
  */
public class LiceChatServer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RelaxHmsApp.class.getResource("/com/example/siechoteli/livechat/sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        String icon = this.getClass().getResource("/com/example/siechoteli/images/LOGO.png").toExternalForm();
        stage.getIcons().add(new Image(icon));

        stage.setTitle("LiveChat - Consultant");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
