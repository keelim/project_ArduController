package com.keelim.hardware.making

class OpenListVIewItem {
    constructor(titleStr: String?, descStr: String?) {
        this.titleStr = titleStr
        this.descStr = descStr
    }

    constructor() {
        titleStr = null
        descStr = null
    }

    var titleStr: String? = null
    var descStr: String? = null
}