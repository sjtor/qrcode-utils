package io.github.sjtor.core;

import java.io.Serializable;

/**
 * @author shijie
 * @since 2022-07-17
 **/
public class ZipBytesEntry implements Serializable {

    private String name;

    private byte[] bytes;

    public ZipBytesEntry() {
    }

    public ZipBytesEntry(String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
