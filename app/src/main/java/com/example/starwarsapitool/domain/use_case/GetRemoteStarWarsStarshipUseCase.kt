package com.example.starwarsapitool.domain.use_case

import android.util.Log
import com.example.starwarsapitool.data.dto.toCharacter
import com.example.starwarsapitool.data.dto.toStarship
import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.model.Starship
import com.example.starwarsapitool.domain.repository.RemoteStarWarsRepository
import com.example.starwarsapitool.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRemoteStarWarsStarshipUseCase @Inject constructor(
    private val remoteStarWarsRepository: RemoteStarWarsRepository
) {
    operator fun invoke(name:String): Flow<Resource<List<Starship>>> = flow{
        try{
            emit(Resource.Loading())
            val starships:List<Starship> = remoteStarWarsRepository.getStarshipByName(name)
            if(starships.isNotEmpty())
                emit(Resource.Success(starships))
            else
                emit(Resource.Error("Not Found"))
        }catch (e: HttpException){
            emit(Resource.Error<List<Starship>>(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e: IOException){
            emit(Resource.Error<List<Starship>>("Couldn't reach server. Check your internet connection."))
        }
    }
}