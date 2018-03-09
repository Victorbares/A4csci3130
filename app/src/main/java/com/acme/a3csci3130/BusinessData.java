package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class BusinessData implements Serializable {

    public  String uid;
    public  String name;
    public String bussnumber;
    public String primbus;
    public String addr;
    public String proTerr;


    public BusinessData() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public BusinessData(String uid, String name, String bussnumber, String primbus, String address, String proTerr){
        this.uid = uid;
        this.name = name;
        this.bussnumber = bussnumber;
        this.primbus = primbus;
        this.addr = address;
        this.proTerr = proTerr;
    }
    public BusinessData(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("bussnumber", bussnumber);
        result.put("primbus", primbus);
        result.put("addr", addr);
        result.put("proTerr",proTerr);

        return result;
    }
}
