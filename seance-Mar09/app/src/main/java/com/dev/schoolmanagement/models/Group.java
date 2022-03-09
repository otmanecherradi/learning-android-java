package com.dev.schoolmanagement.models;

import androidx.annotation.NonNull;

import com.dev.schoolmanagement.models.enums.Branch;
import com.dev.schoolmanagement.models.enums.Level;

public class Group {
    String name;
    Branch branch;
    Level level;

    public Group() {
    }

    public Group(String nom, Branch branch, Level level) {
        this.name = nom;
        this.branch = branch;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @NonNull
    @Override
    public String toString() {
        return "Group{" +
                "nom='" + name + '\'' +
                ", branch=" + branch +
                ", level=" + level +
                '}';
    }
}

