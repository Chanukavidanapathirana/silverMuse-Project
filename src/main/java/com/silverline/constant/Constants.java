package com.silverline.constant;

import com.silverline.util.PropertyUtil;

public class Constants {

        public static final int CABINS_COUNT = Integer.parseInt(PropertyUtil.getPropertyObject().getProperty("CONST.CABINS"));
        public static final int PASSENGERS_IN_A_CABIN = Integer.parseInt(PropertyUtil.getPropertyObject().getProperty("CONST.CABINS.PASSENGERS"));
        public static final String File_Path = PropertyUtil.getPropertyObject().getProperty("CONST.FILEPATH");

}

