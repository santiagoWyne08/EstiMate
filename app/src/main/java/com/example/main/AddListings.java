package com.example.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.main.Utilities.House;
import com.example.main.Utilities.HousePrediction;

public class AddListings extends AppCompatActivity {

    private double vHouseSize;
    private int vBedroom;
    private int vQuality;
    private double vAge;
    private double price;
    private String houseSize, houseName, houseAddress, bedroom, quality, age;

    String name;

    private ImageView uploadImg;
    private EditText edtHouseName, edtHouseAddress, edtArea, edtNumBedroom, edtQuality, edtAge;
    private Button predictPriceBtn, predictedPrice, saveListingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_listings);

        edtHouseName = findViewById(R.id.edtHouseName);
        edtHouseAddress = findViewById(R.id.edtHouseAddress);
        edtArea = findViewById(R.id.edtArea);
        edtNumBedroom = findViewById(R.id.edtNumBedroom);
        edtQuality = findViewById(R.id.edtQuality);
        edtAge = findViewById(R.id.edtAge);

        predictPriceBtn = findViewById(R.id.predictPriceBtn);
        predictedPrice = findViewById(R.id.predictedPrice);
        saveListingBtn = findViewById(R.id.saveListingBtn);

        uploadImg = findViewById(R.id.uploadImg);
    }

    public void predictPrice(View view) {
        houseSize = edtArea.getText().toString();
        houseName = edtHouseName.getText().toString();
        houseAddress = edtHouseAddress.getText().toString();
        bedroom = edtNumBedroom.getText().toString();
        quality = edtQuality.getText().toString();
        age = edtAge.getText().toString();

        if (houseName.isEmpty() || houseSize.isEmpty() || houseAddress.isEmpty() || bedroom.isEmpty() || quality.isEmpty() || age.isEmpty()) {
            checkFields(houseName, houseSize, houseAddress, bedroom, quality, age);
        } else {
            try {
                vHouseSize = Double.parseDouble(houseSize);
                vBedroom = Integer.parseInt(bedroom);
                vQuality = Integer.parseInt(quality);
                vAge = Double.parseDouble(age);

                HousePrediction housePrediction = new HousePrediction();

                housePrediction.addHouse(new House(150, 3, 7, 10, 300000));
                housePrediction.addHouse(new House(200, 4, 8, 5, 450000));
                housePrediction.addHouse(new House(250, 2, 9, 2, 600000));
                housePrediction.addHouse(new House(200, 3, 7, 4, 350000));
                housePrediction.addHouse(new House(175, 2, 6, 8, 150000));
                housePrediction.addHouse(new House(150, 3, 5, 10, 500000));
                housePrediction.addHouse(new House(205, 2, 9, 1, 250000));
                housePrediction.addHouse(new House(215, 2, 7, 3, 550000));
                housePrediction.addHouse(new House(185, 3, 9, 6, 400000));
                housePrediction.addHouse(new House(225, 1, 6, 4, 750000));

                price = housePrediction.predictPrice(vHouseSize, vBedroom, vQuality, vAge);
                price = Math.round(price * 100.0) / 100.0;

                predictedPrice.setVisibility(View.VISIBLE);
                predictedPrice.setText("Php " + String.format("%.2f", price));
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void checkFields(String houseName, String houseSize, String houseAddress, String bedroom, String quality, String age) {
        if (houseName.isEmpty()) {
            edtHouseName.setError("Please enter a house name");
            edtHouseName.requestFocus();
        }

        if (houseSize.isEmpty()) {
            edtArea.setError("Please enter a house address");
            edtArea.requestFocus();
        }

        if (houseAddress.isEmpty()) {
            edtHouseAddress.setError("Please enter a house address");
            edtHouseAddress.requestFocus();
        }

        if (bedroom.isEmpty()) {
            edtNumBedroom.setError("Please enter a number of bedrooms");
            edtNumBedroom.requestFocus();
        }

        if (quality.isEmpty()) {
            edtQuality.setError("Please enter a house quality");
            edtQuality.requestFocus();
        }

        if (age.isEmpty()) {
            edtAge.setError("Please enter a house age");
            edtAge.requestFocus();
        }
    }
}