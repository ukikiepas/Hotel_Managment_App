package com.example.siechoteli.klient.rooms;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * RoomsSpectraController  switch photos with 2s delay
 * Multithreading is used here
 * @author Lukasz Kiepas
 */
public class RoomsSpectraController  implements Initializable{

    @FXML
    private ImageView imageSlider;

    /**
     * String images[] is an array of paths to photos to switch automatically between.
     */
    String images[] = {"/com/example/siechoteli/images/SpectraRoom1.jpg", "/com/example/siechoteli/images/SpectraRoom2.jpg", "/com/example/siechoteli/images/SpectraRoom3.jpg"};

    private Image loadImage(String filename) throws IOException {
        try (InputStream is = getClass().getResourceAsStream(filename)) {
            if (is == null) {
                throw new IOException("InputStream is invalid for filename " + filename);
            }
            return new Image(is);
        }
    }

    int i = 0;

    /**
     * Here we create new instance of SliderThread class and start it.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SliderThread sliderThread = new SliderThread();
        sliderThread.start();

    }

    /**
     * This class switch display different photos using array of Strings -> String images[]
     * We set a delay here -> sleep(2000) - > 2s
     * Second thread is compulsory. Otherwise other main app would be blocked.
     */
    class SliderThread extends Thread {
        @Override
        public void run(){
            try{
                while(true){
                    if(i > 2){
                        i = 0;
                    }

                    Image img  = null;
                    try {
                        img = loadImage(images[i]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    imageSlider.setImage(img);
                    i++;
                    sleep(2000);

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

