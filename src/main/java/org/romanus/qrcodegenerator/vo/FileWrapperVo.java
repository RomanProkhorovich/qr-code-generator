package org.romanus.qrcodegenerator.vo;

public class FileWrapperVo {
    private final String fileName;
    private final String fileExtension;
    private final byte[] fileContent;

    public FileWrapperVo(String fileName, String fileExtension, byte[] fileContent) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.fileContent = fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public byte[] getFileContent() {
        return fileContent;
    }
}
