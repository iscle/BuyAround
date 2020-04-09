package com.selepdf.hackovid.callback;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public interface SearchCallback extends FailureCallback {
    void onSearch(List<Object> items);
}
