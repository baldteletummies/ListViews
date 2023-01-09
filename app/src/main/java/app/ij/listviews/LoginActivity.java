package app.ij.listviews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

  //  private static final String TAG = "LoginActivity";

  //  private FirebaseAuth mAuth;
 //   private FirebaseFirestore firestore;
/*
    private EditText Email;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        Email = (EditText) findViewById(R.id.Login_txtEmail);
        Password = (EditText) findViewById(R.id.Login_txtPassword);
    }


    private boolean formValid() {
        if (Email.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, "Email is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (Password.getText().toString().equals("")) {
            Toast.makeText(LoginActivity.this, "Password is empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void logIn(View view) {
        if (!formValid()) {
            return;
        }

        String email = Email.getText().toString();
        String password = Password.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this,
                        (task) -> {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Log in: success");
                                Toast.makeText(LoginActivity.this, "Log in success", Toast.LENGTH_SHORT).show();

                                FirebaseUser user = mAuth.getCurrentUser();
                                goToMainActivity();
                            } else {
                                Log.w(TAG, "Log in: failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Log in failed", Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    public void signUp(View view) {
        startActivity(new Intent(this, AuthActivity.class));
    }

    public void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
*/

    private EditText Email;
    private EditText Password;
    private Button Btn;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // taking instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        Email = findViewById(R.id.Login_txtEmail);
        Password = findViewById(R.id.Login_txtPassword);
        Btn = findViewById(R.id.Login_btnLogin);

        // Set on Click Listener on Sign-in button
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();
            }
        });
    }

    private void loginUserAccount()
    {


        // Take the value of two edit texts in Strings
        String email, password;
        email = Email.getText().toString();
        password = Password.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                           "Please enter email!!",
                           Toast.LENGTH_LONG)
                .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                           "Please enter password!!",
                           Toast.LENGTH_LONG)
                .show();
            return;
        }

        // signin existing user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                           "Login successful!!",
                                           Toast.LENGTH_LONG)
                                .show();



                            // if sign-in is successful
                            // intent to home activity
                            Intent intent
                                = new Intent(LoginActivity.this,
                                             MainActivity.class);
                            startActivity(intent);
                        }

                        else {

                            // sign-in failed
                            Toast.makeText(getApplicationContext(),
                                           "Login failed!!",
                                           Toast.LENGTH_LONG)
                                .show();




                        }
                    }
                });
    }
    public void signUp(View view) {
        startActivity(new Intent(this, AuthActivity.class));
    }

    public void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}




