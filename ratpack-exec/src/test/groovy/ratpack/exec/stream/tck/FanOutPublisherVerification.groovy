/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package ratpack.exec.stream.tck

import org.reactivestreams.Publisher
import org.reactivestreams.tck.PublisherVerification
import org.reactivestreams.tck.TestEnvironment
import ratpack.exec.stream.Streams

import java.time.Duration
import java.util.concurrent.Executors

class FanOutPublisherVerification extends PublisherVerification<String> {

  FanOutPublisherVerification() {
    super(new TestEnvironment(300L))
  }

  @Override
  Publisher<String> createPublisher(long elements) {
    def i = 0
    Streams.fanOut(Streams.periodically(Executors.newSingleThreadScheduledExecutor(), Duration.ofNanos(100)) {
      if (i >= elements) {
        null
      } else {
        def v = ["${i++}"]
        if (i++ < elements) {
          v << ["$i-a"]
        }
        v
      }
    })
  }

  @Override
  Publisher<String> createFailedPublisher() {
    null
  }
}
