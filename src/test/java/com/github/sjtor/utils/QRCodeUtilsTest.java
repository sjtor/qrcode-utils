package com.github.sjtor.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 二维码工具测试类
 *
 * @author shiji
 * @since 2022-07-17
 **/
public class QRCodeUtilsTest {

    private static final String content = "https://github.com/sjtor/qrcode-utils.git";

    public static void main(String[] args) throws IOException {
        ServiceImpl impl = new ServiceImpl();
        impl.testGenerateQRCodeImage(content);
        impl.testGenerateQRCodeImage(content, "qrcode utils");
    }

    static class ServiceImpl {

        private ServiceImpl() {}

        private void testGenerateQRCodeImage(String content) throws IOException {
            File file1 = new File("./bare.jpg");
            BufferedImage image1 = QRCodeUtils.generateQRCodeImage(content);
            ImageIO.write(image1, QRCodeUtils.FORMAT, file1);
        }

        private void testGenerateQRCodeImage(String content, String desc) throws IOException {
            File file1 = new File("./full.jpg");
            BufferedImage image1 = QRCodeUtils.generateQRCodeImage(content, desc);
            ImageIO.write(image1, QRCodeUtils.FORMAT, file1);
        }

    }

}
