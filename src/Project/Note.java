package Project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Note {

    private String note;
    private int octave;
    private boolean pressed;
    private ImageView view;
    private boolean firstNote;

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

    public void setPressed(){
        if(firstNote){
            this.view.setImage(new Image(getClass().getResourceAsStream("images/marked_fret_first.png")));
            this.pressed = true;
            return;
        }
        this.pressed = true;
        this.view.setImage(new Image(getClass().getResourceAsStream("images/marked_fret.png")));
    }

    public boolean getPressed(){
        return pressed;
    }

    public void unPressed(){
        if(firstNote){
            this.view.setImage(new Image(getClass().getResourceAsStream("images/unmarked_fret_first.png")));
            this.pressed = false;
            return;
        }
        this.pressed = false;
        this.view.setImage(new Image(getClass().getResourceAsStream("images/unmarked_fret.png")));
    }

    public void setView(ImageView view){
        this.view = view;
    }

    public void setFirst(){
        firstNote = true;
    }
}
