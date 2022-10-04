/*
 * Copyright 2022 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.guaranteeaccount

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object Requests {

  def getPage(stepName: String, saveToken: Boolean, url: String): HttpRequestBuilder = {
    val httpRequestBuilder = http("Get " + stepName)
      .get(url)
      .check(status.is(200))
      .check(currentLocation.is(url))
    if (saveToken) {
      httpRequestBuilder.check(css("input[name='csrfToken']", "value").saveAs("csrfToken"))
    }
    else {
      httpRequestBuilder
    }
  }

  def getPage(stepName: String, url: String): HttpRequestBuilder = {
    getPage(stepName, saveToken = false, url)
  }

  def postPage(stepName: String, currentPage: String, nextPage: String, value: String): HttpRequestBuilder =
    postPage(stepName, postToken = true, currentPage, nextPage, value)

  def postPage(stepName: String, postToken: Boolean, currentPage: String, nextPage: String, value: String): HttpRequestBuilder =
    postPage(stepName, postToken, currentPage, nextPage, Map("value" -> value))

  def postPage(stepName: String, currentPage: String, nextPage: String, values: Map[String, String]): HttpRequestBuilder = {
    postPage(stepName, postToken = true, currentPage, nextPage, values)
  }

  def postPage(stepName: String, postToken: Boolean, currentPage: String, nextPage: String, values: Map[String, String]): HttpRequestBuilder = {
    http(_ => "Post " + stepName)
      .post(currentPage)
      .formParamMap(
        if (postToken) {
          values + ("csrfToken" -> f"$${csrfToken}")
        } else {
          values
        }
      )
      .check(status.is(303))
      .check(currentLocation.is(currentPage))
  }

}
