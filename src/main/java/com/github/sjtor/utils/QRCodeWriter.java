package com.github.sjtor.utils;

import com.google.zxing.common.BitMatrix;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 二维码图片写入工具
 *
 * @author shijie
 * @since 2022-07-17
 **/
public final class QRCodeWriter {

    private QRCodeWriter() {}

    private static final int BLACK = 0xFF000000;

    private static final int WHITE = 0xFFFFFFFF;

    /**
     * 生成二维码图片
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK: WHITE);
            }
        }
        return image;
    }

    /**
     * 给图片加注释
     * @param qrCode 二维码
     * @param desc 注释
     * @return
     */
    public static BufferedImage addDesc(BufferedImage qrCode, String desc) throws IOException {
        if (qrCode.getWidth() < desc.length() * 8) {
            throw new IOException("二维码太小，无法正常添加注释");
        }
        BufferedImage image = new BufferedImage(qrCode.getWidth(), qrCode.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.drawImage(qrCode, 0, 0, null);
        graphics.fillRect(0, 0, qrCode.getWidth(),16);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("宋体", Font.BOLD, 14));
        int start = (qrCode.getWidth() - desc.length() * 8) / 2;
        graphics.drawString(desc, start, qrCode.getHeight() - 24);
        graphics.dispose();
        return image;
    }
}
