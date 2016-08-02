package com.altoros;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by kamil.marabet on 8/1/2016.
 */
@JsonRootName("TextModel")
public class TextModel {

    String message;

    public TextModel() {
    }

    public TextModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
