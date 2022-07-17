package io.github.sjtor.core;

import java.io.File;
import java.io.Serializable;

/**
 * @author shijie
 * @since 2022-07-17
 **/
public class ZipFileEntry implements Serializable {

    private File file;

    public ZipFileEntry() {
    }

    public ZipFileEntry(File file) {
        this.file = file;
    }

    public String getName() {
        return file.getName();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
