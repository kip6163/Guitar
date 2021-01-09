package Project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * ErrorBox class is used to create a pop up window indicating that the user has done something wrong and needs to
 * try inputting something else. As of right now this can only show up through the alternative tuning menu.
 *
 * @author Kaleb Pendleton
 */
public class ErrorBox {

    public ErrorBox(String errorMessage) {
        Stage subStage = new Stage();
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 200 );

        BorderPane pane = new BorderPane();
        BorderPane subPane = new BorderPane();
        Label error = new Label(errorMessage);
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
