package com.dangolawski.services;


import com.dangolawski.models.Post;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


// BASED ON : https://medium.com/@prashant.nair2050/hands-on-outlier-detection-and-treatment-in-python-using-1-5-iqr-rule-f9ff1961a414
public class OverfittingService {

    public void refactorData(ArrayList<Post> posts) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ArrayList<Post> sortedPosts = copyArrayList(posts);
        for (String attribute : Globals.allowedColumns) {
            sortPostsByAttribute(sortedPosts, attribute);
            int[] quartiles = getQuartiles(sortedPosts, attribute);
            int iqr = quartiles[1] - quartiles[0];
            double lowerRange = quartiles[0] - 1.5 * iqr;
            double upperRange = quartiles[1] + 1.5 * iqr;
            removeOutliers(lowerRange, upperRange, posts, attribute);
        }
    }

    private void removeOutliers(double lowerRange, double upperRange, ArrayList<Post> sortedPosts, String attribute) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Post post : sortedPosts) {
            int value = (int) post.getClass().getMethod("get"+attribute).invoke(post);
            if (value <= lowerRange || value >= upperRange)
                post.getClass().getMethod("set"+attribute, String.class).invoke(post, "-1");
        }
    }

    private int[] getQuartiles(ArrayList<Post> sortedPosts, String attribute) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int arraySize = sortedPosts.size();
        int[] quartiles = new int[2];
        int firstQuartileIndex = (int) Math.floor((double) arraySize * 0.25);
        quartiles[0] = (int) sortedPosts.get(firstQuartileIndex).getClass().
                getMethod("get"+attribute).invoke(sortedPosts.get(firstQuartileIndex));
        int secondQuartileIndex = (int) Math.floor((double) arraySize * 0.75);
        quartiles[1] = (int) sortedPosts.get(secondQuartileIndex).getClass().
                getMethod("get"+attribute).invoke(sortedPosts.get(secondQuartileIndex));
        return quartiles;
    }

    private void sortPostsByAttribute(ArrayList<Post> sortedPosts, String attribute) {
        sortedPosts.sort((o1, o2) -> {
            try {
                return Integer.compare((int) o1.getClass().getMethod("get"+attribute).invoke(o1),
                        (int) o2.getClass().getMethod("get"+attribute).invoke(o2));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        });
    }

    private ArrayList<Post> copyArrayList(ArrayList<Post> posts) {
        ArrayList<Post> copiedArray = new ArrayList<>();
        for (Post post : posts) copiedArray.add(post);
        return copiedArray;
    }

}
