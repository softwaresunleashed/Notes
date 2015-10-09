package com.unleashed.android.notes.card;

/**
 * Created by OLX - Sudhanshu on 05-10-2015.
 */
public class Card {
    private String NotesHeading;
    //private String NotesDescription;

    public Card(String notesHeading) {
        this.NotesHeading = notesHeading;
        //this.NotesDescription = line2;
    }

    public String getNotesHeading() {
        return NotesHeading;
    }

//    public String getLine2() {
//        return NotesDescription;
//    }

}