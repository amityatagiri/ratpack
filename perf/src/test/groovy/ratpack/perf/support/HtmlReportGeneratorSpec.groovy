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

package ratpack.perf.support

import ratpack.test.internal.BaseRatpackSpec

class HtmlReportGeneratorSpec extends BaseRatpackSpec {

  def "can generate report"() {
    given:
    def json = new ByteArrayInputStream("{}".bytes)
    def html = new ByteArrayOutputStream()

    when:
    HtmlReportGenerator.generate(json, html)

    then:
    html.toString().contains("foo")
  }

}
