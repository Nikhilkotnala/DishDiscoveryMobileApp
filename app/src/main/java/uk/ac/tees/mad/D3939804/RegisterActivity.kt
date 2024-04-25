package uk.ac.tees.mad.D3939804

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import uk.ac.tees.mad.D3939804.dao.User
import uk.ac.tees.mad.D3939804.databinding.ActivityHomeBinding
import uk.ac.tees.mad.D3939804.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity() {
    val mAuth = FirebaseAuth.getInstance();

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonRegisterRegister.setOnClickListener({
            registerClicked()
        })

    }

    fun registerClicked(){
        val user = User(
            binding.edFirstNameRegister.text.toString().trim(),
            binding.edLastNameRegister.text.toString().trim(),
            binding.edEmailRegister.text.toString().trim()
        )
        val password = binding.edPasswordRegister.text.toString()
        createAccountWithEmailAndPassword(user, password)
    }

    private fun createAccountWithEmailAndPassword(user: User, password: String) {
        mAuth.createUserWithEmailAndPassword(user.email, password).addOnCompleteListener(this){ task ->
            if(task.isSuccessful){
                val encodedUid = Base64.encodeToString(task.result?.user?.uid?.toByteArray(), Base64.NO_WRAP)
                Log.d("Firebase", "User created with encodedUID: $encodedUid")
                logIn()
            }else{
                Toast.makeText(this,"Registration Failed. Please check your input", Toast.LENGTH_SHORT)
            }
        }

    }

    private fun logIn() {
        // move to Home activity
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }


}