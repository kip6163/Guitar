package Project;

import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class GuitarString {

    private List<Note> notes;
    private Note key;
    private HBox stringBox;

    public GuitarString(Note key){
        this.key = key;
        this.notes = new ArrayList<>(25);
        List<String> basicNotes = getBasicNotes();
        int currentOctave = key.getOctave();
        int i = 0;
        int j;
        while(true){
            if(basicNotes.get(i).equals(key.getNote())){
                j = i;
                break;
            }
            i++;
        }
        int counter = 0;
        while(counter < 25){
            if(j == basicNotes.size()){
                j = 0;
                currentOctave++;
            }
            else{
                Note tempNote = new Note(basicNotes.get(j), currentOctave);
                notes.add(tempNote);
                j++;
                counter++;
            }
        }
    }

    public ArrayList<String> getBasicNotes(){
        ArrayList<String> basicNotes = new ArrayList<>(12);
        basicNotes.add("A");
        basicNotes.add("A#");
        basicNotes.add("B");
        basicNotes.add("C");
        basicNotes.add("C#");
        basicNotes.add("D");
        basicNotes.add("D#");
        basicNotes.add("E");
        basicNotes.add("F");
        basicNotes.add("F#");
        basicNotes.add("G");
        basicNotes.add("G#");
        return basicNotes;
    }

    public List<Note> getNotes(){
        return notes;
    }

    public void setStringBox(HBox box){
        this.stringBox = box;
    }
}
