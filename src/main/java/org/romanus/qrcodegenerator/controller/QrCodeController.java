package org.romanus.qrcodegenerator.controller;

import org.romanus.qrcodegenerator.util.QrCodeGenerator;
import org.romanus.qrcodegenerator.vo.FileWrapperVo;
import org.romanus.qrcodegenerator.vo.GenerateQrVo;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QrCodeController {

    @PostMapping("/generate")
    public ResponseEntity<FileWrapperVo> generateQrCode(@RequestBody GenerateQrVo vo) {
        FileWrapperVo qrCode = QrCodeGenerator.generate(vo.getContent(),
                vo.getFilename(),
                vo.getOnColor(),
                vo.getOffColor(),
                vo.getWidth(),
                vo.getHeight());

        return ResponseEntity.ok(qrCode);
    }

    @PostMapping(value = "/decode", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> decodeQrCode(byte[] content) {
        String decode = QrCodeGenerator.decode(content);
        return ResponseEntity.ok(decode);
    }
}
