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
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller {

    @FXML
    private Pane root;
    Canvas canvas;
    Camera camera;
    final PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteRgbInstance();
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    Future<?> future;

    @FXML
    void action(ActionEvent event) {
        camera.Process();
    }


    @FXML
    public void initialize(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        camera = new Camera();
        canvas = camera.getCanvas();
        root.getChildren().add(canvas);
        root.heightProperty().addListener(e->pause());
        future = service.scheduleAtFixedRate((Runnable) () -> camera.Process(),1000, 35, TimeUnit.MILLISECONDS);

    }

    void pause(){
        future.cancel(true);
        future = service.scheduleAtFixedRate((Runnable) () -> camera.Process(),1000, 35, TimeUnit.MILLISECONDS);
    }
}
