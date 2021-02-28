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
package com.keelim.hard.ui.fragments.battery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BatteryViewModel : ViewModel() {
  private var _level = MutableLiveData(0)

  private var _gauge = MutableLiveData<String>().apply {
    postValue("Battery Level: 0%")
  }
  val gauge: LiveData<String> = _gauge
  val level: LiveData<Int> = _level

  fun update(level: Int) {
    _gauge.postValue(":Battery Level: $level%")
  }

  fun updateLevel(level: Int) {
    _level.value?.plus(level)
  }
}
