package com.kttest2.operatorOverrideAppoint.lazyby

import java.beans.PropertyChangeSupport
import kotlin.reflect.KProperty

class ObservableProperty(var propValue:Int,val changeSupport:PropertyChangeSupport) {
   operator fun getValue(p:PersonBy, prop:KProperty<*>):Int{
       println("getvalue:${prop.name}")
      return propValue
   }
   operator fun setValue(p:PersonBy, prop: KProperty<*>, newValue:Int){
       println("setValue :${prop.name}")
        val oldValue=propValue
        propValue=newValue
        changeSupport.firePropertyChange(prop.name,oldValue,newValue)
    }
}