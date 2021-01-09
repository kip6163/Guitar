package Project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The StringNumEntryBox is the window that pops up when you choose an alternative tuning, this window
 * asks the user how many strings their guitar has.
 *
 * @author Kaleb Pendleton
 */
public class StringNumEntryBox {

    public StringNumEntryBox(UserInterface ui){
        Stage subStage = new Stage();
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 200 );

        BorderPane pane = new BorderPane();
        VBox vbox = new VBox();
        Label whiteSpace = new Label("");
        Label label = new Label("How many strings does" + "\r\n" + "this guitar have?");
        TextField field = new TextField();
        HBox buttons = new HBox();

        Button B = new Button(" OK ");
        B.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                String stringNumAsString = field.getText();
                try{
                    int stringNum = Integer.parseInt(stringNumAsString);
                    if(stringNum < 1 || stringNum > 20){
                        ErrorBox box = new ErrorBox("Please enter a number between" + "\r\n" +
                                " 1 and 20 for string count");
                    }
                    else{
                        subStage.close();
                        ui.closeStage();
                        TuningBox tBox = new TuningBox(stringNum, ui);
                    }
                } catch (NumberFormatException e) {
                    ErrorBox box = new ErrorBox("Please enter a number");
                }
            }
        });

        Label space = new Label("       ");
        Button C = new Button(" Cancel ");
        C.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                subStage.close();
                UserInterface newInterface = new UserInterface();
            }
        });

        buttons.getChildren().addAll(B, space, C);

        vbox.getChildren().addAll(whiteSpace, buttons);
        pane.setTop(label);
        pane.setCenter(field);
        pane.setBottom(vbox);
        root.getChildren().add(pane);
        subStage.setScene(scene);
        subStage.show();
    }
}
