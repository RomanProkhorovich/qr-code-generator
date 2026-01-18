package org.romanus.qrcodegenerator.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenerateQrVo {
    private String content;
    private String filename;
    @JsonProperty(defaultValue = "0x000000")
    private int onColor;
    @JsonProperty(defaultValue = "0xFFFFFF")
    private int offColor;
    private int width;
    private int height;

    public GenerateQrVo() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getOnColor() {
        return onColor;
    }

    public void setOnColor(int onColor) {
        this.onColor = onColor;
    }

    public int getOffColor() {
        return offColor;
    }

    public void setOffColor(int offColor) {
        this.offColor = offColor;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
