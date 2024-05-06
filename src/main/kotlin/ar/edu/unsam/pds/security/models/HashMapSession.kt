package ar.edu.unsam.pds.security.models

class HashMapSession<K, V : Any> : HashMap<K, V>() {
    override fun get(key: K): V? {
        println("method get -> key -> " + key)
        return super.get(key)
    }

    override fun put(key: K, value: V): V? {
        println("method put -> key -> " + key)
        println("method put -> value -> " + value)
        return super.put(key, value)
    }

    override fun remove(key: K): V? {
        println("method remove -> key -> " + key)
        return super.remove(key)
    }
}