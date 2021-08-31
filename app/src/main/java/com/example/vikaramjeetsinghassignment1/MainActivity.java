package com.example.vikaramjeetsinghassignment1;

// Vikaramjeet Singh

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declaring objects
    EditText personName;
    AutoCompleteTextView province;
    RadioButton desktop, laptop, peripheral1, peripheral2, peripheral3;
    Spinner brand;
    CheckBox ssDrive, printer;
    Button buyProduct;
    TextView invoice;
    Editable enteredName;

    // Array for the AutoCompleteTextView
    String[] provinceList = {"Alberta","British Columbia", "Manitoba", "New Brunswick", "Newfoundland and Labrador",
            "Northwest Territories", "Nova Scotia", "Nunavut", "Ontario", "Prince Edward Island",
            "Quebec", "Saskatchewan", "Yukon Territory"};

    // Array for the Spinner
    String[] brandList = {"Dell", "Lenovo", "HP"};

    // onCreate method is called by android itself before the app is ready to start
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reference variables getting reference by ID
        personName = (EditText) findViewById(R.id.editTextPersonName);
        province = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        desktop = (RadioButton) findViewById(R.id.desktopRadioBtn);
        laptop = (RadioButton) findViewById(R.id.laptopRadioBtn);
        brand = (Spinner) findViewById(R.id.brandSpinner);
        ssDrive = (CheckBox) findViewById(R.id.ssdCheckBox);
        printer = (CheckBox) findViewById(R.id.printerCheckBox);
        peripheral1 = (RadioButton) findViewById(R.id.item1RadioBtn);
        peripheral2 = (RadioButton) findViewById(R.id.item2RadioBtn);
        peripheral3 = (RadioButton) findViewById(R.id.item3RadioBtn);
        buyProduct = (Button) findViewById(R.id.buyBtn);
        invoice = (TextView) findViewById(R.id.invoiceTextView);


        // data adapter to fetch the provinces from the array provinceList
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,provinceList);
        province.setAdapter(adapter1);


        // If user selects the desktop,the state of a desktop radio btn is -
        desktop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(desktop.isChecked())
                    peripheral1.setText("Webcam");
                    peripheral2.setText("External Hard Drive");
                    peripheral3.setText("None");
                    peripheral1.setVisibility(View.VISIBLE);
                    peripheral2.setVisibility(View.VISIBLE);
                    peripheral3.setVisibility(View.VISIBLE);

            }
        });


        // If user selects the laptop,the state of a laptop radioBtn is -
        laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(laptop.isChecked())
                    peripheral1.setText("Laptop Cooling Mat");
                    peripheral2.setText("USB C-HUB");
                    peripheral3.setText("Laptop Stand");
                    peripheral1.setVisibility(View.VISIBLE);
                    peripheral2.setVisibility(View.VISIBLE);
                    peripheral3.setVisibility(View.VISIBLE);
            }
        });


        // Array adapter for selecting the brand from the array brandList
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,brandList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brand.setAdapter(adapter2);
        brand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Selected brand:" + brandList[position],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // click event of a button "Buy Now"
        buyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get and store the value in local variables
                enteredName = personName.getText();
                String selectedProvince = province.getText().toString();
                String selectedBrand = brand.getSelectedItem().toString();

                //Declaring and Initializing the variables
                int computerPrice = 0;
                int additionalPrice = 0;
                int peripheralPrice = 0;
                String computer = "-";
                String additionalItems = "-";
                String peripherals = "-";
                int ssdPrice = 60;
                int printerPrice = 100;


                //Using  If condition on the type of computer
                // If desktop is selected, getting the prices according to type,brand,item
                if (desktop.isChecked()) {
                    computer = desktop.getText().toString();
                    if(selectedBrand == "Dell") {
                        computerPrice = 475;
                    }
                    else if(selectedBrand == "Lenovo") {
                        computerPrice = 450;
                    }
                    else if(selectedBrand == "HP") {
                        computerPrice = 400;
                    }

                    int webcamPrice = 95;
                    int externalHardDrivePrice = 64;
                    if (peripheral1.isChecked())
                        peripheralPrice = webcamPrice;
                    else if(peripheral2.isChecked())
                        peripheralPrice = externalHardDrivePrice;
                }
                // If laptop is selected, getting the prices according to type,brand,item
                else if (laptop.isChecked()) {
                    computer = laptop.getText().toString();
                    if(selectedBrand == "Dell") {
                        computerPrice = 1249;
                    }
                    else if(selectedBrand == "Lenovo") {
                        computerPrice = 1549;
                    }
                    else if(selectedBrand == "HP") {
                        computerPrice = 1150;
                    }

                    int coolingMatPrice = 33;
                    int usbPrice = 60;
                    int laptopStandPrice = 139;
                    if (peripheral1.isChecked())
                        peripheralPrice = coolingMatPrice;
                    else if(peripheral2.isChecked())
                        peripheralPrice = usbPrice;
                    else if(peripheral3.isChecked())
                        peripheralPrice = laptopStandPrice;
                }


                // using if condition on the additional items
                if (ssDrive.isChecked() && printer.isChecked()) {
                    String itemOne = ssDrive.getText().toString();
                    String itemTwo = printer.getText().toString();
                    additionalItems = itemOne + " and " + itemTwo;
                    additionalPrice = ssdPrice + printerPrice;
                } else if (ssDrive.isChecked()) {
                    additionalItems = ssDrive.getText().toString();
                    additionalPrice = ssdPrice;
                } else if (printer.isChecked()) {
                    additionalItems = printer.getText().toString();
                    additionalPrice = printerPrice;
                }


                // using if condition on the peripheral items
                if (peripheral1.isChecked()) {
                    peripherals = peripheral1.getText().toString();
                } else if (peripheral2.isChecked()) {
                    peripherals = peripheral2.getText().toString();
                } else if (peripheral3.isChecked()) {
                    peripherals = peripheral3.getText().toString();
                }

                //Calculating the subtotal amount of ordered item
                double total;
                total = (double)(computerPrice + additionalPrice + peripheralPrice);


                //Calculating the 13% tax on total amount
                double tax;
                tax = (13 * total)/100;


                //Calculating the total amount with tax included
                double finalCost;
                finalCost = total + tax;


                //Generating the invoice when user click on the "Buy Now" Button
                invoice.setText("Customer: " + enteredName + "\n Province: " + selectedProvince
                        + "\n Computer: " + computer + "\n Brand: " + selectedBrand +
                        "\n Additional: " + additionalItems + "\n Added Peripherals: " + peripherals +
                        "\n Subtotal: $" + total + "\n GST(13%): $" + tax +
                        "\n Total Amount: $" + finalCost);

              }

            });

        }
}