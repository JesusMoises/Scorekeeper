package com.example.scorekeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //variables para guardar el score
    private int mScore1;
    private int mScore2;
    //variables para hacer referencia a los texview
    private TextView mScoreText1;
    private TextView mScoreText2;

    // Tags to be used as the keys in OnSavedInstanceState
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hacemos el match de los textiew con nuestras variables TextView
        mScoreText1 = findViewById(R.id.score_1);
        mScoreText2 = findViewById(R.id.score_2);

        // Restores the scores if there is savedInstanceState.
        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);

            //Set the score text views
            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));
        }
    }//fin del método onCreate

    /**
     * Método para decrementar
     * @param view
     */
    public void decreaseScore(View view) {
        int viewID = view.getId();

        switch (viewID){
            //para el equipo 1
            case R.id.decreaseTeam1:
                //decrementamos el valor
                mScore1--;
                //establecemos el valor decrementado
                mScoreText1.setText(String.valueOf(mScore1));
                break;
             //para el equipo 2
            case R.id.decreaseTeam2:
                mScore2--;
                mScoreText2.setText(String.valueOf(mScore2));
        }//fin del switch/case
    }//fin del método decreaseScore

    /**
     * Método para incrementar
     * @param view
     */
    public void increaseScore(View view) {
        //obtenemos el id del view
        int viewID = view.getId();

        switch (viewID){
            //para el equipo 1
            case R.id.increaseTeam1:
                //incrementamos el valor
                mScore1++;
                //establecemos el valor incrementado
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            //para el equipo 2
            case R.id.increaseTeam2:
                //incrementamos el valor
                mScore2++;
                //establecemos el valor incrementado
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }//fin del switch/case
    }//fin del método increaseScore

    /**
     * Creamos la opción para el tema night
     *
     * @param
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //cambiamos el label del menu dependiendo el estado de la app
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }//fin del if/else
        return true;
    }//fin del método onCreateOptionsMenu

    /**
     * Handles options menu item clicks.
     *
     * @param item The item that was pressed
     * @return returns true since the item click wa handled
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check if the correct item was clicked.
        if (item.getItemId() == R.id.night_mode) {
            // Get the night mode state of the app.
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            // Set the theme mode for the restarted activity.
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }//fin del if/else
            // Recreate the activity for the theme change to take effect.
            recreate();
        }//fin del if
        return true;
    }//fin del método onOptionsItemSelected

    /**
     * Method that is called when the configuration changes,
     * used to preserve the state of the app.
     *
     * @param outState The bundle that will be passed in to the Activity when it is restored.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the scores.
        outState.putInt(STATE_SCORE_1, mScore1);
        outState.putInt(STATE_SCORE_2, mScore2);
        super.onSaveInstanceState(outState);
    }//fin del método onSavedInstanceState

}//fin de la clase MainActivity