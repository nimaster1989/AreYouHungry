package comp3350.Group2.areyouhungry.ui.add_food;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;


import java.util.ArrayList;

import comp3350.Group2.areyouhungry.MainActivity;
import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.business.AccessDirections;
import comp3350.Group2.areyouhungry.business.AccessFoods;
import comp3350.Group2.areyouhungry.business.AccessIngredients;
import comp3350.Group2.areyouhungry.objects.Direction;
import comp3350.Group2.areyouhungry.objects.Food;
import comp3350.Group2.areyouhungry.objects.FoodCategory;
import comp3350.Group2.areyouhungry.objects.Ingredient;
import comp3350.Group2.areyouhungry.objects.User;

public class AddNextActivity extends AppCompatActivity{
    ArrayList<String> str_categorys;
    ArrayList<String> str_ingredients;
    ArrayList<String> str_instructions;
    String foodImgUrl;
    Boolean favourite;
    Food newFood;
    LinearLayout ingredientContainer;
    LinearLayout instructionContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        newFood = null;
        str_categorys = null;
        str_ingredients = new ArrayList<>();
        str_instructions = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_next);
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            newFood = (Food)bundle.getSerializable("foodObj");
            favourite = bundle.getBoolean("foodFav");
            str_categorys = bundle.getStringArrayList("foodCats");
            foodImgUrl = bundle.getString("foodImg");
        }
        ingredientContainer = (LinearLayout)findViewById(R.id.ingredientContainer);
        Button addButton = (Button) findViewById(R.id.addIngredientButton);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TextInputEditText textName = (TextInputEditText) findViewById(R.id.ingredientName);
                TextInputEditText textQuat = (TextInputEditText) findViewById(R.id.ingredientQuantity);

                if(TextUtils.isEmpty(textName.getText()) || TextUtils.isEmpty(textQuat.getText())){
                    String alert;
                    if(TextUtils.isEmpty(textName.getText())) alert = "Please input an ingredient.";
                    else alert = "Please input ingredient quantity.";
                    AlertDialog alertDialog = new AlertDialog.Builder(AddNextActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(alert);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else{
                    LayoutInflater layoutInflater =
                            (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View addView = layoutInflater.inflate(R.layout.add_next_row, null);
                    final TextView textOut = (TextView) addView.findViewById(R.id.textout);
                    final String textShow = textName.getText().toString() + " - " + textQuat.getText().toString();
                    str_ingredients.add(textShow);
                    textOut.setText(textShow);
                    textName.getText().clear();
                    textQuat.getText().clear();
                    Button buttonRemove = (Button) addView.findViewById(R.id.remove);
                    buttonRemove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((LinearLayout) addView.getParent()).removeView(addView);
                            str_ingredients.remove(textShow);

                        }
                    });
                    ingredientContainer.addView(addView);
                }
            }
        });

        instructionContainer = (LinearLayout)findViewById(R.id.instructionContainer);
        Button addButton2 = (Button) findViewById(R.id.addInstructionButton);
        addButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                TextInputEditText textName = (TextInputEditText)findViewById(R.id.instructionName);
                if(TextUtils.isEmpty(textName.getText())){
                    AlertDialog alertDialog = new AlertDialog.Builder(AddNextActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Please write a direction.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else {
                    LayoutInflater layoutInflater =
                            (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View addView = layoutInflater.inflate(R.layout.add_next_row, null);
                    final TextView textOut = (TextView) addView.findViewById(R.id.textout);
                    final String textShow = textName.getText().toString();
                    str_instructions.add(textShow);
                    textOut.setText(textShow);
                    textName.getText().clear();
                    Button buttonRemove = (Button) addView.findViewById(R.id.remove);
                    buttonRemove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((LinearLayout) addView.getParent()).removeView(addView);
                            str_instructions.remove(textShow);
                        }
                    });
                    instructionContainer.addView(addView);
                }
            }
        });

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String alertMessage = "";
                if(str_ingredients.isEmpty()){
                    alertMessage += "Please enter at least one ingredient \n";
                }
                if(str_instructions.isEmpty()){
                    alertMessage += "Please enter at least one instruction \n";
                }
                if(!str_ingredients.isEmpty() && !str_instructions.isEmpty()){
                    AccessFoods af = new AccessFoods();
                    if(newFood != null && af.addFood(newFood) == null){
                        if(!foodImgUrl.equals("")){
                            af.addFoodImage(newFood.getFoodID(), foodImgUrl);
                        }
                        if(favourite){
                            User curr_user = MainActivity.currentUser;
                            af.setFoodFavouriteByUser(curr_user,newFood.getFoodID(),true);
                        }
                        for(String str_category:str_categorys){
                            FoodCategory newFc = af.addFoodCategory(newFood,str_category);
                        }
                        for (String str_ingredient:str_ingredients){
                            AccessIngredients ai = new AccessIngredients();
                            int newIngredientId = ai.getNewIngredientId();
                            int lastSpace = str_ingredient.lastIndexOf("-");
                            String measurement;
                            String name;
                            if (lastSpace == -1){
                                measurement = "";
                                name = str_ingredient;
                            } else{
                                measurement = str_ingredient.substring(lastSpace);
                                name = str_ingredient.substring(0, lastSpace);
                            }
                            Ingredient newIngredient = new Ingredient(newIngredientId, name, measurement);
                            String result = ai.addIngredient(newIngredient);
                            if(result == null){
                                ai.setFoodIngredient(Integer.parseInt(newFood.getFoodID()),newIngredient.getIngredientID());
                            }
                        }
                        int step = 1;
                        for(String str_direction:str_instructions){
                            AccessDirections ad = new AccessDirections();
                            int newDirectionId = ad.getNewDirectionId();
                            Direction newDirection = new Direction(newDirectionId,str_direction,step);
                            step++;
                            String result = ad.addDirection(newDirection);
                            if(result == null){
                                ad.addFoodDirection(Integer.parseInt(newFood.getFoodID()),newDirection.getDirectionID());
                            }
                        }
                        AlertDialog alertDialog = new AlertDialog.Builder(AddNextActivity.this).create();
                        alertDialog.setTitle("Success!");
                        alertDialog.setMessage(newFood.getFoodName() + " has been added!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int which){
                                        dialog.dismiss();
                                        finish();
                                    }
                                });
                        alertDialog.show();
                    }
                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(AddNextActivity.this).create();
                    alertDialog.setTitle("Error:");
                    alertDialog.setMessage(alertMessage);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int which){
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
    }


}