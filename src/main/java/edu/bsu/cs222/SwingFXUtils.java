package edu.bsu.cs222;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.SampleModel;
import java.awt.image.SinglePixelPackedSampleModel;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

//VIEW README.MD FILE TO READ THE COPYRIGHT NOTICE FOR THIS USED CLASS

public class SwingFXUtils {

    public static WritableImage toFXImage(BufferedImage bimg, WritableImage wimg) {
        int bw = bimg.getWidth();
        int bh = bimg.getHeight();
        switch (bimg.getType()) {
            case BufferedImage.TYPE_INT_ARGB:
            case BufferedImage.TYPE_INT_ARGB_PRE:
                break;
            default:
                BufferedImage converted =
                        new BufferedImage(bw, bh, BufferedImage.TYPE_INT_ARGB_PRE);
                Graphics2D g2d = converted.createGraphics();
                g2d.drawImage(bimg, 0, 0, null);
                g2d.dispose();
                bimg = converted;
                break;
        }
        if (wimg != null) {
            int iw = (int) wimg.getWidth();
            int ih = (int) wimg.getHeight();
            if (iw < bw || ih < bh) {
                wimg = null;
            } else if (bw < iw || bh < ih) {
                int[] empty = new int[iw];
                PixelWriter pw = wimg.getPixelWriter();
                PixelFormat<java.nio.IntBuffer> pf = PixelFormat.getIntArgbPreInstance();
                if (bw < iw) {
                    pw.setPixels(bw, 0, iw - bw, bh, pf, empty, 0, 0);
                }
                if (bh < ih) {
                    pw.setPixels(0, bh, iw, ih - bh, pf, empty, 0, 0);
                }
            }
        }
        if (wimg == null) {
            wimg = new WritableImage(bw, bh);
        }
        PixelWriter pw = wimg.getPixelWriter();
        DataBufferInt db = (DataBufferInt) bimg.getRaster().getDataBuffer();
        int[] data = db.getData();
        int offset = bimg.getRaster().getDataBuffer().getOffset();
        int scan = 0;
        SampleModel sm = bimg.getRaster().getSampleModel();
        if (sm instanceof SinglePixelPackedSampleModel) {
            scan = ((SinglePixelPackedSampleModel) sm).getScanlineStride();
        }

        PixelFormat<java.nio.IntBuffer> pf = (bimg.isAlphaPremultiplied() ?
                PixelFormat.getIntArgbPreInstance() :
                PixelFormat.getIntArgbInstance());
        pw.setPixels(0, 0, bw, bh, pf, data, offset, scan);
        return wimg;
    }
}