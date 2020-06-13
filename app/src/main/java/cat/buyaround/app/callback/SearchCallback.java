package cat.buyaround.app.callback;

import java.util.List;

public interface SearchCallback extends FailureCallback {
    void onSearch(List<Object> items);
}
