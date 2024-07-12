package ar.edu.unsam.pds.security.repository

import ar.edu.unsam.pds.security.models.Principal

interface PrincipalRepositoryExtra {
    fun deleteWithDestructionOfAllSessions(principal: Principal)
}