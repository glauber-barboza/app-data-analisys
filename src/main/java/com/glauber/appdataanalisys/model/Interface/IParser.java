package com.glauber.appdataanalisys.model.Interface;

import java.util.StringTokenizer;

public interface IParser<T extends IModel> {

    T parse(String line,Integer index);

    default StringTokenizer split(String line) {
        return  new StringTokenizer(line, "ç");
    }

    default StringTokenizer split(String line,String delim) {
        return  new StringTokenizer(line, delim);
    }
}
