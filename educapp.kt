package com.example.educapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_student.*
import kotlinx.android.synthetic.main.activity_teacher.*

data class Lesson(
    val title: String,
    val description: String
)

class LessonAdapter(private val lessons: List<Lesson>) :
    RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(lesson = lessons[position])
    }

    override fun getItemCount(): Int = lessons.size

    class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(lesson: Lesson) {
            itemView.tvLessonTitle.text = lesson.title
            itemView.tvLessonDescription.text = lesson.description
        }
    }
}

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val role = if (username.startsWith("teacher")) "teacher" else "student"

            if (role == "teacher") {
                startActivity(Intent(this, TeacherActivity::class.java))
            } else {
                startActivity(Intent(this, StudentActivity::class.java))
            }
        }
    }
}

class TeacherActivity : AppCompatActivity() {
    private val lessons = mutableListOf<Lesson>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)

        btnAddLesson.setOnClickListener {
            val title = etLessonTitle.text.toString()
            val description = etLessonDescription.text.toString()

            val lesson = Lesson(title, description)
            lessons.add(lesson)

            // Aqui você pode salvar a lição em um banco de dados (ex. Firebase)
            // Por enquanto, apenas adicionamos à lista local
        }
    }
}

class StudentActivity : AppCompatActivity() {
    private val lessons = mutableListOf<Lesson>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        // Simular algumas aulas
        lessons.add(Lesson("Matemática", "Introdução à Álgebra"))
        lessons.add(Lesson("História", "Revolução Industrial"))

        rvLessons.layoutManager = LinearLayoutManager(this)
        rvLessons.adapter = LessonAdapter(lessons)
    }
}

//Mainactivity.kt

activity_login.xml

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/etUsername"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Username" />

    <Button
        android:id="@+id/btnLogin"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Login" />
</LinearLayout>


activity_teacher.xml

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/etLessonTitle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Título da aula" />

    <EditText
        android:id="@+id/etLessonDescription"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Descrição da aula" />

    <Button
        android:id="@+id/btnAddLesson"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Adicionar Aula" />
</LinearLayout>

activity_student.xml

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rvLessons"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
</LinearLayout>

item_lesson.xml

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/tvLessonTitle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Título da aula"
        android:textStyle="bold"
        android:textSize="18sp" />

    <TextView
        android:id="@+id/tvLessonDescription"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Descrição da aula"
        android:textSize="16sp" />
</LinearLayout>



