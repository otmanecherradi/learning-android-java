package com.dev.schoolmanagement.viewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dev.schoolmanagement.models.Group;
import com.dev.schoolmanagement.models.enums.Branch;
import com.dev.schoolmanagement.models.enums.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class GroupViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Group>> mutableLiveData;
    private final ArrayList<Group> mGroupList;

    public GroupViewModel() {
        mGroupList = new ArrayList<>();
        mGroupList.add(new Group("TDI201", Branch.TDI, Level.SpecializedTechnician));
    }

    public MutableLiveData<ArrayList<Group>> getGroupList() {
        if (mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>(mGroupList);
        }
        return mutableLiveData;
    }

    public Group searchGroupByUuid(UUID uuid) {
        for (Group group : mGroupList) {
            if (group.getId().equals(uuid)) {
                return group;
            }
        }
        return null;
    }


    public Group searchGroupByName(String name) {
        for (Group group : mGroupList) {
            if (group.getName().equals(name)) {
                return group;
            }
        }
        return null;
    }

    public void addGroup(@NonNull Group group) {
        mGroupList.add(group);
    }

    public void updateGroup(UUID uuid, @NonNull Group group) {

    }

    public void deleteGroup(Group group) {
        mGroupList.remove(group);
    }
}