package com.kttest2.operatorOverrideAppoint.lazyby

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

open class PropertyChangeAware {
    protected var changeSupport=PropertyChangeSupport(this)

    fun addPropertyChangeListener(listern:PropertyChangeListener){
        changeSupport.addPropertyChangeListener(listern)
    }

    fun removePropertyChangeListener(listern:PropertyChangeListener){
        changeSupport.removePropertyChangeListener(listern)
    }

}