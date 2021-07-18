package com.kttest2.ktclass.data

/**
 * == 方式相当于java中的equals方法
 * === 比较引用相当于java中的==
 *
 * 数据类会自动生成 hashcode,equals，tostring方法，hashcode是各个主构造函数的属性的hashcode，
 * equals是比较各个主构造函数的属性的值。
 * equals和hashCode方法会将所有在主构造方法中声明的属性纳入考虑。生成的equals法会检测所有的属性的值是否相等
 * 。hashCode方法会返回一个根据所有属性生成的哈希值。
 * 请注意没有在主构造方法中声明的属性将不会加入到相等性检查和哈希值计算中去。

 */
 class Client(val name: String, val postalCode: Int) {

//    override fun hashCode(): Int {
//        return name.hashCode()*31+postalCode
//    }
//
//    override fun toString(): String = "Client(name=$name,postalCode=$postalCode)"
//
//    override fun equals(other: Any?): Boolean {
//        if (other == null || other !is Client) {
//            return false
//        }
//
//        return other.name == this.name && other.postalCode == this.postalCode
//    }

    fun copy(name:String=this.name,postalCode: Int=this.postalCode) = Client(name,postalCode)


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var client1 = Client("zhangsan", 333)
            var client2 = Client("zhangsan", 333)
            val hashSetOf = hashSetOf(client1)
            println(hashSetOf.contains(client2)) //返回false，在hashset中先比较hash值，再去比较equals

        }
    }
}