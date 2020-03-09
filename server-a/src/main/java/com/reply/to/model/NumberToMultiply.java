package com.reply.to.model;

import lombok.AllArgsConstructor;
import lombok.Data;



import java.io.Serializable;

@Data
@AllArgsConstructor
public class NumberToMultiply implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    private int numberOne;
    private int numberTwo;

}
