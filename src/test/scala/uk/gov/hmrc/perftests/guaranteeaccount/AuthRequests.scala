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

import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.guaranteeaccount.Requests._

trait AuthRequests {
  self: PerformanceTestRunner with ServicesConfiguration =>

  private val authUrl: String = baseUrlFor("auth-login-stub") + "/auth-login-stub/gg-sign-in"
  private val baseUrl: String = baseUrlFor("customs-guarantee-account-frontend")

  val loginStubPayload = Map(
    "authorityId" -> "",
    "redirectionUrl" -> (baseUrl + "/customs/guarantee-account"),
    "credentialStrength" -> "weak",
    "confidenceLevel" -> "50",
    "affinityGroup" -> "Organisation",
    "enrolment[0].state" -> "Activated",
    "enrolment[0].name" -> "HMRC-CUS-ORG",
    "enrolment[0].taxIdentifier[0].name" -> "EORINumber",
    "enrolment[0].taxIdentifier[0].value" -> "${eoriNumber}"
  )

  setup("login", "Login") withRequests(
    getPage("Auth Page", authUrl),
    postPage("Post Auth Page", postToken = false, authUrl, s"$baseUrl/customs/guarantee-account", loginStubPayload)
  )


}
