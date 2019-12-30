package com.neeson.example.util;

import com.neeson.example.entity.UserDto;

import java.util.Collection;
import java.util.List;

public class ResultUtil {
    public static void clearGm(UserDto userDto) {
        if (userDto != null) {
            userDto.setPassword(null);
            userDto.setGroupMemberList(null);
        }
    }

    public static boolean checkIsEmpty(Collection list) {
        return list == null || list.isEmpty();
    }

    public static boolean checkNotEmpty(Collection list) {
        return !checkIsEmpty(list);
    }

    public static boolean checkNotIn(List<Integer> groupIds, Integer groupId) {
        for (Integer id : groupIds) {
            if (id == groupId) return false;
        }
        return true;
    }
}
