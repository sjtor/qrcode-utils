package io.github.sjtor.utils;

import io.github.sjtor.core.ZipBytesEntry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * zip压缩包工具测试类
 *
 * @author shiji
 * @since 2022-07-17
 **/
public class ZipUtilTest {

    private static final String content = "https://github.com/sjtor/qrcode-utils.git";

    public static void main(String[] args) throws IOException {
        ServiceImpl impl = new ServiceImpl();
        ZipBytesEntry e1 = impl.testGenerateQRCodeImage(content, "bare");
        ZipBytesEntry e2 = impl.testGenerateQRCodeImage(content, "qrcode-utils", "full");

        File file = new File("./qrcode.zip");
        OutputStream os = new FileOutputStream(file);
        ZipUtils.compress(os, e1, e2);
        os.close();
    }

    static class ServiceImpl {

        private ServiceImpl() {}

        private ZipBytesEntry testGenerateQRCodeImage(String content, String fileName) throws IOException {
            byte[] file = QRCodeUtils.generateQRCodeBytes(content);
            return new ZipBytesEntry(fileName + "." + QRCodeUtils.FORMAT, file);
        }

        private ZipBytesEntry testGenerateQRCodeImage(String content, String desc, String fileName) throws IOException {
            byte[] file = QRCodeUtils.generateQRCodeBytes(content, desc);
            return new ZipBytesEntry(fileName + "." + QRCodeUtils.FORMAT, file);
        }

    }

}
