package bt.primes.parallel

import functions._

object Run extends App {

    val default: Int = 30000
    val prz: Int = try {
        if (args(0).toInt >= 3) args(0).toInt else default
    } catch {
        case _: java.lang.ArrayIndexOutOfBoundsException =>
            println(s"Using default search range $default")
            default
    }

    val searchRange = (3 to prz).toVector

    // Parallelizm: Implementacja na parallel collection (metoda .par)
    // Dodajemy 1 bo liczba 2 jest liczbą pierwszą asearchRange zaczyna się od 3
    val found = timeIt {searchRange.par.count(isPrime) + 1}

    println(s"W przedziale do $prz znaleziono $found liczb pierwszysch")
}
