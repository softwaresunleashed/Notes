package com.unleashed.android.notes.ui;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.unleashed.android.notes.R;
import com.unleashed.android.notes.notesDB.ListingsDB;

/**
 * A fragment representing a single Note detail screen.
 * This fragment is either contained in a {@link NoteListActivity}
 * in two-pane mode (on tablets) or a {@link NoteDetailActivity}
 * on handsets.
 */
public class NoteDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The notecontents content this fragment is presenting.
     */


    private EditText heading;
    private EditText description;
    public static Context mFragmentContext;
    private ListingsDB notesDB;

    private String noteHeading;
    private String noteDescription;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoteDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Invoke Database
        notesDB = new ListingsDB(getActivity().getApplicationContext());

        // First check if we did get the ID.
        if (getArguments().containsKey(ARG_ITEM_ID)) {

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

            String id_recieved_via_intent = getArguments().getString(ARG_ITEM_ID);

            if(id_recieved_via_intent.contentEquals("-1")){
                // Code block to create a new Notes (id would be -1)


                if (appBarLayout != null) {
                    appBarLayout.setTitle("Add New Note");
                    appBarLayout.setBackgroundResource(R.drawable.notes_background);
                }

            }else{
                // Code block to open an existing notes



                // Load the notecontents content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.

                //mItem = NotesContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

                Cursor cur = notesDB.retrieveRecord(getArguments().getString(ARG_ITEM_ID));
                noteHeading = cur.getString(1);
                noteDescription = cur.getString(2);

                if (appBarLayout != null) {
                    appBarLayout.setTitle(noteHeading);
                    appBarLayout.setBackgroundResource(R.drawable.notes_background);
                }
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_note_detail, container, false);


        mFragmentContext = rootView.getContext();

        // Fill in the Heading and Text Obtained from DB.
        heading = (EditText)rootView.findViewById(R.id.note_heading);
        heading.setText(noteHeading);

        description = (EditText)rootView.findViewById(R.id.note_description);
        description.setText(noteDescription);




        return rootView;
    }



    public String getNotesHeading(){
        return heading.getText().toString();
    }


    public String getNotesDescription(){
        return description.getText().toString();
    }
}
