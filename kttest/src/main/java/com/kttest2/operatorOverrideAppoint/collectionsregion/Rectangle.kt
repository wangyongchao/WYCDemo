package com.kttest2.operatorOverrideAppoint.collectionsregion

import com.kttest2.operatorOverrideAppoint.Point


data class Rectangle(var upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(point: Point): Boolean {
    // until 构建开区间，不包括最后一个数
    return point.x in upperLeft.x until lowerRight.x
            && point.y in upperLeft.y until lowerRight.y

}
