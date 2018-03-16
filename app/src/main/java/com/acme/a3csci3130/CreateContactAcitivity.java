package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField ,bussnumberField, addrField,primbusField,proTerrField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        bussnumberField= (EditText) findViewById(R.id.bussnumber);
        addrField = (EditText) findViewById(R.id.addr);
        proTerrField = (EditText) findViewById(R.id.proTerr);
    }

    /**
     * Method when te sumbit button if clicked
     * add the new business contact to firebase.
     * @param v
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String bussnumber = bussnumberField.getText().toString();
        String primbus = ((Spinner) findViewById(R.id.primbus)).getSelectedItem().toString();
        String addr = addrField.getText().toString();
        String proTerr = proTerrField.getText().toString();
        BusinessData person = new BusinessData(personID, name,bussnumber,primbus,addr,proTerr);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
