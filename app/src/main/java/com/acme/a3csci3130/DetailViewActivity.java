package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DetailViewActivity extends Activity {

    private EditText nameField ,bussnumberField, addrField,proTerrField;
    private Spinner primbusField;

    BusinessData receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (BusinessData)getIntent().getSerializableExtra("BusinessData");

        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.name);
        bussnumberField= (EditText) findViewById(R.id.bussnumber);
        addrField = (EditText) findViewById(R.id.addr);
        primbusField = (Spinner) findViewById(R.id.primbus);
        proTerrField = (EditText) findViewById(R.id.proTerr);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            bussnumberField.setText(receivedPersonInfo.bussnumber);
            primbusField.setSelection(((ArrayAdapter)primbusField.getAdapter()).getPosition(receivedPersonInfo.primbus));
            //primbusField.item(receivedPersonInfo.primbus);// TODO Il faut ajuster le spinner a la value dans la database.
            addrField.setText(receivedPersonInfo.addr);
            proTerrField.setText(receivedPersonInfo.proTerr);
        }
    }

    /**
     * Update business contact on firebase base on the field of the view.
     * @param v
     */
    public void updateContact(View v){
        String personID = receivedPersonInfo.uid;
        String name = nameField.getText().toString();
        String bussnumber = bussnumberField.getText().toString();
        String primbus = ((Spinner) findViewById(R.id.primbus)).getSelectedItem().toString();
        String addr = addrField.getText().toString();
        String proTerr = proTerrField.getText().toString();
        BusinessData person = new BusinessData(personID, name,bussnumber,primbus,addr,proTerr);
        appState.firebaseReference.child(receivedPersonInfo.uid).setValue(person);
        finish();
    }

    /**
     * Delete the business contact on firebase.
     * @param v
     */
    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        finish();
    }
}
