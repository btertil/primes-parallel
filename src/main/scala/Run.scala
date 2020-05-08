import scala.annotation.tailrec

object Run extends App {

    val default: Int = 30000
    val prz: Int = try {
        if (args(0).toLong >= 3) args(0).toInt else default
    } catch {
        case _: java.lang.ArrayIndexOutOfBoundsException =>
            println(s"Using default search range $default")
            default
    }

    val searchRange = (3 to prz).toVector

    def isPrime(n: Int): Boolean = {

        assert(n >= 3, "Nie można sprawdzać liczby mniejszej niż 3")
        val enough = math.sqrt(n).toLong + 1

        @tailrec
        def check(step: Int = 2): Boolean = {
            if (enough < 3 || step > enough) true; else
            if (n % step != 0) check(step+1) else false
        }

        check()

    }

    // Parallelizm: Implementacja na parallel collection (metoda .par)
    // Dodajemy 1 bo liczba 2 jest liczbą pierwszą asearchRange zaczyna się od 3
    val found = searchRange.par.count(isPrime) + 1

    println(s"W przedziale 0 - $prz znaleziono $found liczb pierwszysch")
}
