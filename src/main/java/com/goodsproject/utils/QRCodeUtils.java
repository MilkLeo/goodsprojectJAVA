package com.goodsproject.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;

public class QRCodeUtils {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    /**
     * 根据字符串生成对应的二维码图片png
     * 大小:200*200
     * <p>
     * content：要转换的内容
     * path：生成的二维码图片的绝对路径
     * filename: 生成的文件名
     */
    public static InputStream buildQuickMark(String content) throws Exception {
        if (content == null) {
            return null;
        }
        try {
            BitMatrix byteMatrix = new MultiFormatWriter().encode(new String(content.getBytes(), "iso-8859-1"),
                    BarcodeFormat.QR_CODE, 200, 200);


            BufferedImage image = toBufferedImage(byteMatrix);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(image, "jpg", imOut);
            InputStream is = new ByteArrayInputStream(bs.toByteArray());

            return is;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }
}
