package com.dangolawski.services;

import com.dangolawski.models.Post;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;


// TODO zaimplementowac algorytm usuwajacy outliery
public class DataFactory {

    String[] columnNames;

    List<String> forbiddenCollumns;

    public LinkedHashSet<Post> readDataAndCreatePlayers() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        LinkedHashSet<Post> postSet = new LinkedHashSet<>();
        forbiddenCollumns = Arrays.asList(Globals.forbiddenColumns);
        BufferedReader csvReader = new BufferedReader(new FileReader(Globals.filePath));
        String row;
        columnNames = csvReader.readLine().split(",");
        while((row = csvReader.readLine()) != null) {
            postSet.add(createNewPost(row.split(",")));
        }
        csvReader.close();
        return postSet;
    }

    private Post createNewPost(String[] data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Post post = new Post();
        for (int i = 0; i < columnNames.length; i++) {
            if (forbiddenCollumns.contains(columnNames[i])) continue;
            post.getClass().getMethod("set"+columnNames[i], String.class).invoke(post, data[i]);
        }
        return post;
    }
}
