package comp3350.Group2.areyouhungry.ui.add_food;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.objects.Food;

public class AddNextActivity extends AppCompatActivity {
    ArrayList<String> str_categorys;
    ArrayList<String> str_ingredients;
    ArrayList<String> str_instructions;
    String foodImgUrl;
    Boolean favourite;
    Food newFood;
    LinearLayout ingredientContainer;
    LinearLayout instrctionContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        newFood = null;
        str_categorys = null;
        str_ingredients = new ArrayList<>();
        str_categorys = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_next);
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            newFood = (Food)bundle.getSerializable("foodObj");
            System.out.println("get Food:"+newFood.getFoodName());
            favourite = bundle.getBoolean("foodFav");
            System.out.println("get fav:"+favourite);
            str_categorys = bundle.getStringArrayList("foodCats");
            System.out.println("get category:"+str_categorys.toString());
            foodImgUrl = bundle.getString("foodImg");
            System.out.println("get image url:"+foodImgUrl);
        }

        ingredientContainer = (LinearLayout)findViewById(R.id.ingredientContainer);
        System.out.println("add next on create");
        Button addButton = (Button) findViewById(R.id.addIngredientButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText textName = (TextInputEditText)findViewById(R.id.ingredientName);
                TextInputEditText textQuat = (TextInputEditText)findViewById(R.id.ingredientQuantity);
                LayoutInflater layoutInflater =
                        (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.add_next_row, null);
                final TextView textOut = (TextView)addView.findViewById(R.id.textout);
                final String textShow = textName.getText().toString()+" - "+textQuat.getText().toString();
                str_ingredients.add(textShow);
                textOut.setText(textShow);
                Button buttonRemove = (Button)addView.findViewById(R.id.remove);
                buttonRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((LinearLayout)addView.getParent()).removeView(addView);
                        str_ingredients.remove(textShow);

                    }
                });
                ingredientContainer.addView(addView);
            }
        });

        instrctionContainer = (LinearLayout)findViewById(R.id.instructionContainer);
        Button addButton2 = (Button) findViewById(R.id.addInstructionButton);
        addButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText textName = (TextInputEditText)findViewById(R.id.instructionName);
                LayoutInflater layoutInflater =
                        (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.add_next_row, null);
                final TextView textOut = (TextView)addView.findViewById(R.id.textout);
                final String textShow = textName.getText().toString();
                str_instructions.add(textShow);
                textOut.setText(textShow);
                Button buttonRemove = (Button)addView.findViewById(R.id.remove);
                buttonRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((LinearLayout)addView.getParent()).removeView(addView);
                        str_instructions.remove(textShow);
                    }
                });
                instrctionContainer.addView(addView);
            }
        });

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String alertMessage = "";
                if(str_ingredients.isEmpty()){
                    alertMessage += "Please enter at least one ingredient \n";
                }
                if(str_instructions.isEmpty()){
                    alertMessage += "Please enter at least one instruction \n";
                }
                if(!str_ingredients.isEmpty() && !str_instructions.isEmpty()){
                    System.out.println("add now !");

                }else {
                    AlertDialog alertDialog = new AlertDialog.Builder(AddNextActivity.this).create();
                    alertDialog.setTitle("Error:");
                    alertDialog.setMessage(alertMessage);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
    }


}