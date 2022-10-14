package com.silverline.constant;

import com.silverline.util.PropertyUtil;

public class Constants {
//todo delete this delete
        //todo static variable
        //todo add service package
        //todo last.java unit test??  3 ----> 4.5
        public static final int CABINS_COUNT = Integer.parseInt(PropertyUtil.getPropertyObject().getProperty("CONST.CABINS"));
        public static final int PASSENGERS_IN_A_CABIN = Integer.parseInt(PropertyUtil.getPropertyObject().getProperty("CONST.CABINS.PASSENGERS"));
}

