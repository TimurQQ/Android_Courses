package ilyasov.androidstartapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ilyasov.androidstartapp.data.Student
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var student: Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.appComponent.inject(this)

        showInfo()
    }

    private fun showInfo() {
        Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show()
    }
}