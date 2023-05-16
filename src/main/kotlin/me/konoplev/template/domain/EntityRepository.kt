package me.konoplev.template.domain


interface EntityRepository <T, ID> {
    fun findById(id: ID): T?
}
