package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField,bussnumberField, addrField,proTerrField;
    private Spinner primbusField;

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
        bussnumberField= (EditText) findViewById(R.id.bussnumber);
        addrField = (EditText) findViewById(R.id.addr);
        primbusField = (Spinner) findViewById(R.id.primbus);
        proTerrField = (EditText) findViewById(R.id.proTerr);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
            bussnumberField.setText(receivedPersonInfo.bussnumber);
            primbusField.setSelection(((ArrayAdapter)primbusField.getAdapter()).getPosition(receivedPersonInfo.primbus));
            //primbusField.item(receivedPersonInfo.primbus);// TODO Il faut ajuster le spinner a la value dans la database.
            addrField.setText(receivedPersonInfo.addr);
            proTerrField.setText(receivedPersonInfo.proTerr);
        }
    }

    public void updateContact(View v){
        String personID = receivedPersonInfo.uid;
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String bussnumber = bussnumberField.getText().toString();
        String primbus = ((Spinner) findViewById(R.id.primbus)).getSelectedItem().toString();
        String addr = addrField.getText().toString();
        String proTerr = proTerrField.getText().toString();
        Contact person = new Contact(personID, name, email,bussnumber,primbus,addr,proTerr);
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
