package com.dev.schoolmanagement.viewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dev.schoolmanagement.models.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupViewModel extends ViewModel {

    private MutableLiveData<List<Group>> mutableLiveData;
    public List<Group> mGroupList;

    public GroupViewModel() {
        mGroupList = new ArrayList<>();
    }

    public MutableLiveData<List<Group>> getGroupList() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>(mGroupList);
        }
        return mutableLiveData;
    }

    public Group searchGroup(String name) {
        for (Group grp : mGroupList) {
            if (grp.getName().equals(name)) {
                return grp;
            }
        }
        return null;
    }

    public void addGroup(@NonNull Group group) {
        final Group grp = searchGroup(group.getName());
        if (grp == null) {
            mGroupList.add(group);
        }
    }

    public Boolean updateGroup(String name, @NonNull Group group) {
        final Group grp = searchGroup(name);
        if (grp != null) {
            grp.setName(group.getName());
            return true;
        }
        return false;
    }

    public Boolean deleteGroup(String name) {
        final Group grp = searchGroup(name);
        if (grp != null) {
            mGroupList.remove(grp);
            return true;
        }
        return false;
    }
}