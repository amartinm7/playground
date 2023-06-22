package play.observable

import io.reactivex.rxjava3.core.Observable

fun main() {

    Observable.create(listOf<String>("Hello", "world", "from", "here"))
}