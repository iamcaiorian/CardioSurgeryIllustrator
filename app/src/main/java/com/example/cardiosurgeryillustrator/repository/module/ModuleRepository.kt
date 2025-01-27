package com.example.cardiosurgeryillustrator.repository.module

import com.example.cardiosurgeryillustrator.core.network.RetrofitInstance
import com.example.cardiosurgeryillustrator.models.student.module.Module
import com.example.cardiosurgeryillustrator.models.student.module.ModuleCreateRequest
import com.example.cardiosurgeryillustrator.models.student.module.ModuleResponse

class ModuleRepository() {

    suspend fun createModule(moduleCreateRequest: ModuleCreateRequest) {
        RetrofitInstance.moduleService.createModule(moduleCreateRequest)
    }

    suspend fun getAllModules(): List<ModuleResponse> {
        return RetrofitInstance.moduleService.getAllModules()
    }
}