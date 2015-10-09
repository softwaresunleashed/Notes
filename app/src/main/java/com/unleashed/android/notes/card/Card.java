package com.unleashed.android.notes.card;

/**
 * Created by OLX - Sudhanshu on 05-10-2015.
 */
public class Card {
    private String NotesHeading;
    private String NotesDescription;

    public Card(String notesHeading, String notesDescription) {
        this.NotesHeading = notesHeading;
        this.NotesDescription = notesDescription;
    }

    public String getNotesHeading() {
        return NotesHeading;
    }

    public String getNotesDescription() {
        return NotesDescription;
    }

}