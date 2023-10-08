package com.zybooks.tictaktoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public GridLayout gameBoard;
    private Menu mMenu;
    public Integer ptrTurn = 0;
    public TextView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameBoard = findViewById(R.id.main_grid);
        player = findViewById(R.id.textViewPlayer);

        // Add the same click handler to all grid buttons
        for (int buttonIndex = 0; buttonIndex < gameBoard.getChildCount(); buttonIndex++) {
            Button gridButton = (Button) gameBoard.getChildAt(buttonIndex);
            gridButton.setOnClickListener(this::onButtonClick);
        }
    }

    private void onButtonClick(View view) {

        // Find the button's row and col
        int buttonIndex = gameBoard.indexOfChild(view);
        int row = buttonIndex / TicTakToeGame.GRID_SIZE;
        int col = buttonIndex % TicTakToeGame.GRID_SIZE;

        // tracks what turn it is though a counter
        ptrTurn = ptrTurn + 1;
        if ((ptrTurn % 2) == 0){
            player.setText("X's Turn");
        }
        else
        {
            player.setText("O's Turn");
        }

        // checks which button was presses and sends message
        if( row == 0){
            if(col == 0){
                Toast.makeText(this, "button 1 clicked", Toast.LENGTH_SHORT).show();
            }
            else if(col == 1){
                Toast.makeText(this, "button 2 clicked", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "button 3 clicked", Toast.LENGTH_SHORT).show();
            }
        } else if (row == 1)
        {
            if(col == 0){
                Toast.makeText(this, "button 4 clicked", Toast.LENGTH_SHORT).show();
            }
            else if(col == 1){
                Toast.makeText(this, "button 5 clicked", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "button 6 clicked", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            if(col == 0){
                Toast.makeText(this, "button 7 clicked", Toast.LENGTH_SHORT).show();
            }
            else if(col == 1){
                Toast.makeText(this, "button 8 clicked", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "button 9 clicked", Toast.LENGTH_SHORT).show();
            }
        }
        Button gridButton = (Button) view;

        if (gridButton.getText().toString().isEmpty()) {
            // Button is not already marked
            gridButton.setText(String.valueOf(currentPlayer));

            // Set vector asset as the background of the button based on player
            int vectorDrawableId = (currentPlayer == 'X') ? R.drawable.baseline_done_24: R.drawable.baseline_circle_24;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                gridButton.setBackground(getDrawable(vectorDrawableId));
            } else {
                gridButton.setBackground(getResources().getDrawable(vectorDrawableId));
            }



            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';

        } else {
            Toast.makeText(this, "Cell already occupied. Choose another cell.", Toast.LENGTH_SHORT).show();
        }

    }

    }

    // inflates appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }


    // When appbar menu is selected, this will attach an action
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.action_new){
            Log.d("onOptionItemSelected", "Activity!");
            return true;
        }
        else if(item.getItemId() == R.id.action_quit){
            FragmentManager manager = getSupportFragmentManager();
            QuitDialogFragment dialogFragment = new QuitDialogFragment();
            dialogFragment.show(manager, "warningDialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

} 
