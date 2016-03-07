package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.Pane;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    @FXML
    private Pane root;
    Canvas canvas;
    Camera camera;
    final PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteRgbInstance();
    Timer timer = new Timer();

    @FXML
    void action(ActionEvent event) {
        camera.Process();
    }


    @FXML
    public void initialize(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        camera = new Camera();
        canvas = camera.getCanvas();
        canvas.setWidth(730);
        canvas.setHeight(700);
        root.getChildren().add(canvas);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                camera.Process();
            }
        },35,35);



    }
}
