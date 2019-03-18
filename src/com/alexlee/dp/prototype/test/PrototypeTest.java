package com.alexlee.dp.prototype.test;

import com.alexlee.dp.prototype.entity.CloneablePojo;
import com.alexlee.dp.prototype.entity.Son;

import java.io.*;

public class PrototypeTest {
    /**
     * 浅复制
     * @param pojo
     * @return
     */
    private static CloneablePojo shallowClone(CloneablePojo pojo) {
        try {
            CloneablePojo clonedPojo = (CloneablePojo) pojo.clone();
            return clonedPojo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 深克隆
     * @param pojo
     */
    public static CloneablePojo deepClone(CloneablePojo pojo) {
        try {
            OutputStream os = null;
            InputStream is = null;
            try {
                File file = new File("testObject." + CloneablePojo.class.getSimpleName());
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(pojo);
                oos.flush();
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                CloneablePojo clonedPojo = (CloneablePojo) ois.readObject();
                return clonedPojo;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        CloneablePojo pojo = new CloneablePojo();
        pojo.setName("张三");
        Son son = new Son();
        son.setName("张奻奻");
        pojo.setSon(son);
        CloneablePojo.InnerPojo inner = pojo.new InnerPojo();
        inner.setMsg("如花");
        pojo.setInner(inner);
        System.out.println("原对象："+pojo+"-名称："+pojo.getName()+"-老婆："+pojo.getInner().getMsg()+"-儿子："+pojo.getSon().getName());
//        浅克隆
//        CloneablePojo clonedPojo = shallowClone(pojo);
        CloneablePojo clonedPojo = deepClone(pojo);
        System.out.println("克隆对象："+clonedPojo+"-名称："+clonedPojo.getName()+"-老婆："+clonedPojo.getInner().getMsg()+"-儿子："+clonedPojo.getSon().getName());
//       改变原对象
        System.out.println("--------改变原对象------");
        pojo.setName("改名后的张三-张麻子");
        pojo.getInner().setMsg("张三的新老婆-PDD");
        pojo.getSon().setName("改名后的儿子-张二狗");
        System.out.println("改变后的对象："+pojo+"-名称："+pojo.getName()+"-老婆："+pojo.getInner().getMsg()+"-儿子："+pojo.getSon().getName());
        System.out.println("克隆对象："+clonedPojo+"-名称："+clonedPojo.getName()+"-老婆："+clonedPojo.getInner().getMsg()+"-儿子："+clonedPojo.getSon().getName());

    }
}
