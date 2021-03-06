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
package com.keelim.benchmark.ui.benchmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.keelim.benchmark.data.Event

class BenchmarkViewModel : ViewModel() {

  private val _viewEvent = MutableLiveData<Event<Any>>()
  val viewEvent: LiveData<Event<Any>>
    get() = _viewEvent

  fun viewEvent(content: Any) {
    _viewEvent.value = Event(content)
  }

  companion object {
    const val VIEW_1 = "list"
    const val VIEW_2 = "http"
    const val VIEW_3 = "storage"
    const val VIEW_4 = "chart"
  }

  fun start1() = viewEvent(VIEW_1)

  fun start2() = viewEvent(VIEW_2)

  fun start3() = viewEvent(VIEW_3)

  fun start4() = viewEvent(VIEW_4)
}
