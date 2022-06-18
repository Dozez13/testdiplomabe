package com.example.projectbe.core.constant;

import java.time.format.DateTimeFormatter;

public final class CoreConstants {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final String pathToProjectData = "D:\\projectDate\\";

    public static final Integer IMAGE_RESIZE_HEIGHT = 300;
    public static final Integer IMAGE_RESIZE_WIDTH = 300;
    public static final Float similarityEdge = 50.0F;
}
