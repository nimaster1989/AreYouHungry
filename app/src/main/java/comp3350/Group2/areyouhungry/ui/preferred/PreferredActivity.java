package comp3350.Group2.areyouhungry.ui.preferred;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import comp3350.Group2.areyouhungry.business.AccessQuestions;
import comp3350.Group2.areyouhungry.R;
import comp3350.Group2.areyouhungry.objects.Answers;
import comp3350.Group2.areyouhungry.objects.Question;
import comp3350.Group2.areyouhungry.ui.home.HomeActivity;


/* This is the page that loads when the user selects. */
public class PreferredActivity extends AppCompatActivity  {
    private AccessQuestions accessQuestions;

    private TextView textViewQuestion;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;

    private int questionCount;
    private int questionTotal;
    private boolean answered;
    private List<Question> questionList;
    private List<Integer> answers;
    private Answers answer;

    private Question currentQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred);
        textViewQuestion = findViewById(R.id.text_view_question);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonConfirmNext = findViewById(R.id.preferredSearch);

        answered = false;
        accessQuestions = new AccessQuestions();
        answers = new ArrayList<>();
        questionList = accessQuestions.getQuestions();
        questionTotal = accessQuestions.getTotalQuestions();
        questionCount = 0;
        showNextQuestion();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        insertAnswer();
                    }else{
                        Toast.makeText(PreferredActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion(){
        rbGroup.clearCheck();

        if(questionCount < questionTotal){
            currentQuestion = questionList.get(questionCount);
            textViewQuestion.setText(currentQuestion.getQuestion());
            System.out.println(questionCount);
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            questionCount++;
            answered = false;
            buttonConfirmNext.setText("Confirm");
        }else{
            finishQuestions();
        }

    }
    private void insertAnswer(){
        answered = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNumber = rbGroup.indexOfChild(rbSelected);
        answers.add(answerNumber);
        if(questionCount<questionTotal){
            buttonConfirmNext.setText("Next");
        }else{
            buttonConfirmNext.setText("Finish");
        }
        showNextQuestion();
    }
    private void finishQuestions(){
        answer = new Answers(answers);
        Intent intent = new Intent(PreferredActivity.this, PreferredSearchActivity.class);
        //intent.putExtra("Answers", answer);
        PreferredActivity.this.startActivity(intent);


    }



}
