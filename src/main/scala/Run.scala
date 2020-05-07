import sun.reflect.generics.reflectiveObjects.NotImplementedException

import scala.annotation.tailrec

object Run extends App {

    val default: Int = 30000
    val prz: Int = try {
        if (args(0).toLong >= 2) args(0).toInt else default
    } catch {
        case _: java.lang.ArrayIndexOutOfBoundsException => println(s"Using default search range $default"); default
    }

    val searchRange = (2 to prz).toVector

    def isPrime(n: Int): Boolean = {

        assert(n >= 2, "Nie można sprawdzać liczby mniejszej niż 2")
        val enough = math.sqrt(n).toLong + 1

        @tailrec
        def check(step: Int = 2): Boolean = {
            if (enough < 2 || step > enough) true; else
            if (n % step != 0) check(step+1) else false
        }

        check()

    }

    // TODO: pomysł jest taki, aby spróbować sparalelizować przetwarzania. Możlie, że JVM rozrzuci je efektywniej
    //       po wątkach jeśłi będzie napisane w funkcyjnym stylu


    // Najpierw sprawdzamy (1 wywołanie) czy da się podzielić wektor / range na mniejsze zakres i je dzielimy jeśli tak
    // Potem, jeśli 1 elementowy to sprawdzamy czy to liczba 1, uwaga, akumulator musi mieć informację o już znalezionych liczbach


    @tailrec
    def countPrimes(v: Vector[Int], acc: Int = 1): Int = v match {

        case Vector() => acc
        case x +: xs => if (isPrime(x)) countPrimes(xs, acc+1) else countPrimes(xs, acc)

    }

    println(s"W zadanym przedziale $prz znaleziono ${countPrimes(searchRange)} liczb pierwszysch")
}
