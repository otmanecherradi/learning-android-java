package com.dev.schoolmanagement.viewModels;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dev.schoolmanagement.models.Group;
import com.dev.schoolmanagement.models.Intern;
import com.dev.schoolmanagement.models.Module;

import java.util.ArrayList;

public class AppViewModel {
    static ArrayList<Group> groups = new ArrayList<>();
    static ArrayList<Module> modules = new ArrayList<>();
    static ArrayList<Intern> interns = new ArrayList<>();

    public static ArrayList<Group> getGroups() {
        return groups;
    }
    public static ArrayList<Module> getModules() {
        return modules;
    }
    public static ArrayList<Intern> getInterns() {
        return interns;
    }

    @Nullable
    public static Group searchGroup(String name) {
        for (Group group : groups) {
            if (group.getName().equals(name)) {
                return group;
            }
        }
        return null;
    }

    @Nullable
    public static Module searchModule(String name) {
        for (Module module : modules) {
            if (module.getName().equals(name)) {
                return module;
            }
        }
        return null;
    }

    @Nullable
    public static Intern searchIntern(String cin) {
        for (Intern intern : interns) {
            if (intern.getName().equals(cin)) {
                return intern;
            }
        }
        return null;
    }

    @NonNull
    public static Boolean addGroup(@NonNull Group group) {
        if (searchGroup(group.getName()) == null) {
            groups.add(group);
            return true;
        }
        return false;
    }

    @NonNull
    public static Boolean addModule(@NonNull Module module) {
        if (searchModule(module.getName()) == null) {
            modules.add(module);
            return true;
        }
        return false;
    }

    @NonNull
    public static Boolean addIntern(@NonNull Intern intern) {
        if (searchModule(intern.getCin()) == null) {
            interns.add(intern);
            return true;
        }
        return false;
    }

    @NonNull
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

    public static void deleteGroup(@NonNull Group group) {
        final Group grp = searchGroup(group.getName());
        if (grp != null) {
            groups.remove(grp);
        }
    }

    public static void deleteModule(@NonNull Module module) {
        final Module mdl = searchModule(module.getName());
        if (mdl != null) {
            modules.remove(mdl);
        }
    }

    public static void deleteIntern(@NonNull Intern intern) {
        final Intern itr = searchIntern(intern.getName());
        if (itr != null) {
            interns.remove(itr);
        }
    }
}
