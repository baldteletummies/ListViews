package app.ij.listviews;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;

import app.ij.listviews.Models.User;

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

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private Button GglBtn;

    private FirebaseAuth mAuth;

    private static final int RC_SIGN_IN = 123;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore rootRef;
    private FirebaseAuth.AuthStateListener authStateListener;

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
        GglBtn = findViewById(R.id.login_btnGoogle);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, connectionResult -> Log.d("TAG", "You got an Error!"))
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

        GglBtn.setOnClickListener(view -> {
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });

        firebaseAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseFirestore.getInstance();

        authStateListener = firebaseAuth -> {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
/*
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

 */

        // Set on Click Listener on Sign-in button
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();
            }
        });
/*
        // Set on Click Listener on Google Sign-in button
        GglBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                googleSignIn();
            }
        });

 */
    }

    private void loginUserAccount()
    {

        // Take the value of two edit texts in Strings
        String email, password;
        email = Email.getText().toString();
        password = Password.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email!!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!!", Toast.LENGTH_LONG).show();
            return;
        }

        // sign-in existing user
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser user= mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Login successful!!", Toast.LENGTH_LONG).show();

                            // if sign-in is successful
                            // intent to home activity
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        else {
                            // sign-in failed
                            Toast.makeText(getApplicationContext(), "Login failed!!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    void googleSignIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (googleSignInResult.isSuccess()) {
                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();
                firebaseSignInWithGoogle(googleSignInAccount);
            } else {
                Log.d("TAG", "You got an Error!");
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);

    }
    private void firebaseSignInWithGoogle(GoogleSignInAccount googleSignInAccount) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                String email = googleSignInAccount.getEmail();
                String name = googleSignInAccount.getDisplayName();
                String uid = FirebaseInstanceId.getInstance().getToken();

                User user = new User(uid, name, email);
                rootRef.collection("users").document(email).set(user).addOnSuccessListener(aVoid -> Log.d("TAG", "User successfully created!"));
            } else {
                Log.d("TAG", "Failed with: " + task.getException());
            }
        });
    }
/*
    void  navigateToMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
    }

 */

    public void signUp(View view) {
        startActivity(new Intent(this, AuthActivity.class));
    }

    public void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}




