package org.romanus.qrcodegenerator.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import org.romanus.qrcodegenerator.vo.FileWrapperVo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class QrCodeGenerator {

    public static FileWrapperVo generate(String content, String filename, int onColor, int offColor, int width, int height) {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            filename = (filename == null ? getCurrentDateTime() : filename) + ".png";
            filename = FileNameUtils.safeFileName(filename);

            MatrixToImageConfig config = getConfig(onColor, offColor);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix, config);

            byte[] byteArray;

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(image, "PNG", baos);
                byteArray = baos.toByteArray();
                Files.write(Path.of("qrcodes/" + filename), byteArray);
            }
            return new FileWrapperVo(filename, ".png", byteArray);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("cannot write to file", e);
        }
    }

    public static String decode(byte[] content) {
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(content));
            LuminanceSource source = new BufferedImageLuminanceSource(image);

            HybridBinarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap bitmap = new BinaryBitmap(binarizer);

            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

            Result result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static MatrixToImageConfig getConfig(int onColor, int offColor) {
        return new MatrixToImageConfig(onColor, offColor);
    }

    private static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return now.format(formatter);
    }
}
