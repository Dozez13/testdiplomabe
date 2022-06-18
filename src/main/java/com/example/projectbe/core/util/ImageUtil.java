package com.example.projectbe.core.util;

import com.example.projectbe.core.constant.CoreConstants;
import com.example.projectbe.core.dto.ShoesSimilarImageDto;
import com.example.projectbe.core.dto.ShoesSizeDto;
import com.example.projectbe.core.expection.FileNotFoundRuntimeException;
import com.example.projectbe.core.expection.IORuntimeException;
import com.example.projectbe.domain.enums.Color;
import com.example.projectbe.domain.enums.ModelCategory;
import com.example.projectbe.domain.enums.ModelType;
import ij.IJ;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

import javax.imageio.ImageIO;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;


public class ImageUtil {

    public static String base64ImageFromFilePath(String path) {
        return bytesToBase64(imageToByte(path));
    }

    public static byte[] imageToByte(String pathToFile) {
        byte[] bytes;
        try (RandomAccessFile f = new RandomAccessFile(pathToFile, "r")) {
            bytes = new byte[(int) f.length()];
            f.readFully(bytes);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundRuntimeException(e);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        return bytes;
    }

    public static String bytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }


    private static float compareImage(ImageProcessor biA, ImageProcessor biB) {
        if ((biA.getWidth() != biB.getWidth()) && biA.getHeight() != biB.getHeight()) {
            return 0.0F;
        }
        float diff = 0.0F;
        int[] r1 = new int[3];
        int[] r2 = new int[3];
        for (int i = 0; i < biA.getWidth(); i++) {
            for (int j = 0; j < biA.getHeight(); j++) {
                biA.getPixel(i, j, r1);
                biB.getPixel(i, j, r2);
                diff += Math.abs(r1[0] - r2[0]);
                diff += Math.abs(r1[1] - r2[1]);
                diff += Math.abs(r1[2] - r2[2]);
            }
        }

        return diff / (biA.getHeight() * biA.getWidth() * 3);

    }


    public static boolean hasSimilarImages(ShoesSimilarImageDto shoesSimilarImageDto, List<String> imagesPaths) {
        try {
            byte[] decodedImage = Base64.getDecoder().decode(shoesSimilarImageDto.getBase64Image());
            InputStream is = new ByteArrayInputStream(decodedImage);
            ImageProcessor similarImageProcessor = new ColorProcessor(ImageIO.read(is)).resize(CoreConstants.IMAGE_RESIZE_WIDTH, CoreConstants.IMAGE_RESIZE_HEIGHT);
            similarImageProcessor.findEdges();
            return imagesPaths.stream().anyMatch(imagePath -> {
                ImageProcessor imageProcessor = IJ.openImage(imagePath).getProcessor().resize(CoreConstants.IMAGE_RESIZE_WIDTH, CoreConstants.IMAGE_RESIZE_HEIGHT);
                imageProcessor.findEdges();
                return compareImage(similarImageProcessor, imageProcessor) <= CoreConstants.similarityEdge;
            });


        } catch (IOException e) {
            throw new IORuntimeException(e);
        }

    }

    public static String createImageAndReturnPath(String modelName,
                                                  ModelCategory modelCategory,
                                                  ModelType modelType,
                                                  Color color,
                                                  ShoesSizeDto size,
                                                  Double price,
                                                  Integer imageIndex,
                                                  String base64Image) {
        StringBuilder stringBuilder = new StringBuilder(CoreConstants.pathToProjectData);
        stringBuilder.append(modelCategory.getUiRepresentation()).append(File.separator);
        stringBuilder.append(modelType.getUiRepresentation()).append(File.separator);
        stringBuilder.append(modelName);
        try {
            if (!Files.exists(Paths.get(stringBuilder.toString()))) {
                Files.createDirectory(Paths.get(stringBuilder.toString()));
            }

        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        stringBuilder.append(File.separator).append(modelName);
        stringBuilder.append("_").append(imageIndex);
        stringBuilder.append("_").append(color.getUiRepresentation());
        stringBuilder.append("_").append(size.getCountryType());
        stringBuilder.append("_").append(size.getSize());
        stringBuilder.append("_").append(price);
        stringBuilder.append("_").append(".png");
        byte[] decodedImg = Base64.getDecoder()
                .decode(base64Image);
        Path destinationFile = Paths.get(stringBuilder.toString());
        try {
            if (!Files.exists(Paths.get(stringBuilder.toString()))) {
                Files.write(destinationFile, decodedImg);
            }

        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        return stringBuilder.toString();
    }
}
