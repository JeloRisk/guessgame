// Import necessary classes and packages

package com.example.guessgame;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
public class GuessingGame extends AppCompatActivity {

    // Declare class level variables
    private int randomNumber;           // variable for Random Numbers
    private int remainingLives = 9;     // variable for lives
    private EditText inputNumberEditText;
    private TextView outputTextView;
    private TextView outputTextViewSmall;
    private Button guessButton;
    private Button tryAgainButton;

    @Override
    // Override the onCreate method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guess); // Set the layout
        generateRandomNumber(); // Generate a random number to be guessed
        inputNumberEditText = findViewById(R.id.input_number_edit_text);    // Find the EditText for the user's guess
        // Find the TextView to display output messages
        outputTextView = findViewById(R.id.output_text_view);
        outputTextViewSmall = findViewById(R.id.output_text_view_small);
        // Find the button
        guessButton = findViewById(R.id.guess_button);
        tryAgainButton = findViewById(R.id.try_again_button);
        // Add an onClickListener to the guessButton
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // Call the checkGuess method when the guessButton is clicked
            public void onClick(View v) {
                checkGuess();
            }
        });

        inputNumberEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    checkGuess();
                    return true;
                }
                return false;
            }
        });

        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // Call the resetGame method when the tryAgainButton is clicked
            public void onClick(View v) {
                resetGame();
            }
        });
    }
    // Call the resetGame method when the tryAgainButton is clicked
    private void generateRandomNumber() {
        // Create a new Random object
        Random random = new Random();
        // Generate a random number between 1 and 50 and store it in the randomNumber variable
        randomNumber = random.nextInt(50) + 1;
    }
    // Method to check the user's guess and update the outputTextView and remainingLives variable
    private void checkGuess() {
        // Get the user's guess from the inputNumberEditText
        String guessString = inputNumberEditText.getText().toString();
        if (!guessString.isEmpty()) {                           // Check if the guessString is not empty
            int guess = Integer.parseInt(guessString);          // Convert the guessString to an integer
            if (guess == randomNumber) {
                // Update the outputTextView with a congratulatory message
                outputTextView.setText("Congratulations! You guessed the number.");
                // Make the tryAgainButton visible. Disable the guessButton
                guessButton.setEnabled(false);
                tryAgainButton.setVisibility(View.VISIBLE);
            } else {
                // Decrement the remainingLives variable
                // Check if there are no more remaining lives
                remainingLives--;
                inputNumberEditText.setText("");
                if (remainingLives == 0) {
                    // Update the outputTextView with a losing message
                    // Disable the guessButton
                    //
                    outputTextView.setText("You lost! The correct number was " + randomNumber + ".");
                    guessButton.setEnabled(false);
//                    goToNextPage();
                    tryAgainButton.setVisibility(View.VISIBLE);
                    guessButton.setVisibility(View.INVISIBLE);
                    inputNumberEditText.setEnabled(false);

                } else {
                    if (guess < 1 || guess > 50) {
                        outputTextViewSmall.setText("Your guess must be between 1 and 50.");
                    } else if (guess < randomNumber) {
                        outputTextViewSmall.setText("Your guess is too low.");
                    } else {
                        outputTextViewSmall.setText("Your guess is too high.");
                    }
                    String message = "Wrong guess! You have " + remainingLives + " lives remaining.";
                    outputTextView.setText(message);
                }
            }
        }else{
            outputTextViewSmall.setText("Empty");
        }
    }
    // It is not used
    private void resetGame() {
        generateRandomNumber();
        remainingLives = 9;
        inputNumberEditText.setText("");
        outputTextView.setText("");
        outputTextViewSmall.setText("");
        guessButton.setEnabled(true);
        guessButton.setVisibility(View.VISIBLE);
        inputNumberEditText.setEnabled(true);
        tryAgainButton.setVisibility(View.INVISIBLE);
    }
    public void goToNextPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
    }
}
