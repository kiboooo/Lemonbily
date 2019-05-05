package com.example.basemodule.bean;

/**
 * 决定RecyclerView 将要展示哪一种UI的
 *      Object 决定每个Item所具有数据集合；
 *
 *      */
public class UIBeans {

    private int uiType;
    private String titleContext;
    private Object object;

    public UIBeans() {
    }

    public UIBeans(int uiType, Object object) {
        this.uiType = uiType;
        this.object = object;
    }

    public UIBeans(int uiType, String titleContext, Object object) {
        this.uiType = uiType;
        this.titleContext = titleContext;
        this.object = object;
    }

    public String getTitleContext() {
        return titleContext;
    }

    public void setTitleContext(String titleContext) {
        this.titleContext = titleContext;
    }

    public int getUiType() {
        return uiType;
    }

    public void setUiType(int uiType) {
        this.uiType = uiType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
