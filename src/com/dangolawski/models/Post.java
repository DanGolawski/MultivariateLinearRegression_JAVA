package com.dangolawski.models;

import com.dangolawski.services.Globals;

public class Post {

    private int tag;

    private int reputation;

    private int answers;

    private int views;

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
