package net.mindview.util;

import java.util.Date;
import java.util.Objects;

/**
 * Created by wangyongchao on 16/11/15.
 */

public class Test {

    public static void main(String[] args) {
        TwoTuple twoTuple = new TwoTuple(new String("jj"),new Object());
        System.out.println(twoTuple);

        ThreeTuple threeTuple = new ThreeTuple(new String("ff"), new Thread(), new Date());
        System.out.println(threeTuple);
    }
}
