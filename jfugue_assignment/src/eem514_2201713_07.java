import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.theory.ChordProgression;

import java.io.File;
import java.io.IOException;

public class eem514_2201713_07 {
    public static void main(String[] args) {
        ChordProgression progression = new ChordProgression("I IV V vi");

        Pattern pattern = progression
                .distribute("7%6")
                .allChordsAs("$0 $0 $0 $0 $1 $1 $0 $0 $2 $1 $0 $0")
                .eachChordAs("$0ia100 $1ia80 $2ia80 $3ia100 $4ia80 $2ia100 $1ia80")
                .getPattern()
                .setInstrument("Piano")
                .setTempo(120);

        Pattern melody = new Pattern("V1 I[Flute] C5q D5i E5i F5q G5q A5q B5q C6h");
        melody.setTempo(120);

        Pattern combined = new Pattern();
        combined.add(pattern).add(melody);

        new Player().play(combined);

        File midiFile = new File("eem514_2201713_07.mid");

        try {
            MidiFileManager.savePatternToMidi(combined, midiFile);
            System.out.println("MIDI file saved as " + midiFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
