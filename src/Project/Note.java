package Project;

/**
 * The Note class represents a single musical note with its own attributes.
 *
 * @author Kaleb Pendleton
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Note {

    private String note;    //string identifier for the note
    private int octave;     //determines how high/low the note is
    private boolean pressed;    //bool value so the GUI knows to display the note as pressed or unpressed
    private ImageView view;     //image representing this note on the GUI
    private boolean firstNote;  //bool value so open notes on a guitar string do not become frets

    /**
     * Note constructor sets the default values for a note
     * @param note: a notes string identifier
     * @param octave: the notes pitch
     */
    public Note(String note, int octave){
        this.note = note;
        this.octave = octave;
        this.pressed = false;
        this.firstNote = false;
    }

    public int getOctave(){
        return octave;
    }

    public String getNote(){
        return note;
    }

    /**
     * updates the image view for this note to be pressed.
     */
    public void setPressed(){
        if(firstNote){
            this.view.setImage(new Image(getClass().getResourceAsStream("images/marked_fret_first.png")));
            this.pressed = true;
            return;
        }
        this.pressed = true;
        this.view.setImage(new Image(getClass().getResourceAsStream("images/marked_fret.png")));
    }

    /**
     * updates the image view for this note to be unpressed.
     */
    public void unPressed(){
        if(firstNote){
            this.view.setImage(new Image(getClass().getResourceAsStream("images/unmarked_fret_first.png")));
            this.pressed = false;
            return;
        }
        this.pressed = false;
        this.view.setImage(new Image(getClass().getResourceAsStream("images/unmarked_fret.png")));
    }

    /**
     * function for updating the imageview that this note has.
     * @param view: new image for this note
     */
    public void setView(ImageView view){
        this.view = view;
    }

    public void setFirst(){
        firstNote = true;
    }
}
