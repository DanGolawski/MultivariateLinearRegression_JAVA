package com.dangolawski;

import com.dangolawski.models.Post;
import com.dangolawski.services.DataFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
	    DataFactory dataFactory = new DataFactory();
	    LinkedHashSet<Post> posts = dataFactory.readDataAndCreatePlayers();
        System.out.println(posts);
    }
}
