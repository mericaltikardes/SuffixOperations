package com.mericaltikardes.suffixoperations.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.UUID;

@Document("SuffixWords")
public class SuffixDatas {
    @Id
    private String id = UUID.randomUUID().toString();

    private List<String> myStrings;

    private String result;

    private double responseTime;

    public SuffixDatas(List<String> myStrings, String result,Double responseTime) {
        this.myStrings = myStrings;
        this.result = result;
        this.responseTime=responseTime;
    }

    public SuffixDatas() {
    }

    public List<String> getMyStrings() {
        return myStrings;
    }

    public void setMyStrings(List<String> myStrings) {
        this.myStrings = myStrings;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
