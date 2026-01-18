package org.romanus.qrcodegenerator.util;
public class FileNameUtils {

    public static String safeFileName(String originalName) {
        if (originalName == null || originalName.trim().isEmpty()) {
            return "file";
        }
        String safe = originalName
                .replace("\\", "_")
                .replace("/", "_")
                .replace(":", "_")
                .replace("*", "_")
                .replace("?", "_")
                .replace("\"", "_")
                .replace("<", "_")
                .replace(">", "_")
                .replace("|", "_");


        safe = safe.trim();
        if (safe.startsWith(".")) {
            safe = safe.substring(1);
        }

        return safe;
    }
}
