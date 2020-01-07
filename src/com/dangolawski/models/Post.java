package com.dangolawski.models;

import com.dangolawski.services.Globals;

public class Post {

    private final int one = 1;

    private int tag;

    private int reputation;

    private int answers;

    private int views;

    // SETTERS //

    public void setTag(String tag) {
        this.tag = Globals.tagValuse.indexOf(tag)+1;
    }

    public void setReputation(String reputation) {
        this.reputation = Integer.parseInt(reputation);
    }

    public void setAnswers(String answers) {
        this.answers = Integer.parseInt(answers);
    }

    public void setViews(String views) {
        this.views = Integer.parseInt(views);
    }

    // GETTERS //

    public int getOne() { return one; }

    public int getTag() {
        return tag;
    }

    public int getReputation() {
        return reputation;
    }

    public int getAnswers() {
        return answers;
    }

    public int getViews() {
        return views;
    }
}
