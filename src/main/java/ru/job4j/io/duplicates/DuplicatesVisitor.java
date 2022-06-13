package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    List<FileProperty> tempFPList = new ArrayList<>();
    Set<FileProperty> dublicateSet = new HashSet<>();


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toAbsolutePath().toString());

        if (tempFPList.isEmpty()) {
            tempFPList.add(fileProperty);
            System.out.println("Number of elements in tempFPCList is "+tempFPList.size());
        }else {
            for (int i = 0; i < tempFPList.size(); i++) {
                int lastFileSepInd1 = tempFPList.get(i).getName().lastIndexOf('\\');
                int lastFileSepInd2 = fileProperty.getName().lastIndexOf('\\');

                if (tempFPList.get(i).getName().substring(lastFileSepInd1 + 1)
                        .equals(fileProperty.getName().substring(lastFileSepInd2+1))) {
                    dublicateSet.add(fileProperty);
                    dublicateSet.add(tempFPList.get(i));
                    System.out.println("Number of elements in tempFPCList is "+tempFPList.size());
                } else {
                    tempFPList.add(fileProperty);

                }
            }
        }

//    List<FilePropertyCover> tempFPCList = new ArrayList<>();
//    Set<FilePropertyCover> dublicateSet = new HashSet<>();
//
//
//    @Override
//    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
//        FilePropertyCover filePropertyCover = new FilePropertyCover(file.toAbsolutePath(), fileProperty);
//
//        if (tempFPCList.isEmpty()) {
//            tempFPCList.add(filePropertyCover);
//            System.out.println("Number of elements in tempFPCList is "+tempFPCList.size());
//        }else {
//            for (int i = 0; i < tempFPCList.size(); i++) {
//                if (tempFPCList.get(i).getFileProperty().getName().toString().equals(fileProperty.getName().toString())) {
//                    dublicateSet.add(filePropertyCover);
//                    dublicateSet.add(tempFPCList.get(i));
//                    System.out.println("Number of elements in tempFPCList is "+tempFPCList.size());
//                } else {
//                    tempFPCList.add(filePropertyCover);
//
//                }
//            }
//        }

        return super.visitFile(file, attrs);

    }
}
