package ui.entiity;

/**
 * Created by xuchichi on 2017/12/7.
 */
public class Tab {
    private String title;
    private Class fragment;

    public Tab(String title, Class fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
