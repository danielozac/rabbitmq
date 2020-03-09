package com.reply.to.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class NumberToMultiply implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    private int numberOne;
    private int numberTwo;

}
