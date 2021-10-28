package com.kttest2.operatorOverrideAppoint.lazyby

class Person1(val name:String, var age:Int) {
    private var _emails:List<Email>?=null

    //lazy函数线程安全
    val emails by lazy {
        loadEmails(this)
    }


    // 老的委托方式，写起来麻烦，还有线程安全问题
//    get(){
//        if(_emails==null){
//            _emails=loadEmails(this)
//        }
//        return _emails!!
//    }



    fun loadEmails(person:Person1):List<Email>{
        println("Load emails for ${person.name}")
        return listOf(Email(), Email())

    }
}