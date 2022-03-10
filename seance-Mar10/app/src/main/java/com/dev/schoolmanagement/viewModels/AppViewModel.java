package com.dev.schoolmanagement.viewModels;

import com.dev.schoolmanagement.models.Group;

import java.util.ArrayList;

public class AppViewModel {
    static ArrayList<Group> groups = new ArrayList<>();

    public static ArrayList<Group> getGroups() {
        return groups;
    }

    public static Group searchGroup(String name) {
        for (Group group : groups) {
            if (group.getName().equals(name)) {
                return group;
            }
        }
        return null;
    }

    public static Boolean addGroup(Group group) {
        if (searchGroup(group.getName()) == null) {
            groups.add(group);
            return true;
        }
        return false;
    }

    public static Boolean updateGroup(String name, Group group) {
        final Group grp = searchGroup(name);
        if (grp != null) {
            int position = groups.indexOf(grp);
            grp.setName(group.getName());
            grp.setBranch(group.getBranch());
            grp.setLevel(group.getLevel());

            groups.remove(position);
            groups.add(position, grp);
            return true;
        }
        return false;
    }

    public static void deleteGroup(Group group) {
        final Group grp = searchGroup(group.getName());
        if (grp != null) {
            groups.remove(grp);
        }
    }
}
