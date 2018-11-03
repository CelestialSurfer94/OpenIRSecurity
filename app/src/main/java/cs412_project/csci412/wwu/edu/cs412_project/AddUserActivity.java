package cs412_project.csci412.wwu.edu.cs412_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class AddUserActivity extends AppCompatActivity {
    private EditText emailText;
    private EditText password0Text;
    private EditText password1Text;
    private Button createUserButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emailText = findViewById(R.id.createUserEmailText);
        password0Text = findViewById(R.id.createUserPasswordText0);
        password1Text = findViewById(R.id.createUserPasswordText1);
        createUserButton = findViewById(R.id.createUserButton);
        mAuth = FirebaseAuth.getInstance();
    }

    public void createUser(View v){
        String email = emailText.getText().toString();
        String pass0 = password0Text.getText().toString();
        String pass1 = password1Text.getText().toString();

        if(!pass0.equals(pass1)){ //passwords do not match
            Toast.makeText(this, "passwords do not match, try again", Toast.LENGTH_SHORT).show();
            password0Text.setText("");
            password1Text.setText("");
        } else {
            mAuth.createUserWithEmailAndPassword(email, pass0)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                //TODO ADD BETTER ERROR HANDLING:
                                Toast.makeText(AddUserActivity.this, task.getException().toString(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

}
