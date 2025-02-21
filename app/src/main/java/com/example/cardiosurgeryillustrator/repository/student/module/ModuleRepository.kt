package com.example.cardiosurgeryillustrator.repository.student.module

import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.student.module.Module
import com.example.cardiosurgeryillustrator.models.student.module.ModuleCreateRequest
import com.example.cardiosurgeryillustrator.models.student.module.ModuleResponse

class ModuleRepository() {

    suspend fun createModule(moduleCreateRequest: ModuleCreateRequest) {
        RetrofitInstance.moduleService.createModule(moduleCreateRequest)
    }

    suspend fun getModuleById(moduleId: String): ModuleResponse {
        return RetrofitInstance.moduleService.getModuleById(moduleId)
    }

    suspend fun getAllModules(): List<ModuleResponse> {
        return RetrofitInstance.moduleService.getAllModules()
    }

    suspend fun getAllModulesBySubjectId(subjectId: String): List<ModuleResponse> {
        return RetrofitInstance.moduleService.getAllModulesBySubjectId(subjectId)
    }

    suspend fun toggleFavoriteModule(moduleId: String): ModuleResponse {
        return RetrofitInstance.moduleService.toggleFavorite(moduleId)
    }
}