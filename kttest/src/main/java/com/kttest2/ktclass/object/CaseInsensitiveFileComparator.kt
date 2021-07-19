package com.kttest2.ktclass.`object`

import java.io.File

/**
 * 对象声明同样可以继承自类或接口。这通常在你使用的框架需要去实现一个接口，
 * 但是你的实现并不包含任何状态的时候使用。例如Comparator，只比较大小，不保存状态。
 */
object CaseInsensitiveFileComparator : Comparator<File> {

    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path,ignoreCase = true)
    }

}

