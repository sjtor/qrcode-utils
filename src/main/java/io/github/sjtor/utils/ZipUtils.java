package io.github.sjtor.utils;

import io.github.sjtor.core.ZipBytesEntry;
import io.github.sjtor.core.ZipFileEntry;
import io.github.sjtor.core.ZipISEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * zip 打包工具
 *
 * @author shijie
 * @since 2022-07-17
 **/
public final class ZipUtils {

    private ZipUtils() {}

    private static final Logger logger = LoggerFactory.getLogger(ZipUtils.class);

    /**
     * 打包文件到zip包中
     * @param os zip 包输出流
     * @param entries 欲打包的
     */
    public static void compress(OutputStream os, ZipISEntry... entries) throws IOException {
        if (entries == null || entries.length == 0) {
            logger.info("no file need compress");
            return;
        }
        ZipOutputStream out = new ZipOutputStream( os );
        BufferedOutputStream bos = new BufferedOutputStream(out);
        for (ZipISEntry entry: entries) {
            out.putNextEntry(new ZipEntry(entry.getName()));
            write(bos, entry.getInputStream());
        }
        out.finish();
        bos.close();
        out.close();
    }

    /**
     * 打包文件到zip包中
     * @param os zip 包输出流
     * @param entries 欲打包的
     */
    public static void compress(OutputStream os, ZipFileEntry... entries) throws IOException {
        if (entries == null || entries.length == 0) {
            logger.info("no file need compress");
            return;
        }
        ZipOutputStream out = new ZipOutputStream( os );
        BufferedOutputStream bos = new BufferedOutputStream(out);
        for (ZipFileEntry entry: entries) {
            out.putNextEntry(new ZipEntry(entry.getName()));
            write(bos, entry.getFile());
        }
        out.finish();
        bos.close();
        out.close();
    }

    /**
     * 打包文件到zip包中
     * @param os zip 包输出流
     * @param entries 欲打包的
     */
    public static void compress(OutputStream os, ZipBytesEntry... entries) throws IOException {
        if (entries == null || entries.length == 0) {
            logger.info("no file need compress");
            return;
        }
        ZipOutputStream out = new ZipOutputStream( os );
        BufferedOutputStream bos = new BufferedOutputStream(out);
        for (ZipBytesEntry entry: entries) {
            out.putNextEntry(new ZipEntry(entry.getName()));
            write(bos, entry.getBytes());
        }
        out.finish();
        bos.close();
        out.close();
    }

    private static void write(BufferedOutputStream bos, byte[] file) throws IOException{
        if (file == null || file.length == 0) {
            throw new IllegalArgumentException("被压缩文件不存在！");
        }
        InputStream is = new ByteArrayInputStream(file);
        write(bos, is);
    }

    private static void write(BufferedOutputStream bos, File file) throws IOException{
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("被压缩文件不存在！");
        }
        InputStream is = Files.newInputStream(file.toPath());
        write(bos, is);
    }

    private static void write(BufferedOutputStream bos, InputStream bis) throws IOException{
        int bytesRead;
        while ( (bytesRead = bis.read()) != -1 ) {
            bos.write(bytesRead);
        }
        bis.close();
        bos.flush();
    }

}
