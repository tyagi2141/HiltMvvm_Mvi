package com.example.hiltmvvm_mvi.util

/**
 * Created by Rahul on 01/08/20.
 */


interface EntityMapper<Entity,DomainModdel> {

    fun mapFromEntity(entity: Entity):DomainModdel
    fun mapToEntity(domainModdel: DomainModdel):Entity
}