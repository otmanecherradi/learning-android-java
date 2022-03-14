package com.dev.schoolmanagement.ui.group;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dev.schoolmanagement.models.enums.Branch;

public class AddGroupFormViewModel extends ViewModel {
    MutableLiveData<String> name = new MutableLiveData<>();
    MutableLiveData<Branch> branch = new MutableLiveData<>();

    public LiveData<String> getName() {
        return name;
    }

    public void setName(String value) {
        name.setValue(value);
    }

    public LiveData<Branch> getBranch() {
        return branch;
    }

    public void setBranch(String value) {
        branch.setValue(Branch.valueOf(value));
    }

}
