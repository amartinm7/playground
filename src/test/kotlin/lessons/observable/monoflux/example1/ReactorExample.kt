package lessons.observable.monoflux.example1

import io.reactivex.rxjava3.core.Observable
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux


class ReactorExample {

    fun reactor(): Unit {
//        Mono.just(Person.SimplePerson())
//            .subscribe { p -> log.info(" [Reactor] Persona: $p") }
        Flux.just(listOf(Person.NormalPerson(1), Person.SimplePerson(2), Person.NormalPerson(3))).subscribe { p -> log.info(" [Reactor] Persona: $p") }
    }

    fun rxjava3() {
        Observable.just(Person.SimplePerson(4))
            .subscribe { p -> log.info("[RJava3] Persona: $p") }
    }

    companion object {
        val log = LoggerFactory.getLogger(ReactorExample::class.java)
    }

    fun execute() {
        reactor()
        rxjava3()
    }
}

sealed class Person(open val id: Int, val name: String, val age: Int){
    data class NormalPerson(override val id: Int): Person(id = 1, name = "Normal", age = 25)
    data class SimplePerson(override val id: Int) : Person(id = 2, name = "Simple", age = 24)
}

fun main() {
    ReactorExample().execute()
}
