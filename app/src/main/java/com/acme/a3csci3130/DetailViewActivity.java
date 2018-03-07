package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
        }
    }

    public void updateContact(View v){
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        Contact person = new Contact(receivedPersonInfo.uid, name, email);
        appState.firebaseReference.child(receivedPersonInfo.uid).setValue(person);
        finish();
    }

    public void eraseContact(View v)
    {
        /*String personID = appState.firebaseReference.getKey(receivedPersonInfo.name);

        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        Contact person = new Contact(personID, name, email);

        appState.firebaseReference.child(personID).setValue(person);*/
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        finish();
    }
}
