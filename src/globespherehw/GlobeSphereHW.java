package globespherehw;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Scanner;

public class GlobeSphereHW extends Application {

    private static Scanner input = new Scanner(System.in);
    private static int FOV;
    private static int xCoord;
    private static int yCoord;
    private static int zCoord;
    private static double Time;

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();

        //root.getChildren().add(btn);
        Scene scene = new Scene(root, 300, 300);
        PerspectiveCamera camera = new PerspectiveCamera(true);

        //Backs the camera away from the scene by 1000 units
        camera.setTranslateZ(-1000);  //-1000 makes it smaller or bigger

        //This is the range of which the camera will render objects. (not sure but maybe makes things clearer
        camera.setNearClip(1); //0.1
        camera.setFarClip(5000.0); //2000.0

        //The default field of view for the scene is 30 but change to suit
        camera.setFieldOfView(FOV); //30 //makes bigger or smaller (the bigger the number the smaller it gets)
        scene.setCamera(camera);

        //This sets up my sphere
        Sphere mysphere = new Sphere(200); //200
        mysphere.setTranslateX(xCoord); //-180 x axis
        mysphere.setTranslateY(yCoord); //-100 y axis
        mysphere.setTranslateZ(zCoord); //100 z axis
        root.getChildren().add(mysphere);

        //This sets up the image of the earth to wrap around my sphere
        Image earthImage = new Image("file:earth.jpg");
        PhongMaterial earthPhong = new PhongMaterial();
        earthPhong.setDiffuseMap(earthImage);
        mysphere.setMaterial(earthPhong);

        //This rotates my sphere
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(mysphere);
        rotate.setDuration(Duration.seconds(Time)); //5000 changed to secs when it was orginally millisecs
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.setByAngle(-360); //-360
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.play();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void GetFOV() {
            System.out.println("What would you like the field of view to be? :");
            FOV = input.nextInt();
    }

    public static void GetCoords() {
       
            System.out.println("Please enter the x coordinate you would like the globe to appear on :");
            xCoord = input.nextInt();
            System.out.println("Please enter the y coordinate :");
            yCoord = input.nextInt();
            System.out.println("Please enter the z coordinate:");
            zCoord = input.nextInt();

    }

    public static void GetTime() {
        
            System.out.println("Please enter the time it will take for the globe to do one full spin in seconds:");
            Time = input.nextInt();
    }

    public static void main(String[] args) {
        System.out.println("Let's set up your globe!");
        GetFOV();
        GetCoords();
        GetTime();
        System.out.println("Your globe has been made!");
        launch(args);

    }

}
