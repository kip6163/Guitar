package Project;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

public class UserInterface extends Application {

    private static List<GuitarString> guitar;
    private static int selection;
    private Stage stage;

    public static void main(String[] args){
        selection = 0;
        Application.launch();
    }

    public static void selectionFinder(){
        switch(selection){
            case 1:
                guitar = EStandard();
                break;

            case 2:
                guitar = DropD();
                break;

            case 3:
                guitar = DStandard();
                break;

            case 4:
                guitar = DropC();
                break;
        }
    }

    public static ArrayList<GuitarString> EStandard(){
        ArrayList<GuitarString> strings = new ArrayList<>(6);
        strings.add(new GuitarString(new Note("E",2)));
        strings.add(new GuitarString(new Note("A",3)));
        strings.add(new GuitarString(new Note("D",3)));
        strings.add(new GuitarString(new Note("G",3)));
        strings.add(new GuitarString(new Note("B",4)));
        strings.add(new GuitarString(new Note("E",4)));
        return strings;
    }

    public static ArrayList<GuitarString> DStandard(){
        ArrayList<GuitarString> strings = new ArrayList<>(6);
        strings.add(new GuitarString(new Note("D",2)));
        strings.add(new GuitarString(new Note("G",2)));
        strings.add(new GuitarString(new Note("C",3)));
        strings.add(new GuitarString(new Note("F",3)));
        strings.add(new GuitarString(new Note("A",4)));
        strings.add(new GuitarString(new Note("D",4)));
        return strings;
    }

    public static ArrayList<GuitarString> DropD(){
        ArrayList<GuitarString> strings = new ArrayList<>(6);
        strings.add(new GuitarString(new Note("D",2)));
        strings.add(new GuitarString(new Note("A",3)));
        strings.add(new GuitarString(new Note("D",3)));
        strings.add(new GuitarString(new Note("G",3)));
        strings.add(new GuitarString(new Note("B",4)));
        strings.add(new GuitarString(new Note("E",4)));
        return strings;
    }

    public static ArrayList<GuitarString> DropC(){
        ArrayList<GuitarString> strings = new ArrayList<>(6);
        strings.add(new GuitarString(new Note("C",2)));
        strings.add(new GuitarString(new Note("G",2)));
        strings.add(new GuitarString(new Note("C",3)));
        strings.add(new GuitarString(new Note("F",3)));
        strings.add(new GuitarString(new Note("A",4)));
        strings.add(new GuitarString(new Note("D",4)));
        return strings;
    }



    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Guitar Project");
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root);
        BorderPane pane = new BorderPane();
        VBox vbox = new VBox();
        Label instructions = new Label("\r\n" + "   Select your desired tuning configuration below and hit the Run " +
                "button to continue   " + "\r\n");
        Label currentTuning = new Label("Configuration: " + "No tuning configuration currently selected");

        Button eStandard = new Button("E Standard" + "\r\n" + "(E,A,D,G,B,E)");
        eStandard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentTuning.setText("Configuration: " + "E Standard");
                selection = 1;
            }
        });

        Button dStandard = new Button("D Standard" + "\r\n" + "(D,G,C,F,A,D)");
        dStandard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentTuning.setText("Configuration: " + "D Standard");
                selection = 3;
            }
        });

        Button dropD = new Button("Drop D" + "\r\n" + "(D,A,D,G,B,E)");
        dropD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentTuning.setText("Configuration: " + "Drop D");
                selection = 2;
            }
        });

        Button dropC = new Button("Drop C" + "\r\n" + "(C,G,C,F,A,D)");
        dropC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentTuning.setText("Configuration: " + "Drop C");
                selection = 4;
            }
        });

        Button custom = new Button("alternative" + "\r\n" + "tuning menu");
        UserInterface ui = this;
        custom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StringNumEntryBox popUp = new StringNumEntryBox(ui);
            }
        });

        Button ok = new Button(" Run ");
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(selection != 0){
                    stage.close();
                    selectionFinder();
                    Guitar application = new Guitar(guitar, true);
                }
                else{
                    ErrorBox box = new ErrorBox("ERROR: no tuning configuration currently selected");
                }
            }
        });

        VBox bufferHeight = new VBox();
        HBox bufferSpace = new HBox();
        Label moreSpace = new Label("");
        Label space = new Label("                                                               ");
        bufferSpace.getChildren().addAll(space, ok);
        bufferHeight.getChildren().addAll(bufferSpace, moreSpace);
        vbox.getChildren().addAll(eStandard, dStandard, dropD, dropC, custom);
        pane.setLeft(vbox);
        pane.setTop(instructions);
        pane.setCenter(currentTuning);
        pane.setBottom(bufferHeight);

        root.getChildren().add(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void closeStage(){
        stage.close();
    }

    public void openStage(){
        stage.show();
    }
}
