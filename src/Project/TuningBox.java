package Project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Windows for the user to enter the tunings of each string on their guitar if they aren't using a preset
 * tuning configuration.
 *
 * @author Kaleb Pendleton
 */
public class TuningBox {

    public TuningBox(int stringNum, UserInterface ui){
        Stage subStage = new Stage();
        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        List<TextField> fields = new ArrayList<>();
        HashSet<String> validKeys = validInputs();
        Scene scene = new Scene(root);
        VBox box =  new VBox();
        HBox hbox = new HBox();

        Label instructions = new Label("Enter what each string on your" +  "\r\n" + "guitar is "  +
                "tuned to, with String #1" + "\r\n" +  "being the lowest(thickest) string." + "\r\n" +
                "Notes should be capital letters (ex: A)" + "\r\n" + "and use the symbols '#' and 'b' " +
                "\r\n" + "for sharps and flats.");

        box.getChildren().addAll(new Label(""), instructions);

        for(int i = 0; i < stringNum; i++){
            if(i == 0){
                box.getChildren().add(new Label(""));
            }
            int temp = i + 1;
            Label label = new Label("String #" + temp);
            TextField field = new TextField();
            fields.add(field);
            box.getChildren().addAll(label, field);
            if(temp == stringNum){
                box.getChildren().add(new Label(""));
            }
        }
        Button B = new Button(" Run ");
        B.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<GuitarString> strings = new ArrayList<>();
                int counter = 1;
                boolean valid = true;
                for(TextField entry: fields){
                    if(!(validKeys.contains(entry.getText()))){
                        ErrorBox errorBox = new ErrorBox("Invalid input on string #" + counter +
                                ": " + entry.getText());
                        valid = false;
                        break;
                    }
                    else{
                        switch(entry.getText()){
                            case "Ab":
                                Note temp = new Note("G#", 2);
                                strings.add(new GuitarString(temp));
                                break;
                            case "Bb":
                                Note temp2 = new Note("A#", 2);
                                strings.add(new GuitarString(temp2));
                                break;
                            case "Db":
                                Note temp3 = new Note("C#", 2);
                                strings.add(new GuitarString(temp3));
                                break;
                            case "Eb":
                                Note temp4 = new Note("D#", 2);
                                strings.add(new GuitarString(temp4));
                                break;
                            case "Gb":
                                Note temp5 = new Note("F#", 2);
                                strings.add(new GuitarString(temp5));
                                break;
                            default:
                                Note temp6 = new Note(entry.getText(), 2);
                                strings.add(new GuitarString(temp6));
                        }
                    }
                    counter++;
                }
                if(valid){
                    subStage.close();
                    Guitar application = new Guitar(strings, false);
                }
            }
        });

        Button C = new Button("Cancel");
        C.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                subStage.close();
                ui.openStage();
            }
        });

        hbox.getChildren().addAll(B, new Label("                    "), C );
        box.getChildren().addAll(hbox, new Label(""));
        root.getChildren().add(box);
        subStage.setScene(scene);
        subStage.show();
    }




    public HashSet<String> validInputs(){
        HashSet<String> valid = new HashSet<>();
        valid.add("A");
        valid.add("A#");
        valid.add("Bb");
        valid.add("B");
        valid.add("C");
        valid.add("C#");
        valid.add("Db");
        valid.add("D");
        valid.add("D#");
        valid.add("Eb");
        valid.add("E");
        valid.add("F");
        valid.add("F#");
        valid.add("Gb");
        valid.add("G");
        valid.add("G#");
        valid.add("Ab");
        return valid;
    }
}
