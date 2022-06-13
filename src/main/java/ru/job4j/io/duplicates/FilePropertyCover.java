package ru.job4j.io.duplicates;

import java.nio.file.Path;
import java.util.Objects;

public class FilePropertyCover {
    private Path filePath;
    private FileProperty fileProperty;


    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public FileProperty getFileProperty() {
        return fileProperty;
    }

    public void setFileProperty(FileProperty fileProperty) {
        this.fileProperty = fileProperty;
    }

    public FilePropertyCover(Path filePath, FileProperty fileProperty) {
        this.filePath = filePath;
        this.fileProperty = fileProperty;

    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof FilePropertyCover)) return false;
//        FilePropertyCover that = (FilePropertyCover) o;
//        return Objects.equals(fileProperty, that.fileProperty) && Objects.equals(getFilePath(), that.getFilePath());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(fileProperty, getFilePath());
//    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilePropertyCover)) return false;
        FilePropertyCover that = (FilePropertyCover) o;
        return Objects.equals(getFileProperty(), that.getFileProperty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileProperty());
    }

    @Override
    public String toString() {
        return "FilePropertyCover{" +
                "fileProperty=" + fileProperty +
                ", filePath=" + filePath +
                '}';
    }
}
