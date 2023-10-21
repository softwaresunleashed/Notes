package com.unleashed.android.notes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.unleashed.android.notes.R;
import com.unleashed.android.notes.notesDB.ListingsDB;

import java.util.Calendar;

/**
 * An activity representing a single Note detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link NoteListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link NoteDetailFragment}.
 */
public class NoteDetailActivity extends AppCompatActivity implements View.OnClickListener{


    // Invoke Database
    private ListingsDB notesDB;
    private NoteDetailFragment fragment;


    public interface OnSaveNotesToDb{
        public void onSaveNotesToDb();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Get a handle of DB
        notesDB = new ListingsDB(getApplicationContext());

        // Set handler for Save FAB button.
        FloatingActionButton fabbtnSaveNote = (FloatingActionButton) findViewById(R.id.fabSaveNote);
        fabbtnSaveNote.setOnClickListener(this);

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(NoteDetailFragment.ARG_ITEM_ID, getIntent().getStringExtra(NoteDetailFragment.ARG_ITEM_ID));

            fragment = new NoteDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.note_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, NoteListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.fabSaveNote:
                saveNotesToDb();
                break;
        }
    }

    private void saveNotesToDb() {
        try{
            String NotesHeading = fragment.getNotesHeading();
            String NotesDetails = fragment.getNotesDescription();

            // Generate Date and insert in DB
            Calendar cal = Calendar.getInstance();
            String year = String.format("%02d", cal.get(Calendar.YEAR)); ;
            String month = String.format("%02d", cal.get(Calendar.MONTH)+1);
            String day = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
            String hour = String.format("%02d", cal.get(Calendar.HOUR));
            String minutes = String.format("%02d", cal.get(Calendar.MINUTE));
            String seconds = String.format("%02d", cal.get(Calendar.SECOND));

            String NotesDate = year + "-" + month + "-" + day + "-" + hour + "-" + minutes + "-" + seconds;


            // Insert Record in DB
            notesDB.insertRecord(NotesHeading, NotesDetails, NotesDate);

            finish();
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }
}
