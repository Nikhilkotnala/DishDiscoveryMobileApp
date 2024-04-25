package uk.ac.tees.mad.D3939804

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import uk.ac.tees.mad.D3939804.databinding.ActivityLoginBinding
import uk.ac.tees.mad.D3939804.databinding.ActivityRegisterBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    val mAuth = FirebaseAuth.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonLoginLogin.setOnClickListener({
            loginClicked()
        })
        binding.tvDontHaveAccount.setOnClickListener({
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        })
    }

    private fun loginClicked() {
        val email = binding.edEmailLogin.text.toString().trim()
        val password = binding.edPasswordLogin.text.toString()
        mAuth.signInWithEmailAndPassword(
            email, password
        ).addOnSuccessListener {
            // move to Home activity
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }.addOnFailureListener {
            Toast.makeText(this,"Invalid Login Credentials!", Toast.LENGTH_SHORT).show()
        }

    }
}