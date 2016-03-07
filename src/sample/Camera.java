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

    Canvas canvas;
    PixelWriter pixelWriter;
    Mat frame;
    VideoCapture camera;
    final PixelFormat<ByteBuffer> pixelFormat = PixelFormat.getByteRgbInstance();
    byte[] byteArray;

    public Camera() {
        canvas = new Canvas();
        frame = new Mat();
        pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();
        camera = new VideoCapture(0);


    }

    void newSize(){

    }

    public void Process(){
        if (camera.isOpened()){
            camera.read(frame);
            Imgproc.resize(frame, frame, new Size(canvas.getWidth(), canvas.getHeight()));
            byte[] byteArray = new byte[(int) frame.total() * frame.channels()];
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
