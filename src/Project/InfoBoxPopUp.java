package Project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Informational windows that is available once a guitar has been setup, basically explains what
 * the purpose of the program is.
 *
 * @author Kaleb Pendleton
 */
public class InfoBoxPopUp {

    public InfoBoxPopUp(){
        Stage subStage = new Stage();
        subStage.setTitle("Information");
        subStage.getIcons().add(new Image(getClass().getResourceAsStream("images/info.png")));
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 320, 400 );

        BorderPane pane = new BorderPane();
        BorderPane subPane = new BorderPane();
        Label error = new Label("This program is designed to show the user where " + "\r\n" +
                                   "the notes in a given scale are on the fretboard." + "\r\n" +
                                   "Do you want to be able to see where all the notes in" + "\r\n" +
                                   "the A minor scale are on a guitar tuned to Drop C?" + "\r\n" +
                                   "Well you can use this program to do that by selecting" + "\r\n" +
                                   "Drop C in the configuration menu, hitting the run button," + "\r\n" +
                                   "selecting A as the key, minor as the scale, and clicking" + "\r\n" +
                                   "the 'View this scale' button." + "\r\n" + "\r\n" +
                                   "The goal of this program is to help the user try new" + "\r\n" +
                                   "scales, and to gain a better understanding of the scales" + "\r\n" +
                                   "they already know.");
        Button B = new Button(" OK ");
        B.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                subStage.close();
            }
        });
        pane.setCenter(error);
        subPane.setTop(new Label("\r\n" + "\r\n"));
        subPane.setCenter(B);
        pane.setBottom(subPane);
        root.getChildren().add(pane);
        subStage.setScene(scene);
        subStage.show();
    }
}
