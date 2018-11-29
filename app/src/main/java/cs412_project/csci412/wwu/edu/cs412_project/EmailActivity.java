package cs412_project.csci412.wwu.edu.cs412_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmailActivity extends AppCompatActivity {

    private EditText to_edit_text;
    private TextView subject_contents;
    private TextView message_contents;
    private String message;
    private String id;
    private String email;
    public Bundle bundle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        //get parameters sent through intent
        bundle = this.getIntent().getExtras();
        email = bundle.getString("email");
        id = bundle.getString("id");

        to_edit_text = findViewById(R.id.edit_text_to);
        subject_contents = findViewById(R.id.text_view_subject_contents);
        message_contents = findViewById(R.id.text_view_message_contents);


        to_edit_text.setText(email);
        message = "Hello, you device key for Open IR Security is: \n\n" + id + "\n\nPlease follow the " +
                "instructions to enter the key on your device.\nThank you for using Open" +
                " IR Security!";
        message_contents.setText(message);


        Button buttonSend = findViewById(R.id.button_send);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(v);
            }
        });


    }

    public void sendEmail(View v) {

        String recipientList = to_edit_text.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = subject_contents.getText().toString();
        String message = message_contents.getText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        emailIntent.setType("message/rfc822");
        startActivityForResult(Intent.createChooser(emailIntent, "Choose an email client"), 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            Intent returnIntent = new Intent();
            setResult(EmailActivity.RESULT_OK, returnIntent);
            finish();
        }
    }
}
