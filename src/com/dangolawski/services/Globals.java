package com.dangolawski.services;

import com.dangolawski.models.Post;

import java.util.ArrayList;
import java.util.List;

public class Globals {

    public final static String filePath = "src/com/dangolawski/posts_score102.csv";

    public static final String[] forbiddenColumns = {"ID", "Username"};

    public static ArrayList<String> allowedColumns;

    public static final String tagValuse = "abcdefghijklmnopqrstuvwxyz";

    public static String[] columnNames;

    public static String dependentVariable;
}
