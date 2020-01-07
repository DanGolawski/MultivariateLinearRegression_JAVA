package com.dangolawski.services;

import com.dangolawski.models.Post;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFactory {

    private List<String> forbiddenColumns;
    private BufferedReader csvReader;
    private String row;

    public DataFactory() {
        forbiddenColumns = Arrays.asList(Globals.forbiddenColumns);
    }

    public ArrayList<Post> readDataAndCreatePlayers() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ArrayList<Post> postArray = new ArrayList<>();
        csvReader = new BufferedReader(new FileReader(Globals.filePath));
        Globals.columnNames = csvReader.readLine().split(",");
        while((row = csvReader.readLine()) != null) postArray.add(createNewPost(row.split(",")));
        csvReader.close();
        createListOfAllowedColumnNames();
        return postArray;
    }

    private Post createNewPost(String[] data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Post post = new Post();
        for (int i = 0; i < Globals.columnNames.length; i++) {
            if (forbiddenColumns.contains(Globals.columnNames[i])) continue;
            post.getClass().getMethod("set"+Globals.columnNames[i], String.class).invoke(post, data[i]);
        }
        return post;
    }

    public void createListOfAllowedColumnNames() {
        Globals.allowedColumns = new ArrayList<String>(Arrays.asList(Globals.columnNames));
        Globals.allowedColumns.removeAll(Arrays.asList(Globals.forbiddenColumns));
    }
}
