package com.alexlee.dp.prototype.entity;

import java.io.Serializable;

/**
 * 可克隆对象
 */
public class CloneablePojo implements Cloneable, Serializable {
    private String name;
    private InnerPojo inner;
    private Son son;

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public InnerPojo getInner() {
        return inner;
    }

    public void setInner(InnerPojo inner) {
        this.inner = inner;
    }

    public class InnerPojo implements Serializable{
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
