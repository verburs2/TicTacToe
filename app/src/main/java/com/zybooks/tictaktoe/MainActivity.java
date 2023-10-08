package com.zybooks.tictaktoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Build;
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
    public char currentPlayer;

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
            currentPlayer = 'X';


        }
        else
        {
            player.setText("O's Turn");
            currentPlayer = 'O';
        }

        // checks which button was presses and sends message
        if( row == 0){
            if(col == 0){


                Toast.makeText(this, "button 1 clicked", Toast.LENGTH_SHORT).show();
                checkForWin(row, col);
            }
            else if(col == 1){
                Toast.makeText(this, "button 2 clicked", Toast.LENGTH_SHORT).show();
                checkForWin(row, col);
            }
            else {
                Toast.makeText(this, "button 3 clicked", Toast.LENGTH_SHORT).show();
                checkForWin(row, col);
            }
        } else if (row == 1)
        {
            if(col == 0){
                Toast.makeText(this, "button 4 clicked", Toast.LENGTH_SHORT).show();
                checkForWin(row, col);
            }
            else if(col == 1){
                Toast.makeText(this, "button 5 clicked", Toast.LENGTH_SHORT).show();
                checkForWin(row, col);
            }
            else {
                Toast.makeText(this, "button 6 clicked", Toast.LENGTH_SHORT).show();
                checkForWin(row, col);
            }
        }
        else{
            if(col == 0){
                Toast.makeText(this, "button 7 clicked", Toast.LENGTH_SHORT).show();
                checkForWin(row, col);
            }
            else if(col == 1){
                Toast.makeText(this, "button 8 clicked", Toast.LENGTH_SHORT).show();
                checkForWin(row, col);
            }
            else {
                Toast.makeText(this, "button 9 clicked", Toast.LENGTH_SHORT).show();
                checkForWin(row, col);
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


        } else {
            Toast.makeText(this, "Cell already occupied. Choose another cell.", Toast.LENGTH_SHORT).show();
        }
    }
    private void checkForWin(int row, int col) {
        // Check for a win in the current row
        if (checkRowWin(row) || checkColumnWin(col) || checkDiagonalWin(row, col)) {
            // Win detected, show a message and reset the grid
            Toast.makeText(this, "Player " + currentPlayer + " wins!", Toast.LENGTH_SHORT).show();
            resetGrid();
        }
    }

    private boolean checkRowWin(int row) {
        Button button1 = (Button) gameBoard.getChildAt(row * 3);
        Button button2 = (Button) gameBoard.getChildAt(row * 3 + 1);
        Button button3 = (Button) gameBoard.getChildAt(row * 3 + 2);

        return button1.getText().toString().equals(String.valueOf(currentPlayer)) &&
                button2.getText().toString().equals(String.valueOf(currentPlayer)) &&
                button3.getText().toString().equals(String.valueOf(currentPlayer));
    }

    private boolean checkColumnWin(int col) {
        Button button1 = (Button) gameBoard.getChildAt(col);
        Button button2 = (Button) gameBoard.getChildAt(col + 3);
        Button button3 = (Button) gameBoard.getChildAt(col + 6);

        return button1.getText().toString().equals(String.valueOf(currentPlayer)) &&
                button2.getText().toString().equals(String.valueOf(currentPlayer)) &&
                button3.getText().toString().equals(String.valueOf(currentPlayer));
    }

    private boolean checkDiagonalWin(int row, int col) {
        if (row == col || (row + col) == 2) {
            Button button1 = (Button) gameBoard.getChildAt(0);
            Button button2 = (Button) gameBoard.getChildAt(4);
            Button button3 = (Button) gameBoard.getChildAt(8);

            return button1.getText().toString().equals(String.valueOf(currentPlayer)) &&
                    button2.getText().toString().equals(String.valueOf(currentPlayer)) &&
                    button3.getText().toString().equals(String.valueOf(currentPlayer));
        }
        return false;
    }

    // inflates appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    private void resetGrid() {
        // Clear text and background for all buttons in the grid
        for (int i = 0; i < gameBoard.getChildCount(); i++) {
            Button gridButton = (Button) gameBoard.getChildAt(i);
            gridButton.setText("");
            gridButton.setBackgroundResource(android.R.drawable.btn_default);
        }

        // Reset turn counter and player indicator
        ptrTurn = 0;
        currentPlayer = 'X';
        player.setText("X's Turn");
    }



    // When appbar menu is selected, this will attach an action
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.action_new){
            Log.d("onOptionItemSelected", "Activity!");
            resetGrid();
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
