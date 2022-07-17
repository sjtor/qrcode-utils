package io.github.sjtor.core;

import java.io.InputStream;
import java.io.Serializable;

/**
 * @author shijie
 * @since 2022-07-17
 **/
public class ZipISEntry implements Serializable {

    private String name;

    private InputStream inputStream;

    public ZipISEntry() {
    }

    public ZipISEntry(String name, InputStream inputStream) {
        this.name = name;
        this.inputStream = inputStream;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
