package com.keelim.hardware.making

class OpenItem {
    constructor(titleStr: String?, descStr: String?) {
        this.title = titleStr
        this.desc = descStr
    }

    constructor() {
        title = null
        desc = null
    }

    var title: String? = null
    var desc: String? = null
}