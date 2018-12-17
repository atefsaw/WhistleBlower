package com.whistleBlower.model;

import java.util.List;

public class GroupListWrapper {

    private List<Group> groupList;

    public GroupListWrapper(List<Group> groupList) {
        this.groupList = groupList;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

}
