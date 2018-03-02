package com.personaltrainer

import android.databinding.BaseObservable
import kotlin.reflect.KProperty

/**
 * Created by Honza on 26.02.2018.
 */

class BindableDelegate<in R: BaseObservable, T: Any>(private var value: T, private val bindingRes: Int) {

    operator fun getValue(thisRef: R, property: KProperty<*>) : T = value

    operator fun setValue(thisRef: R, property: KProperty<*>, value: T) {
        this.value = value
        thisRef.notifyPropertyChanged(bindingRes)
    }
}

class NullableBindableDelegate<in R: BaseObservable, T: Any?>(private var value: T?, private val bindingRes: Int) {

    operator fun getValue(thisRef: R, property: KProperty<*>) : T? = value

    operator fun setValue(thisRef: R, property: KProperty<*>, value: T?) {
        this.value = value
        thisRef.notifyPropertyChanged(bindingRes)
    }
}
