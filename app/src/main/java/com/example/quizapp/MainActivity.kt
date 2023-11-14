package com.example.quizapp

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.ui.theme.QuizAppTheme
import java.util.Date

class MainActivity : ComponentActivity() {

    var totalScore : Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        val radioGroup = findViewById<RadioGroup>(R.id.grp_1st)
        val clearButton:Button = findViewById(R.id.btnClear)
        val submitButton:Button = findViewById(R.id.btnSubmit)
        val chk1 : CheckBox = findViewById(R.id.chk1)
        val chk2 : CheckBox = findViewById(R.id.chk2)
        val chk3 : CheckBox = findViewById(R.id.chk3)
        val chk4 : CheckBox = findViewById(R.id.chk4)

        clearButton.setOnClickListener{
            radioGroup.clearCheck()
            chk1.isChecked = false
            chk2.isChecked = false
            chk3.isChecked = false
            chk4.isChecked = false

        }



        submitButton.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)

            alertDialogBuilder.apply {
                setTitle("Your Score")
                setMessage("Congratulations! You submitted on "+ Date() + "\nYou achieved "+ getScore(chk1,chk2) + "%")
                setPositiveButton("Close") { dialog, _ ->
                    dialog.dismiss()
                }
            }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }

            radioGroup.setOnCheckedChangeListener { group, selected ->
                when (selected) {
                    R.id.rdTrue -> {
                        totalScore++;
                    }

                    R.id.rdFalse -> {
                        if (totalScore > 0){
                            totalScore--;
                        }
                    }
                }
            }

    }

    private fun getScore(checkBox1: CheckBox, checkBox2: CheckBox): Int {
        var temp = 0
        if (checkBox1.isChecked){
            temp++
        }else if(temp > 0){
            temp--
        }
        if (checkBox2.isChecked){
            temp++
        }else if(temp > 0){
            temp--
        }

        val q2Score = temp/2
        return ((totalScore + q2Score)/2 ) * 100

    }
}

