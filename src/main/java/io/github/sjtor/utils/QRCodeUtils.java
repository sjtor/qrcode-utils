package io.github.sjtor.utils;

import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 二维码生成工具
 *
 * @author shijie
 * @since 2022-07-17
 **/
public final class QRCodeUtils {

    private QRCodeUtils() {}

    private static final Logger logger = LoggerFactory.getLogger(QRCodeUtils.class);

    /**
     * 生成二维码的默认边长，因为是正方形的，所以高度和宽度一致
     */
    private static final int DEFAULT_LENGTH = 300;

    /**
     * 生成二维码的格式
     */
    public static final String FORMAT = "jpg";

    /**
     * 根据内容生成二维码数据
     *
     * @param content 二维码文字内容[为了信息安全性，一般都要先进行数据加密]
     * @param length  二维码图片宽度和高度
     */
    private static BitMatrix createQRCodeMatrix(String content, int length) {
        return createQRCodeMatrix(content, length, ErrorCorrectionLevel.H);
    }

    private static BitMatrix createQRCodeMatrix(String content, int length, ErrorCorrectionLevel level) {
        Map<EncodeHintType, Object> hints = Maps.newEnumMap(EncodeHintType.class);
        // 设置字符编码
        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, level);

        try {
            return new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, length, length, hints);
        } catch (WriterException e) {
            throw new RuntimeException("二维码生成失败！", e);
        }
    }

    /**
     * 图片转 byte[]
     * @return 图片对应的 byte[]
     */
    private static byte[] imageToBytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, FORMAT, os);
        return os.toByteArray();
    }

    /**
     * 生成二维码图像
     * @param content 二维码要加密的字符串
     * @return 二维码图片
     */
    public static BufferedImage generateQRCodeImage(String content) {
        BitMatrix qrCodeMatrix = createQRCodeMatrix(content, DEFAULT_LENGTH);
        return QRCodeWriter.toBufferedImage(qrCodeMatrix);
    }

    /**
     * 生成二维码图像bytes
     * @param content 二维码要加密的字符串
     * @return 二维码图片
     */
    public static byte[] generateQRCodeBytes(String content) {
        try {
            BufferedImage image =  generateQRCodeImage(content);
            return imageToBytes(image);
        } catch (IOException e) {
            throw new RuntimeException("二维码生成失败！", e);
        }
    }

    /**
     * 生成自定义大小的二维码图像
     * @param content 二维码要加密的字符串
     * @param length 二维码边长
     * @return 二维码图片
     */
    public static BufferedImage generateQRCodeImage(String content, int length) {
        BitMatrix qrCodeMatrix = createQRCodeMatrix(content, length);
        return QRCodeWriter.toBufferedImage(qrCodeMatrix);
    }

    /**
     * 生成二维码图像bytes
     * @param content 二维码要加密的字符串
     * @return 二维码图片
     */
    public static byte[] generateQRCodeBytes(String content, int length) {
        try {
            BufferedImage image =  generateQRCodeImage(content, length);
            return imageToBytes(image);
        } catch (IOException e) {
            throw new RuntimeException("二维码生成失败！", e);
        }
    }

    /**
     * 生成自定义大小带注释的二维码图像
     * @param content 二维码要加密的字符串
     * @param desc 二维码注释
     * @return 二维码图片
     */
    public static BufferedImage generateQRCodeImage(String content, String desc) throws IOException {
        // 生成二维码图像
        BitMatrix qrCodeMatrix = createQRCodeMatrix(content, DEFAULT_LENGTH);
        BufferedImage qrCode = QRCodeWriter.toBufferedImage(qrCodeMatrix);
        return QRCodeWriter.addDesc(qrCode, desc);
    }

    /**
     * 生成二维码图像bytes
     * @param content 二维码要加密的字符串
     * @return 二维码图片
     */
    public static byte[] generateQRCodeBytes(String content, String desc) {
        try {
            BufferedImage image =  generateQRCodeImage(content, desc);
            return imageToBytes(image);
        } catch (IOException e) {
            throw new RuntimeException("二维码生成失败！", e);
        }
    }

    /**
     * 生成自定义大小带注释的二维码图像
     * @param content 二维码要加密的字符串
     * @param desc 二维码注释
     * @param length 二维码边长
     * @return 二维码图片
     */
    public static BufferedImage generateQRCodeImage(String content, String desc, int length) throws IOException {
        // 生成二维码图像
        BitMatrix qrCodeMatrix = createQRCodeMatrix(content, length);
        BufferedImage qrCode = QRCodeWriter.toBufferedImage(qrCodeMatrix);
        return QRCodeWriter.addDesc(qrCode, desc);
    }

    /**
     * 生成二维码图像bytes
     * @param content 二维码要加密的字符串
     * @return 二维码图片
     */
    public static byte[] generateQRCodeBytes(String content, String desc, int length) {
        try {
            BufferedImage image =  generateQRCodeImage(content, desc, length);
            return imageToBytes(image);
        } catch (IOException e) {
            throw new RuntimeException("二维码生成失败！", e);
        }
    }

}
