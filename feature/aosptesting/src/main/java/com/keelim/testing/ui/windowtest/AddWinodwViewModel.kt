/*
 * Designed and developed by 2020 keelim (Jaehyun Kim)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.keelim.testing.ui.windowtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddWinodwViewModel : ViewModel() {
  private var _counter = MutableLiveData<Int>()
  val counter: LiveData<Int> = _counter

  init {
    _counter.value = 0
  }

  fun increase() {
    _counter.value = _counter.value!! + 500
  }

  fun decrease() {
    _counter.value = _counter.value!! - 500
    if (_counter.value!! < 0) _counter.value = 0
  }
}
