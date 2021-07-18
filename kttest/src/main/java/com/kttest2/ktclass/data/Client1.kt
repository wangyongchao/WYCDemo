package com.kttest2.ktclass.data

/**
 * == 方式相当于java中的equals方法
 * === 比较引用相当于java中的==
 */
class Client1(val name: String, val postalCode: Int) {

    override fun hashCode(): Int {
        return name.hashCode()*31+postalCode
    }

    override fun toString(): String = "Client(name=$name,postalCode=$postalCode)"

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client1) {
            return false
        }

        return other.name == this.name && other.postalCode == this.postalCode
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var client1 = Client1("zhangsan", 333)
            var client2 = Client1("zhangsan", 333)
            val hashSetOf = hashSetOf(client1)
            println(hashSetOf.contains(client2)) //返回false，在hashset中先比较hash值，再去比较equals

        }
    }
}