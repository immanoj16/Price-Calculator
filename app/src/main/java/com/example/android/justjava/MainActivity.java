package com.example.android.justjava;

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (quantity == 100) {
            // Show an error message as a toast
            Toast.makeText(this, "You can not have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there is nothing left to do
        }
        quantity += 1;
        display(quantity);
    }

    public void decrement(View view) {
        quantity -= 1;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText editText = (EditText) findViewById(R.id.name_view);
        String name = editText.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        displayMessage(priceMessage);
    }

    /**
     * Calculates the order summary.
     *
     * @param price is the total price of the order
     */

    public String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name) {
        return "Name: " + name + "\nAdd Whipped cream? "+ addWhippedCream + "\nAdd Chocolate? "+ addChocolate +"\nQuantity: " + quantity + "\nPrice: $" + price + "\nThank You!";
    }

    /**
     * Calculates the price of the order.
     *
     * @param addWhippedCream is whether or not the user wants the whipped cream toppings
     * @param addChocolate is whether or not the user wants the chocolate toppings
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // Price of 1 cup of coffee
        int basePrice = 5;

        // Add $1 if the user wants whipped cream
        if(addWhippedCream) {
            basePrice++;
        }

        // Add $2 if the user wants chocolate
        if(addChocolate) {
            basePrice += 2;
        }

        // Calculate the total order price by multiplying by quantity
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}
