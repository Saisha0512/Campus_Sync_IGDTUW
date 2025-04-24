package com.example.campussyncigdtuw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.campussyncigdtuw.theme.SGPACalculatorTheme

class SGPACalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SGPACalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF8AFA6) // Medium pink background
                ) {
                    CGPA()
                }
            }
        }
    }
}

data class SubjectInput(
    var marksOrGrade: String = "",
    var credit: String = ""
)

@Composable
fun CGPA() {
    val subjects = remember { mutableStateListOf(SubjectInput()) }
    var sgpa by remember { mutableStateOf<Double?>(null) }
    var mode by remember { mutableStateOf("Marks") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("SGPA Calculator", fontSize = 26.sp, textAlign = TextAlign.Center)
        Text("IGDTUW", fontSize = 18.sp, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    mode = "Marks"
                    subjects.clear()
                    subjects.add(SubjectInput())
                    sgpa = null
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (mode == "Marks") Color(0xFFDC5088) else Color.Gray
                )
            ) {
                Text("Enter Marks", color = Color.White)
            }

            Button(
                onClick = {
                    mode = "Grades"
                    subjects.clear()
                    subjects.add(SubjectInput())
                    sgpa = null
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (mode == "Grades") Color(0xFFDC5088) else Color.Gray
                )
            ) {
                Text("Enter Grades", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Enter ${if (mode == "Marks") "Marks" else "Grades"} & Credits",
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(12.dp))

        Box(modifier = Modifier.weight(1f)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(subjects) { index, subject ->
                    SubjectInputField(subject, mode) { updatedSubject ->
                        subjects[index] = updatedSubject
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { subjects.add(SubjectInput()) },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC5088))
        ) {
            Text("+ Add Subject", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                sgpa = if (mode == "Marks") calculateSGPAFromMarks(subjects)
                else calculateSGPAFromGrades(subjects)
            },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC5088))
        ) {
            Text("Calculate SGPA", color = Color.White)
        }

        sgpa?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text("Your SGPA: %.2f".format(it), fontSize = 22.sp, color = Color(0xFF000000))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectInputField(subject: SubjectInput, mode: String, onValueChange: (SubjectInput) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = subject.marksOrGrade,
            onValueChange = { onValueChange(subject.copy(marksOrGrade = it)) },
            label = {
                Text(if (mode == "Marks") "Enter Marks (0-100)" else "Enter Grade (A+, A, B+, etc.)")
            },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = subject.credit,
            onValueChange = { onValueChange(subject.copy(credit = it)) },
            label = { Text("Enter Credit") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// MARKS-BASED SGPA
fun calculateSGPAFromMarks(subjects: List<SubjectInput>): Double {
    var totalPoints = 0.0
    var totalCredits = 0.0

    for (subject in subjects) {
        val marks = subject.marksOrGrade.toDoubleOrNull() ?: continue
        val credit = subject.credit.toDoubleOrNull() ?: continue
        val gradePoint = marksToGradePoint(marks)

        totalPoints += gradePoint * credit
        totalCredits += credit
    }

    return if (totalCredits > 0) totalPoints / totalCredits else 0.0
}

fun marksToGradePoint(marks: Double): Int {
    return when {
        marks in 93.0..100.0 -> 10
        marks in 85.0..92.0 -> 9
        marks in 77.0..84.0 -> 8
        marks in 69.0..76.0 -> 7
        marks in 61.0..68.0 -> 6
        marks in 53.0..60.0 -> 5
        marks in 45.0..52.0 -> 4
        else -> 0
    }
}

// GRADE-BASED SGPA
fun calculateSGPAFromGrades(subjects: List<SubjectInput>): Double {
    var totalPoints = 0.0
    var totalCredits = 0.0

    for (subject in subjects) {
        val gradePoint = gradeLetterToPoint(subject.marksOrGrade)
        val credit = subject.credit.toDoubleOrNull() ?: continue

        totalPoints += gradePoint * credit
        totalCredits += credit
    }

    return if (totalCredits > 0) totalPoints / totalCredits else 0.0
}

fun gradeLetterToPoint(grade: String): Int {
    return when (grade.trim().uppercase()) {
        "A+" -> 10
        "A" -> 9
        "B+" -> 8
        "B" -> 7
        "C+" -> 6
        "C" -> 5
        "D" -> 4
        else -> 0
    }
}
