package bt.primes.parallel

import scala.annotation.tailrec

object functions {

    def isPrime(n: Int): Boolean = {

        assert(n >= 3, "Nie można sprawdzać liczby mniejszej niż 3")
        val enough = math.sqrt(n).toLong + 1

        @tailrec
        def check(step: Int = 2, incr: Int = 2): Boolean = {

            // W kolejnych iteracjach (poza 2) sprawdzamy tylko nieparzyste dzielniki!
            if (step > enough) true; else
            if (n % step != 0) check(step + incr) else false
        }

        // dla dzielnika 2 increment będzie wyjątkowo o 1, potem zawsze o 2 aby sprawdzać tylko nieparzyste dzielniki
        check(incr = 1)
    }


    def timeIt[R](block: => R): R = {
        val t0 = System.currentTimeMillis()
        val result = block  // call by name
        val t1 = System.currentTimeMillis()
        println("Elapsed time: " + (t1 - t0) / 1000.toDouble + " s")

        result
    }

}
