package Project;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.io.File;
import java.util.*;

public class Guitar {

    private final List<GuitarString> strings;
    private String key;
    private final HashMap<String, List<String>> scaleMap;
    private boolean sound;


    public Guitar(List<GuitarString> strings, boolean sound){
        this.sound = sound;
        this.strings = strings;
        //Major Scale
        List<String> majorScale = createScale("1", "0", "1", "0", "1", "1", "0", "1", "0", "1", "0", "1");

        //Harmonic Minor Scale
        List<String> harmonicMinorScale = createScale("1", "0", "1", "1", "0", "1", "0", "1", "1", "0", "0", "1");

        //Pentatonic Minor Scale
        List<String> pentatonicMinorScale = createScale("1", "0", "0", "1", "0", "1", "0", "1", "0", "0", "1", "0");

        //Minor Scale
        List<String> minorScale = createScale("1", "0", "1", "1", "0", "1", "0", "1", "1", "0", "1", "0");

        //Pentatonic Major Scale
        List<String> pentatonicMajorScale = createScale("1", "0", "1", "0", "1", "0", "0", "1", "0", "1", "0", "0");

        //Dorian Scale
        List<String> dorianScale = createScale("1", "0", "1", "1", "0", "1", "0", "1", "0", "1", "1", "0");

        //Phrygian Scale
        List<String> phrygianScale = createScale("1", "1", "0", "1", "0", "1", "0", "1", "1", "0", "1", "0");

        //Lydian Scale
        List<String> lydianScale = createScale("1", "0", "1", "0", "1", "0", "1", "1", "0", "1", "0", "1");

        //Mixolydian Scale
        List<String> mixolydianScale = createScale("1", "0", "1", "0", "1", "1", "0", "1", "0", "1", "1", "0");

        //Aeolian Scale
        List<String> aeolianScale = createScale("1", "0", "1", "1", "0", "1", "0", "1", "1", "0", "1", "0");

        //Locrian Scale
        List<String> locrianScale = createScale("1", "1", "0", "1", "0", "1", "1", "0", "1", "0", "1", "0");

        this.scaleMap = new HashMap<>();
        scaleMap.put("major", majorScale);
        scaleMap.put("minor", minorScale);
        scaleMap.put("harmonic minor", harmonicMinorScale);
        scaleMap.put("major pentatonic", pentatonicMajorScale);
        scaleMap.put("minor pentatonic", pentatonicMinorScale);
        scaleMap.put("dorian (minor)", dorianScale);
        scaleMap.put("phrygian (minor)", phrygianScale);
        scaleMap.put("lydian (major)", lydianScale);
        scaleMap.put("mixolydian (major)", mixolydianScale);
        scaleMap.put("aeolian (minor)", aeolianScale);
        scaleMap.put("locrian (minor)", locrianScale);


        startStage();
    }

    public List<String> createScale(String a, String b, String c, String d, String e, String f, String g, String h,
    String i, String j, String k, String l){
        List<String> temp = new ArrayList<>();
        temp.add(a);
        temp.add(b);
        temp.add(c);
        temp.add(d);
        temp.add(e);
        temp.add(f);
        temp.add(g);
        temp.add(h);
        temp.add(i);
        temp.add(j);
        temp.add(k);
        temp.add(l);
        return temp;
    }

    public void startStage(){
        //mainBox is the whole screen, vbox is all the custom made gui stuff
        Stage subStage = new Stage();
        subStage.setTitle("Guitar project");
        subStage.getIcons().add(new Image(getClass().getResourceAsStream("images/unmarked_fret.png")));
        VBox mainBox = new VBox();
        VBox vbox = new VBox();
        ImageView empty1 = new ImageView(new Image(getClass().getResourceAsStream("images/empty_space.png")));
        ImageView empty2 = new ImageView(new Image(getClass().getResourceAsStream("images/empty_space.png")));
        ImageView empty3 = new ImageView(new Image(getClass().getResourceAsStream("images/empty_space.png")));
        ImageView empty4 = new ImageView(new Image(getClass().getResourceAsStream("images/empty_space.png")));

        //FRETMARKERS AT THE TOP
        HBox fretMarkers = new HBox();
        for(int i = 0; i < 27; i++){
             if(i == 5 || i == 7 || i == 9 || i == 11 || i == 17 || i == 19 || i == 21 || i == 23){
                ImageView tempview = new ImageView(new Image(getClass().getResourceAsStream("images/fretmarker.png")));
                fretMarkers.getChildren().add(tempview);
            }
            else if(i == 2){
                 ImageView tempview = new ImageView(new Image(getClass().getResourceAsStream("images/0.png")));
                 fretMarkers.getChildren().add(tempview);
            }
            else if( i == 14 || i == 26){
                ImageView tempview = new ImageView(new Image(getClass().getResourceAsStream("images/fretmarker_double.png")));
                fretMarkers.getChildren().add(tempview);
            }
            else{
                 ImageView tempview = new ImageView(new Image(getClass().getResourceAsStream("images/empty_space.png")));
                 fretMarkers.getChildren().add(tempview);
             }
        }
        vbox.getChildren().add(fretMarkers);

        //BORDER ABOVE THE GUITAR NECK
        HBox upperBorder = new HBox();
        upperBorder.getChildren().addAll(empty1, empty2);
        for(int w = 0; w < 25; w++){
            if(w == 0){
                Image image = new Image(getClass().getResourceAsStream("images/empty_space.png"));
                ImageView view = new ImageView(image);
                upperBorder.getChildren().add(view);
                continue;
            }
            Image image = new Image(getClass().getResourceAsStream("images/guitar_border_upper.png"));
            ImageView view = new ImageView(image);
            upperBorder.getChildren().add(view);
        }
        vbox.getChildren().add(upperBorder);

        //FRETBOARD
        for(int i = strings.size()-1; i >= 0; i--){
            boolean firstNote = true;
            HBox hbox  = new HBox();
            strings.get(i).setStringBox(hbox);
            for(Note note : strings.get(i).getNotes()){
                if(firstNote){
                    String imageUrl = imageKeyFinder(note.getNote());
                    ImageView noteOfString = new ImageView(new Image(getClass().getResourceAsStream(imageUrl)));
                    Image image = new Image(getClass().getResourceAsStream("images/unmarked_fret_first.png"));
                    firstNote = false;
                    ImageView view = new ImageView(image);
                    if(sound){
                        view.setCursor(Cursor.HAND);
                        view.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                String path = "src/Project/audio/" + note.getNote() + note.getOctave() + ".wav";
                                retrieveStream(path);
                            }
                        });
                    }
                    note.setView(view);
                    note.setFirst();
                    hbox.getChildren().add(noteOfString);
                    hbox.getChildren().add(view);
                }
                else {
                    Image image = new Image(getClass().getResourceAsStream("images/unmarked_fret.png"));
                    ImageView view = new ImageView(image);
                    if(sound){
                        view.setCursor(Cursor.HAND);
                        view.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                String path = "src/Project/audio/" + note.getNote() + note.getOctave() + ".wav";
                                retrieveStream(path);
                            }
                        });
                    }
                    note.setView(view);
                    hbox.getChildren().add(view);
                }
            }
            vbox.getChildren().add(hbox);
        }

        //BORDER BELOW THE GUITAR NECK
        HBox lowerBorder = new HBox();
        lowerBorder.getChildren().addAll(empty3, empty4);
        for(int w = 0; w < 25; w++){
            if(w == 0){
                Image image = new Image(getClass().getResourceAsStream("images/empty_space.png"));
                ImageView view = new ImageView(image);
                lowerBorder.getChildren().add(view);
                continue;
            }
            Image image = new Image(getClass().getResourceAsStream("images/guitar_border_lower.png"));
            ImageView view = new ImageView(image);
            lowerBorder.getChildren().add(view);
        }
        vbox.getChildren().add(lowerBorder);


        //FRETMARKERS BELOW THE NECK
        HBox fretMarkers2 = new HBox();
        for(int i = 0; i < 27; i++){
            if(i == 5 || i == 7 || i == 9 || i == 11 || i == 17 || i == 19 || i == 21 || i == 23){
                ImageView tempview = new ImageView(new Image(getClass().getResourceAsStream("images/fretmarker.png")));
                fretMarkers2.getChildren().add(tempview);
            }
            else if(i == 2){
                ImageView tempview = new ImageView(new Image(getClass().getResourceAsStream("images/0.png")));
                fretMarkers2.getChildren().add(tempview);
            }
            else if( i == 14 || i == 26){
                ImageView tempview = new ImageView(new Image(getClass().getResourceAsStream("images/fretmarker_double.png")));
                fretMarkers2.getChildren().add(tempview);
            }
            else{
                ImageView tempview = new ImageView(new Image(getClass().getResourceAsStream("images/empty_space.png")));
                fretMarkers2.getChildren().add(tempview);
            }
        }
        vbox.getChildren().add(fretMarkers2);
        //vbox (top half of the gui) completed
        mainBox.getChildren().add(vbox);

        HBox lowerHalf = new HBox();
        VBox noteBox = new VBox();
        lowerHalf.getChildren().add(noteBox);

        HBox subBox1 = new HBox();
        HBox subBox2 = new HBox();
        HBox subBox3 = new HBox();
        Label whiteSpace1 = new Label("                  ");
        Label whiteSpace2 = new Label("                  ");
        Label whiteSpace3 = new Label("                        " + "\r\n");
        subBox1.getChildren().add(whiteSpace1);
        subBox2.getChildren().add(whiteSpace2);


        Label currentNote = new Label("Key Selected: none");
        subBox3.getChildren().addAll(whiteSpace3, currentNote);
        noteBox.getChildren().add(subBox3);
        List<String> noteButtonStrings = noteList();

        //NOTE SELECTION BUTTONS
        int i = 1;
        for(String note: noteButtonStrings){
            Button temp = new Button(note);
            temp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    key = note;
                    currentNote.setText("Key Selected: " + key);
                }
            });
            if(i > 6){
                subBox2.getChildren().add(temp);
            }
            else{
                subBox1.getChildren().add(temp);
                i++;
            }
        }
        noteBox.getChildren().addAll(subBox1, subBox2);
        noteBox.getChildren().add(new Label("" + "\r\n"));


        //SCALE SELECTION COMBO BOXES
        VBox comboVbox = new VBox();
        Label spacer = new Label("                                                                             " +
                "                                             ");
        lowerHalf.getChildren().add(spacer);
        ComboBox comboBox = boxCreator();
        comboVbox.getChildren().addAll(new Label("\r\n" + "Scales"), comboBox);

        lowerHalf.getChildren().add(comboVbox);


        //APPLY BUTTON
        Label spacer2 = new Label("                                                                             " +
                "                              ");
        lowerHalf.getChildren().add(spacer2);
        VBox applyButtonBox = new VBox();
        lowerHalf.getChildren().add(applyButtonBox);
        Button B = new Button("View this scale");
        B.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String value = (String) comboBox.getValue();
                if(scaleMap.containsKey(value) && key != null){
                    scaleMarker(scaleMap.get(value));
                    subStage.setTitle(key + " " + value);
                    subStage.getIcons().remove(0);
                    subStage.getIcons().add(new Image(getClass().getResourceAsStream("images/marked_fret.png")));
                }
            }
        });
        applyButtonBox.getChildren().addAll(new Label("\r\n" + "" ), B);

        //INFO BOX
        Label spacer3 = new Label("                          ");
        VBox infoBoxBox = new VBox();
        Button info = new Button();
        info.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("images/info.png"))));
        info.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                InfoBoxPopUp popUp = new InfoBoxPopUp();
            }
        });
        infoBoxBox.getChildren().addAll(new Label("\r\n" + "\r\n" + "\r\n" + "\r\n" + "     info"), info);
        lowerHalf.getChildren().add(spacer3);
        lowerHalf.getChildren().add(infoBoxBox);




        mainBox.getChildren().add(lowerHalf);
        Scene scene = new Scene(mainBox);
        subStage.setScene(scene);
        subStage.show();
    }




    public String imageKeyFinder(String note){
        String url = "";
        switch(note){
            case "A":
                url = "images/A.png";
                break;
            case "A#":
                url = "images/ABb.png";
                break;
            case "B":
                url = "images/B.png";
                break;
            case "C":
                url = "images/C.png";
                break;
            case "C#":
                url = "images/CDb.png";
                break;
            case "D":
                url = "images/D.png";
                break;
            case "D#":
                url = "images/DEb.png";
                break;
            case "E":
                url = "images/E.png";
                break;
            case "F":
                url = "images/F.png";
                break;
            case "F#":
                url = "images/FGb.png";
                break;
            case "G":
                url = "images/G.png";
                break;
            case "G#":
                url = "images/GAb.png";
                break;
        }
        return url;
    }

    public void scaleMarker(List<String> scale){
        HashSet<String> scaleNotes = new HashSet<>();
        int i = 0;
        GuitarString tempString = new GuitarString(new Note(key,2));
        while(i < scale.size()){
            if(scale.get(i).equals("1")){
                scaleNotes.add(tempString.getNotes().get(i).getNote());
            }
            i++;
        }
        for(GuitarString gs: strings){
            List<Note> notes = gs.getNotes();
            for(Note n: notes){
                if(scaleNotes.contains(n.getNote())){
                    n.setPressed();
                }
                else{
                    n.unPressed();
                }
            }
        }
    }

    public List<String> noteList(){
        List<String> blah = new ArrayList<>();
        blah.add("A");
        blah.add("A#");
        blah.add("B");
        blah.add("C");
        blah.add("C#");
        blah.add("D");
        blah.add("D#");
        blah.add("E");
        blah.add("F");
        blah.add("F#");
        blah.add("G");
        blah.add("G#");
        return blah;
    }

    public ComboBox boxCreator(){
        ComboBox box = new ComboBox();
        box.getItems().add("major");
        box.getItems().add("major pentatonic");
        box.getItems().add("lydian (major)");
        box.getItems().add("mixolydian (major)");
        box.getItems().add("minor");
        box.getItems().add("minor pentatonic");
        box.getItems().add("dorian (minor)");
        box.getItems().add("phrygian (minor)");
        box.getItems().add("aeolian (minor)");
        box.getItems().add("locrian (minor)");
        box.getItems().add("harmonic minor");
        return box;
    }

    public void retrieveStream(String path){

        try{
            File file = new File(path);

            if(file.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

            }
            else{
                System.exit(0);
            }
        } catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }
}

