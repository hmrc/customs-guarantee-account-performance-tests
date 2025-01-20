import sbt._

object Dependencies {
  val test = Seq(
    "com.typesafe"          % "config"                    % "1.4.2"        % Test,
    "uk.gov.hmrc"          %% "performance-test-runner"   % "6.1.0"        % Test
  )
}
