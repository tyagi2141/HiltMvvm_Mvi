package com.example.hiltmvvm_mvi.mappers

import javax.inject.Inject
import com.example.hiltmvvm_mvi.util.EntityMapper
import com.example.hiltmvvm_mvi.model.BlogNetworkEntity
import com.example.hiltmvvm_mvi.model.Blog
/**
 * Created by Rahul on 01/08/20.
 */
class NetwokMappers
    @Inject
    constructor() : EntityMapper<BlogNetworkEntity , Blog>{
    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            image = entity.image,
            category = entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            image = domainModel.image,
            category = domainModel.category
        )
    }

    fun mapFromEntityList(entitie:List<BlogNetworkEntity>):List<Blog>{
        return entitie.map { mapFromEntity(it) }
    }
}
