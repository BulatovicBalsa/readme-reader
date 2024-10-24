package transformers

open class Chainable<T> {
    protected var next: T? = null

    fun chain(next: T) {
        this.next = next
    }
}