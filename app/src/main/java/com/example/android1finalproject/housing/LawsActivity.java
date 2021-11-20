package com.example.android1finalproject.housing;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android1finalproject.R;

public class LawsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laws);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("Rules and Regulations");

        TextView rulesTextView = findViewById(R.id.rules);
        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Laws");

        StringBuilder builder = new StringBuilder();

        builder.append("1. ").append("All homes should be priced to deliver the same expected total return.").append("\n").append("\n");
        builder.append("2. ").append("Over the long haul, home prices nationwide should rise roughly in line with per-capita GDP.").append("\n").append("\n");
        builder.append("3. ").append("We shouldn’t buy unless we can see staying put for at least five years—and preferably seven years or longer.").append("\n").append("\n");
        builder.append("4. ").append("The land underneath our homes should appreciate, but the dwelling itself will depreciate.").append("\n").append("\n");
        builder.append("5. ").append("Any gain in our home’s value will likely be largely or entirely offset by transaction costs, maintenance, property taxes and homeowner’s insurance.").append("\n").append("\n");
        builder.append("6. ").append("The mortgage-interest tax deduction has always been overrated—and, today, that’s truer than ever.").append("\n").append("\n");
        builder.append("7. ").append("A paid-off home is the cornerstone of a comfortable retirement, for two reasons. First, by paying off our mortgage, we eliminate a major expense, making retirement more affordable.").append("\n").append("\n");
        builder.append("8. ").append("A real estate agent’s greatest financial incentive isn’t to get us the best price, but to get us to act quickly.").append("\n").append("\n");

        rulesTextView.setText(builder.toString());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}