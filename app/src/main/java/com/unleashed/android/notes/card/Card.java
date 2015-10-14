package com.unleashed.android.notes.card;

/**
 * Created by OLX - Sudhanshu on 05-10-2015.
 */
public class Card {
    private String NotesHeading;
    private String NotesDescription;
    private String NotesLastUpdate;

    public Card(String notesHeading, String notesDescription, String notesLastUpdate) {
        this.NotesHeading = notesHeading;
        this.NotesDescription = notesDescription;
        this.NotesLastUpdate = notesLastUpdate;

    }

    public String getNotesHeading() {
        return NotesHeading;
    }

    public String getNotesDescription() {
        return NotesDescription;
    }

    public String getNotesLastUpdated() {
        return NotesLastUpdate;
    }

}