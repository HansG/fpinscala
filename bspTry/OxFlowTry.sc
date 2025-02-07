//> using dep "com.softwaremill.ox::core:0.5.0"

import ox.flow.Flow
import java.net.URI


//https://softwaremill.com/direct-style-concurrent-streaming/
@main def capitals(): Unit =
  Flow
    .fromValues("Poland", "Argentina", "Italy", "Germany",
      "Republic of India",  "France", "Japan", "United Kingdom",
      "Australia", "USA")
    .map(_.toLowerCase().replace(" ", "%20"))
    .mapPar(3)(country =>
      val getCapital = new URI(
        s"https://restcountries.com/v3.1/name/$country?fields=capital")
      Flow
        .fromInputStream(getCapital.toURL().openStream())
        .linesUtf8
        .take(1)
        .runToList()
    )
    .runForeach(println)