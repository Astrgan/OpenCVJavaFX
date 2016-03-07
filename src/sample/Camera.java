package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import java.nio.ByteBuffer;

/**
 * Created by astrg_000 on 07.03.2016.
 */
public class Camera {

    CanvasResizable canvas;
    PixelWriter pixelWriter;
    Mat frame;
    VideoCapture camera;
    final PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteRgbInstance();
    byte[] byteArray;
    int width,height, channels;
    boolean flag = true;

    public Camera() {
        canvas = new CanvasResizable();
        frame = new Mat();
        pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        camera = new VideoCapture(0);
        canvas.heightProperty().addListener(e -> newSize());
        canvas.widthProperty().addListener(e -> newSize());
        camera.read(frame);
        byteArray = new byte[width * height * channels];

    }

    void newSize(){
//        System.out.println("Width: " + canvas.getWidth() + " Height:  " + canvas.getHeight());

        width = (int) canvas.getWidth();
        height = (int) canvas.getHeight();
        channels = frame.channels();
        byteArray = new byte[width * height * channels];

    }

    public void Process(){
        if (camera.isOpened()){
//            int width = this.width;
//            int height = this.height;
//            byteArray = new byte[width * height * channels];
            camera.read(frame);
            Imgproc.resize(frame, frame, new Size(width, height));
            frame.get(0, 0, byteArray);
            pixelWriter.setPixels(0, 0, frame.width(), frame.height(), pixelFormat, byteArray, 0, frame.width() * frame.channels());
        }else {
            System.out.println("Error");
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
