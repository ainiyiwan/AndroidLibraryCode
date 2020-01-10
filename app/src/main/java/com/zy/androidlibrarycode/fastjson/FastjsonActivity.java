package com.zy.androidlibrarycode.fastjson;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zy.androidlibrarycode.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class FastjsonActivity extends AppCompatActivity {
    public static final String TAG = FastjsonActivity.class.getSimpleName();
    private List<Person> listOfPersons = new ArrayList<>();
    private String json = "[{\"FULL NAME\":\"John Doe\",\"AGE\":15},{\"FULL NAME\":\"\",\"AGE\":20}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastjson);

        toJsonString();
//        toJavaObject();
    }

    public void toJsonString() {
        listOfPersons.add(new Person(15, "John Doe"));
        listOfPersons.add(new Person(20, null));
        listOfPersons.add(new Person("null"));
        String jsonOutput = JSON.toJSONString(listOfPersons);
        String jsonOutput1 = JSON.toJSONString(listOfPersons,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullListAsEmpty);
        Log.e(TAG, jsonOutput);
        Log.e(TAG, jsonOutput1);
    }

    private void toJavaObject() {
        List<Person> list = JSON.parseArray(json, Person.class);
        List<Person> list1 = JSON.parseObject(json, new TypeReference<List<Person>>(){});
        Log.e(TAG, list.toString());
        Log.e(TAG, list1.toString());
    }

}
